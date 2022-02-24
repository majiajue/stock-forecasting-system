from flask import Flask, request

app = Flask(__name__)


@app.route('/', methods=["GET", "POST"])
def hello_world():  # put application's code here
    if request.method == "POST":
        # print(request.headers)
        input = request.stream.read()
        input = str(input, 'utf-8')
        print(input)
        # result = ["Porsche", "Volvo", "BMW"]
        result = "123.23123, 123, 456, 789, 456, 789"
        return result
    else:
        return 'GET'


if __name__ == '__main__':
    app.run()
