package com.bendezu.resourceoverride

import org.gradle.api.Plugin
import org.gradle.api.Project

class ResourceOverridePlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.tasks.register("findResourceOverrides", FindResourceOverrideTask::class.java)
    }
}