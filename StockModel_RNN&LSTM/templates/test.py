import numpy as np
import pymysql.cursors
import tushare as ts

# 获取数据连接
ts.set_token('0552d7adcfe8c321bd512ea8e7ff66c69375a9aeeb567fbfc7440cd4')
pro = ts.pro_api()
# 查询当前所有正常上市交易的股票列表

# 获取沪股通成分
# SH沪股通SZ深股通
df = pro.hs_const(hs_type='SH')
for name in np.array(df['ts_code']):
    print(name)
