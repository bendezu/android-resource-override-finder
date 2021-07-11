package com.bendezu.resourceoverride

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

abstract class FindResourceOverrideTask : DefaultTask() {

    @TaskAction
    fun findOverrides() {
        println("FindResourceOverrideTask starts")
    }
}
