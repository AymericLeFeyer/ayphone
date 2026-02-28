package fr.aylabs.ayphone.resume.domain.models

import fr.aylabs.ayphone.resume.domain.models.SkillCategory.BACKEND
import fr.aylabs.ayphone.resume.domain.models.SkillCategory.DATA
import fr.aylabs.ayphone.resume.domain.models.SkillCategory.MOBILE
import fr.aylabs.ayphone.resume.domain.models.SkillCategory.OPS
import fr.aylabs.ayphone.resume.domain.models.SkillCategory.TOOLS
import fr.aylabs.ayphone.resume.domain.models.SkillCategory.WEB

enum class Skill(
    val label: String,
    val iconUrl: String,
    val category: SkillCategory,
    val description: String,
) {
    ANDROID("Android", "https://api.aymeric.lefeyer.fr/icons/technologies/android.png", MOBILE, "Développement d'applications Android natives avec le SDK Android."),
    ANDROID_JAVA("Android (Java)", "https://api.aymeric.lefeyer.fr/icons/technologies/android.png", MOBILE, "Développement Android natif en Java."),
    ANGULAR("Angular", "https://api.aymeric.lefeyer.fr/icons/technologies/angular.png", WEB, "Framework web front-end développé par Google."),
    AZURE("Azure", "https://api.aymeric.lefeyer.fr/icons/technologies/azure.png", OPS, "Plateforme cloud de Microsoft pour l'hébergement et les services."),
    BITRISE("Bitrise", "https://api.aymeric.lefeyer.fr/icons/technologies/bitrise.png", OPS, "Plateforme d'intégration et de déploiement continu pour mobile."),
    COMPOSE("Compose", "https://api.aymeric.lefeyer.fr/icons/technologies/compose.png", MOBILE, "Framework UI déclaratif de Jetpack pour Android et multiplateforme."),
    COPILOT("Copilot", "https://api.aymeric.lefeyer.fr/icons/technologies/copilot.png", TOOLS, "Assistant de programmation basé sur l'intelligence artificielle."),
    CSS("CSS", "https://api.aymeric.lefeyer.fr/icons/technologies/css.png", WEB, "Langage de mise en forme pour les pages web."),
    DART("Dart", "https://api.aymeric.lefeyer.fr/icons/technologies/dart.png", MOBILE, "Langage de programmation développé par Google, utilisé avec Flutter."),
    DJANGO("Django", "https://api.aymeric.lefeyer.fr/icons/technologies/django.png", BACKEND, "Framework web Python pour le développement rapide."),
    DOCKER("Docker", "https://api.aymeric.lefeyer.fr/icons/technologies/docker.png", OPS, "Plateforme de conteneurisation pour le déploiement d'applications."),
    DOCKER_COMPOSE("Docker-Compose", "https://api.aymeric.lefeyer.fr/icons/technologies/docker.png", OPS, "Outil d'orchestration de conteneurs Docker multi-services."),
    DOTNET(".NET", "https://api.aymeric.lefeyer.fr/icons/technologies/dotnet.png", BACKEND, "Framework de développement multiplateforme de Microsoft."),
    DOTNET_CORE(".NET Core", "https://api.aymeric.lefeyer.fr/icons/technologies/dotnet.png", BACKEND, "Version multiplateforme et open-source du framework .NET."),
    EXPRESS_JS("ExpressJS", "https://api.aymeric.lefeyer.fr/icons/technologies/express-js.webp", BACKEND, "Framework minimaliste pour applications web Node.js."),
    FIGMA("Figma", "https://api.aymeric.lefeyer.fr/icons/technologies/figma.png", TOOLS, "Outil de design d'interfaces collaboratif."),
    FIREBASE("Firebase", "https://api.aymeric.lefeyer.fr/icons/technologies/firebase.png", OPS, "Plateforme de développement d'applications de Google."),
    FIRESTORE("Cloud Firestore", "https://api.aymeric.lefeyer.fr/icons/technologies/firestore.png", DATA, "Base de données NoSQL temps réel de Firebase."),
    FLUTTER("Flutter", "https://api.aymeric.lefeyer.fr/icons/technologies/flutter.png", MOBILE, "Framework multiplateforme de Google pour mobile, web et desktop."),
    GIT("Git", "https://api.aymeric.lefeyer.fr/icons/technologies/git.png", OPS, "Système de contrôle de version distribué."),
    GITHUB("GitHub", "https://api.aymeric.lefeyer.fr/icons/technologies/github.png", OPS, "Plateforme d'hébergement de code et de collaboration."),
    GITHUB_ACTIONS("GitHub Actions", "https://api.aymeric.lefeyer.fr/icons/technologies/github.png", OPS, "Service d'intégration et de déploiement continu de GitHub."),
    GITLAB("GitLab", "https://api.aymeric.lefeyer.fr/icons/technologies/gitlab.png", OPS, "Plateforme DevOps complète avec gestion de code source."),
    GOOGLE_CLOUD_PLATFORM("Google Cloud Platform", "https://api.aymeric.lefeyer.fr/icons/technologies/google-cloud.png", OPS, "Plateforme cloud de Google pour l'hébergement et les services."),
    GHERKIN("Gherkin", "https://api.aymeric.lefeyer.fr/icons/technologies/guerkin.png", TOOLS, "Langage de spécification pour les tests comportementaux (BDD)."),
    HTML("HTML", "https://api.aymeric.lefeyer.fr/icons/technologies/html.png", WEB, "Langage de balisage pour la structure des pages web."),
    JENKINS("Jenkins", "https://api.aymeric.lefeyer.fr/icons/technologies/jenkins.png", OPS, "Serveur d'automatisation pour l'intégration continue."),
    JAVASCRIPT("JavaScript", "https://api.aymeric.lefeyer.fr/icons/technologies/js.png", WEB, "Langage de programmation pour le web côté client et serveur."),
    KAFKA("Kafka", "https://api.aymeric.lefeyer.fr/icons/technologies/kafka.png", DATA, "Plateforme de streaming d'événements distribué."),
    KOIN("Koin", "https://api.aymeric.lefeyer.fr/icons/technologies/koin.png", MOBILE, "Framework d'injection de dépendances léger pour Kotlin."),
    KOTLIN("Kotlin", "https://api.aymeric.lefeyer.fr/icons/technologies/kotlin.png", MOBILE, "Langage de programmation moderne pour la JVM et le multiplateforme."),
    KTOR("Ktor", "https://api.aymeric.lefeyer.fr/icons/technologies/ktor.png", MOBILE, "Framework asynchrone pour applications web et API en Kotlin."),
    KUBERNETES("Kubernetes", "https://api.aymeric.lefeyer.fr/icons/technologies/kubernetes.png", OPS, "Système d'orchestration de conteneurs à grande échelle."),
    LEAF("Leaf", "https://api.aymeric.lefeyer.fr/icons/technologies/leaf.png", BACKEND, "Moteur de templates pour le framework Vapor en Swift."),
    MATERIAL_UI("Material UI", "https://api.aymeric.lefeyer.fr/icons/technologies/material-ui.jpg", WEB, "Bibliothèque de composants React basée sur Material Design."),
    MONGODB("MongoDB", "https://api.aymeric.lefeyer.fr/icons/technologies/mongodb.png", DATA, "Base de données NoSQL orientée documents."),
    NODE_JS("Node.js", "https://api.aymeric.lefeyer.fr/icons/technologies/nodejs.png", BACKEND, "Environnement d'exécution JavaScript côté serveur."),
    NOTION("Notion", "https://api.aymeric.lefeyer.fr/icons/technologies/notion.png", TOOLS, "Outil de gestion de projet et de documentation."),
    ORACLE("Oracle", "https://api.aymeric.lefeyer.fr/icons/technologies/oracle.png", DATA, "Système de gestion de base de données relationnelle."),
    PLANTUML("PlantUML", "https://api.aymeric.lefeyer.fr/icons/technologies/plantuml.webp", TOOLS, "Outil de création de diagrammes UML à partir de texte."),
    POSTGRESQL("PostgreSQL", "https://api.aymeric.lefeyer.fr/icons/technologies/postgres.png", DATA, "Système de gestion de base de données relationnelle open-source."),
    PYTHON("Python", "https://api.aymeric.lefeyer.fr/icons/technologies/python.png", BACKEND, "Langage de programmation polyvalent et accessible."),
    RABBITMQ("RabbitMQ", "https://api.aymeric.lefeyer.fr/icons/technologies/rabbitmq.png", DATA, "Broker de messages pour la communication inter-services."),
    REACT("React", "https://api.aymeric.lefeyer.fr/icons/technologies/react.png", WEB, "Bibliothèque JavaScript pour la création d'interfaces utilisateur."),
    REDIS("Redis", "https://api.aymeric.lefeyer.fr/icons/technologies/redis.png", DATA, "Base de données en mémoire pour le cache et le messaging."),
    STRIPE("Stripe", "https://api.aymeric.lefeyer.fr/icons/technologies/stripe.png", TOOLS, "Plateforme de paiement en ligne pour les applications."),
    VUE_JS("VueJS", "https://api.aymeric.lefeyer.fr/icons/technologies/vuejs.png", WEB, "Framework JavaScript progressif pour les interfaces web."),
    WPF("WPF", "https://api.aymeric.lefeyer.fr/icons/technologies/wpf.png", BACKEND, "Framework de développement d'interfaces desktop Windows."),
    ;

    companion object {
        fun fromLabel(name: String): Skill? =
            entries.find { it.label.equals(name, ignoreCase = true) }
    }
}
