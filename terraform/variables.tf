variable "gcp_project" {
  type = string
  description = "GCP project name"
  default = "ylebi-rnd"
}

variable "gcp_region" {
  type = string
  description = "GCP region name"
  default = "europe-west1"
}

variable "gcp_zone" {
  type = string
  description = "GCP zone name"
  default = "europe-west1-b"
}

variable "github" {
  description = "GitHub info"
  default = {
    organization = "ealebed"
    repo         = "demo-spring-boot-app"
  }
}

variable "sa_cloud_build_roles" {
  type        = set(string)
  description = "List Roles to assign to Cloud Build service account"
  default = [
    "roles/cloudbuild.builds.builder",
    "roles/cloudbuild.builds.editor",
    "roles/cloudbuild.workerPoolUser",
    "roles/logging.logWriter",
    "roles/artifactregistry.writer",
    "roles/container.developer",
    "roles/iam.serviceAccountUser"
  ]
}

variable "sa_gke_roles" {
  type        = set(string)
  description = "List Roles to assign to GKE service account"
  default = [
    "roles/artifactregistry.reader",
    "roles/container.developer",
    "roles/clouddeploy.jobRunner",
    "roles/iam.serviceAccountUser"
  ]
}
