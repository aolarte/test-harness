from flask import Flask

print("Starting Flask")
app = Flask(__name__)
print("Flask App created")


from app_api import endpoints

print("Endpoints created")
