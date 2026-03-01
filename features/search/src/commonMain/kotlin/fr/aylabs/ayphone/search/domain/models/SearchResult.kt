package fr.aylabs.ayphone.search.domain.models

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.woowla.compose.icon.collections.remix.Remix
import com.woowla.compose.icon.collections.remix.remix.Development
import com.woowla.compose.icon.collections.remix.remix.HealthAndMedical
import com.woowla.compose.icon.collections.remix.remix.Map
import com.woowla.compose.icon.collections.remix.remix.UserAndFaces
import com.woowla.compose.icon.collections.remix.remix.development.CodeSSlashLine
import com.woowla.compose.icon.collections.remix.remix.healthandmedical.FlaskLine
import com.woowla.compose.icon.collections.remix.remix.map.RocketLine
import com.woowla.compose.icon.collections.remix.remix.userandfaces.TeamLine
import fr.aylabs.ayphone.application.data.AyApp
import fr.aylabs.ayphone.resume.domain.models.Skill

sealed interface SearchResult {
    val icon: ImageVector
    val displayTitle: String
    val displaySubtitle: String
    val backgroundColor: Color

    data class App(val app: AyApp) : SearchResult {
        override val icon: ImageVector = app.logo
        override val displayTitle: String = app.title
        override val displaySubtitle: String = "Application"
        override val backgroundColor: Color = app.color
    }

    data class Mission(val index: Int, val title: String, val company: String) : SearchResult {
        override val icon: ImageVector = Remix.Map.RocketLine
        override val displayTitle: String = title
        override val displaySubtitle: String = "Mission · $company"
        override val backgroundColor: Color = AyApp.MISSIONS.color
    }

    data class TechSkill(val skill: Skill) : SearchResult {
        override val icon: ImageVector = Remix.Development.CodeSSlashLine
        override val displayTitle: String = skill.label
        override val displaySubtitle: String = "Compétence · ${skill.category}"
        override val backgroundColor: Color = AyApp.STACK.color
    }

    data class Client(val name: String) : SearchResult {
        override val icon: ImageVector = Remix.UserAndFaces.TeamLine
        override val displayTitle: String = name
        override val displaySubtitle: String = "Client"
        override val backgroundColor: Color = AyApp.CLIENTS.color
    }

    data class SideProject(val index: Int, val title: String) : SearchResult {
        override val icon: ImageVector = Remix.HealthAndMedical.FlaskLine
        override val displayTitle: String = title
        override val displaySubtitle: String = "Side Project"
        override val backgroundColor: Color = AyApp.SIDE_PROJECTS.color
    }
}
