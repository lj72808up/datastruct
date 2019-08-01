package linklist;

/**
 * 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。
 * 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
 */
public class DeleteDuplication {
    public ListNode deleteDuplication(ListNode pHead) {
        if (pHead==null || pHead.next==null)
            return pHead;
        ListNode pre = new ListNode(-1);
        ListNode Head = pre; // 制造头节点, 开始的2个节点就是重复节点
        pre.next = pHead;
        ListNode cur = pHead;
        ListNode next = pHead.next;

        while (next!=null){
            if(next.val == cur.val){
                while (next!=null && next.val==cur.val)
                    next = next.next;
                pre.next = next;
                cur = next;
                if (cur!=null)
                    next = cur.next;
                else
                    next = null;
            }else{
                pre = cur;
                cur = next;
                next = cur.next;
            }

        }
        return Head.next;
    }
}
/**
 * 思路:
 *  重复的节点1个都不保留, 因此需要3个指针, pre指向前一个节点,cur当前节点, next后一个节点
 *      1. 如果cur.val==next.val, next再向后找直到找到1个不同val的节点, 连接pre和next
 *      2. 如果cur.val!-next.val, 3个指针全都向后移动
 *  为防止链表的头2个节点的val就相同, 因此构造空节点Head, 让Head.next指向链表的头一个节点, 最后返回值为Head.next
 *[注意]:
 *   链表题目中, 为方便操作, 通常要构造一个无意义的Head节点, 便于返回值(Head.next).
 */