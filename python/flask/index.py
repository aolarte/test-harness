from flask import Flask
from flask import abort

app = Flask(__name__)
@app.route("/")
def hello():
    return "Hello World!"
@app.route("/ret/<code>")
def ret(code):
    abort(int(code))
if __name__ == "__main__":
    app.run(host="0.0.0.0", port=int("5000"), debug=True)
