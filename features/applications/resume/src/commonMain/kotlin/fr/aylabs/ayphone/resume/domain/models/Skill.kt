package fr.aylabs.ayphone.resume.domain.models

import fr.aylabs.ayphone.resume.domain.models.SkillCategory.BACKEND
import fr.aylabs.ayphone.resume.domain.models.SkillCategory.DATA
import fr.aylabs.ayphone.resume.domain.models.SkillCategory.MOBILE
import fr.aylabs.ayphone.resume.domain.models.SkillCategory.OPS
import fr.aylabs.ayphone.resume.domain.models.SkillCategory.TOOLS
import fr.aylabs.ayphone.resume.domain.models.SkillCategory.WEB

enum class Skill(
    val label: String,
    val iconPath: String,
    val category: SkillCategory,
    val description: String,
) {
    ANDROID("Android", "drawable/android.png", MOBILE, "Développement d'applications Android natives avec le SDK Android."),
    ANDROID_JAVA("Android (Java)", "drawable/android.png", MOBILE, "Développement Android natif en Java."),
    ANGULAR("Angular", "drawable/angular.png", WEB, "Framework web front-end développé par Google."),
    AZURE("Azure", "drawable/azure.png", OPS, "Plateforme cloud de Microsoft pour l'hébergement et les services."),
    BITRISE("Bitrise", "drawable/bitrise.png", OPS, "Plateforme d'intégration et de déploiement continu pour mobile."),
    COMPOSE("Compose", "drawable/compose.png", MOBILE, "Framework UI déclaratif de Jetpack pour Android et multiplateforme."),
    COPILOT("Copilot", "drawable/copilot.png", TOOLS, "Assistant de programmation basé sur l'intelligence artificielle."),
    CSS("CSS", "drawable/css.png", WEB, "Langage de mise en forme pour les pages web."),
    DART("Dart", "drawable/dart.png", MOBILE, "Langage de programmation développé par Google, utilisé avec Flutter."),
    DJANGO("Django", "drawable/django.png", BACKEND, "Framework web Python pour le développement rapide."),
    DOCKER("Docker", "drawable/docker.png", OPS, "Plateforme de conteneurisation pour le déploiement d'applications."),
    DOCKER_COMPOSE("Docker-Compose", "drawable/docker.png", OPS, "Outil d'orchestration de conteneurs Docker multi-services."),
    DOTNET(".NET", "drawable/dotnet.png", BACKEND, "Framework de développement multiplateforme de Microsoft."),
    DOTNET_CORE(".NET Core", "drawable/dotnet.png", BACKEND, "Version multiplateforme et open-source du framework .NET."),
    EXPRESS_JS("ExpressJS", "drawable/express-js.webp", BACKEND, "Framework minimaliste pour applications web Node.js."),
    FIGMA("Figma", "drawable/figma.png", TOOLS, "Outil de design d'interfaces collaboratif."),
    FIREBASE("Firebase", "drawable/firebase.png", OPS, "Plateforme de développement d'applications de Google."),
    FIRESTORE("Cloud Firestore", "drawable/firestore.png", DATA, "Base de données NoSQL temps réel de Firebase."),
    FLUTTER("Flutter", "drawable/flutter.png", MOBILE, "Framework multiplateforme de Google pour mobile, web et desktop."),
    GIT("Git", "drawable/git.png", OPS, "Système de contrôle de version distribué."),
    GITHUB("GitHub", "drawable/github.png", OPS, "Plateforme d'hébergement de code et de collaboration."),
    GITHUB_ACTIONS("GitHub Actions", "drawable/github.png", OPS, "Service d'intégration et de déploiement continu de GitHub."),
    GITLAB("GitLab", "drawable/gitlab.png", OPS, "Plateforme DevOps complète avec gestion de code source."),
    GOOGLE_CLOUD_PLATFORM("Google Cloud Platform", "drawable/google-cloud.png", OPS, "Plateforme cloud de Google pour l'hébergement et les services."),
    GHERKIN("Gherkin", "drawable/guerkin.png", TOOLS, "Langage de spécification pour les tests comportementaux (BDD)."),
    HTML("HTML", "drawable/html.png", WEB, "Langage de balisage pour la structure des pages web."),
    JENKINS("Jenkins", "drawable/jenkins.png", OPS, "Serveur d'automatisation pour l'intégration continue."),
    JAVASCRIPT("JavaScript", "drawable/js.png", WEB, "Langage de programmation pour le web côté client et serveur."),
    KAFKA("Kafka", "drawable/kafka.png", DATA, "Plateforme de streaming d'événements distribué."),
    KOIN("Koin", "drawable/koin.png", MOBILE, "Framework d'injection de dépendances léger pour Kotlin."),
    KOTLIN("Kotlin", "drawable/kotlin.png", MOBILE, "Langage de programmation moderne pour la JVM et le multiplateforme."),
    KTOR("Ktor", "drawable/ktor.png", MOBILE, "Framework asynchrone pour applications web et API en Kotlin."),
    KUBERNETES("Kubernetes", "drawable/kubernetes.png", OPS, "Système d'orchestration de conteneurs à grande échelle."),
    LEAF("Leaf", "drawable/leaf.png", BACKEND, "Moteur de templates pour le framework Vapor en Swift."),
    MATERIAL_UI("Material UI", "drawable/material-ui.jpg", WEB, "Bibliothèque de composants React basée sur Material Design."),
    MONGODB("MongoDB", "drawable/mongodb.png", DATA, "Base de données NoSQL orientée documents."),
    NODE_JS("Node.js", "drawable/nodejs.png", BACKEND, "Environnement d'exécution JavaScript côté serveur."),
    NOTION("Notion", "drawable/notion.png", TOOLS, "Outil de gestion de projet et de documentation."),
    ORACLE("Oracle", "drawable/oracle.png", DATA, "Système de gestion de base de données relationnelle."),
    PLANTUML("PlantUML", "drawable/plantuml.webp", TOOLS, "Outil de création de diagrammes UML à partir de texte."),
    POSTGRESQL("PostgreSQL", "drawable/postgres.png", DATA, "Système de gestion de base de données relationnelle open-source."),
    PYTHON("Python", "drawable/python.png", BACKEND, "Langage de programmation polyvalent et accessible."),
    RABBITMQ("RabbitMQ", "drawable/rabbitmq.png", DATA, "Broker de messages pour la communication inter-services."),
    REACT("React", "drawable/react.png", WEB, "Bibliothèque JavaScript pour la création d'interfaces utilisateur."),
    REDIS("Redis", "drawable/redis.png", DATA, "Base de données en mémoire pour le cache et le messaging."),
    STRIPE("Stripe", "drawable/stripe.png", TOOLS, "Plateforme de paiement en ligne pour les applications."),
    VUE_JS("VueJS", "drawable/vuejs.png", WEB, "Framework JavaScript progressif pour les interfaces web."),
    WPF("WPF", "drawable/wpf.png", BACKEND, "Framework de développement d'interfaces desktop Windows."),
    ;

    companion object {
        fun fromLabel(name: String): Skill? =
            entries.find { it.label.equals(name, ignoreCase = true) }
    }
}
