package binaryTree;


/**
 * 题目描述
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，只能调整树中结点指针的指向。
 */
public class ConvertBinarySearchTree_36 {


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

    }
}
