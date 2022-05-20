import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

allprojects {
    group = "com.ealebed.demo"
}

subprojects {
    apply(plugin = "java")

    beforeEvaluate {
        project.version = projectVersion()

        repositories {
            mavenCentral()
        }
    }
}

fun projectVersion(): String {
    val formatter = DateTimeFormatter.ofPattern("yy.MM.dd-HH.mm")
    val formatted = LocalDateTime.now().format(formatter)
    return System.getenv("PROJECT_VERSION") ?: ((System.getProperty("user.name") + "_DEV_") + formatted)
}
