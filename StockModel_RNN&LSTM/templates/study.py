import numpy as np
a = np.arange(12).reshape((3, 4))
b1 = np.arange(100, 104)

print(b1)
# [100 101 102 103]

print(np.insert(a, a.shape[0], b1, axis=0))
# [[  0   1   2   3]
#  [100 101 102 103]
#  [  4   5   6   7]
#  [  8   9  10  11]]

print(np.insert(a, 3, b1, axis=0))
# [[  0   1   2   3]
#  [  4   5   6   7]
#  [  8   9  10  11]
#  [100 101 102 103]]

print(np.insert(a, [0, 2], b1, axis=0))
# [[100 101 102 103]
#  [  0   1   2   3]
#  [  4   5   6   7]
#  [100 101 102 103]
#  [  8   9  10  11]]
