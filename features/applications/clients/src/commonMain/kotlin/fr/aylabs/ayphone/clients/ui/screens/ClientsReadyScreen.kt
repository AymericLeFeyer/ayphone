package fr.aylabs.ayphone.clients.ui.screens

import AyColors
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import fr.aylabs.ayphone.clients.ui.components.SafeImage
import fr.aylabs.ayphone.resume.domain.models.Company
import fr.aylabs.ayphone.resume.domain.models.Resume
import fr.aylabs.ayphone.resume.domain.models.ResumeMission
import fr.aylabs.dates.formatYearMonth
import fr.aylabs.dates.monthsBetween
import kotlinx.datetime.YearMonth

private data class ClientSummary(
    val name: String,
    val position: String?,
    val startDate: YearMonth,
    val endDate: YearMonth?,
    val totalMonths: Int,
)

private enum class SortMode { RECENT, DURATION }

@Composable
fun ClientsReadyScreen(
    resume: Resume,
    onClientClick: (String) -> Unit,
) {
    var sortMode by remember { mutableStateOf(SortMode.RECENT) }

    val clients = remember(resume) {
        resume.missions
            .groupBy { it.company }
            .map { (companyName, missions) ->
                val company = resume.companies.find { it.name == companyName }
                val startDate = missions.minOf { it.startDate }
                val endDate = if (missions.any { it.endDate == null }) null
                    else missions.maxOf { it.endDate!! }
                val totalMonths = missions.sumOf { monthsBetween(it.startDate, it.endDate) }
                ClientSummary(
                    name = companyName,
                    position = company?.position,
                    startDate = startDate,
                    endDate = endDate,
                    totalMonths = totalMonths,
                )
            }
    }

    val sortedClients = remember(clients, sortMode) {
        when (sortMode) {
            SortMode.RECENT -> clients.sortedByDescending { it.startDate }
            SortMode.DURATION -> clients.sortedByDescending { it.totalMonths }
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        item {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(top = 16.dp),
            ) {
                FilterChip(
                    selected = sortMode == SortMode.RECENT,
                    onClick = { sortMode = SortMode.RECENT },
                    label = { Text("Plus récent") },
                )
                FilterChip(
                    selected = sortMode == SortMode.DURATION,
                    onClick = { sortMode = SortMode.DURATION },
                    label = { Text("Plus long") },
                )
            }
        }

        items(sortedClients, key = { it.name }) { client ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(AyColors.ContainerQuiet)
                    .clickable { onClientClick(client.name) }
                    .padding(12.dp),
            ) {
                Company.fromLabel(client.name)?.let { c ->
                    SafeImage(
                        resourcePath = c.iconPath,
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(RoundedCornerShape(8.dp)),
                    )
                    Spacer(Modifier.width(12.dp))
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = client.name,
                        style = MaterialTheme.typography.titleSmall,
                    )
                    client.position?.let { position ->
                        Text(
                            text = position,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                    Text(
                        text = "${formatYearMonth(client.startDate)} - ${client.endDate?.let { formatYearMonth(it) } ?: "Présent"}",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
                Text(
                    text = "${client.totalMonths} mois",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}
