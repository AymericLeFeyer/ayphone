package fr.aylabs.ayphone.sideprojects.domain

import androidx.navigation.NavController
import fr.aylabs.ayphone.application.data.Application
import fr.aylabs.ayphone.sideprojects.ui.navigation.SideProjectsRoutes

class SideProjectsApp(private val navController: NavController? = null) : Application(
    title = "Side Projects",
    iconEmoji = "🧪",
    iconColor = 0xFF7C3AED,
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
) {
    override fun onClick() {
        navController?.navigate(SideProjectsRoutes.Root())
    }
}
