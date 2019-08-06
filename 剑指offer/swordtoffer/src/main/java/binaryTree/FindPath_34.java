package binaryTree;

import array.ArrayUtil;

import java.util.ArrayList;

/**
 * 题目描述
 * 输入一颗二叉树的根节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
 * 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。(注意: 在返回值的list中，数组长度大的数组靠前)
 */
public class FindPath_34 {
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if(root==null)
            return res;
        ArrayList<Integer> path = new ArrayList<>();
        int sum = 0;
//        findPathUntilLeaf(root, target, sum,path,res);
        findPathAnyNode(root, target, sum,path,res);
        return res;
    }

    /**
     * 从root到每个叶子节点的路径中, 所有和为sum的节点
     */
    private void findPathUntilLeaf(TreeNode node, int target, int sum, ArrayList<Integer> path,ArrayList<ArrayList<Integer>> res) {
        /*if(node==null)
            return;*/
        sum = sum + node.val;
        System.out.println(sum);
        path.add(node.val);

        if(sum==target && (node.left==null&&node.right==null)){
//            ArrayUtil.printList(path);
            ArrayList<Integer> okPath = new ArrayList<>();
            okPath.addAll(path);
            res.add(okPath);
        }

        if (node.left!=null)
            findPathUntilLeaf(node.left,target,sum,path,res);
        if (node.right!=null)
            findPathUntilLeaf(node.right,target,sum,path,res);

        path.remove(path.size()-1);  // 从path中删除自身,确保返回父节点时,path只包含root到父节点的路径
    }

    /**
     * 从root节点到任意节点中和为sum的路径
     */
    private void findPathAnyNode(TreeNode node, int target, int sum, ArrayList<Integer> path,ArrayList<ArrayList<Integer>> res) {
        if(node==null)
            return;
        sum = sum + node.val;
        System.out.println(sum);
        path.add(node.val);

        if(sum==target){
//            ArrayUtil.printList(path);
            ArrayList<Integer> okPath = new ArrayList<>();
            okPath.addAll(path);
            res.add(okPath);
        }


        findPathAnyNode(node.left,target,sum,path,res);
        findPathAnyNode(node.right,target,sum,path,res);

        path.remove(path.size()-1);
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

        ArrayList<ArrayList<Integer>> res = new FindPath_34().FindPath(a,22);
        System.out.println(res);
    }
}

/**
 * 思路:
 *  当用前序遍历的方法访问到某一个节点时, 应该吧节点添加到路径上, 再累计该节点计算当前的和.
 *  如果这个和与target相同, 则找到一个路径打印出来; 接着继续在左右孩子的节点上进行迭代.
 *  在函数返回之前, 一定要将节点从经过的路径上删除, 以确保返回到父节点时的路径只包含从root到父节点的路径.
 * */
