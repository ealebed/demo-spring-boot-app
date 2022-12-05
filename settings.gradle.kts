pluginManagement {
    plugins {
        id("com.google.cloud.tools.jib") version "3.3.1"
    }
}

rootProject.name = "demo-app"

include("spring-boot-app")
