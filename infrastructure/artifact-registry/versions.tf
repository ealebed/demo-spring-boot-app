terraform {
  required_version = ">= 0.13"
  
  required_providers {
    google-beta = {
      source  = "hashicorp/google-beta"
      version = "~> 4.0"
    }
    google = {
      source = "hashicorp/google"
      version = ">= 3.53, < 5.0"
    }
  }
}

provider "google-beta" {
  credentials = file("../../credentials.json")

  project     = var.gcp_project
  region      = var.gcp_region
  zone        = var.gcp_zone
}

provider "google" {
  credentials = file("../../credentials.json")

  project     = var.gcp_project
  region      = var.gcp_region
  zone        = var.gcp_zone
}
