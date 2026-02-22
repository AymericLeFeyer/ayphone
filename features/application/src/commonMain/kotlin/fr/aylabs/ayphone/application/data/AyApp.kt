package fr.aylabs.ayphone.application.data

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.woowla.compose.icon.collections.remix.Remix
import com.woowla.compose.icon.collections.remix.remix.Development
import com.woowla.compose.icon.collections.remix.remix.Finance
import com.woowla.compose.icon.collections.remix.remix.GameAndSports
import com.woowla.compose.icon.collections.remix.remix.HealthAndMedical
import com.woowla.compose.icon.collections.remix.remix.Map
import com.woowla.compose.icon.collections.remix.remix.Media
import com.woowla.compose.icon.collections.remix.remix.System
import com.woowla.compose.icon.collections.remix.remix.UserAndFaces
import com.woowla.compose.icon.collections.remix.remix.development.CodeSSlashLine
import com.woowla.compose.icon.collections.remix.remix.finance.ShoppingBag3Line
import com.woowla.compose.icon.collections.remix.remix.gameandsports.TargetLine
import com.woowla.compose.icon.collections.remix.remix.healthandmedical.FlaskLine
import com.woowla.compose.icon.collections.remix.remix.map.PlaneLine
import com.woowla.compose.icon.collections.remix.remix.map.RocketLine
import com.woowla.compose.icon.collections.remix.remix.media.FilmLine
import com.woowla.compose.icon.collections.remix.remix.system.HourglassLine
import com.woowla.compose.icon.collections.remix.remix.system.Settings3Line
import com.woowla.compose.icon.collections.remix.remix.userandfaces.TeamLine
import com.woowla.compose.icon.collections.remix.remix.userandfaces.UserLine

enum class AyApp(
    val title: String,
    val color: Color,
    val logo: ImageVector,
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
        logo = Remix.Map.RocketLine,
        color = Color(0xFF1E40AF),
    ),
    STACK(
        title = "Stack tech",
        logo = Remix.Development.CodeSSlashLine,
        color = Color(0xFF065F46),
    ),
    ABOUT(
        title = "A propos",
        logo = Remix.UserAndFaces.UserLine,
        color = Color(0xFF3730A3),
    ),
    CLIENTS(
        title = "Clients",
        logo = Remix.UserAndFaces.TeamLine,
        color = Color(0xFFB45309),
    ),
    AYSHOP(
        title = "AyShop",
        logo = Remix.Finance.ShoppingBag3Line,
        color = Color(0xFFBE185D),
    ),
    TIMELINE(
        title = "Timelife",
        logo = Remix.System.HourglassLine,
        color = Color(0xFF0369A1),
    ),
    SETTINGS(
        title = "Réglages",
        logo = Remix.System.Settings3Line,
        color = Color(0xFF374151),
    ),
    TRAVEL(
        title = "Voyages",
        logo = Remix.Map.PlaneLine,
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
        logo = Remix.Media.FilmLine,
        color = Color(0xFFB91C1C),
    ),
    HOBBIES(
        title = "Loisirs",
        logo = Remix.GameAndSports.TargetLine,
        color = Color(0xFF0D9488),
        id = "hobbies",
        developer = "AyLabs",
        category = "Lifestyle & Gaming",
        shortDescription = "Jeux vidéo, jeux de société, ciné et passion tech",
        description = "Loisirs, c'est une fenêtre sur ce qui fait vibrer Aymeric en dehors du travail — des jeux vidéo cultes aux jeux de société du week-end, en passant par le cinéma, la domotique et l'impression 3D.\n\n" +
                "Explore les licences qui ont marqué son parcours gaming (PS2, Nintendo DS, Steam, PS5), les jeux de société qu'il sort régulièrement, et les passions tech comme le homelab et la fabrication.\n\n" +
                "Une façon ludique de découvrir la personne derrière le développeur.",
        features = listOf(
            "Gaming : PC/Steam, Nintendo, PlayStation",
            "Jeux de société favoris avec liens BGG",
            "Passions tech : homelab, domotique, impression 3D",
            "Liens directs vers profils PSN et MyLudo",
            "Sections dépliables pour explorer à ton rythme",
        ),
        whatsNew = listOf(
            "Première version sur AyShop",
            "Sections collapsibles par univers",
            "Lien vers le profil PSN",
            "Collection MyLudo intégrée",
        ),
        rating = 4.8f,
        reviewCount = "37",
        downloads = "150+",
        version = "1.0.0",
        size = "1,6 Mo",
    ),
    SIDE_PROJECTS(
        title = "Side Projects",
        logo = Remix.HealthAndMedical.FlaskLine,
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
