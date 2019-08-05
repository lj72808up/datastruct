package binaryTree;

/**
 * 题目描述
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
 */
public class VerifySquenceOfBST_33 {
    public boolean VerifySquenceOfBST(int [] sequence) {
        return VerifySquenceOfBST2(sequence,0,sequence.length-1);
    }
    private boolean VerifySquenceOfBST2(int[] sequence,int start,int end){
        int root = sequence[end];
        int leftEndIndex = findLeft(sequence,start,end,root);
        int rightStartIndex = leftEndIndex==-1? start:leftEndIndex+1;
        boolean isBSTRight = isBSTRightChild(sequence,rightStartIndex,end,root);
        if (!isBSTRight)
            return false;
        else{
            boolean leftFlag = true;
            boolean rightFlag = true;
            if (leftEndIndex>start)
                leftFlag = VerifySquenceOfBST2(sequence,start,leftEndIndex);
            if (rightStartIndex<end)
                rightFlag = VerifySquenceOfBST2(sequence,rightStartIndex,end);
            return leftFlag && rightFlag;
        }


    }

    /** 找到左子树的最后一个节点
     */
    private int findLeft(int[] sequence,int start,int end,int root){
        int leftIndex = -1;
        for (int i = start; i <= end; i++) {
            if(sequence[i]<root)
                leftIndex=i;
            else
                break;
        }
        return leftIndex;
    }

    /** 判断右子树是否满足平衡
     */
    private boolean isBSTRightChild(int[] sequence,int start,int end,int root){
        for (int i = start; i <= end; i++) {
            if(sequence[i]<=end)
                return false;
        }
        return true;
    }
}

/**
 * 思路:
 *  (1) 平衡二叉树, 左子树<root节点<右子树
 *  (2) 后序遍历, root节点在遍历数组的最后一位; 可以根据元素值的大小区分左右子树:
 *          如果右子树存在比root小的节点, 则不是平衡二叉树
 * */
