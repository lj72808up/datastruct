package binaryTree;

/**
 * 题目(一)描述
 * 输入一棵二叉树，求该树的深度。从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。
 */
public class TreeDepth_55 {
    public int TreeDepth(TreeNode root) {
        return TreeDepthRec(root);
    }
    private int TreeDepthRec(TreeNode root) {
        if(root==null)
            return 0;
        int leftDeep = TreeDepthRec(root.left);
        int rightDeep = TreeDepthRec(root.right);
        return Math.max(leftDeep,rightDeep)+1;  // 深度=左右自述最大深度+1
    }
}
/**
 * 思路:
 *   后序遍历的思路, 先找左子树的深度, 再找右子树的深度, 最后返回当前节点的深度为左右子树中深度最大的+1
 * */







/**
 * 题目二:
 *   输入一棵二叉树，判断该二叉树是否是平衡二叉树。(平衡二叉树是左右子树深度相差不超过1的数)
 */
class IsBalanced_Solution{
    public boolean IsBalanced_Solution(TreeNode root) {
        return getDeep(root)!=-1;
    }
    private int getDeep(TreeNode root){
        if (root==null)
            return 0;
        int left = getDeep(root.left);
        if (left==-1) // 左子树不平衡了, 立刻返回, 不用计算了(剪枝)
            return -1;
        int right = getDeep(root.right);
        if(right==-1)
            return -1;
        int res = 0;
        if (Math.abs(left-right)>1)  // 返回节点的deep
            res = -1;
        else
            res =  Math.max(left,right)+1;
        System.out.println(root.val+"==>"+res);
        return res;
    }

    public static void main(String[] args) {
        TreeNode z = new TreeNode(13);
        TreeNode z2 = new TreeNode(14);
        TreeNode a = new TreeNode(10);
        TreeNode b = new TreeNode(5);
        TreeNode c = new TreeNode(12);
        TreeNode d = new TreeNode(4);
        TreeNode e = new TreeNode(6);

        z.left=a;
        z.right = z2;
        a.left = b;
//        a.right = c;
        b.left = d;
        b.right = e;

        System.out.println(new IsBalanced_Solution().IsBalanced_Solution(z));
// out:  (10的时候已经不平衡了, 不会再去取右子树计算12的深度)
//        4==>1
//        6==>1
//        5==>2
//        10==>-1
//        false

    }
}

/**
 * 思路:
 *   仍然采用查看节点深度的办法, 只是在左右自述深度相差大于1的时候, 返回-1,在该节点表示不平衡了; 而一旦发现左右子树中有不平衡的, 立刻返回-1, 表示不平衡了(剪枝)
 * */