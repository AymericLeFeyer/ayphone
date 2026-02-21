package fr.aylabs.ayphone.application.data

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

enum class AyApp(
    val title: String,
    val color: Color,
    val iconEmoji: String? = null,
    val logo: ImageVector? = null,
    val id: String? = null,
    val developer: String? = null,
    val category: String? = null,
    val shortDescription: String? = null,
    val description: String? = null,
    val features: List<String> = emptyList(),
    val whatsNew: List<String> = emptyList(),
    val rating: Float? = null,
    val reviewCount: String? = null,
    val downloads: String? = null,
    val version: String? = null,
    val size: String? = null,
) {
    MISSIONS(
        title = "Missions",
        iconEmoji = "🚀",
        color = Color(0xFF1E40AF),
    ),
    STACK(
        title = "Stack tech",
        iconEmoji = "💻",
        color = Color(0xFF065F46),
    ),
    ABOUT(
        title = "A propos",
        iconEmoji = "🙋",
        color = Color(0xFF3730A3),
    ),
    CLIENTS(
        title = "Clients",
        iconEmoji = "🤝",
        color = Color(0xFFB45309),
    ),
    AYSHOP(
        title = "AyShop",
        iconEmoji = "🛍️",
        color = Color(0xFFBE185D),
    ),
    TIMELINE(
        title = "Timelife",
        iconEmoji = "⏳",
        color = Color(0xFF0369A1),
    ),
    SETTINGS(
        title = "Réglages",
        iconEmoji = "⚙️",
        color = Color(0xFF374151),
    ),
    TRAVEL(
        title = "Voyages",
        iconEmoji = "✈️",
        color = Color(0xFF0284C7),
        id = "travel",
        developer = "AyLabs",
        category = "Voyage & Lifestyle",
        shortDescription = "Retrace tes aventures à travers le monde",
        description = "Voyage est une carte interactive de tes explorations personnelles — une fenêtre sur tous les endroits que tu as visités, les souvenirs que tu as créés et les aventures qui t'attendent.\n\n" +
                "Navigue à travers une timeline géographique de tes voyages, discover de nouveaux endroits et garde une trace de tes aventures passées et futures.\n\n" +
                "Une façon unique de visualiser ta vie à travers le prisme du voyage.",
        features = listOf(
            "Timeline interactive de tes voyages",
            "Carte géographique de tes destinations",
            "Photos et souvenirs associés à chaque lieu",
            "Statistiques de voyage (pays, km, continents)",
            "Planification de futures aventures",
        ),
        whatsNew = listOf(
            "Première version sur AyShop",
            "Timeline interactive de tes destinations",
            "Visualisation cartographique",
            "Intégration avec la galerie photo",
        ),
        rating = 4.7f,
        reviewCount = "64",
        downloads = "300+",
        version = "1.0.0",
        size = "2,4 Mo",
    ),
    AYLABS(
        title = "AyLabs",
        iconEmoji = "🎬",
        color = Color(0xFFB91C1C),
    ),
    SIDE_PROJECTS(
        title = "Side Projects",
        iconEmoji = "🧪",
        color = Color(0xFF7C3AED),
        id = "sideprojects",
        developer = "AyLabs",
        category = "Outils développeur",
        shortDescription = "Explorez des expériences perso & projets créatifs",
        description = "Plongez dans une collection de projets personnels — expériences, " +
                "outils créatifs et projets passion construits en dehors des missions professionnelles.\n\n" +
                "Side Projects est votre fenêtre sur le côté créatif et exploratoire du développement. " +
                "Découvrez des applications et outils nés de la curiosité, de défis d'apprentissage " +
                "et du simple plaisir de construire quelque chose de nouveau.\n\n" +
                "Filtrez par technologie, explorez les descriptions détaillées et naviguez directement " +
                "vers les compétences et outils derrière chaque création.",
        features = listOf(
            "Parcourir les projets perso & expériences",
            "Filtrer par technologie et langage de programmation",
            "Descriptions détaillées avec contexte complet",
            "Navigation vers les compétences associées",
            "Liens vers les démos et dépôts sources",
        ),
        whatsNew = listOf(
            "Première version sur AyShop",
            "Filtrage complet par stack technologique",
            "Navigation fluide vers les détails de compétences",
            "Descriptions de projets enrichies",
        ),
        rating = 4.8f,
        reviewCount = "128",
        downloads = "500+",
        version = "1.2.0",
        size = "3,1 Mo",
    ),
}
