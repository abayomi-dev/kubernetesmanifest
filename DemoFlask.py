from flask import Flask 
Demoapp = Flask(__name__)
@Demoapp.route("/")
def Function():
    return "HELLO GOOD MORNING FROM YOUR FLASK DEV"
if __name__ == "__main__":
    Demoapp.run(host = '0.0.0.0')