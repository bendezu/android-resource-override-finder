package com.bendezu.resourceoverride.data

import java.util.regex.Pattern

class Configuration(
    val isDebug: Boolean = false,

    val libsPathSuffix: String = "/.idea/libraries",
    val androidLibraryXmlSuffix: String = "aar.xml",
    val xmlLibraryPathPattern: Pattern = Pattern.compile("<root url=\"file://(.+/res)\""),

//    val resourceDefinitionPattern: Pattern = Pattern.compile("<(string|color|dimen|style).+name=\"([\\S]+)\""),
    val resourceDefinitionPattern: Pattern = Pattern.compile("<(string|color|dimen|style).+name=\"([a-zA-Z0-9_.]+)\""),

    val envMapper: List<Pair<String, String>> = listOf("\$USER_HOME\$" to System.getenv("HOME"))
)