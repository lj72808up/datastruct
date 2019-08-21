/**
 * 题目描述:
 *      给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 *
 * 示例 1:
 *      输入: 123
 *      输出: 321
 * 示例 2:
 *      输入: -123
 *      输出: -321
 * 示例 3:
 *      输入: 120
 *      输出: 21
 */
public class ReverseInt_7 {
    public int reverse(int x) {
        int newNum = 0;
        int num = x;
        while(num!=0){
            int extra = num % 10;
            num = num/10;
            if (newNum > Integer.MAX_VALUE/10 || (newNum == Integer.MAX_VALUE / 10 && extra > 7))
                return 0;  // 溢出
            if (newNum < Integer.MIN_VALUE/10 || (newNum == Integer.MIN_VALUE / 10 && extra < -8))
                return 0;  // 溢出
            newNum = newNum*10 + extra;
        }
        return newNum;
    }

    public static void main(String[] args) {
        System.out.println(new ReverseInt_7().reverse(210));
    }
}


/**思路:
 *   (1) 反转整数, 每次从原数字取余得到最后一位(extra = num%10), 原数字/10得到迭代的下一下输入(num=num/10)
 *   (2) 新数字初始取newNum=0, 每得到1个数字, 就让newNum=newNum*10+extra;
 * */