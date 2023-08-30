# R in Docker

## Running from Command Line

    R CMD BATCH test.R
    cat test.Rout

or:

    Rscript test.R

## Container

    docker build -t us-central1-docker.pkg.dev/`gcloud config get-value project`/docker/rtest .
    docker push us-central1-docker.pkg.dev/`gcloud config get-value project`/docker/rtest
    docker run -it us-central1-docker.pkg.dev/`gcloud config get-value project`/docker/rtest

## Cloud RUN

    export TF_VAR_image_id=us-central1-docker.pkg.dev/`gcloud config get-value project`/docker/rtest
    terraform apply