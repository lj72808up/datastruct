# -*- coding:utf-8 -*-
class ListNode(object):
    
    def __init__(self,data=None,pred=None,succ=None):
        self.data = data
        self.pred = pred
        self.succ = succ
        
    ### 插入
    def insertAsPred(self,data):
        new_node = ListNode(data,pred=self.pred,succ=self)
        self.pred.succ = new_node
        self.pred = new_node
        return new_node
    
    def insertAsSucc(self,data):
        new_node = ListNode(data,pred=self,succ=self.succ)
        self.succ.pred = new_node
        self.succ = new_node
        return new_node

class List(object):
    def __init__(self):
        self.header = ListNode()  # 头哨兵
        self.tailer = ListNode()    # 尾哨兵
        self.header.succ = self.tailer  # 连接首尾
        self.tailer.pred = self.header
        self.header.pred = None
        self.tailer.succ = None
        self.size = 0
        
    def first(self):
        return self.header.succ
    
    def last(self):
        return self.tailer.pred
    
    def valid(self,node):
        '''判断该位置是否合法'''
        return (node is not None) and (self.tailer is not node) and (self.header is not node)
    
    def size(self):
        return self.size
    
    def empty(self):
        return self.size<=0
    
    ### 查找
    def find(self,data,n_range,node):
        '''在顺序表内的节点node的前n个真子集中, 查找元素值为data的节点'''
        while(n_range>0):
            node = node.pred
            if data==node.data:
                return node
            n_range = n_range-1
        return None
    
    ### 插入
    def insertAsFirst(self,data):
        self.size = self.size+1
        return self.header.insertAsSucc(data)
    
    def insertAsLast(self,data):
        self.size = self.size+1
        return self.tailer.insertAsPred(data)
    
    def insertA(self,node,data):
        '''后插'''
        self.size = self.size+1
        return node.insertAsSucc(data)
    
    def insertB(self,node,data):
        '''前插'''
        self.size = self.size+1
        return node.insertAsPred(data)
    
    def traverse(self,visit):
        node = self.header.succ
        while(node is not self.tailer):
            visit(node.data)
            node = node.succ
    def remove(self,node):
        node.pred.succ = node.succ
        node.succ.pred = node.pred
        self.size = self.size-1
        return node.data
