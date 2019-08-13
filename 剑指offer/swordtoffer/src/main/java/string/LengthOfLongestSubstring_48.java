package string;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 示例 2:
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 * 示例 3:
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 */
public class LengthOfLongestSubstring_48 {
    public int lengthOfLongestSubstring(String s) {
        if (s.length()==0)
            return 0;

        int maxLength = 1; // 最大字串长度
        char[] chars = s.toCharArray();

        int[] location = new int[255]; // 记录26个字符上次出现的位置(意味着出现重复字符)
        for (int i = 0; i < location.length; i++) {
            location[i] = -1;
        }

        int curLength = 1;  //第1个字符的最长无重复子串长度为1
        location[chars[0]] = 0;

        for (int i = 1; i < chars.length; i++) {
            int lastLocation = location[chars[i]];
            if(lastLocation==-1) {
                curLength++;
            }else{
                int distance = i-lastLocation;
                if (distance<=curLength){
                    curLength = distance;
                }else{
                    curLength++;
                }
            }
            if(curLength > maxLength)
                maxLength = curLength;
            location[chars[i]] = i;
        }
        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println((char)0);
        String str = " ";
        System.out.println(new LengthOfLongestSubstring_48().lengthOfLongestSubstring(str));
    }

}


/**
 * 思路:
 *  假设最长无重复子串这个解的结尾字符是原字符串中第i位置, 则我们只要找到每个为i为结尾的无重复子串, 求出所有位置i的解得最大值, 就是原问题的解;
 *  此题为动态规划后. 求max(dp)的问题, 比单纯的求dp多出一步:
 *    (1) 用f(i)表示以第i个元素为结尾的最长子串长度
 *          例如'abcdd'这个字符串,其f(4)=1: 因为以角标4的'd'为结尾的最长子串就是'd', 角标3的'd'就和重复了
 *    (2) f(i) = f(i-1)+1  (第i个字符在前面没有出现过)
 *        f(i) = d        (第i个字符,距离上次出现位置的距离为d, 这个d小于等于f(i-1); 即以i-1字符为结尾的最长字串中包含字符char[i])
 *        f(i) = f(i-1)+1 (第i个字符,距离上次出现位置的距离为d, 这个d大于f(i-1); 在之前的d个字符中, 存在和char[i-1]相同的字符, 因此去f(i-1)+1)
 *    (3) 为了表示字符在前面是否出现过, 可以用一个int[255]记录每个字符上次出现的位置, 没心迭代都去更新这个数组即可
 * */