package queueStack;

import java.util.Stack;

/**
 * 题目描述
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。假设压入栈的所有数字均不相等。
 * 例如序列1,2,3,4,5是某栈的压入顺序，序列4,5,3,2,1是该压栈序列对应的一个弹出序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列。
 * （注意：这两个序列的长度是相等的）
 * https://www.nowcoder.com/practice/d77d11405cc7470d82554cb392585106?tpId=13&tqId=11174&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 */
public class StackPopOrder_31 {
    private Stack<Integer> stack = new Stack<>();

    public boolean IsPopOrder(int [] pushA,int [] popA) {
        if(pushA==null || popA==null)
            return false;
        int pushIndex = 0;
        stack.push(pushA[pushIndex]);
        pushIndex++;

        for (int i = 0; i < popA.length; i++) {
            while (stack.peek()!=popA[i]) {
                if(pushIndex<pushA.length){
                    stack.push(pushA[pushIndex]);
                    pushIndex++;
                }else {
                    return false;
                }
            }
            stack.pop();  // 栈顶和待删除元素相同的时候, pop数据
        }
        return true;
    }
}





/**
 * 思路:
 *    考察某个序列是否是栈的弹出序列, 可以用一个辅助栈, 模拟压栈操作,
 *      (1)如果栈顶元素不是弹出栈的元素, 则继续压栈, 直到栈顶和出栈元素相同; 如果直到入栈元素全部入栈完毕, 还是没在栈顶找到待出栈元素, 则返回false
 *      (2)如果栈顶元素和弹出栈的元素系相同, 则辅助栈执行弹栈出操作
 * */