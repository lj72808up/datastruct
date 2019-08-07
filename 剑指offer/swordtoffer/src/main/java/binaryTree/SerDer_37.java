package binaryTree;

import java.util.ArrayList;
import java.util.Arrays;

public class SerDer_37 {
    StringBuilder sb = new StringBuilder();
    int index = 0;

    public String Serialize(TreeNode root) {
        this.serializeNode(root);
        return sb.toString();
    }

    private void serializeNode(TreeNode root) {
        if(root==null){
            sb.append("$,");
            return;
        }
        sb.append(root.val+",");
        serializeNode(root.left);
        serializeNode(root.right);
    }

    TreeNode Deserialize(String[] strs) {
        if (strs[index].equals("$"))
            return null;
        TreeNode node = new TreeNode(Integer.parseInt(strs[index]));
        index++;  // 左节点构建完毕, index+1
        node.left = Deserialize(strs);
        index++;  // 右节点构建完毕, index+1
        node.right = Deserialize(strs);
        return node;
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
        SerDer_37 obj = new SerDer_37();
        System.out.println(obj.Serialize(a));


        SerDer_37 obj2 = new SerDer_37();
        String[] strs = "10,5,4,$,$,6,$,$,12,$,$".split(",");
        ArrayList<String> arr = new ArrayList<String>(Arrays.asList(strs));
        TreeNode node = obj2.Deserialize(strs);
        System.out.println(obj2.Serialize(node));
    }
}
/**
 * 思路:
 *  序列化,发序列化二叉树是String<==>TreeNode的双向变化, 回忆07, 用前序遍历和中序遍历可以确定一颗二叉树, 但这样的做法用来序列化太繁琐, 而且要求书中补鞥存在数值相同的节点;
 *  考虑另一种做法:
 *      序列化时, 先序遍历一颗二叉树, 如果遇空节点就用一个特殊字符'$'代替, 这样序列化出来的字符串保存了原有数的完整结构
 *      反序列化时, 仍采用先序遍历的方式创建节点, 每次递增的取字符串上的字符, 遇到特殊字符构造null节点维护关系.
 * */