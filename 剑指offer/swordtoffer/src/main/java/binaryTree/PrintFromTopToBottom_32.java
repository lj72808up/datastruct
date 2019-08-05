package binaryTree;

import java.util.ArrayList;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingDeque;


public class PrintFromTopToBottom_32 {
    /**
     * 题目描述
     * 从上往下打印出二叉树的每个节点，同层节点从左至右打印。
     */
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) throws InterruptedException {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null)
            return res;
        LinkedBlockingDeque<TreeNode> queue = new LinkedBlockingDeque<TreeNode>();
        queue.put(root);
        while (queue.size() != 0) {
            int length = queue.size();  // 记录queue当前的长度
            for (int i = 0; i < length; i++) {
                TreeNode node = queue.take();
                res.add(node.val);
                if (node.left!=null)
                    queue.put(node.left);
                if (node.right!=null)
                    queue.put(node.right);
            }
        }
        return res;
    }

    /**
     * 题目描述
     * 请实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。
     */

    public ArrayList<ArrayList<Integer> > Print(TreeNode pRoot) {
        if (pRoot == null)
            return new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer> > finalRes = new ArrayList<ArrayList<Integer>>();

        Stack<TreeNode> oddStack = new Stack<TreeNode>();
        Stack<TreeNode> evenStack = new Stack<TreeNode>();

        int layer = 1;
        oddStack.push(pRoot);

        while(!(oddStack.empty() && evenStack.empty())){
            if(layer%2==1){
                int length = oddStack.size();
                ArrayList<Integer> res = new ArrayList<>();
                for (int i = 0; i < length; i++) {
                    TreeNode node = oddStack.pop();
                    if (node.left!=null)
                        evenStack.push(node.left);
                    if (node.right!=null)
                        evenStack.push(node.right);
                    res.add(node.val);
                }
                finalRes.add(res);
            }else{
                int length = evenStack.size();
                ArrayList<Integer> res = new ArrayList<>();
                for (int i = 0; i < length; i++) {
                    TreeNode node = evenStack.pop();
                    if (node.right!=null)
                        oddStack.push(node.right);
                    if (node.left!=null)
                        oddStack.push(node.left);
                    res.add(node.val);
                }
                finalRes.add(res);
            }
            layer++;
        }
        return finalRes;
    }


    public static void main(String[] args) {
        TreeNode a = new TreeNode(8);
        TreeNode b = new TreeNode(6);
        TreeNode c = new TreeNode(10);
        TreeNode d = new TreeNode(5);
        TreeNode e = new TreeNode(7);
//        TreeNode f = new TreeNode(9);

        a.left=b;
        a.right=c;
        b.left=d;
        b.right=e;
//        c.left=f;

        new PrintFromTopToBottom_32().Print(a);
    }

}


/**
 * 从上往下打印
 *   [注意]:
 *      1.queue的2个方法:
 *          put: 放数据
 *          take: 拿数据
 *      2.插入队列时, 先判断left和right是否为空
 *
 * 之字打印
 *  思路:
 *      画出一个二叉树, 找到之字打印的规律:
 *          每层分奇数层,偶数层;
 *          奇数层从奇数栈中pop元素, 分别加入left,right孩子;
 *          偶数层从偶数栈中pop元素, 分别加入right,left孩子
 * */