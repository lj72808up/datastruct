# -*- coding: UTF-8 -*-

# 我们要构造轴点,然后以轴点左侧和右侧形成的列表各自进行排序
def quickSort(l, low, high):
    # 单个元素自然有序
    if (high - low <= 1):
        return
    pivlot = findPivlot(l, low, high)
    # 轴点的位置,满足排序前和排序后所处位置不变
    quickSort(l, low, pivlot)
    quickSort(l, pivlot + 1, high)


def findPivlot(l, low, high):
    # 选取首元素为待构造的轴点
    m = l[low]
    while (low < high):
        while (l[high] >= m and low<high):
            high = high - 1
        l[low] = l[high]
        while (l[low] <= m and low<high):
            low = low + 1
        l[high] = l[low]
    # 循环结束时,low==high
    l[low] = m
    return low

if __name__=="__main__":
    l = [12,3,4,1,6,8,4]
    quickSort(l,0,len(l)-1)
    print l