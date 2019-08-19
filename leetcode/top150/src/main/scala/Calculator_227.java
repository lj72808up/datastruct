import javax.management.relation.RoleUnresolved;
import java.util.Stack;


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
    private char[] opChs = {'+','-','*','/','^','!','(',')'};
    private char[][] priority = null;

    public Calculator_227() {
        char[][] priority = {/** 当前元素
                                 +   -   *   /   ^   !   (   ) */
             /** 栈顶元素: + */ {'>','>','<','<','<','<','<','>'},
                      /** - */ {'>','>','<','<','<','<','<','>'},
                      /** * */ {'>','>','>','>','<','<','<','>'},
                      /** / */ {'>','>','>','>','<','<','<','>'},
                      /** ^ */ {'>','>','>','>','<','<','<','>'},
                      /** ! */ {'>','>','>','>','>','>',' ','>'},
                      /** ( */ {'<','<','<','<','<','<','<','='},
                      /** ) */ {' ',' ',' ',' ',' ',' ',' ',' '}
                        };
        this.priority = priority;
    }

    public double calculate(String s) {
        if( s==null || s.length()==0 )
            return -1;
        Stack<Double> opNums = new Stack<>();
        Stack<Character> operators = new Stack<>();
        char[] chs = s.toCharArray();
        int i = 0;
        while (i<=chs.length-1 || operators.size()>0){
            if(i <= chs.length-1) {  // 表达式还未遍历完
                if(Character.isDigit(chs[i])){
                    i = readNum(chs,i,opNums);   // 返回是数字的后面一位(操作符位)
                }
                else if(operators.size()==0) {   // 栈顶为空
                    operators.push(chs[i]);
                    i++;
                }else {                     // 栈顶非空
                    char top = operators.peek();
                    char priori = getPriority(top, chs[i]);
                    switch (priori) {
                        case '>':   // 栈顶优先级高, 可以进行计算
                            simpleCal(opNums, operators);  // 循环计算, 角标不增加, 操作符也不入栈, 直到栈顶优先级比当前优先级小
                            break;
                        case '<':
                            operators.push(chs[i]);
                            i++;
                            break;
                        case '=':
                            operators.pop();
                            i++;
                            break;
                        default:     // 表达式语法出错
                            throw new RuntimeException("priority 非法");
                    }  // switch

                }
            }else{    // 表达式已经遍历完
                simpleCal(opNums,operators);
            }

        }// while
        return opNums.pop();
    }

    private int readNum(char[] chs, int start, Stack<Double> numStack){
        /**读取数字, 返回数字的后一位*/
        int i = start;
        Stack<Integer> tmp = new Stack<Integer>();
        while(i<chs.length && Character.isDigit(chs[i])){
            tmp.push(chs[i]-'0');
            i++;
        }
        int extra = 0;
        double sum = 0.0;
        while (!tmp.empty()){
            sum += tmp.pop() * Math.pow(10,extra);
            extra++;
        }
        numStack.push(sum);
        return i;
    }

    private void simpleCal(Stack<Double> opNums, Stack<Character> operators){
        /** 弹出栈顶的操作符,针对不同操作符弹出不同操作数进行计算 */
        char op = operators.pop();
        switch (op){
            case '+': {
                double num2 = opNums.pop();
                double num1 = opNums.pop();
                opNums.push(num1 + num2);
                break;
            }
            case '-': {
                double num2 = opNums.pop();
                double num1 = opNums.pop();
                opNums.push(num1 - num2);
                break;
            }
            case '*': {
                double num2 = opNums.pop();
                double num1 = opNums.pop();
                opNums.push(num1 * num2);
                break;
            }
            case '/': {
                double num2 = opNums.pop();
                double num1 = opNums.pop();
                opNums.push(num1 / num2);
                break;
            }
            case '^': {
                double num2 = opNums.pop();
                double num1 = opNums.pop();
                opNums.push(Math.pow(num1, num2));
                break;
            }
            case '!': {
                double num = opNums.pop();
                double product = 1.0;
                while (num>1.0){
                    product *= num;
                    num--;
                }
                opNums.push(product);
                break;
            }
            case ')':{
                operators.pop();  // 栈顶遇到反括号, 只弹栈
                break;
            }
            default:
                throw new RuntimeException("非法操作符:"+op);
        }
    }

    private char getPriority(char top,char cur){
        int topIndex = -1;
        int curIndex = -1;
        for (int i = 0; i < opChs.length; i++) {
            if(opChs[i]==top)
                topIndex = i;
            if(opChs[i]==cur)
                curIndex = i;
        }
        return priority[topIndex][curIndex];
    }

    public static void main(String[] args) {
        String str = "2+(3+2*(2-1))*2";
        String str2 = "14/3*2";
        Calculator_227 cal = new Calculator_227();
        System.out.println(str+" = "+cal.calculate(str));
    }
}

