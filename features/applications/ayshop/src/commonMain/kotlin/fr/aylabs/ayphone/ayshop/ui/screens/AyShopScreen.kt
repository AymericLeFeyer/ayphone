package fr.aylabs.ayphone.ayshop.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.woowla.compose.icon.collections.remix.Remix
import com.woowla.compose.icon.collections.remix.remix.Arrows
import com.woowla.compose.icon.collections.remix.remix.System
import com.woowla.compose.icon.collections.remix.remix.arrows.ArrowLeftSLine
import com.woowla.compose.icon.collections.remix.remix.system.SearchLine
import fr.aylabs.ayphone.application.data.AyApp
import fr.aylabs.ayphone.ayshop.ui.components.AppIcon
import fr.aylabs.ayphone.ayshop.ui.components.InstallButtonOnCard
import fr.aylabs.ayphone.ayshop.ui.components.RatingRow
import fr.aylabs.ayphone.ayshop.ui.states.AyShopState
import fr.aylabs.ayphone.ayshop.ui.viewmodels.AyShopViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AyShopScreen(
    onBackClick: () -> Unit,
    onAppDetail: (appId: String) -> Unit,
    onOpenApp: (appId: String) -> Unit,
    vm: AyShopViewModel,
) {
    val state by vm.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            Surface {
                TopAppBar(
                    title = {
                        Text(
                            text = "AyShop",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = onBackClick) {
                            Icon(
                                imageVector = Remix.Arrows.ArrowLeftSLine,
                                contentDescription = "Retour",
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = {}) {
                            Icon(
                                imageVector = Remix.System.SearchLine,
                                contentDescription = "Rechercher",
                            )
                        }
                    },
                )
            }
        },
    ) { padding ->
        when (val s = state) {
            is AyShopState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize().padding(padding),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator()
                }
            }

            is AyShopState.Success -> {
                LazyColumn(
                    contentPadding = padding,
                    modifier = Modifier.fillMaxSize(),
                ) {
                    item {
                        Text(
                            text = "En vedette",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                        )
                    }

                    s.apps.firstOrNull()?.let { featured ->
                        val appId = featured.id ?: return@let
                        item {
                            FeaturedAppCard(
                                app = featured,
                                isInstalled = s.isInstalled(appId),
                                isInstalling = s.isInstalling(appId),
                                onInstall = { vm.install(appId) },
                                onOpen = { onOpenApp(appId) },
                                onClick = { onAppDetail(appId) },
                                modifier = Modifier.padding(horizontal = 16.dp),
                            )
                        }
                    }

                    item {
                        Spacer(Modifier.height(24.dp))
                        Text(
                            text = "Applications pour vous",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                        )
                    }

                    items(s.apps) { app ->
                        val appId = app.id ?: return@items
                        AppListItem(
                            app = app,
                            isInstalled = s.isInstalled(appId),
                            isInstalling = s.isInstalling(appId),
                            onInstall = { vm.install(appId) },
                            onOpen = { onOpenApp(appId) },
                            onClick = { onAppDetail(appId) },
                        )
                        HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
                    }

                    item { Spacer(Modifier.height(16.dp)) }
                }
            }
        }
    }
}

@Composable
private fun FeaturedAppCard(
    app: AyApp,
    isInstalled: Boolean,
    isInstalling: Boolean,
    onInstall: () -> Unit,
    onOpen: () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val gradient = Brush.linearGradient(
        colors = listOf(Color(0xFF7C3AED), Color(0xFF2563EB)),
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(210.dp)
                .background(gradient)
                .padding(20.dp),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    AppIcon(emoji = app.iconEmoji ?: "📦", color = 0xFFFFFFFF, size = 64.dp)
                    Spacer(Modifier.width(16.dp))
                    Column {
                        Text(
                            text = app.title,
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = app.developer.orEmpty(),
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White.copy(alpha = 0.75f),
                        )
                        Text(
                            text = app.category.orEmpty(),
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.White.copy(alpha = 0.6f),
                        )
                    }
                }

                Text(
                    text = app.shortDescription.orEmpty(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.9f),
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "★ ${app.rating ?: "—"}  •  ${app.downloads.orEmpty()}",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White.copy(alpha = 0.8f),
                    )
                    InstallButtonOnCard(
                        isInstalled = isInstalled,
                        isInstalling = isInstalling,
                        onInstall = onInstall,
                        onOpen = onOpen,
                    )
                }
            }
        }
    }
}

@Composable
private fun AppListItem(
    app: AyApp,
    isInstalled: Boolean,
    isInstalling: Boolean,
    onInstall: () -> Unit,
    onOpen: () -> Unit,
    onClick: () -> Unit,
) {
    ListItem(
        headlineContent = {
            Text(
                text = app.title,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
            )
        },
        supportingContent = {
            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Text(
                    text = app.developer.orEmpty(),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                RatingRow(
                    rating = app.rating ?: 0f,
                    reviewCount = app.reviewCount.orEmpty(),
                    downloads = app.downloads.orEmpty(),
                )
            }
        },
        leadingContent = {
            AppIcon(
                emoji = app.iconEmoji ?: "📦",
                color = app.color.value.toLong(),
                size = 56.dp,
            )
        },
        trailingContent = {
            if (isInstalling) {
                CircularProgressIndicator(modifier = Modifier.size(24.dp), strokeWidth = 2.dp)
            } else if (isInstalled) {
                OutlinedButton(
                    onClick = onOpen,
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                ) {
                    Text("Ouvrir")
                }
            } else {
                OutlinedButton(
                    onClick = onInstall,
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                ) {
                    Text("Installer")
                }
            }
        },
        modifier = Modifier.clickable(onClick = onClick),
    )
}
