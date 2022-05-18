import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

plugins {
    id("java")
    id("com.google.cloud.tools.jib")
}

val mainClassName by extra("com.ealebed.demo.app.Application")

description = "Undertow demo application"
group = "com.ealebed"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
        vendor.set(JvmVendorSpec.ORACLE)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.typesafe:config:1.4.2")
    implementation("io.undertow:undertow-core:2.2.17.Final")
    implementation("org.slf4j:slf4j-api:1.7.36")
}

jib {
    val mainClassName = extra["mainClassName"] as String
    val imageVersion = projectVersion()

    from {
        image = "openjdk:17.0-jdk-oracle"
    }
    to {
        image = "us-east1-docker.pkg.dev/ylebi-rnd/docker/${project.name}:" + imageVersion
        // image = "ealebed/${project.name}:" + imageVersion
    }
    container {
        workingDirectory = "/app"
        environment = mapOf(
            "JAVA_MAIN_CLASS" to mainClassName,
            "PROJECT_VERSION" to imageVersion
        )
        entrypoint = listOf(
            "sh",
            "-c",
            "exec java \$JAVA_OPTS -cp /app/resources:/app/classes:/app/libs/* \$JAVA_MAIN_CLASS"
        )
        // disabled because of https://github.com/GoogleContainerTools/jib/blob/master/docs/faq.md#why-is-my-image-created-48-years-ago
        //creationTime = Instant.now().toString().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"))
    }
    extraDirectories {
        paths {
            path {
                setFrom("${project.buildDir}/conf")
                into = "/app/conf"
            }
        }
    }
}

tasks.register<Copy>("CopyConfigsTask") {
    from("${project.rootDir}/conf") {
        include("*.conf")
    }
    into("${project.buildDir}/conf")
}

tasks.named("jib") {
    dependsOn("CopyConfigsTask")
}

tasks.named("jibDockerBuild") {
    dependsOn("CopyConfigsTask")
}

fun projectVersion(): String {
    val formatter = DateTimeFormatter.ofPattern("yy.MM.dd-HH.mm")
    val formatted = LocalDateTime.now().format(formatter)
    return System.getenv("PROJECT_VERSION") ?: ((System.getProperty("user.name") + "_DEV_") + formatted)
}
