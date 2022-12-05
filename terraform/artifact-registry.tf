# Configuration for a Google Cloud Artifact Registry for storing docker images

resource "google_artifact_registry_repository" "docker" {
  location      = var.gcp_region
  repository_id = "docker"
  description   = "Example docker images repository"
  format        = "DOCKER"
}
