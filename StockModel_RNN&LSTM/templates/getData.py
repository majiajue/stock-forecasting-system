import sys

import numpy as np
import pymysql.cursors
import tushare as ts

np.set_printoptions(threshold=sys.maxsize)

# 获取数据连接
ts.set_token('0552d7adcfe8c321bd512ea8e7ff66c69375a9aeeb567fbfc7440cd4')
pro = ts.pro_api()
# 查询当前所有正常上市交易的股票列表

# 获取沪股通成分
# SH沪股通SZ深股通
df = pro.hs_const(hs_type='SH')
df = df[-400:]

# 获取数据库连接
db = pymysql.connect(
    host='192.168.100.240',
    port=3306,
    user='test',
    password='Hk@datanbiz123',
    db='test_app'
)

# 遍历并拿到data数据
for value in df["ts_code"]:

    data = pro.daily(ts_code=value, start_date="20000101")

    col = []
    for column in data:
        col.append(column)

    data = np.array(data)

    # 使用cursor()方法获取操作游标
    cursor = db.cursor()

    for row in data:
        sql = "INSERT INTO stock_data (" + col[0] + ", " + col[1] + ", " + col[2] + ", " + col[3] + ", " + col[
            4] + ", " + \
              col[5] + ", " + col[6] + ", `" + col[7] + "`, " + col[8] + ", " + col[9] + ", " + col[
                  10] + ") VALUES ('%s', '%s',  %s,  %s,  %s,  %s,  %s,  %s,  %s,  %s,  %s)" % (
                  row[0], row[1], row[2], row[3], row[4], row[5], row[6], row[7], row[8], row[9], row[10])
        print(sql)
        try:
            # 执行sql语句
            cursor.execute(sql)
            # 执行sql语句
            db.commit()
        except:
            # 发生错误时回滚
            db.rollback()
            print("出错")

# 关闭数据库连接
db.close()
