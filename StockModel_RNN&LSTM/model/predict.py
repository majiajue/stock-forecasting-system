import warnings

warnings.filterwarnings("ignore")

from model.util import create_dataset, FNormalizeMult, FNormalizeMultY, NormalizeMult

import tushare as ts
import matplotlib.pyplot as plt
import numpy as np
from keras.models import load_model


def predict(model, tsCode, startDate='20200101'):
    # 获取数据
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
    _result = model.predict(data)

    # 反归一化
    _result = FNormalizeMult(_result, normalize)
    _real = FNormalizeMult(_real, normalize)

    return _result, _real


def predict_future(model, tsCode, startDate='20220101', predictDate=30):
    # 获取数据
    ts.set_token('0552d7adcfe8c321bd512ea8e7ff66c69375a9aeeb567fbfc7440cd4')
    pro = ts.pro_api()
    data = pro.daily(ts_code=tsCode, start_date=startDate)
    data = data.drop(['ts_code', 'trade_date', 'high', 'low', 'pre_close'], axis=1)
    data = np.array(data)

    # 归一化数据
    data, normalize = NormalizeMult(data)

    data, _ = create_dataset(data, 40)
    data = np.array(data)
    _allResult = np.array([])

    # 预测结果
    for i in range(predictDate):
        _result = model.predict(data[-1:, :, :])
        _allResult = np.append(_allResult, _result)
        # print(type(data[:1, :, :]))
        temp = data[-1:, :, :][0]
        temp = np.insert(temp, temp.shape[0], _result, axis=0)
        temp = temp[1:, :]

        data = np.insert(data, data.shape[0], temp, axis=0)

    _allResult = _allResult.reshape(predictDate, 6)
    # 反归一化
    _result = FNormalizeMult(_allResult, normalize)

    return _result, _


if __name__ == '__main__':
    # 加载需要的数据
    # sh = 上证指数；sz = 深圳成指；hs300 = 沪深300指数；sz50 = 上证50
    # normalize = np.load("normalize.npy")
    model = load_model("model.h5")
    tsCode = "601058.SH"
    predictData = 500

    # 获得预测数据
    result, real = predict_future(model, tsCode, predictDate=predictData)

    # plt可视化
    plt.figure(figsize=(14, 5))
    # plt.plot(real[:, 1], color='red', label='Real ' + tsCode + ' Price')
    plt.plot(result[:, 1], color='blue', label='Predicted ' + tsCode + ' Price')
    plt.title('Stock Price Prediction')
    plt.xlabel('Time')
    plt.ylabel('Stock Price')
    plt.legend()
    plt.show()
