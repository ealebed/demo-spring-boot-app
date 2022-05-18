TODO:
# Enable APIs
# artifactregistry.googleapis.com
# container.googleapis.com
- create Artifact Registry
# Login 
# cat keyoutput.json | docker login -u _json_key --password-stdin https://us-central1-docker.pkg.dev
- create k8s cluster

# Get the cluster credentials and configure kubectl:
# gcloud container clusters get-credentials $(terraform output --raw cluster_name) --zone $(terraform output --raw cluster_location)

- deploy Jenkins
- deploy Spinnaker
- grant access to Artifact Registry for Jenkins/Spinnaker
- grant access to k8s cluster for Spinnaker
- create Jenkinsfile for build image from master branch
- create Jenkinsfile for validation PR (just build? checkstyle? SonarQube?)
- generate k8s manifest for application
- generate Spinnaker application
- generate Spinnaker pipeline(s) for application (beta/promote-to-prod/prod)
