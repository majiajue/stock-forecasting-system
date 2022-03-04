import warnings

from keras.layers import Conv1D, Bidirectional, Multiply
from keras.layers.core import *
from keras.layers.recurrent import LSTM
from keras.models import *

warnings.filterwarnings("ignore")

# 多维归一化  返回数据和最大最小值
def NormalizeMult(_data):
    # normalize 用于反归一化
    _data = np.array(_data)
    _normalize = np.arange(2 * _data.shape[1], dtype='float64')
    _normalize = _normalize.reshape(_data.shape[1], 2)
    for col in range(0, _data.shape[1]):
        dataCol = _data[:, col]
        listLow, listHigh = np.percentile(dataCol, [0, 100])

        _normalize[col, 0] = listLow
        _normalize[col, 1] = listHigh
        delta = listHigh - listLow
        if delta != 0:
            # 第j行
            for row in range(0, _data.shape[0]):
                _data[row, col] = (_data[row, col] - listLow) / delta

    return _data, _normalize


# 多维反归一化
def FNormalizeMult(_data, normalize):
    _data = np.array(_data)
    for col in range(0, _data.shape[1]):
        listLow = normalize[col, 0]
        listHigh = normalize[col, 1]
        delta = listHigh - listLow

        if delta != 0:
            for row in range(0, _data.shape[0]):
                _data[row, col] = _data[row, col] * delta + listLow

    return _data


# 多维反归一化
def FNormalizeMultY(_data, normalize):
    _data = np.array(_data)
    for col in range(0, 1):
        listLow = normalize[col, 0]
        listHigh = normalize[col, 1]
        delta = listHigh - listLow

        if delta != 0:
            for row in range(0, _data.shape[0]):
                _data[row, col] = _data[row, col] * delta + listLow

    return _data

def create_dataset(dataset, look_back=40):
    X = []
    Y = []
    # eg：通过1~60天数据，预测61天的开盘价
    for i in range(look_back, dataset.shape[0]):
        X.append(dataset[i - look_back:i])
        # 取开盘价作为y轴
        Y.append(dataset[i, 0])

    return np.array(X), np.array(Y)

