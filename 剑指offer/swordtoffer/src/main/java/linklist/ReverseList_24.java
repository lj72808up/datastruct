package linklist;

/**
 * 题目描述:
 *   输入一个链表，反转链表后，输出新链表的表头。
 */
public class ReverseList_24 {
    public ListNode ReverseList(ListNode head) {
        if (head==null)
            return null;
        ListNode pre = null;
        ListNode cur = head;
        ListNode next = head.next;

        while (cur!=null){
            cur.next = pre;

            pre = cur;
            cur = next;
            if(next!=null)
                next = next.next;
            else
                next = null;
        }

        return pre;
    }
}

/**
 * 思路:
 *  构造空节点pre, 当前节点cur, next节点, pre和cur负责执行反转操作, next负责保证沿着原有方向进行迭代
 * 注意:
 *  链表操作, 在执行next操作前, 要先判断当前节点是否为空; 像数组一样, 在取角标时,先判断角标是否在合理范围内, 不会产生outofbounds
 * */