package com.bendezu.resourceoverride.data

import java.io.File
import java.nio.file.Path

data class ModuleInfo(
    val name: String,
    val dir: File,
    val resFiles: List<ResourceFile>
)

data class ResourceFile(
    val path: Path,
    val definedResIds: List<String>
)