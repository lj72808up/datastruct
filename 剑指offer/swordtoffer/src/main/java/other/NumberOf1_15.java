package other;

/**
 * 题目描述
 * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
 */
public class NumberOf1_15 {
    public int NumberOf1(int n) {
        int flag = 1;
        int count = 0;
        while (flag!=0){
            int res = n & flag;
            if(res!=0)
                count++;
            flag = flag<<1;
        }
        return count;
    }
}
/**
 * 思路:
 *  (1)查看数字的二进制表示中1的个数, 是一个基本的二进制与位运算题目; 很自然的想到:
 *      让数字的二进制的每一位与1做"与", 如果结果不是0, 则说明数字在该位置上是0; 每做完一次运算, 向右移位1位;
 *  (2)上述方法在附属上不适用, 因为负数右移,收尾补1不是补0(为了保证补位后仍是负数)
 *      因此, 略做调整, 我们让1每次左移一位, 来从后向前的判断判断数字每一位是否为1, 直到1左移溢出
 * */