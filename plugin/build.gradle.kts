plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
    `maven-publish`
    id("com.gradle.plugin-publish") version "0.15.0"
}

repositories {
    mavenCentral()
}

group = "com.github.bendezu"
version = "0.0.1"

pluginBundle {
    website = "https://github.com/bendezu/android-resource-override-finder"
    vcsUrl = "https://github.com/bendezu/android-resource-override-finder.git"
    tags = listOf("android", "resource", "override")
}

gradlePlugin {
    plugins.create("resource-override-plugin") {
        id = "com.github.bendezu.resourceoverride"
        displayName = "Android Resource Override Finder"
        description = "A plugin that finds resource overrides in android project"
        implementationClass = "com.bendezu.resourceoverride.ResourceOverridePlugin"
    }
}

publishing {
    repositories {
        mavenLocal()
    }
}

// Publish to Maven Local
//   ./gradlew plugin:publishToMavenLocal

// Publish to Gradle Plugin Portal
//   ./gradlew plugin:publishPlugins