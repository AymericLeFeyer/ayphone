rootProject.name = "AyPhone"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

include(":composeApp")
include(":core:dates")
include(":core:design-system")
include(":core:network")
include(":core:settings")
include(":features:frame")
include(":features:widget")
include(":features:application")
include(":features:applications:resume")
include(":features:applications:missions")
include(":features:applications:stack")
include(":features:applications:about")
include(":features:applications:clients")
include(":features:applications:sideprojects")
include(":features:applications:aylabs")
include(":features:applications:hobbies")
include(":features:applications:ayshop")
include(":features:applications:timeline")
include(":features:applications:settings")
include(":features:applications:travel")
