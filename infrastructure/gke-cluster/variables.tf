variable "gcp_project" {
  type = string
  description = "GCP project name"
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

variable "gcp_network_name" {
  type = string
  description = "GCP network name"
  default = "tf-gke-k8s"
}
