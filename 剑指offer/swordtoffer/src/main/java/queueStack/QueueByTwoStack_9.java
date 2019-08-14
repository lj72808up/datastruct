package queueStack;

import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 题目描述
 * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
 * https://www.nowcoder.com/practice/54275ddae22f475981afa2244dd448c6?tpId=13&tqId=11158&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 */
public class QueueByTwoStack_9 {
    Stack<Integer> addStack = new Stack<Integer>();
    Stack<Integer> rmStack = new Stack<Integer>();

    public void push(int node) {
        addStack.push(node);
    }

    public int pop() {
        if(rmStack.size()>0){
            return rmStack.pop();
        }else{
            while(addStack.size()!=0){
                int val = addStack.pop();
                rmStack.push(val);
            }
            return rmStack.pop();
        }
    }
}


/**
 * 思路: 两个栈实现队列, 关键是实现add和pop操作
 *  2个栈, 一个作为add栈, 一个作为rm栈;
 *     (1)增加数据时, 直接在add栈中增加即可;
 *     (2)在rm数据时, 由于栈是先进后出的, 所以先要把add栈中的数据全部pop再依次插入到rm栈后, rm栈的栈顶就是要删除的元素;
 *        由于rm栈中的元素都是比add栈中的元素早入栈的, 所以只要rm栈非空, rm栈的栈顶就是待删除元素.
 * */





/**
 * 题目描述: 两个队列实现一个栈
 *  完成队列的Push和Pop操作。 队列中的元素为int类型。
 */
class MyStack {
    private int flag = 0;
    private Queue<Integer> queue1 = new LinkedBlockingDeque<>();
    private Queue<Integer> queue2 = new LinkedBlockingDeque<>();

    public void push(int node) {
        if(flag%2==0)
            queue1.offer(node);
        else
            queue2.offer(node);
    }

    public int pop() {
        Queue<Integer> queue = null;
        Queue<Integer> standByqueue = null;
        if(flag%2==0){  // queue1中有数据
            queue = queue1;
            standByqueue = queue2;
        }else {
            queue = queue2;
            standByqueue = queue1;
        }

        while(queue.size()>1){
            int val = queue.poll();
            standByqueue.offer(val);
        }
        if(queue.size()>0) {
            flag = flag % 2 + 1;
            return queue.poll();
        }else
            return -1;

    }

    public static void main(String[] args) {
        MyStack stack = new MyStack();
        System.out.println(stack.pop());
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pop());
        System.out.println(stack.pop());

        stack.push(4);
        System.out.println(stack.pop());
    }
}
/**
 * 思路: 两个队列实现栈, 关键是实现push和pop操作:
 *  (1)2个队列, 1个作为主操作队列, 另一个作为删除数据时的缓存队列; 主操作队列和缓存队列每次执行删除后相互切换.
 *  (2)push操作, 直接在主队列上进行; pop操作, 需要先把数据从主队列拉到缓存队列, 再对剩余的一个元素执行删除
 * */

