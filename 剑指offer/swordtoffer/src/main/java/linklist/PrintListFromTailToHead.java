package linklist;

import java.util.ArrayList;

/**
 * 题目描述
  输入一个链表，按链表值从尾到头的顺序返回一个ArrayList。
 */
public class PrintListFromTailToHead {
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (listNode==null)
            return res;
        else
            addList(res,listNode);
        return res;
    }

    private void addList(ArrayList<Integer> arr,ListNode listNode){
        if (listNode.next!=null){
            addList(arr,listNode.next);
            arr.add(listNode.val);
        }else{
            arr.add(listNode.val);
            return;
        }
    }
}



/**
 * 思路:
 *  从尾到头打印, 很自然想到用栈存储遇到的节点, 最后再弹栈打印;
 *  栈实现起来复杂, 考虑用函数的递归代替栈实现: 遇到节点, 先遍历后面的节点, 如果后面每节点再打印;
 *
 * 注意点:
 * 书写顺序
 *  (1)递归基, 终止条件,
 *  (2)递归逻辑, 自身操作
 */