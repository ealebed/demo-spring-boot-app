pluginManagement {
    plugins {
        id("com.google.cloud.tools.jib") version "3.2.1"
    }
}

rootProject.name = "demo-apps"

include("apps:undertow-app")
include("apps:spring-boot-app")
