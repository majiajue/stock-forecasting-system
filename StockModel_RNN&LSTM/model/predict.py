import numpy as np
import matplotlib.pyplot as plt
import pandas as pd
from sklearn.preprocessing import MinMaxScaler
from tensorflow.python.keras import Sequential
from tensorflow.python.keras.layers import Dense, LSTM, Dropout
from tensorflow.python.keras.models import load_model

if __name__ == '__main__':

    data = pd.read_csv('GOOG.csv', date_parser=True)
    data_training = data[data['Date'] < '2019-01-01'].copy()
    data_test = data[data['Date'] >= '2019-01-01'].copy()

    data_training = data_training.drop(['Date', 'Adj Close'], axis=1)
    scaler = MinMaxScaler()
    # print(type(data_training))
    data_training = scaler.fit_transform(data_training)
    # print(type(data_training))

    data_training = data[data['Date'] < '2019-01-01'].copy()
    past_60_days = data_training.tail(60)
    df = past_60_days.append(data_test, ignore_index=True)
    df = df.drop(['Date', 'Adj Close'], axis=1)

    inputs = scaler.transform(df)

    X_test = []
    y_test = []

    for i in range(60, inputs.shape[0]):
        X_test.append(inputs[i - 60:i])
        y_test.append(inputs[i, 0])

    X_test, y_test = np.array(X_test), np.array(y_test)
    X_test.shape, y_test.shape

    model = load_model("stock_model")
    y_pred = model.predict(X_test)
    scale = 1 / 8.18605127e-04

    y_pred = y_pred * scale
    y_test = y_test * scale

    # Visualising the results
    plt.figure(figsize=(14, 5))
    plt.plot(y_test, color='red', label='Real Google Stock Price')
    plt.plot(y_pred, color='blue', label='Predicted Google Stock Price')
    plt.title('Google Stock Price Prediction')
    plt.xlabel('Time')
    plt.ylabel('Google Stock Price')
    plt.legend()
    plt.show()
