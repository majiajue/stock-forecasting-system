# 这是一个示例 Python 脚本。

# 按 Shift+F10 执行或将其替换为您的代码。
# 按 双击 Shift 在所有地方搜索类、文件、工具窗口、操作和设置。

import numpy as np
import pandas as pd
import tensorflow as tf
from sklearn.preprocessing import MinMaxScaler
from tensorflow.python.keras import Sequential
from tensorflow.python.keras.layers import Dense, LSTM, Dropout


def pre_precess():
    data = pd.read_csv('./data/GOOG.csv', date_parser=True)
    data_training = data[data['Date'] < '2019-01-01'].copy()
    # data_test = data[data['Date'] >= '2019-01-01'].copy()
    data_training = data_training.drop(['Date', 'Adj Close'], axis=1)
    scaler = MinMaxScaler()
    data_training = scaler.fit_transform(data_training)

    X_train = []
    y_train = []
    for i in range(60, data_training.shape[0]):
        X_train.append(data_training[i - 60:i])
        y_train.append(data_training[i, 0])
    return np.array(X_train), np.array(y_train)


def creat_model(in_shape):
    regressior = Sequential()

    regressior.add(LSTM(units=60, activation='relu', return_sequences=True, input_shape=(in_shape, 5)))
    regressior.add(Dropout(0.2))

    regressior.add(LSTM(units=60, activation='relu', return_sequences=True))
    regressior.add(Dropout(0.2))

    regressior.add(LSTM(units=80, activation='relu', return_sequences=True))
    regressior.add(Dropout(0.2))

    regressior.add(LSTM(units=120, activation='relu'))
    regressior.add(Dropout(0.2))

    regressior.add(Dense(units=1))
    regressior.compile(optimizer='adam', loss='mean_squared_error')

    return regressior


# 按间距中的绿色按钮以运行脚本。
if __name__ == '__main__':
    X_train, y_train = pre_precess()

    model = creat_model(X_train.shape[1])
    model.fit(X_train, y_train, epochs=2, batch_size=32, verbose=1)

    model.save("stock_model.h5")


# 访问 https://www.jetbrains.com/help/pycharm/ 获取 PyCharm 帮助
