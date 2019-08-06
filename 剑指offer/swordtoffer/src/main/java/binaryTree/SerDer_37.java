package binaryTree;

import array.ArrayUtil;
import com.sun.deploy.panel.TreeBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

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
        index++;
        node.left = Deserialize(strs);
        index++;
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
 * 
 * */