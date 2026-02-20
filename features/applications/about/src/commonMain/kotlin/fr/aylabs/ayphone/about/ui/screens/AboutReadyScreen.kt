package fr.aylabs.ayphone.about.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import ayphone.features.applications.resume.generated.resources.Res
import coil3.compose.AsyncImage
import com.woowla.compose.icon.collections.remix.Remix
import com.woowla.compose.icon.collections.remix.remix.Business
import com.woowla.compose.icon.collections.remix.remix.Device
import com.woowla.compose.icon.collections.remix.remix.Logos
import com.woowla.compose.icon.collections.remix.remix.business.MailLine
import com.woowla.compose.icon.collections.remix.remix.device.PhoneLine
import com.woowla.compose.icon.collections.remix.remix.logos.GithubLine
import com.woowla.compose.icon.collections.remix.remix.logos.LinkedinLine
import fr.aylabs.ayphone.resume.domain.models.Company
import fr.aylabs.ayphone.resume.domain.models.Resume
import fr.aylabs.dates.formatYearMonth
import fr.aylabs.dates.monthsBetween
import fr.aylabs.design_system.AyCorners
import fr.aylabs.design_system.AySizes
import fr.aylabs.design_system.AySpacings

@Composable
fun AboutReadyScreen(
    resume: Resume,
) {
    val uriHandler = LocalUriHandler.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(AySpacings.l),
        verticalArrangement = Arrangement.spacedBy(AySpacings.l),
    ) {
        // Header: name + role
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    MaterialTheme.colorScheme.surfaceVariant,
                    RoundedCornerShape(AyCorners.m)
                )
                .padding(AySpacings.l),
        ) {
            Text(
                text = resume.name,
                style = MaterialTheme.typography.headlineSmall,
            )
            Text(
                text = resume.role,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(top = AySpacings.s),
            ) {
                IconButton(onClick = { uriHandler.openUri("mailto:${resume.contacts.email}") }) {
                    Icon(Remix.Business.MailLine, contentDescription = "Email")
                }
                IconButton(onClick = { uriHandler.openUri("tel:${resume.contacts.phone}") }) {
                    Icon(Remix.Device.PhoneLine, contentDescription = "Phone")
                }
                IconButton(onClick = { uriHandler.openUri(resume.contacts.linkedin) }) {
                    Icon(Remix.Logos.LinkedinLine, contentDescription = "LinkedIn")
                }
                IconButton(onClick = { uriHandler.openUri(resume.contacts.github) }) {
                    Icon(Remix.Logos.GithubLine, contentDescription = "GitHub")
                }
            }
        }

        // Education section
        if (resume.education.isNotEmpty()) {
            val sortedEducation = resume.education.sortedByDescending { it.startDate }
            val majorEducation =
                sortedEducation.filter { monthsBetween(it.startDate, it.endDate) > 6 }
            val minorEducation =
                sortedEducation.filter { monthsBetween(it.startDate, it.endDate) <= 6 }

            Text(
                text = "Formation",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
            )
            majorEducation.forEach { edu ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            MaterialTheme.colorScheme.surfaceVariant,
                            RoundedCornerShape(AyCorners.s)
                        )
                        .padding(AySpacings.m),
                ) {
                    Text(
                        text = edu.institution,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = edu.degree,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    Text(
                        text = "${formatYearMonth(edu.startDate)} - ${
                            edu.endDate?.let {
                                formatYearMonth(
                                    it
                                )
                            } ?: "Présent"
                        }",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
            if (minorEducation.isNotEmpty()) {
                Text(
                    text = "Certifications & formations courtes",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = AySpacings.xs),
                )
                minorEducation.forEach { edu ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = AySpacings.xs, horizontal = AySpacings.xs),
                    ) {
                        Text(
                            text = "\u2022",
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(end = AySpacings.s),
                        )
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = edu.degree,
                                style = MaterialTheme.typography.bodySmall,
                            )
                            Text(
                                text = "${edu.institution} \u2022 ${formatYearMonth(edu.startDate)}",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                        }
                    }
                }
            }
        }

        // Companies section
        if (resume.companies.isNotEmpty()) {
            Text(
                text = "Entreprises",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
            )
            resume.companies.sortedByDescending { it.startDate }.forEach { company ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            MaterialTheme.colorScheme.surfaceVariant,
                            RoundedCornerShape(AyCorners.s)
                        )
                        .padding(AySpacings.m),
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Company.fromLabel(company.name)?.let { c ->
                            AsyncImage(
                                model = Res.getUri(c.iconPath),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(AySizes.iconM)
                                    .clip(RoundedCornerShape(AyCorners.xs)),
                            )
                            Spacer(Modifier.width(AySpacings.s))
                        }
                        Text(
                            text = company.name,
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                    Text(
                        text = company.position,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    Text(
                        text = "${formatYearMonth(company.startDate)} - ${
                            company.endDate?.let {
                                formatYearMonth(
                                    it
                                )
                            } ?: "Présent"
                        }",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                    if (company.responsibilities.isNotEmpty()) {
                        Spacer(Modifier.height(AySpacings.xs))
                        company.responsibilities.forEach { resp ->
                            Text(
                                text = "\u2022 $resp",
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.padding(
                                    start = AySpacings.xs,
                                    top = AySpacings.xxs
                                ),
                            )
                        }
                    }
                }
            }
        }
    }
}
