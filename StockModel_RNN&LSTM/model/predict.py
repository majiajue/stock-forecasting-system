import warnings
warnings.filterwarnings("ignore")

from model.util import create_dataset, FNormalizeMult, FNormalizeMultY, NormalizeMult

import tushare as ts
import matplotlib.pyplot as plt
import numpy as np
from keras.models import load_model


def predict(model, tsCode):
    # 获取2022年之后的数据
    ts.set_token('0552d7adcfe8c321bd512ea8e7ff66c69375a9aeeb567fbfc7440cd4')
    pro = ts.pro_api()
    data = pro.daily(ts_code=tsCode, start_date='20200101')
    data = data.drop(['ts_code', 'trade_date', 'high', 'low', 'pre_close'], axis=1)
    data = np.array(data)

    # 归一化数据
    data, normalize = NormalizeMult(data)

    data, _real = create_dataset(data, 40)
    data = np.array(data)
    _real = np.array(_real)

    # 预测结果
    _result = model.predict(data)

    # 反归一化
    listLow, listHigh = normalize[0, 0], normalize[0, 1]
    delta = listHigh - listLow

    if delta != 0:
        for row in range(0, data.shape[0]):
            _result[row, 0] = _result[row, 0] * delta + listLow
            _real[row] = _real[row] * delta + listLow

    return _result, _real


def predict_future(model, tsCode, startDate='20211001', predictDate=30):

    # 获取2022年之后的数据
    ts.set_token('0552d7adcfe8c321bd512ea8e7ff66c69375a9aeeb567fbfc7440cd4')
    pro = ts.pro_api()
    data = pro.daily(ts_code=tsCode, start_date=startDate)
    data = data.drop(['ts_code', 'trade_date', 'high', 'low', 'pre_close'], axis=1)
    data = np.array(data)

    # 归一化数据
    data, normalize = NormalizeMult(data)

    data, _real = create_dataset(data, 40)
    data = np.array(data)
    _real = np.array(_real)

    # 预测结果
    print(data.shape)
    _result = model.predict(data)
    for i in range(predictDate):
        input = [_result[:40]]
        input = np.array(input)
        print(input.shape)

    # 反归一化
    listLow, listHigh = normalize[0, 0], normalize[0, 1]
    delta = listHigh - listLow

    if delta != 0:
        for row in range(0, data.shape[0]):
            _result[row, 0] = _result[row, 0] * delta + listLow
            _real[row] = _real[row] * delta + listLow

    return _result, _real


if __name__ == '__main__':
    # 加载需要的数据
    # sh = 上证指数；sz = 深圳成指；hs300 = 沪深300指数；sz50 = 上证50
    model = load_model("model.h5")
    normalize = np.load("normalize.npy")
    tsCode = "603919.SH"

    # 获得预测数据
    result, real = predict_future(model, tsCode)

    # plt可视化
    plt.figure(figsize=(14, 5))
    plt.plot(real, color='red', label='Real Google Stock Price')
    plt.plot(result, color='blue', label='Predicted Google Stock Price')
    plt.title('Stock Price Prediction')
    plt.xlabel('Time')
    plt.ylabel('Stock Price')
    plt.legend()
    plt.show()
