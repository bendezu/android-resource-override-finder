package com.bendezu.resourceoverride.data

data class ProjectInfo(
    val gradleModules: List<ModuleInfo>
) {

    val distinctResources = gradleModules
        .flatMap { it.resFiles }
        .flatMap { it.definedResIds }
        .distinct()
}