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


??? Grant the Source Repository Writer IAM role to the Cloud Build service account for the hello-cloudbuild-env repository.

```PROJECT_NUMBER="$(gcloud projects describe ${PROJECT_ID} \
    --format='get(projectNumber)')"
cat >/tmp/hello-cloudbuild-env-policy.yaml <<EOF
bindings:
- members:
  - serviceAccount:${PROJECT_NUMBER}@cloudbuild.gserviceaccount.com
  role: roles/source.writer
EOF
gcloud source repos set-iam-policy \
    hello-cloudbuild-env /tmp/hello-cloudbuild-env-policy.yaml```



https://partner.cloudskillsboost.google/focuses/11586?parent=catalog
https://partner.cloudskillsboost.google/focuses/14875?parent=catalog
https://cloud.google.com/architecture/jenkins-on-kubernetes-engine
