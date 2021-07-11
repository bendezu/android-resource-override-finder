package com.bendezu.resourceoverride.data

import java.nio.file.Path

data class LibrariesInfo(
    val libraries: List<LibraryInfo>
) {

    val distinctResources = libraries
        .flatMap { it.resFolders }
        .flatMap { it.resFiles }
        .flatMap { it.definedResIds }
        .distinct()
}

data class LibraryInfo(
    val metadataPath: Path,
    val resFolders: List<LibraryResFolderInfo>
)

data class LibraryResFolderInfo(
    val dir: Path,
    val resFiles: List<ResourceFile>
)