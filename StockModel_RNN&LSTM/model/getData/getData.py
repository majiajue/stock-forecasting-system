import yfinance as yf

apple = yf.Ticker("603022")

# show actions (dividends, splits)
apple.actions()

# show dividends
apple.dividends()

# show splits
apple.splits()

# + other methods etc.
print(apple.history(start="2020-06-02", end="2022-06-07"))
