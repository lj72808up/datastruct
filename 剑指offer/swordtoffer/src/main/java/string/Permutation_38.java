package string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

/**
 * 题目描述
 * 输入一个字符串,按字典序打印出该字符串中字符的所有排列。
 * 例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
 * 输入描述:
 * 输入一个字符串,长度不超过9(可能有字符重复),字符只包括大小写字母。
 */
public class Permutation_38 {
    public ArrayList<String> Permutation(String str) {
        ArrayList<String> res = new ArrayList<String>();
        char[] arr = str.toCharArray();
        permRec(arr,res,0,arr.length-1);
//        res = new ArrayList<String>(new HashSet<String>(res));  // 输入字符串中有有重复字符,会产生重复结果
//        Collections.sort(res);
        return res;
    }

    private void permRec(char[] arr, ArrayList<String> res, int start,int end){
        if(start==end){
            char[] dest = new char[arr.length];
            System.arraycopy(arr,0,dest,0,arr.length);
            res.add(new String(dest));
        }
        for(int i = start;i<=end;i++){
            swap(arr,start,i);
            permRec(arr,res,start+1,end);
            swap(arr,i,start);
        }
    }

    private void swap(char[] arr, int index1, int index2){
        char tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }

    public static void main(String[] args) {
        System.out.println(new Permutation_38().Permutation("aba"));
    }
}

/**
 * 思路:
 *  要计算字符串的排列结果, 则题目中隐含条件:
 *      (1) 输入字符串中的所有字符各不相同
 *      (2) 输入字符串和输出的排列后字符串是等长的
 *  因此, 可以想到如下步骤:
 *      (1) 求出第一步可能出现的所有字符:
 *          让原字符串依次和后面的每个字符交换即可
 *      (2) 固定第一个字符吗求后面字符串的排列, 这个有可以把后面的字符串看成1个固定字符+剩余字符串的结果, 于是使用递归处理;
 * */








/**
 * 题目描述:
 * 输入一个字符串，输出该字符串中字符的所有组合。举个例子，如果输入abc，它的组合有a、b、c、ab、ac、bc、abc。
 */
class Combination{
    public ArrayList<String> combine(String str) {
        ArrayList<String> res = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();
        combineRec(str.toCharArray(),0,res,sb);
        return res;
    }

    private void combineRec(char[] arr, int index, ArrayList<String> res, StringBuilder sb){
        if(index > arr.length-1) {
            if(sb.length()>0)  // 防止哪个字符都不取得到的空字符串加入结果
                res.add(sb.toString());
            return;
        }
        // 该字符不加入结果
        combineRec(arr,index+1,res,sb);
        // 该字符加入结果
        sb.append(arr[index]);
        combineRec(arr,index+1,res,sb);
        sb.deleteCharAt(sb.length()-1);
    }

    public static void main(String[] args) {
        System.out.println(new Combination().combine("abc"));
    }
}