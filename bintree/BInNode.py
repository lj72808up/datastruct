# -*- coding: UTF-8 -*-
class BinNode(object):
    def __init__(self, data, parent=None, lc=None, rc=None):
        self.data = data
        self.parent = parent
        self.lc = lc
        self.rc = rc

    def __eq__(self, other):
        return self.data == other.data

    def __lt__(self, other):
        return self.data < other.data


if __name__ == "__main__":
    n = BinNode(2, 3, 7)
    m = BinNode(1, 2, 6)
    print m<n