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
                      /** * */ {'>','>','<','<','<','<','<','>'},
                      /** / */ {'>','>','>','>','<','<','<','>'},
                      /** ^ */ {'>','>','>','>','>','<','<','>'},
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
                            simpleCal(opNums, operators);
                            operators.push(chs[i]);
                            break;
                        case '<':
                            operators.push(chs[i]);
                            break;
                        case '=':
                            operators.pop();
                            break;
                        default:     // 表达式语法出错
                            return -1;
                    }  // switch
                    i++;
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
            default: break;
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
        String str = "2+(3*2)-1";
        System.out.println(new Calculator_227().calculate(str));
    }
}


/**
 * 思路:
 *  (1) 算法从左到右扫描字符, 尚不能参与计算的操作数和操作符分别入栈opNums,operators
 *      a. 这里允许读取数字为小数, 因此需要处理小数点问题
 *  (2) 一旦发现栈顶的操作符优先级比当前操作符优先级高, 则弹出栈定进行计算, 并将结果写会操作数栈opNums
 *      a. 如果遇到'('操作符, 由于需要先计算小括号内的数字, 所以'('比所有其他操作符的优先级都低;
 *         即小括号及其右面的数字统一压入栈
 *      a2. 如果当前为运算符, 而栈顶为'(', 说明已经进入小括号内进行计算, 此时不能立刻计算, 还要看其后面的操作符, 因次priority[操作符]['(']='<'
 *      b. 如果遇到')'操作符, 说明小括号内的字符扫描完毕, 可以进行计算, 因次, ')'比其他所有自读的优先级都高;
 *         对')'的上述处理方式, 直到栈顶操作符为'('为止, 弹出'(', 可以让')'的优先级等于'('
 *      b2. 由于遇到')'后, 需要弹栈计算, 所以还在那鼎不会出现')'
 * */