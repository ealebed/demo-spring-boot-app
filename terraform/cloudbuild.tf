resource "google_cloudbuild_trigger" "default" {
  location           = "global"
  description        = "Trigger for CI/CD demo-spring-boot-app"
  tags               = ["terraform", "demo", "jib"]
  include_build_logs = "INCLUDE_BUILD_LOGS_WITH_STATUS"
  name               = "demo-spring-boot-app-trigger"
  service_account    = google_service_account.sa_cloud_build.id

  github {
    owner = var.github.organization
    name  = var.github.repo
    push {
      branch = "^master$"
    }
  }

  substitutions = {
    _REGION: var.gcp_region
    _PROJECT_ID: var.gcp_project
  }

  filename = "cloudbuild.yaml"
}
