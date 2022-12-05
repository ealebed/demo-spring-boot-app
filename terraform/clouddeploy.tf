resource "google_clouddeploy_delivery_pipeline" "cd_pipeline" {
  name        = "demo-spring-boot-app-cd"
  location    = var.gcp_region
  project     = var.gcp_project
  description = "Demo spring-boot application Cloud Deploy pipeline"

  serial_pipeline {
    stages {
      profiles  = []
      target_id = "demo-target"
    }
  }
}

resource "google_clouddeploy_target" "target" {
  name             = "demo-target"
  location         = var.gcp_region
  project          = var.gcp_project
  description      = "Demo GKE cluster"

  require_approval = true

  gke {
    cluster = google_container_cluster.default.id
  }

  depends_on = [
    google_clouddeploy_delivery_pipeline.cd_pipeline
  ]
}
