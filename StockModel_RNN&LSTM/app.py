import tensorflow as tf

global graph
graph = tf.get_default_graph()

import json
import flask
from flask import Flask, request
from keras.models import load_model

from model.predict import predict_future

app = Flask(__name__)

model = load_model("./model/model.h5")

@app.route('/', methods=["GET", "POST"])
def hello_world():  # put application's code here

    if request.method == "POST":
        input = request.stream.read()
        input = json.loads(input)
        print("输入：" + input["data"])

        with graph.as_default():
            result, _ = predict_future(model, input["data"], predictDate=input["predictDate"])

        respond = dict()
        respond.setdefault('data', result)
        return flask.jsonify(respond)
    else:
        return 'GET'


if __name__ == '__main__':

    app.run()
