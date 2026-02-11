package fr.aylabs.ayphone.resume.ui.components

import AyColors
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.woowla.compose.icon.collections.remix.Remix
import com.woowla.compose.icon.collections.remix.remix.System
import com.woowla.compose.icon.collections.remix.remix.system.FilterLine
import com.woowla.compose.icon.collections.remix.remix.system.SearchLine

@Composable
fun MissionSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    hasActiveFilters: Boolean,
    onFilterClick: () -> Unit,
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        placeholder = { Text("Rechercher une mission…") },
        leadingIcon = {
            Icon(
                imageVector = Remix.System.SearchLine,
                contentDescription = "Rechercher",
            )
        },
        trailingIcon = {
            IconButton(onClick = onFilterClick) {
                Icon(
                    imageVector = Remix.System.FilterLine,
                    contentDescription = "Filtres",
                    tint = if (hasActiveFilters) AyColors.Primary else MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
    )
}
