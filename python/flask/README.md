# Python app

## Virtual Environment

    virtualenv venv
    source venv/bin/activate
    pip install -r requirements.txt
    python ./index.py

## Docker

To build:

    docker build -t="python-flask-test" .

To run:

    docker run -d -p 5000:5000 --name python-flask-test python-flask-test

## KNative build with Helm

To verify the output:

    helm install --dry-run --debug --set project=`gcloud config list --format 'value(core.project)' 2>/dev/null` ./build-chart

To install:

    helm install  --set project=`gcloud config list --format 'value(core.project)' 2>/dev/null` ./build-chart

To check the logs of the build steps:

    kubectl logs `kubectl get pods | grep python-flask-test-build-pod | awk '{print $1}'` -c build-step-git
    kubectl logs `kubectl get pods | grep python-flask-test-build-pod | awk '{print $1}'` -c build-step-build-and-push

## KNative deploy with Helm

To verify the output:

    helm install --dry-run --debug --set gateway=true,image.project=`gcloud config list --format 'value(core.project)' 2>/dev/null` ./deploy-chart

To deploy with Istio Gateway enabled:

    kubectl create ns python
    kubectl label namespace python istio-injection=enabled

To install:

    helm --namespace python install --set gateway=true,image.project=`gcloud config list --format 'value(core.project)' 2>/dev/null` ./deploy-chart

To upgrade:

    helm upgrade --set gateway=true,image.project=`gcloud config list --format 'value(core.project)' 2>/dev/null` [RELEASE] ./deploy-chart

To test the deployment:

    helm test [RELEASE]

To clean up:

    helm delete [RELEASE]
