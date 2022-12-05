output "artifact-registry-sa" {
  value = google_service_account.sa_artifact_registry.email
}

output "cluster_name" {
  value = google_container_cluster.default.name
}

output "cluster_region" {
  value = var.gcp_region
}

output "cluster_location" {
  value = google_container_cluster.default.location
}
