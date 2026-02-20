package fr.aylabs.ayphone.settings.ui.screens

import fr.aylabs.design_system.AyAppScaffold
import AySpacings
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import fr.aylabs.ayphone.application.data.AyApp
import fr.aylabs.ayphone.settings.AppTheme
import fr.aylabs.ayphone.settings.ui.viewmodels.DevModeEvent
import fr.aylabs.ayphone.settings.ui.viewmodels.SettingsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onBackClick: () -> Unit,
    vm: SettingsViewModel,
) {
    val theme by vm.theme.collectAsStateWithLifecycle()
    val showAppTitles by vm.showAppTitles.collectAsStateWithLifecycle()
    val developerMode by vm.developerMode.collectAsStateWithLifecycle()
    val installedApps by vm.installedApps.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        vm.devModeEvents.collect { event ->
            snackbarHostState.currentSnackbarData?.dismiss()
            when (event) {
                is DevModeEvent.Countdown -> snackbarHostState.showSnackbar(
                    message = "Plus que ${event.remaining} appui${if (event.remaining > 1) "s" else ""} pour le mode développeur",
                    duration = SnackbarDuration.Short,
                )

                DevModeEvent.Activated -> snackbarHostState.showSnackbar(
                    message = "Mode développeur activé 🛠️",
                    duration = SnackbarDuration.Short,
                )

                DevModeEvent.AlreadyActive -> snackbarHostState.showSnackbar(
                    message = "Tu es déjà développeur 😎",
                    duration = SnackbarDuration.Short,
                )
            }
        }
    }

    AyAppScaffold(
        title = "Réglages",
        containerColor = AyApp.SETTINGS.color,
        onBackClick = onBackClick,
        snackbarHostState = snackbarHostState,
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {

            // ─── Apparence ───────────────────────────────────────────────
            item { SettingsSectionHeader("Apparence") }
            item {
                ListItem(
                    headlineContent = { Text("Thème") },
                    supportingContent = {
                        SingleChoiceSegmentedButtonRow(modifier = Modifier.fillMaxWidth()) {
                            SegmentedButton(
                                shape = SegmentedButtonDefaults.itemShape(index = 0, count = 3),
                                selected = theme == AppTheme.SYSTEM,
                                onClick = { vm.setTheme(AppTheme.SYSTEM) },
                            ) { Text("Auto") }
                            SegmentedButton(
                                shape = SegmentedButtonDefaults.itemShape(index = 1, count = 3),
                                selected = theme == AppTheme.LIGHT,
                                onClick = { vm.setTheme(AppTheme.LIGHT) },
                            ) { Text("Clair") }
                            SegmentedButton(
                                shape = SegmentedButtonDefaults.itemShape(index = 2, count = 3),
                                selected = theme == AppTheme.DARK,
                                onClick = { vm.setTheme(AppTheme.DARK) },
                            ) { Text("Sombre") }
                        }
                    },
                )
            }

            // ─── Personnalisation ─────────────────────────────────────────
            item { HorizontalDivider() }
            item { SettingsSectionHeader("Personnalisation") }
            item {
                ListItem(
                    headlineContent = { Text("Titres des applications") },
                    supportingContent = { Text("Affiche le nom sous chaque icône") },
                    trailingContent = {
                        Switch(
                            checked = showAppTitles,
                            onCheckedChange = vm::setShowAppTitles,
                        )
                    },
                    modifier = Modifier.clickable { vm.setShowAppTitles(!showAppTitles) },
                )
            }
            item {
                ListItem(
                    headlineContent = { Text("Colonnes de la grille") },
                    supportingContent = { Text("Bientôt disponible") },
                    trailingContent = {
                        Switch(checked = false, onCheckedChange = {}, enabled = false)
                    },
                )
            }
            item {
                ListItem(
                    headlineContent = { Text("Style des icônes") },
                    supportingContent = { Text("Bientôt disponible") },
                    trailingContent = {
                        Switch(checked = false, onCheckedChange = {}, enabled = false)
                    },
                )
            }

            // ─── Applications ─────────────────────────────────────────────
            if (installedApps.isNotEmpty()) {
                item { HorizontalDivider() }
                item { SettingsSectionHeader("Applications") }
                item {
                    ListItem(
                        headlineContent = {
                            Text(
                                text = "Désinstaller toutes les applications",
                                color = MaterialTheme.colorScheme.error,
                            )
                        },
                        supportingContent = { Text("${installedApps.size} application${if (installedApps.size > 1) "s" else ""} installée${if (installedApps.size > 1) "s" else ""}") },
                        modifier = Modifier.clickable { vm.uninstallAllApps() },
                    )
                }
            }

            // ─── Accessibilité ────────────────────────────────────────────
            item { HorizontalDivider() }
            item { SettingsSectionHeader("Accessibilité") }
            item {
                ListItem(
                    headlineContent = { Text("Réduire les animations") },
                    supportingContent = { Text("Bientôt disponible") },
                    trailingContent = {
                        Switch(checked = false, onCheckedChange = {}, enabled = false)
                    },
                )
            }
            item {
                ListItem(
                    headlineContent = { Text("Agrandir les icônes") },
                    supportingContent = { Text("Bientôt disponible") },
                    trailingContent = {
                        Switch(checked = false, onCheckedChange = {}, enabled = false)
                    },
                )
            }

            // ─── Développeur ──────────────────────────────────────────────
            if (developerMode) {
                item { HorizontalDivider() }
                item { SettingsSectionHeader("Développeur") }
                item {
                    ListItem(
                        headlineContent = { Text("Mode développeur") },
                        supportingContent = { Text("Tu vois des trucs que les autres ne voient pas 🛠️") },
                    )
                }
                item {
                    ListItem(
                        headlineContent = { Text("Journal des requêtes réseau") },
                        supportingContent = { Text("Bientôt disponible") },
                    )
                }
                item {
                    ListItem(
                        headlineContent = { Text("Afficher les routes de navigation") },
                        supportingContent = { Text("Bientôt disponible") },
                    )
                }
                item {
                    ListItem(
                        headlineContent = {
                            Text(
                                text = "Désactiver le mode développeur",
                                color = MaterialTheme.colorScheme.error,
                            )
                        },
                        modifier = Modifier.clickable { vm.disableDeveloperMode() },
                    )
                }
            }

            // ─── À propos ─────────────────────────────────────────────────
            item { HorizontalDivider() }
            item { SettingsSectionHeader("À propos") }
            item {
                ListItem(
                    headlineContent = { Text("AyPhone") },
                    supportingContent = { Text("Version 1.0.0") },
                )
            }
            item {
                ListItem(
                    headlineContent = { Text("Build") },
                    supportingContent = {
                        Text(if (developerMode) "1 — mode développeur activé 🛠️" else "1")
                    },
                    modifier = Modifier.clickable { vm.onBuildTap() },
                )
            }
        }
    }
}

@Composable
private fun SettingsSectionHeader(title: String) {
    Text(
        text = title.uppercase(),
        style = MaterialTheme.typography.labelSmall,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(
            start = AySpacings.l,
            top = AySpacings.l,
            bottom = AySpacings.xs,
        ),
    )
}
