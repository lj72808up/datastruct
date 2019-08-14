package queueStack;
import java.util.Stack;
/**
 * 题目描述
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
 * https://www.nowcoder.com/practice/4c776177d2c04c2494f2555c9fcc1e49?tpId=13&tqId=11173&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 */
public class StackByMin_30 {
    private Stack<Integer> elemStack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();
    private int min = Integer.MAX_VALUE;

    public void push(int node) {
        elemStack.push(node);
        min = min>node? node:min;
        minStack.push(min);
    }

    public void pop() {
        if(elemStack.size()>0) {
            minStack.pop();
            elemStack.pop();
        }
    }

    public int top() {
        if(elemStack.size()>0)
            return elemStack.peek();
        else
            return -1;
    }

    public int min() {
        if(minStack.size()>0)
            return minStack.peek();
        else
            return -1;
    }
}

/**
 * 思路:
 *  如果我们使用一个成员变量, 记录当前栈内元素的最小值, 这样在调用min方法时, 直接返回数值不就行了吗; 可是有一个问题是, 当这最小值从栈顶弹出后, 剩下元素的最小值是什么呢?
 *  因此, 并不能用1个成员变量记录当前栈内元素的最小值, 而是同样使用一个栈记录, 每次插入元素后, 都从=把当前站内的最小值压入min栈
 * */