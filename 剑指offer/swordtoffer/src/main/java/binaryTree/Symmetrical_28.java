package binaryTree;

/**
 * 题目描述
 * 请实现一个函数，用来判断一颗二叉树是不是对称的。注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
 */
public class Symmetrical_28 {
    boolean isSymmetrical(TreeNode pRoot) {
        return isSym(pRoot,pRoot);
    }

    private boolean isSym(TreeNode root1,TreeNode root2){
        if (root1==null && root2==null)
            return true;
        if (root1==null || root2==null)
            return false;
        if (root1.val!=root2.val)
            return false;
        else
            return isSym(root1.left,root2.right) && isSym(root1.right,root2.left);
    }
}

/**
 * 思路:
 *  树是否对称 :
 *    从root节点开始,
 *    a. 2个节点的val是否一致
 *    b. 对比左孩子的左孩子和右孩子的右孩子
 * */
