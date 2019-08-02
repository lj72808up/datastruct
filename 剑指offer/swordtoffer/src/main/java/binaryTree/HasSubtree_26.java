package binaryTree;

/**
 * 题目描述
 * 输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
 */
public class HasSubtree_26 {
    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        if (root1==null || root2==null)
            return false;
        boolean res = false;
        if(root1.val == root2.val && isEqualTree(root1,root2)){
            res = true;
        }else{
            if(isEqualTree(root1.left,root2))
                res = true;
            else
                res = isEqualTree(root1.right,root2);
        }
        return res;
    }


    private boolean isEqualTree(TreeNode root1,TreeNode root2){
        if(root2==null)   // root2已经比较完毕
            return true;
        if ((root1==null && root2!=null)  || root1.val!=root2.val){   // root1以比较完毕, root2还未比较完毕
            return false;
        }else{
            return isEqualTree(root1.left,root2.left) && isEqualTree(root1.right,root2.right);

        }
    }
}

/**
 * 思路:
 *  1. 首先, 想如何判断root2是另一个子树root1的子树: (func1)
 *      如果2个子树的root节点值相同, 要看其左子树,右子树的值是否相同, 若都相同, 则递归到底后是相同的子树
 *  2. 其次, 对于一个完整的树root1, 对其实施遍历
 *      如果遍历到的节点值与root2的节点值相同, 则开始实施上面的func1, 查看是否以该节点为根的子树包含root2
 *      如果遍历到的节点值与root2的节点值不同, 则继续比较这个节点的左右孩子, 找到节点值与root2节点值相同的点, 再进行func1的判断
 * 注意:
 *   浮点数的表示在计算机中存在误差, 以为对于小数来讲,二进制并不能意义对应十进制(例如二进制的小数只能表示2的-n次方)
 *   在计算机中(1-0.99==0.01)这个判别式会返回false, 因为1-0.99=0.010000000000000009
 */