package fr.aylabs.ayphone.resume.domain.models

import ayphone.features.applications.resume.generated.resources.Res
import ayphone.features.applications.resume.generated.resources.android
import ayphone.features.applications.resume.generated.resources.angular
import ayphone.features.applications.resume.generated.resources.azure
import ayphone.features.applications.resume.generated.resources.bitrise
import ayphone.features.applications.resume.generated.resources.compose
import ayphone.features.applications.resume.generated.resources.copilot
import ayphone.features.applications.resume.generated.resources.css
import ayphone.features.applications.resume.generated.resources.dart
import ayphone.features.applications.resume.generated.resources.django
import ayphone.features.applications.resume.generated.resources.docker
import ayphone.features.applications.resume.generated.resources.docusaurus
import ayphone.features.applications.resume.generated.resources.dotnet
import ayphone.features.applications.resume.generated.resources.express_js
import ayphone.features.applications.resume.generated.resources.figma
import ayphone.features.applications.resume.generated.resources.firebase
import ayphone.features.applications.resume.generated.resources.firestore
import ayphone.features.applications.resume.generated.resources.flutter
import ayphone.features.applications.resume.generated.resources.git
import ayphone.features.applications.resume.generated.resources.github
import ayphone.features.applications.resume.generated.resources.gitlab
import ayphone.features.applications.resume.generated.resources.google_cloud
import ayphone.features.applications.resume.generated.resources.guerkin
import ayphone.features.applications.resume.generated.resources.html
import ayphone.features.applications.resume.generated.resources.jenkins
import ayphone.features.applications.resume.generated.resources.jira
import ayphone.features.applications.resume.generated.resources.js
import ayphone.features.applications.resume.generated.resources.kafka
import ayphone.features.applications.resume.generated.resources.koin
import ayphone.features.applications.resume.generated.resources.kotlin
import ayphone.features.applications.resume.generated.resources.ktor
import ayphone.features.applications.resume.generated.resources.kubernetes
import ayphone.features.applications.resume.generated.resources.leaf
import ayphone.features.applications.resume.generated.resources.material_ui
import ayphone.features.applications.resume.generated.resources.mongodb
import ayphone.features.applications.resume.generated.resources.nodejs
import ayphone.features.applications.resume.generated.resources.notion
import ayphone.features.applications.resume.generated.resources.oracle
import ayphone.features.applications.resume.generated.resources.plantuml
import ayphone.features.applications.resume.generated.resources.postgres
import ayphone.features.applications.resume.generated.resources.python
import ayphone.features.applications.resume.generated.resources.rabbitmq
import ayphone.features.applications.resume.generated.resources.react
import ayphone.features.applications.resume.generated.resources.redis
import ayphone.features.applications.resume.generated.resources.stripe
import ayphone.features.applications.resume.generated.resources.vuejs
import ayphone.features.applications.resume.generated.resources.wpf
import org.jetbrains.compose.resources.DrawableResource

enum class Technology(
    val label: String,
    val icon: DrawableResource,
) {
    ANDROID("Android", Res.drawable.android),
    ANGULAR("Angular", Res.drawable.angular),
    AZURE("Azure", Res.drawable.azure),
    BITRISE("Bitrise", Res.drawable.bitrise),
    COMPOSE("Compose", Res.drawable.compose),
    COPILOT("Copilot", Res.drawable.copilot),
    CSS("CSS", Res.drawable.css),
    DART("Dart", Res.drawable.dart),
    DJANGO("Django", Res.drawable.django),
    DOCKER("Docker", Res.drawable.docker),
    DOCUSAURUS("Docusaurus", Res.drawable.docusaurus),
    DOTNET(".NET", Res.drawable.dotnet),
    EXPRESS_JS("Express.js", Res.drawable.express_js),
    FIGMA("Figma", Res.drawable.figma),
    FIREBASE("Firebase", Res.drawable.firebase),
    FIRESTORE("Firestore", Res.drawable.firestore),
    FLUTTER("Flutter", Res.drawable.flutter),
    GIT("Git", Res.drawable.git),
    GITHUB("GitHub", Res.drawable.github),
    GITLAB("GitLab", Res.drawable.gitlab),
    GOOGLE_CLOUD("Google Cloud", Res.drawable.google_cloud),
    GHERKIN("Gherkin", Res.drawable.guerkin),
    HTML("HTML", Res.drawable.html),
    JENKINS("Jenkins", Res.drawable.jenkins),
    JIRA("Jira", Res.drawable.jira),
    JAVASCRIPT("JavaScript", Res.drawable.js),
    KAFKA("Kafka", Res.drawable.kafka),
    KOIN("Koin", Res.drawable.koin),
    KOTLIN("Kotlin", Res.drawable.kotlin),
    KTOR("Ktor", Res.drawable.ktor),
    KUBERNETES("Kubernetes", Res.drawable.kubernetes),
    LEAF("Leaf", Res.drawable.leaf),
    MATERIAL_UI("Material UI", Res.drawable.material_ui),
    MONGODB("MongoDB", Res.drawable.mongodb),
    NODE_JS("Node.js", Res.drawable.nodejs),
    NOTION("Notion", Res.drawable.notion),
    ORACLE("Oracle", Res.drawable.oracle),
    PLANTUML("PlantUML", Res.drawable.plantuml),
    POSTGRESQL("PostgreSQL", Res.drawable.postgres),
    PYTHON("Python", Res.drawable.python),
    RABBITMQ("RabbitMQ", Res.drawable.rabbitmq),
    REACT("React", Res.drawable.react),
    REDIS("Redis", Res.drawable.redis),
    STRIPE("Stripe", Res.drawable.stripe),
    VUE_JS("Vue.js", Res.drawable.vuejs),
    WPF("WPF", Res.drawable.wpf),
    ;

    companion object {
        fun fromLabel(name: String): Technology? =
            entries.find { it.label.equals(name, ignoreCase = true) }
    }
}
