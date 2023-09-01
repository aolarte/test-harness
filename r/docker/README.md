# R in Docker

## Running from Command Line


    Rscript test.R

## Container

    curl -o cloud-sql-proxy https://storage.googleapis.com/cloud-sql-connectors/cloud-sql-proxy/v2.6.1/cloud-sql-proxy.linux.amd64

    docker build -t us-central1-docker.pkg.dev/`gcloud config get-value project`/docker/rtest .
    docker push us-central1-docker.pkg.dev/`gcloud config get-value project`/docker/rtest
    docker run -it us-central1-docker.pkg.dev/`gcloud config get-value project`/docker/rtest

## Cloud RUN


Apply the terraform:

    export TF_VAR_image_id=us-central1-docker.pkg.dev/`gcloud config get-value project`/docker/rtest
    terraform apply





### Local dev

    sudo apt-get update
    sudo apt-get install -y r-base r-base-dev


    R 


    install.packages("littler")
    install.packages("DBI")
    install.packages("RPostgreSQL")

