package binaryTree;

/**
 * 题目描述
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
 */
public class ReConstructBinaryTree_07 {
    public TreeNode reConstructBinaryTree(int [] pre,int [] in) throws Exception {
        return reConstructBinaryTree(pre,in,0,pre.length-1,0,in.length-1);
    }

    private TreeNode reConstructBinaryTree(int [] pre,int [] in, int preIndex1, int preIndex2, int inIndex1, int inIndex2){
        if(preIndex1==preIndex2)
            return new TreeNode(pre[preIndex1]);

        int rootVal = pre[preIndex1];
        TreeNode root = new TreeNode(rootVal);
        int rootLocate = -1;  // 中序遍历下root的位置
        for (int i = inIndex1; i <=inIndex2 ; i++) {
            if(in[i]==rootVal){
                rootLocate = i;
                break;
            }
        }
        int leftLength = rootLocate-inIndex1;

        if(leftLength>0){
            // 左子树
            TreeNode left = reConstructBinaryTree(pre,in,preIndex1+1,preIndex1+leftLength,inIndex1,rootLocate-1);
            root.left = left;
        }
        if (leftLength<preIndex2-preIndex1){
            // 右子树
            TreeNode right = reConstructBinaryTree(pre,in,preIndex1+leftLength+1,preIndex2,rootLocate+1,inIndex2);
            root.right = right;
        }
        return root;
    }
}
/** 思路:
 *    先序遍历的第1个节点确定该子树的root节点
 *    找到该root节点在中序遍历下的位置, 在中序遍历下划分左子树和右子树
 *    根据左子树的长度, 找到先序遍历下的左右子树数组, 和中序遍历下的左右子树数组一起进行递归计算
 *  注意点:
 *      构建左右子树时, 要先判断是否存在左子树或右子树 (根据数组长度决定)
 */
