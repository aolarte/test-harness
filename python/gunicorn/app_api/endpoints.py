from app_api import app

@app.route("/")
def hello():
    return "Hello World!"