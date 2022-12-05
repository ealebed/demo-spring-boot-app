# Prerequisites:

- You will need a [Google Cloud Platform](https://cloud.google.com/) project(s) and a Google Cloud Service Account with enough permissions to manage resources in related GCP project(s).

## Getting started with Google Cloud Platform (optional)

Install the [Google Cloud CLI](https://cloud.google.com/sdk/docs/install-sdk)

### Create project (optional)
```bash
PROJECT_ID=new-project-id
PROJECT_NAME="New project name"

gcloud projects create ${PROJECT_ID} --name=${PROJECT_NAME}
```

If project already exist, you can get `project id` and define under environment variable PROJECT_ID:
```bash
export PROJECT_ID=$(gcloud config get-value project 2> /dev/null)
```
---

### Enable necessary API's in GCP

Enable API
```bash
gcloud services enable \
  serviceusage.googleapis.com \
  servicemanagement.googleapis.com \
  cloudresourcemanager.googleapis.com \
  cloudbuild.googleapis.com \
  artifactregistry.googleapis.com \
  container.googleapis.com \
  clouddeploy.googleapis.com \
  storage-api.googleapis.com \
  storage.googleapis.com \
  iam.googleapis.com \
  --project ${PROJECT_ID}
```

### Create custom Service Account for Terraform

Define Service Account name under environment variable SA_NAME:
```bash
export SA_NAME=sa-terraform
```

Create Service Account:
```bash
gcloud iam service-accounts create ${SA_NAME} \
  --display-name "Terraform Admin Service Account"
```

### Grant Service Account(s) necessary roles

Run script `scripts/gcp_sa_role_assignment.sh`:
```bash
bash scripts/gcp_sa_role_assignment.sh
```

OR grant permissions from [IAM](https://console.cloud.google.com/iam-admin/iam) page in the Google Cloud console.

### Create and download JSON credentials (for Terraform Service Account only)

Define Service Account keyfile name under environment variable SA_KEYFILE_NAME:
```bash
export SA_KEYFILE_NAME=credentials
```

Create and download Service Account Key:
```bash
gcloud iam service-accounts keys create ${SA_KEYFILE_NAME}.json \
  --iam-account ${SA_NAME}@${PROJECT_ID}.iam.gserviceaccount.com
```

### Activate service account in CLI (optional)

To work with GCP Project from local CLI unders Service account, activate it
```bash
gcloud auth activate-service-account \
  ${SA_NAME}@${PROJECT_ID}.iam.gserviceaccount.com \
  --key-file=./${SA_KEYFILE_NAME}.json \
  --project=${PROJECT_ID}
```

### Connect GitHub repo to Cloud Build in GCP

<b>NOTE:</b> To configure this, you should have enough permissions in GCP project.

1. Go to [Cloud Build](https://console.cloud.google.com/cloud-build/triggers) page in the Google Cloud console.
2. On the Cloud Build Triggers page, click "Connect Repository".
3. Choose Github > Authenticate in it > select as Github account - `ORG` and repository `ORG/REPO`.
4. Mark security agreement checkbox and press "Connect".
5. Finish this process without creating triggers.
