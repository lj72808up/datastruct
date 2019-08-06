package binaryTree;

/**
 * 题目描述
 * 给定一棵二叉搜索树，请找出其中的第k小的结点。
 * 例如， （5，3，7，2，4，6，8）中，按结点数值大小顺序第三小结点的值为4。
 */
public class KthNodeInBST_54 {
    private int cnt = 1;  // 写在参数中数值类型变量,递归只能改变和递归层级相关的参数, 全局上的计数器要写成全局变量的形式
    private TreeNode res = null;

    /**
     * 方法二: 不使用全局变量记录最终结果, 用方法的返回值表示结果
     *   此时, 方法的返回值表示截止到遍历当前节点为止, 是否找到了第k个小的节点.
     *   所以: (1)遇到pRoot=null时, 返回null
     *         (2)遍历左子树时的返回值如果不是则找到目标, 直接返回; 如果是null, 就继续比较当前节点(cnt++)
     *         (3)处理当前节点
     *         (4)遍历右子树时的返回值如果不是null, 说明右子树中找到结果, 直接返回; 如果还是没找到, 返回null
     */
    public TreeNode KthNodeWithoutGlobalAttribute(TreeNode pRoot, int k) {
        if(pRoot==null)
            return null;
        TreeNode resInLeft = KthNodeWithoutGlobalAttribute(pRoot.left,k);
        if(resInLeft!=null) // 如果左面已经找到结果就返回
            return resInLeft;
        if(k==cnt)  // 操作本节点
            return pRoot;
        this.cnt++;
        TreeNode resInRight = KthNodeWithoutGlobalAttribute(pRoot.right,k);
        if(resInRight!=null) // 如果右面找到结果就返回
            return resInRight;
        return null;  // 这行是左面右面都没找到结果, 且遍历到该节点时也k!=cnt
    }

    /**
     * 方法一: 使用全局变量记录结果
     *      该方法是直接在中序遍历的代码上修改而来, 只是把print()改成:
     *      把全局的cnt和target比较, 相等则将本节点记录到全局的结果上
     */
    public TreeNode KthNodeGlobalAttribute(TreeNode pRoot, int k) {
        KthNodeRec(pRoot,k);
        return res;

    }
    private void KthNodeRec(TreeNode pRoot, int k) {
        if(pRoot==null)
            return;
        KthNodeRec(pRoot.left,k);
        if(k==this.cnt)
            res=pRoot;
        this.cnt++;
        KthNodeRec(pRoot.right,k);
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

        System.out.println(new KthNodeInBST_54().KthNodeGlobalAttribute(a,1));
    }
}


/**
 * 思路:
 *  要寻找二叉搜索树的第k小节点, 发现中序遍历会自动把BST的节点从小到大查找
 * */