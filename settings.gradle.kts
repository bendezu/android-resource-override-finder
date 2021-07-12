rootProject.name = "android-resource-override-finder"
include(":sample-app")
//include(":plugin") // Uncomment to publish plugin
includeBuild("plugin") // Uncomment to use local plugin

pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
    }
}