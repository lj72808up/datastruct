package binaryTree;

/**
 * 题目描述
 * 请实现一个函数，用来判断一颗二叉树是不是对称的。注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
 */
public class Symmetrical_28 {
    boolean isSymmetrical(TreeNode pRoot) {

        if(pRoot==null) {
            return true;
        }

        if(pRoot.left!=null && pRoot.right!=null){
            if(pRoot.left.val==pRoot.right.val)
                return isSymmetrical(pRoot.left) && isSymmetrical(pRoot.right);
            else
                return false;
        }else if(pRoot.left==null && pRoot.right==null) {
            return true;
        }else {
            return false;
        }
    }
}
