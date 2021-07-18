package slideWindow;

import java.util.HashMap;

/**
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 * 注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。
 * <p>
 * 示例 1：
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * <p>
 * 示例 2：
 * 输入：s = "a", t = "a"
 * 输出："a"
 */
public class MinSubCoverString_76 {
    public String minWindow(String s, String t) {
        HashMap<Character, Integer> needs = new HashMap<>();
        for (char c : t.toCharArray()) {
            needs.put(c, needs.getOrDefault(c, 0) + 1);
        }
        final int needValid = needs.keySet().size();

        int windowValid = 0;  // needValid 和 windowValid 相等就可以收缩 left
        HashMap<Character, Integer> window = new HashMap<>();
        int start = 0, length = Integer.MAX_VALUE;  // 记录子串结果
        int left = 0, right = 0;
        while (right < s.length()) {
            char cur = s.charAt(right);
            right++; // 向右滑动

            if (needs.getOrDefault(cur, 0) > 0) {
                window.put(cur, window.getOrDefault(cur, 0) + 1);
                if (window.get(cur) == needs.get(cur))
                    windowValid++;
            }

            while (windowValid == needValid) {
                if (right - left < length) {   // 更新最小覆盖子串的结果
                    start = left;
                    length = right - left;
                }
                char lcur = s.charAt(left);
                left++; // 窗口左侧滑动
                if (needs.getOrDefault(lcur, 0) > 0) { // 如果子串 t 中包含该滑出窗口的字符
                    window.put(lcur, window.get(lcur) - 1);
                    if (window.get(lcur) < needs.get(lcur)) {   // 窗口内该字符数小于 t , 则 windowValid
                        windowValid--;
                    }
                }
            }
        }
        System.out.println(length);
        return length == Integer.MAX_VALUE ? "" : s.substring(start, start + length);
    }

    public static void main(String[] args) {
        String s = "aaaaaaaaaaaabbbbbcdd";
        String t = "abcdd";

        System.out.println(new MinSubCoverString_76().minWindow(s, t));
    }
}
