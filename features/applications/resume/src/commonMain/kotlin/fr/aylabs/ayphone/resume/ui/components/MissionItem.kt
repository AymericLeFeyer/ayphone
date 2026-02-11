package fr.aylabs.ayphone.resume.ui.components

import AyColors
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import fr.aylabs.ayphone.resume.domain.models.ResumeMission
import fr.aylabs.dates.monthsBetween

@Composable
fun MissionItem(mission: ResumeMission) {
    Row(
        modifier = Modifier
            .padding(horizontal = 4.dp)
            .background(AyColors.ContainerQuiet, RoundedCornerShape(8.dp))
            .padding(horizontal = 4.dp)
    ) {
        ListItem(
            headlineContent = { Text(mission.title) },
            supportingContent = { Text(mission.company) },
            trailingContent = { Text("${monthsBetween(mission.startDate, mission.endDate)} mois") },
            colors = ListItemDefaults.colors().copy(containerColor = Color.Transparent)
        )
    }
}
