package string;

import scala.math.Ordering;

/**
 * 题目描述
 * 牛客最近来了一个新员工Fish，每天早晨总是会拿着一本英文杂志，写些句子在本子上。同事Cat对Fish写的内容颇感兴趣，有一天他向Fish借来翻看，但却读不懂它的意思。
 * 例如，“student. a am I”。后来才意识到，这家伙原来把句子单词的顺序翻转了，正确的句子应该是“I am a student.”。Cat对一一的翻转这些单词顺序可不在行，你能帮助他么？
 */
public class ReverseSentence_58 {
    public String ReverseSentence(String str) {
        if(str==null || str=="")
            return "";
        char[] chs = str.toCharArray();
        reverse(chs,0,chs.length-1);
        int index1 = 0;
        int index2 = 0;
        while (index2<=chs.length-1){
            while (index2<=chs.length-1 && chs[index2]!=' ')
                index2++;
            reverse(chs,index1,index2-1);
            index2 = index2+1;
            index1 = index2;
        }
        return new String(chs);
    }
    private void reverse(char[] chs, int index1, int index2){
        while(index1<index2){
            char tmp = chs[index1];
            chs[index1] = chs[index2];
            chs[index2] = tmp;
            index1++;
            index2--;
        }
    }

    public static void main(String[] args) {
        System.out.println(new ReverseSentence_58().ReverseSentence("hello world"));
    }
}



/**
 * 思路: 此题有一个固定解法, 两次翻转字符串
 *   (1) 第一次整个字符串翻转:
 *     "hello world" => "dlrow olleh"
 *   (2) 以空格分割的每个字符串再次各自翻转
 *      "dlrow" => "world"
 *      "olleh" => "hello"
 *
 * 注意:
 *   (1) 查找以空格分开的每个子串时, 不用拿string.split(" ")计算, 避免string和chararray互相转化
 *       可以使用start和end角标, 没遇到" "就把end++, 前提是end<=string.length-1;
 * */