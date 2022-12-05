# Configuration for a Google Cloud service account(s) and IAM policy for a project

# Create a custom Cloud Build Service Account
resource "google_service_account" "sa_cloud_build" {
  account_id   = "sa-cloud-build"
  display_name = "sa-cloud-build"
  description  = "Cloud Build Service account with custom permissions"
}

# Create a custom Artifact Registry Service Account
resource "google_service_account" "sa_artifact_registry" {
  account_id   = "sa-artifact-registry"
  display_name = "sa-artifact-registry"
  description  = "Artifact Registry Service account with custom permissions"
}

# Updates the IAM policy to grant a roles to a Cloud Build service account
resource "google_project_iam_member" "sa_cloud_build_roles" {
  for_each = var.sa_cloud_build_roles

  project = var.gcp_project
  role    = each.value
  member  = "serviceAccount:${google_service_account.sa_cloud_build.email}"
}

# Updates the IAM policy to grant a roles to a Artifact Registry service account
resource "google_artifact_registry_repository_iam_member" "sa_artifact_registry_roles" {
  project    = var.gcp_project
  location   = var.gcp_region
  repository = google_artifact_registry_repository.docker.name

  role       = "roles/artifactregistry.reader"
  member     = "serviceAccount:${google_service_account.sa_artifact_registry.email}"
}
