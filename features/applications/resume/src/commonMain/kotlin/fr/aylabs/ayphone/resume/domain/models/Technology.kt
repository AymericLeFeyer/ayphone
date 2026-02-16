package fr.aylabs.ayphone.resume.domain.models

import fr.aylabs.ayphone.resume.domain.models.TechnologyCategory.BACKEND
import fr.aylabs.ayphone.resume.domain.models.TechnologyCategory.DATA
import fr.aylabs.ayphone.resume.domain.models.TechnologyCategory.MOBILE
import fr.aylabs.ayphone.resume.domain.models.TechnologyCategory.OPS
import fr.aylabs.ayphone.resume.domain.models.TechnologyCategory.TOOLS
import fr.aylabs.ayphone.resume.domain.models.TechnologyCategory.WEB

enum class Technology(
    val label: String,
    val iconPath: String,
    val category: TechnologyCategory,
) {
    ANDROID("Android", "drawable/android.png", MOBILE),
    ANDROID_JAVA("Android (Java)", "drawable/android.png", MOBILE),
    ANGULAR("Angular", "drawable/angular.png", WEB),
    AZURE("Azure", "drawable/azure.png", OPS),
    BITRISE("Bitrise", "drawable/bitrise.png", OPS),
    COMPOSE("Compose", "drawable/compose.png", MOBILE),
    COPILOT("Copilot", "drawable/copilot.png", TOOLS),
    CSS("CSS", "drawable/css.png", WEB),
    DART("Dart", "drawable/dart.png", MOBILE),
    DJANGO("Django", "drawable/django.png", BACKEND),
    DOCKER("Docker", "drawable/docker.png", OPS),
    DOCKER_COMPOSE("Docker-Compose", "drawable/docker.png", OPS),
    DOTNET(".NET", "drawable/dotnet.png", BACKEND),
    DOTNET_CORE(".NET Core", "drawable/dotnet.png", BACKEND),
    EXPRESS_JS("ExpressJS", "drawable/express-js.webp", BACKEND),
    FIGMA("Figma", "drawable/figma.png", TOOLS),
    FIREBASE("Firebase", "drawable/firebase.png", OPS),
    FIRESTORE("Cloud Firestore", "drawable/firestore.png", DATA),
    FLUTTER("Flutter", "drawable/flutter.png", MOBILE),
    GIT("Git", "drawable/git.png", OPS),
    GITHUB("GitHub", "drawable/github.png", OPS),
    GITHUB_ACTIONS("GitHub Actions", "drawable/github.png", OPS),
    GITLAB("GitLab", "drawable/gitlab.png", OPS),
    GOOGLE_CLOUD_PLATFORM("Google Cloud Platform", "drawable/google-cloud.png", OPS),
    GHERKIN("Gherkin", "drawable/guerkin.png", TOOLS),
    HTML("HTML", "drawable/html.png", WEB),
    JENKINS("Jenkins", "drawable/jenkins.png", OPS),
    JAVASCRIPT("JavaScript", "drawable/js.png", WEB),
    KAFKA("Kafka", "drawable/kafka.png", DATA),
    KOIN("Koin", "drawable/koin.png", MOBILE),
    KOTLIN("Kotlin", "drawable/kotlin.png", MOBILE),
    KTOR("Ktor", "drawable/ktor.png", MOBILE),
    KUBERNETES("Kubernetes", "drawable/kubernetes.png", OPS),
    LEAF("Leaf", "drawable/leaf.png", BACKEND),
    MATERIAL_UI("Material UI", "drawable/material-ui.jpg", WEB),
    MONGODB("MongoDB", "drawable/mongodb.png", DATA),
    NODE_JS("Node.js", "drawable/nodejs.png", BACKEND),
    NOTION("Notion", "drawable/notion.png", TOOLS),
    ORACLE("Oracle", "drawable/oracle.png", DATA),
    PLANTUML("PlantUML", "drawable/plantuml.webp", TOOLS),
    POSTGRESQL("PostgreSQL", "drawable/postgres.png", DATA),
    PYTHON("Python", "drawable/python.png", BACKEND),
    RABBITMQ("RabbitMQ", "drawable/rabbitmq.png", DATA),
    REACT("React", "drawable/react.png", WEB),
    REDIS("Redis", "drawable/redis.png", DATA),
    STRIPE("Stripe", "drawable/stripe.png", TOOLS),
    VUE_JS("VueJS", "drawable/vuejs.png", WEB),
    WPF("WPF", "drawable/wpf.png", BACKEND),
    ;

    companion object {
        fun fromLabel(name: String): Technology? =
            entries.find { it.label.equals(name, ignoreCase = true) }
    }
}
