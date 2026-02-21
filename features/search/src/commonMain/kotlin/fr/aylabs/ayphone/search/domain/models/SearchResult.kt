package fr.aylabs.ayphone.search.domain.models

import androidx.compose.ui.graphics.Color
import fr.aylabs.ayphone.application.data.AyApp
import fr.aylabs.ayphone.resume.domain.models.Skill

sealed interface SearchResult {
    val emoji: String
    val displayTitle: String
    val displaySubtitle: String
    val backgroundColor: Color

    data class App(val app: AyApp) : SearchResult {
        override val emoji: String = app.iconEmoji ?: "📱"
        override val displayTitle: String = app.title
        override val displaySubtitle: String = "Application"
        override val backgroundColor: Color = app.color
    }

    data class Mission(val index: Int, val title: String, val company: String) : SearchResult {
        override val emoji: String = "💼"
        override val displayTitle: String = title
        override val displaySubtitle: String = "Mission · $company"
        override val backgroundColor: Color = AyApp.MISSIONS.color
    }

    data class TechSkill(val skill: Skill) : SearchResult {
        override val emoji: String = "⚙️"
        override val displayTitle: String = skill.label
        override val displaySubtitle: String = "Compétence · ${skill.category.label}"
        override val backgroundColor: Color = AyApp.STACK.color
    }

    data class Client(val name: String) : SearchResult {
        override val emoji: String = "🤝"
        override val displayTitle: String = name
        override val displaySubtitle: String = "Client"
        override val backgroundColor: Color = AyApp.CLIENTS.color
    }

    data class SideProject(val index: Int, val title: String) : SearchResult {
        override val emoji: String = "🧪"
        override val displayTitle: String = title
        override val displaySubtitle: String = "Side Project"
        override val backgroundColor: Color = AyApp.SIDE_PROJECTS.color
    }
}
