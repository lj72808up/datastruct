package string;

import java.util.ArrayList;

/**
 * 给定一个数字，我们按照如下规则把它翻译为字符串：
 * 0翻译成”a”，1翻译成”b”，……，11翻译成”l”，……，25翻译成”z”。
 * 一个数字可能有多个翻译。例如12258有5种不同的翻译，它们分别是”bccfi”、”bwfi”、”bczi”、”mcfi”和”mzi”。
 * 请编程实现一个函数用来计算一个数字有多少种不同的翻译方法。
 * https://www.acwing.com/problem/content/description/55/
 */
public class TranslationCount2String_46 {
    public int getTranslationCount(String s) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        return getCountRec(s.toCharArray(),0,res);
    }
    private int getCountRec(char[] nums,int index, ArrayList<Integer> res){
        if(index > nums.length-1) {
            System.out.println(res);
            return 1;
        }
        res.add(nums[index]-'0');
        int singleCount = getCountRec(nums, index + 1,res);
        int pairCounts = 0;
        res.remove(res.size()-1);

        // 必须有nums[index]!='0',因为0与任何数字合起来如果都会变成1个数字, 如'04'变成'4', 会丢掉0这个字符
        if(nums[index]!='0' && index+1<nums.length && (nums[index]-'0')*10+(nums[index+1]-'0')<=25) {
            res.add((nums[index]-'0')*10+(nums[index+1]-'0'));
            pairCounts = getCountRec(nums, index + 2,res);
            res.remove(res.size()-1);
        }

        return singleCount+pairCounts;
    }

    public static void main(String[] args) {
        System.out.println(new TranslationCount2String_46().getTranslationCount("32456216578"));
    }
}



/**
 * 思路:
 *   判断字符串有几种翻译方法, 主要是在于当前数字能否和下一个数字一起翻译成1个字符, 即(2个数字拼起来是否<=25);
 *   此时想到使用递归求解.
 *
 * 注意:
 *   2个问题:
 *   (1) 字符'9',如何翻译成数字9 :  使用'9'-'0'
 *   (2) 判断两个数字拼起来是否小于等于25时, 要先判断nums[index]!='0',因为0与任何数字合起来都会变成1个数字, 如'04'变成'4', 会丢掉0这个字符
 * */