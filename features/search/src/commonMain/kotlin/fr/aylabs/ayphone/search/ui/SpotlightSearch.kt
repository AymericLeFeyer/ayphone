package fr.aylabs.ayphone.search.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.woowla.compose.icon.collections.remix.Remix
import com.woowla.compose.icon.collections.remix.remix.Arrows
import com.woowla.compose.icon.collections.remix.remix.System
import com.woowla.compose.icon.collections.remix.remix.arrows.ArrowRightLine
import com.woowla.compose.icon.collections.remix.remix.system.CloseLine
import com.woowla.compose.icon.collections.remix.remix.system.SearchLine
import fr.aylabs.ayphone.application.data.AyApp
import fr.aylabs.ayphone.search.domain.models.SearchResult
import fr.aylabs.design_system.AyCorners
import fr.aylabs.design_system.AySizes
import fr.aylabs.design_system.AySpacings
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SpotlightSearch(
    onOpenApp: (AyApp) -> Unit,
    onOpenMission: (Int) -> Unit,
    onOpenSkill: (String) -> Unit,
    onOpenClient: (String) -> Unit,
    onOpenSideProject: (Int) -> Unit,
    modifier: Modifier = Modifier,
    vm: SearchViewModel = koinViewModel(),
) {
    val query by vm.query.collectAsStateWithLifecycle()
    val results by vm.results.collectAsStateWithLifecycle()
    val isSearching = query.isNotBlank()

    fun handleResult(result: SearchResult) {
        when (result) {
            is SearchResult.App -> {
                onOpenApp(result.app); vm.clear()
            }

            is SearchResult.Mission -> {
                onOpenMission(result.index); vm.clear()
            }

            is SearchResult.TechSkill -> {
                onOpenSkill(result.skill.label); vm.clear()
            }

            is SearchResult.Client -> {
                onOpenClient(result.name); vm.clear()
            }

            is SearchResult.SideProject -> {
                onOpenSideProject(result.index); vm.clear()
            }
        }
    }

    Box(modifier = modifier) {
        // Backdrop — dims the home screen when searching
        AnimatedVisibility(
            visible = isSearching,
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.6f))
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                    ) { vm.clear() },
            )
        }

        // Search UI anchored to the bottom, results grow upward
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(AySpacings.s),
            verticalArrangement = Arrangement.spacedBy(AySpacings.s),
        ) {
            // Results list (sits above the search bar)
            AnimatedVisibility(visible = results.isNotEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(AyCorners.m))
                        .background(MaterialTheme.colorScheme.surface),
                ) {
                    results.forEachIndexed { index, result ->
                        SearchResultRow(
                            result = result,
                            onClick = { handleResult(result) },
                        )
                        if (index < results.lastIndex) {
                            HorizontalDivider(
                                modifier = Modifier.padding(horizontal = AySpacings.l),
                                color = MaterialTheme.colorScheme.outlineVariant,
                            )
                        }
                    }
                }
            }

            // Search bar pill
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(percent = 50))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .padding(horizontal = AySpacings.m, vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Remix.System.SearchLine,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(18.dp),
                )
                Spacer(Modifier.width(AySpacings.s))
                BasicTextField(
                    value = query,
                    onValueChange = vm::onQueryChange,
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onSurface,
                    ),
                    cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                    decorationBox = { innerTextField ->
                        Box {
                            if (query.isEmpty()) {
                                Text(
                                    text = "Rechercher...",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                )
                            }
                            innerTextField()
                        }
                    },
                )
                AnimatedVisibility(visible = isSearching) {
                    Icon(
                        imageVector = Remix.System.CloseLine,
                        contentDescription = "Effacer",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier
                            .padding(start = AySpacings.s)
                            .size(18.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                            ) { vm.clear() },
                    )
                }
            }
        }
    }
}

@Composable
private fun SearchResultRow(
    result: SearchResult,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = AySpacings.m, vertical = AySpacings.s),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(AyCorners.s))
                .background(result.backgroundColor),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = result.emoji,
                style = MaterialTheme.typography.bodyLarge,
            )
        }
        Spacer(Modifier.width(AySpacings.m))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = result.displayTitle,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = result.displaySubtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
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
