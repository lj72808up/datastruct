# -*- coding: UTF-8 -*-
class BinNode(object):
    def __init__(self, data, parent=None, lc=None, rc=None, height=0,):
        self.data = data
        self.parent = parent
        self.height = height
        self.lc = lc
        self.rc = rc

    def __eq__(self, other):
        return self.data == other.data

    def __lt__(self, other):
        return self.data < other.data
    
    def insert_aslchild(self,data):
        new_node = BinNode(data,self)
        self.lc = new_node
        return new_node
    
    def insert_asrchild(self,data):
        new_node = BinNode(data,self)
        self.rc = new_node
        return new_node

class BinTree(object):
    def __init__(self):
        self.size = 0
        self.root = None
        
    def empty(self):
        return self.root is None
    
    def __eq__(self,other):
        return (self.root is not None) and (other.root is not None) and (self.root is other.root)
    
    def stature(self,node):
        return -1 if node is None else node.height
    
    def update_height(self,node):
        node.height = 1 + max(self.stature(node.lc), self.stature(node.rc))
        return node.height
    
    def update_height_above(self,node):
        while node is not None:
            self.update_height(node)
            node = node.parent
            
    def insert_root(self,data):
        self.size = 1
        self.root = BinNode(data)
        return self.root
    
    def insert_as_lc(self, node,data):
        self.size = self.size + 1
        lc = node.insert_aslchild(data)
        self.update_height_above(node)
        return lc
    
    def insert_as_rc(self, node , data):
        self.size = self.size + 1
        rc = node.insert_asrchild(data)
        self.update_height_above(node)
        return rc
    
    def attach_as_lc(self, node,other_tree):
        '''node: 该二叉树中的节点
           other_tree : 作为子树接入该二叉树. 接入点为node节点的左孩子'''
        node.lc = other_tree.root
        other_tree.root.parent = node
        self.size = self.size + other_tree.size
        self.update_height_above(node)
        return node
    
    def attach_as_rc(self, node,other_tree):
        '''node: 该二叉树中的节点
           other_tree : 作为子树接入该二叉树. 接入点为node节点的右孩子'''
        node.rc = other_tree.root
        other_tree.root.parent = node
        self.size = self.size + other_tree.size
        self.update_height_above(node)
        return node
    
    def remove(self,node):
        '''删除该二叉树中节点node及其后代'''
        n = self.removeAt(node)
        
        BinNodeUtil.from_parent_to(node,None)
        self.update_height_above(node.parent)
        self.size = self.size - n
        return n
        
    def removeAt(self,node):
        '''删除节点node及其后代'''
        if node is None:
            return 0
        n = 1 + self.removeAt(node.lc) + self.removeAt(node.rc)
        node = None 
        return n
    
class BinNodeUtil(object):
    @staticmethod
    def is_root(node):
        return not node.parent
    @staticmethod
    def is_lchild(node):
        return (not BinNodeUtil.is_root(node))&(node.parent.lc is node)
    @staticmethod
    def is_rchild(node):
        return (not BinNodeUtil.is_root(node))&(node.parent.rc is node)
    @staticmethod
    def has_parent(node):
        return not BinNodeUtil.is_root(node)
    @staticmethod
    def has_lchild(node):
        return not node.lc is None
    @staticmethod
    def has_rchild(node):
        return not node.rc is None
    @staticmethod
    def has_child(node):
        return BinNodeUtil.has_lchild(node) or BinNodeUtil.has_rchild(node)
    @staticmethod
    def has_bothchild(node):
        return BinNodeUtil.has_lchild(node) & BinNodeUtil.has_rchild(node)
    @staticmethod
    def is_leaf(node):
        return not BinNodeUtil.has_child(node)
    @staticmethod
    def sibling(node):
        try:
            return  node.parent.rc if BinNodeUtil.is_lchild(node) else node.parent.lc
        except  AttributeError:
            return None
    @staticmethod
    def uncile(node):
        try:
            parent = node.parent
            return parent.parent.rc if BinNodeUtil.is_lchild(parent) else parent.parent.lc
        except  AttributeError:
            return None
    @staticmethod
    def from_parent_to(node,value=None):
        '''来自父亲的引用'''
        if (BinNodeUtil.is_lchild(node)):
            node.parent.lc = None
        if (BinNodeUtil.is_rchild(node)):
            node.parent.rc = None
    @staticmethod
    def stature(node):
        return -1 if node is None else node.height
    
class  BinTreeUtility(object):
    @staticmethod
    def travIn_R(node , visit):
        if node is None : 
            return 
        BinTreeUtility.travIn_R(node.lc , visit)
        visit (node.data)
        BinTreeUtility.travIn_R(node.rc , visit)
        
    @staticmethod
    def travLevel(node,visit):
        queue = [node]
        while len(queue)>0 :
            top_node = queue[0]  # 取队列第一个元素
            queue = queue[1:]
            visit (top_node.data)
            if top_node.lc is not None : 
                queue.append(top_node.lc)
            if top_node.rc is not None : 
                queue.append(top_node.rc)
                
    @staticmethod
    def succ(node):
        if node.rc is not None :
            node_now = node.rc
            while node_now.lc is not None:
                node_now = node_now.lc
        else : 
            node_now = node
            while BinNodeUtil.is_rchild(node_now):  # 一直向左上方提升
                node_now = node_now.parent 
            node_now = node_now.parent 
        return node_now
            
