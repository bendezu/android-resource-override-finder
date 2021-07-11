plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

repositories {
    mavenCentral()
}

gradlePlugin {
    plugins.register("resource-override-plugin") {
        id = "com.bendezu.resourceoverride"
        implementationClass = "com.bendezu.resourceoverride.ResourceOverridePlugin"
    }
}