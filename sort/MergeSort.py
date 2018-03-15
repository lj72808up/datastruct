# -*- coding: UTF-8 -*-

# l:待排序列表,用角标,截取出待排列表
def mergeSort(l,low,high):
    # 单个元素自然有序
    if(high-low<=1):
        return
    mid = (low+high)>>1
    mergeSort(l,low,mid)
    mergeSort(l,mid,high)
    merge(l,low,mid,high)


# 排序两个有序列表
# 两个有序列表为l[low,mid]和l[mid,high]
# 以下python的列表截取和赋值操作,都是基于深copy
# python的参数传递都是对象的引用
def merge(l,low,mid,high):
    # 第一个列表为l[low:mid]
    # 第二个列表为l[mid:high]
    # 以下i,j分别记录列表的起始位置
    i = low
    j = mid
    combineList = []
    for k in range(low,high):
        # 如果l1已经用完,l2还有元素,则直接并入l2
        if(i==mid and j<high):
            combineList.append(l[j]);j=j+1
        # 如果l2已经用完,l1还有元素,则直接并入l1
        elif(j==high and i<mid):
            combineList.append(l[i]);i=i+1
        # 如果l1和l2都还有元素,就比较首元素大小
        else:
            if(l[i]<=l[j]):
                combineList.append(l[i]);i = i+1
            else:
                combineList.append(l[j]);j = j+1
    l[low:high] = combineList


if __name__=="__main__":
    l = [5,2,1,8,4,0]
    mergeSort(l,0,len(l))
    print l