# Configuration for a GKE cluster
resource "google_container_cluster" "default" {
  name               = "demo-k8s-cluster"
  location           = var.gcp_zone
  initial_node_count = 3

  node_config {
    preemptible  = true
    machine_type = "n1-standard-2"

    # Google recommends custom service accounts that have cloud-platform scope and permissions granted via IAM Roles.
    service_account = "sa-gke@${var.gcp_project}.iam.gserviceaccount.com"
    oauth_scopes = [
      "https://www.googleapis.com/auth/cloud-platform"
    ]
  }

  // Wait for the GCE LB controller to cleanup the resources.
  provisioner "local-exec" {
    when    = destroy
    command = "sleep 90"
  }
}
