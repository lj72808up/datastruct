package string;

/**
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
 *
 * 注意：
 *
 * num1 和num2 的长度都小于 5100.
 * num1 和num2 都只包含数字 0-9.
 * num1 和num2 都不包含任何前导零。
 * 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class AddStrings_17 {
    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int i = num1.length()-1;
        int j = num2.length()-1;
        int extra = 0; // 进位结果
        while (i>=0||j>=0||extra>0){
            int res = 0;
            if(i>=0) {
                res = res + (num1.charAt(i) - '0');
                i--;
            }
            if(j>=0) {
                res = res + (num2.charAt(j) - '0');
                j--;
            }
            res = res + extra;
            sb.append(res%10);
            extra = res/10;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(new AddStrings_17().addStrings("1234","18234"));
    }
}

/**
 * 思路: (1) 字符串相加, 从个位数相加, 就是从字符串的最后一位开始加
 *       (2) 为了避免比较字符串长度, 可以用while循环, 每次判断str1和str2是否有剩余字符
 *              设置res=0, 如果num1有字符, 先用res+num1, 如果num2有剩余, 就用res+num2, 最后再加上上一步的进位 (及时上次进位是0), 得到本次的结果res
 *       (3) 每一位上的2个字符相加, 其输出结果和产生的进位都只和这2个字符和上次的进位有关:
 *              该次结果: res%10
 *              进位结果: res/10
 */
