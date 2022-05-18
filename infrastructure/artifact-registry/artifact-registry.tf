resource "google_artifact_registry_repository" "docker-images" {
  provider      = google-beta

  location      = var.gcp_region
  repository_id = "docker"
  description   = "Example docker images repository with iam"
  format        = "DOCKER"
}

resource "google_service_account" "artifact-registry-sa" {
  provider     = google-beta

  account_id   = "artifact-registry-sa"
  display_name = "Service Account for Artifact Registry"
}

resource "google_artifact_registry_repository_iam_member" "artifact-registry-iam" {
  provider   = google-beta
  
  project    = google_artifact_registry_repository.docker-images.project
  location   = google_artifact_registry_repository.docker-images.location
  repository = google_artifact_registry_repository.docker-images.name
  role       = "roles/artifactregistry.admin"
  member     = "serviceAccount:${google_service_account.artifact-registry-sa.email}"
}

resource "google_service_account_key" "artifact-registry-sa-key" {
  service_account_id = "${google_service_account.artifact-registry-sa.name}"
}

resource "local_file" "key" {
  filename = "keyoutput.json"
  content  = "${base64decode(google_service_account_key.artifact-registry-sa-key.private_key)}"
}
