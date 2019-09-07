/**
 * 题目描述:
 *  给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *  (回文是一个正读和反读都相同的字符串，例如，“aba” 是回文，而 “abc” 不是。)
 */
public class LongestPalindrome_5 {
    public String longestPalindrome(String s) {
        if(s.length()==0)
            return "";

        char[] chs = s.toCharArray();
        int maxLength = 0;
        int start = 0;
        int end = 0;
        for (int i = 0; i < chs.length; i++) {
            int length1 = this.palindromeLength(chs,i,i+1);
            int length2 = this.palindromeLength(chs,i,i);
            int tmp = Math.max(length1,length2);
            if(tmp > maxLength){
                maxLength = tmp;
                start = i-(tmp-1)/2;
                end = i+(tmp)/2;
            }
        }
        return s.substring(start,end+1);
    }

    private int palindromeLength(char[] chs, int index1, int index2){
        while(index1>=0 && index2<chs.length && chs[index1] == chs[index2]){
            index1 --;
            index2 ++;
        }
        return  index2-index1-1;
    }

    public static void main(String[] args) {
        System.out.println(new LongestPalindrome_5().longestPalindrome("abba"));
    }
}


/**思路:
 *    回文子串即左右对称的子串, 如果一个字符串有最大回文子串, 则该最大回文子串存在一个对称中心, 因此, 可以从每个字符开始, 判断以该字符为中心时的最大对称字串长度;
 *    即, 设置2个索引i和j, 若chs[i]==chs[j], 则再比较i--,j++;
 * 注意
 *    (1) 对称中心即可能是一个字符, 如"aba", 也可能是相邻的两个字符"bb", 所以要比较两个情况:
 *          即上述的2个索引分2种情况, i=j , 和j=i+1;
 * */
