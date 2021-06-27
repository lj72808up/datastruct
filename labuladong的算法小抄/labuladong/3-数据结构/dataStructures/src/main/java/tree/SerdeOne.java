package tree;

import common.TreeNode;
import sun.reflect.generics.tree.Tree;

import java.util.LinkedList;

/**
 * 前序遍历序列化反序列化二叉树
 */
public class SerdeOne {
    private StringBuilder sb = new StringBuilder();

    public void serial(TreeNode root){
        // null 节点
        if (root == null){
            this.sb.append("#").append(",");
            return;
        }
        // 非 null 节点
        this.sb.append(root.val).append(",");
        serial(root.left);
        serial(root.right);
    }

    public TreeNode deSerial(String strs){
        LinkedList<String> list = new LinkedList<>();
        for (String s : strs.split(",")) {
            list.add(s);
        }
        return this.deSerial(list);
    }
    private TreeNode deSerial(LinkedList<String> lists){
        // 第一个元素是先序遍历的 root。
        // 这里没有使用 index 访问是因为，树形不同，左右孩子的 index 不同
        // 通过在递归中不断删除元素，达到自动变换 index 的目的
        String rootVal = lists.removeFirst();
        if ("#".equals(rootVal))
            return null;
        TreeNode root = new TreeNode(Integer.parseInt(rootVal));

        root.left = deSerial(lists);
        root.right = deSerial(lists);
        return root;
    }

    public static void main(String[] args) {
        TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(2);
        TreeNode c = new TreeNode(3);
        TreeNode d = new TreeNode(4);

        a.left = b;
        b.left = c;
        b.right = d;

        SerdeOne serder = new SerdeOne();
        serder.serial(a);
        String serials = serder.sb.toString();
        System.out.println(serials);

        TreeNode newRoot = serder.deSerial(serials);
        System.out.println(newRoot);
    }
}
