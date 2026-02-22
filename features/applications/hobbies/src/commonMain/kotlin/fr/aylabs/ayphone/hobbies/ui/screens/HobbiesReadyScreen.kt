package fr.aylabs.ayphone.hobbies.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.font.FontWeight
import com.woowla.compose.icon.collections.remix.Remix
import com.woowla.compose.icon.collections.remix.remix.Arrows
import com.woowla.compose.icon.collections.remix.remix.System
import com.woowla.compose.icon.collections.remix.remix.arrows.ArrowDownSLine
import com.woowla.compose.icon.collections.remix.remix.arrows.ArrowRightLine
import com.woowla.compose.icon.collections.remix.remix.arrows.ArrowUpSLine
import com.woowla.compose.icon.collections.remix.remix.system.ExternalLinkLine
import fr.aylabs.ayphone.hobbies.domain.models.HobbiesAction
import fr.aylabs.ayphone.hobbies.domain.models.HobbiesLink
import fr.aylabs.ayphone.hobbies.domain.models.HobbiesSection
import fr.aylabs.ayphone.hobbies.domain.models.HomelabTool
import fr.aylabs.design_system.AyCorners
import fr.aylabs.design_system.AySizes
import fr.aylabs.design_system.AySpacings

@Composable
fun HobbiesReadyScreen(
    sections: List<HobbiesSection>,
    onAction: (HobbiesAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(AySpacings.l),
        verticalArrangement = Arrangement.spacedBy(AySpacings.l),
    ) {
        sections.forEach { section ->
            SectionCard(section = section, onAction = onAction)
        }
        Spacer(Modifier.height(AySpacings.l))
    }
}

@Composable
private fun SectionCard(
    section: HobbiesSection,
    onAction: (HobbiesAction) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(AyCorners.m))
            .background(MaterialTheme.colorScheme.surfaceVariant),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded }
                .padding(AySpacings.l),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(AySpacings.s),
            ) {
                Icon(
                    imageVector = section.icon,
                    contentDescription = null,
                    modifier = Modifier.size(AySizes.iconL),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                Text(
                    text = section.title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                )
            }
            Icon(
                imageVector = if (expanded) Remix.Arrows.ArrowUpSLine else Remix.Arrows.ArrowDownSLine,
                contentDescription = null,
                modifier = Modifier.size(AySizes.iconM),
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }

        AnimatedVisibility(visible = expanded) {
            Column(
                modifier = Modifier.padding(
                    start = AySpacings.l,
                    end = AySpacings.l,
                    bottom = AySpacings.l,
                ),
                verticalArrangement = Arrangement.spacedBy(AySpacings.m),
            ) {
                Text(
                    text = section.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )

                if (section.tools.isNotEmpty()) {
                    Column(verticalArrangement = Arrangement.spacedBy(AySpacings.s)) {
                        section.tools.forEach { tool ->
                            ToolCard(tool = tool, onAction = onAction)
                        }
                    }
                }

                if (section.links.isNotEmpty()) {
                    Column(verticalArrangement = Arrangement.spacedBy(AySpacings.xs)) {
                        section.links.forEach { link ->
                            LinkRow(link = link, onAction = onAction)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ToolCard(
    tool: HomelabTool,
    onAction: (HobbiesAction) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(AyCorners.s))
            .background(MaterialTheme.colorScheme.surface)
            .clickable { onAction(tool.action) }
            .padding(AySpacings.m),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(AySpacings.m),
    ) {
        Icon(
            imageVector = tool.logo,
            contentDescription = tool.name,
            modifier = Modifier.size(AySizes.iconXxxl),
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(AySpacings.xxs),
        ) {
            Text(
                text = tool.name,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = tool.description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
        Icon(
            imageVector = Remix.System.ExternalLinkLine,
            contentDescription = null,
            modifier = Modifier.size(AySizes.iconM),
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@Composable
private fun LinkRow(
    link: HobbiesLink,
    onAction: (HobbiesAction) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(AyCorners.s))
            .background(MaterialTheme.colorScheme.surface)
            .clickable { onAction(link.action) }
            .padding(horizontal = AySpacings.l, vertical = AySpacings.m),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(AySpacings.s),
        ) {
            Icon(
                imageVector = link.icon,
                contentDescription = null,
                modifier = Modifier.size(AySizes.iconM),
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Text(
                text = link.label,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
        Icon(
            imageVector = Remix.Arrows.ArrowRightLine,
            contentDescription = null,
            modifier = Modifier.size(AySizes.iconM),
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}
