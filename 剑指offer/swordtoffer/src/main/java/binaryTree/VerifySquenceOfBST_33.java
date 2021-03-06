package binaryTree;

/**
 * 题目描述
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
 */
public class VerifySquenceOfBST_33 {
    public boolean VerifySquenceOfBST(int [] sequence) {
        return false;
    }
    public boolean VerifySquenceOfBST(int [] sequence,int start,int end) {

        return false;
    }
}

/**
 * 思路:
 *  (1) 平衡二叉树, 左子树<root节点<右子树
 *  (2) 后序遍历, root节点在遍历数组的最后一位; 可以根据元素值的大小区分左右子树:
 *          如果右子树存在比root小的节点, 则不是平衡二叉树
 * */
