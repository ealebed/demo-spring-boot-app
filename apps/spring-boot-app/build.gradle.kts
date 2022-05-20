plugins {
    id("com.google.cloud.tools.jib")
}

val mainClassName by extra("com.ealebed.springboot.demo.app.Application")

description = "Spring Boot demo application"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
        vendor.set(JvmVendorSpec.ORACLE)
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:2.6.7")
}

jib {
    val mainClassName = extra["mainClassName"] as String
    val imageVersion = project.version

    from {
        image = "openjdk:17.0-jdk-oracle"
    }
    to {
        // IMPORTANT: Set the environment variable PROJECT_ID to your own Google Cloud Platform project
        image = "us-central1-docker.pkg.dev/${System.getenv("PROJECT_ID")}/docker/${project.name}:" + imageVersion
    }
    container {
        workingDirectory = "/app"
        environment = mapOf(
                "JAVA_MAIN_CLASS" to mainClassName,
                "PROJECT_VERSION" to imageVersion
        ) as MutableMap<String, String>?
        entrypoint = listOf(
            "sh",
            "-c",
            "exec java \$JAVA_OPTS -cp /app/resources:/app/classes:/app/libs/* \$JAVA_MAIN_CLASS"
        )
        // disabled because of https://github.com/GoogleContainerTools/jib/blob/master/docs/faq.md#why-is-my-image-created-48-years-ago
        //creationTime = Instant.now().toString().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"))
    }
}
