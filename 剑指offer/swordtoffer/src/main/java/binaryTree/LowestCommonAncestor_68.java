package binaryTree;

import java.util.ArrayList;

/**
 * 题目描述:
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * 最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 */
public class LowestCommonAncestor_68 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null || p==null || q==null)
            return null;
        ArrayList<TreeNode> pathP = new  ArrayList<TreeNode>();
        ArrayList<TreeNode> pathQ = new  ArrayList<TreeNode>();

        boolean pFind = this.getPath(root,p,pathP);
        boolean qFind = this.getPath(root,q,pathQ);

        System.out.println(pathP);
        System.out.println(pathQ);

        if(pFind && qFind){
            int length = Math.min(pathP.size(),pathQ.size());  // 长度的最小值
            int i = 0;
            for (; i < length; i++) {
                if (pathP.get(i)!=pathQ.get(i))
                    break;
            }
            return pathP.get(i-1);
        }else{
            return null;
        }
    }

    private boolean getPath(TreeNode node, TreeNode target, ArrayList<TreeNode> path){
        if(node==null)
            return false;
        path.add(node);
        if(node==target)
            return true;
        else{
            boolean left = getPath(node.left,target,path);
            if (left)
                return true;
            boolean right = getPath(node.right,target,path);
            if(right)
                return true;

            path.remove(path.size()-1);
            return false;
        }
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

        System.out.println(new LowestCommonAncestor_68().lowestCommonAncestor(a,d,e));
    }
}

/**
 * 思路:
 *  2个节点的最低公共祖先, 即从root开始到2个节点的路径中, 有一段路径是相同的; 找到最后相同的节点,急时他们的公共祖先;
 *  问题转化为求的root到节点的路径
 * */









/**
 * 题目描述:
 *  给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 *  (相比上题, 题目有二叉树改为更具规律的二叉搜索树)
 */
class LowestCommonAncestorBST{
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root==null || p==null || q==null)
            return null;
        TreeNode lowestCommonAncestor = null;
        while(root!=null){
            if(root.val<p.val && root.val<q.val){
                root=root.right;
                lowestCommonAncestor = root;
            }else if(root.val>p.val&&root.val>q.val){
                root=root.left;
                lowestCommonAncestor = root;
            }else{
                return lowestCommonAncestor = root;
            }
        }
        return null;
    }
}

/**
 * 思路:
 *  二叉搜索树的性质是, 比root小的节点都在左子树, 比其打的都在右子树; 因此, 查找两个节点的公共祖先,必然满足:
 *      同时大于节点p和节点q的val, 或者同时小于节点p和节点q的val;
 *  为了找到最低公共祖先, 若root同时大于, 则从root.left继续寻找; 若同时小于, 则从root.right继续虚招
 */
