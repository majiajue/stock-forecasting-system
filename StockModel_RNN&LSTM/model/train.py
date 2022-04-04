import warnings

import numpy as np

warnings.filterwarnings("ignore")

from keras.layers import Conv1D, Bidirectional, Multiply, MaxPooling1D
from keras.layers.core import *
from keras.layers.recurrent import LSTM
from keras.models import *
import tushare as ts

from model.util import NormalizeMult, create_dataset


def attention_3d_block(inputs, SINGLE_ATTENTION_VECTOR=False):
    input_dim = int(inputs.shape[2])
    a = inputs
    # a = Permute((2, 1))(inputs)

    # 将输入进行一次变换后，计算出Attention权重，将输入乘上Attention权重，获得新的特征。
    a = Dense(input_dim, activation='softmax')(a)
    if SINGLE_ATTENTION_VECTOR:
        a = Lambda(lambda x: K.mean(x, axis=1), name='dim_reduction')(a)
        a = RepeatVector(input_dim)(a)
    a_probs = Permute((1, 2), name='attention_vec')(a)

    # output_attention_mul = merge([inputs, a_probs], name='attention_mul', mode='mul')
    output_attention_mul = Multiply()([inputs, a_probs])
    return output_attention_mul

s
# 注意力机制的另一种写法，适合上述报错使用。
# 借鉴:https://blog.csdn.net/weixin_41888257/article/details/114888547
# https://github.com/philipperemy/keras-attention-mechanism
def attention_3d_block2(inputs, SINGLE_ATTENTION_VECTOR=False):
    time_steps = K.int_shape(inputs)[1]
    input_dim = K.int_shape(inputs)[2]
    a = Permute((2, 1))(inputs)
    a = Dense(time_steps, activation='softmax')(a)
    if SINGLE_ATTENTION_VECTOR:
        a = Lambda(lambda x: K.mean(x, axis=1))(a)
        a = RepeatVector(input_dim)(a)

    a_probs = Permute((2, 1))(a)
    # 乘上了attention权重，但是并没有求和，好像影响不大?
    output_attention_mul = Multiply()([inputs, a_probs])
    return output_attention_mul


# 函数式 API。中文文档：https://keras.io/zh/models/model/
def attention_model():
    inputs = Input(shape=(TIME_STEPS, INPUT_DIMS))

    # 特征提取
    x = Conv1D(filters=64, kernel_size=2, activation='relu')(inputs)  # , padding = 'same'
    x = Dropout(0.3)(x)
    x = MaxPooling1D(pool_size=2, strides=None, padding='valid')(x)

    # 时序分析
    lstm_out = Bidirectional(LSTM(lstm_units, return_sequences=True))(x)
    lstm_out = Dropout(0.3)(lstm_out)
    # lstm_out = Bidirectional(LSTM(lstm_units, return_sequences=True))(x)
    # lstm_out = Dropout(0.3)(lstm_out)
    # lstm_out.shape: (batch_size, time_steps, input_dim)

    # 注意力机制
    attention_mul = attention_3d_block2(lstm_out, SINGLE_ATTENTION_VECTOR=True)

    attention_mul = Flatten()(attention_mul)
    output = Dense(6, activation='sigmoid')(attention_mul)
    _model = Model(inputs=[inputs], outputs=output)
    return _model


def getData():
    ts.set_token('0552d7adcfe8c321bd512ea8e7ff66c69375a9aeeb567fbfc7440cd4')

    # 获取数据连接
    ts.set_token('0552d7adcfe8c321bd512ea8e7ff66c69375a9aeeb567fbfc7440cd4')
    pro = ts.pro_api()
    # 查询当前所有正常上市交易的股票列表
    # SH沪股通SZ深股通
    df = pro.hs_const(hs_type='SH')
    df = df[0:1]

    dataset = np.empty((0, 6), dtype=float)
    for name in np.array(df['ts_code']):
        data = pro.daily(ts_code=name, start_date='20000101', end_date='20220101')
        col = [column for column in data]
        data = data.drop(['ts_code', 'trade_date', 'high', 'low', 'pre_close'], axis=1)
        dataset = np.concatenate((dataset, data), axis=0)

    return np.array(dataset)


if __name__ == '__main__':
    # 加载数据
    data = getData()
    print(data.shape)

    INPUT_DIMS = 6
    OUTPUT_DIMS = 6
    TIME_STEPS = 40
    lstm_units = 32

    # 归一化
    data, normalize = NormalizeMult(data)
    train_X, train_Y = create_dataset(data, TIME_STEPS)

    model = attention_model()
    model.summary()
    model.compile(optimizer='adam', loss='mse')
    model.fit([train_X], train_Y, epochs=25, batch_size=64, validation_split=0.1)

    # 保存结果
    model.save("model.h5")
    np.save("normalize.npy", normalize)
