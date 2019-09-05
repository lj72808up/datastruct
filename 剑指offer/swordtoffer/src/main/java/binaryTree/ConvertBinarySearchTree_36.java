package binaryTree;


import sun.reflect.generics.tree.Tree;

/**
 * 题目描述
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，只能调整树中结点指针的指向。
 */
public class ConvertBinarySearchTree_36 {
    public TreeNode Convert(TreeNode node) {
        if(node==null)
            return null;
        TreeNode[] lastNode = new TreeNode[1];
        convertNode(node,lastNode);
        TreeNode head = node;
        while (head.left!=null){
            head = head.left;
        }
        return head;
    }
    private void convertNode(TreeNode node,TreeNode[] lastNode) {
        if(node == null)
            return;
        convertNode(node.left,lastNode);

        if(lastNode[0]!=null)
            lastNode[0].right = node;
        node.left = lastNode[0];
//        System.out.println(node);
        lastNode[0] = node;

        convertNode(node.right,lastNode);

    }

    public static void main(String[] args) {
        TreeNode a = new TreeNode(10);
        TreeNode b = new TreeNode(5);
        TreeNode c = new TreeNode(12);
        TreeNode d = new TreeNode(4);
        TreeNode e = new TreeNode(6);

        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;

//        new ConvertBinarySearchTree_36().convertNode(a,new TreeNode[1]);
        TreeNode root = new ConvertBinarySearchTree_36().Convert(a);
        while (root!=null){
            System.out.println(root);
            root = root.right;
        }
    }
}

/**
 * 思路:
 *  修改二叉树中序遍历算法, 将打印语句, 换成调整数结构的语句维护左右指针, 并将lastNode赋值成自己
 * */