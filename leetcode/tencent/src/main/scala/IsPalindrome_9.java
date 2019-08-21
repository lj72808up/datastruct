/**
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 * 示例 1:
 *  输入: 121
 *  输出: true
 *
 * 示例 2:
 *  输入: -121
 *  输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class IsPalindrome_9 {
    public boolean isPalindrome(int x) {
        if(x<0)
            return false;
        int num = x;
        int newNum = 0;
        while(num!=0){
            int extra = num%10;
            num = num/10;
            newNum = newNum*10 + extra;
        }
        System.out.println(x+":"+newNum);
        return x == newNum;

    }

    public static void main(String[] args) {
        System.out.println(new IsPalindrome_9().isPalindrome(-121));
    }
}

/**思路:
 *  1. 判断数字是否是回文数字, 想到和题目09反转数字相似, 如果翻转后的数字和原数字相等, 等是回文数字
 *  2. 如果数字为回文数字, 则这个数字一定不会溢出
 * */