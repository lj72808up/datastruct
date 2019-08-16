/**
 * 题目描述:
 * 实现一个基本的计算器来计算一个简单的字符串表达式的值。
 * 字符串表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格  。 整数除法仅保留整数部分。
 *
 * 示例 1:
 *   输入: "3+2*2"
 *   输出: 7
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/basic-calculator-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Calculator_227 {
    private String[] operators = {"+","-","*","/","(",")"};
    private int[][] priority = null;

    public Calculator_227() {
        int[][] priority = {{1,1,0,0,1,1},  //+
                            {1,1,0,0,1,1},  //-
                            {1,1,1,1,1,1},  //*
                            {1,1,1,1,1,1},  ///
                            {0,0,0,0,0,2},  //需要弹栈
                            {0,0,0,0,0,2}   //需要弹栈
                        };
        this.priority = priority;
    }

    public int calculate(String s) {
        return 0/1;
    }
}
