package linkedList;
/**
 * 题目描述:
 *      给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 *      k 是一个正整数，它的值小于或等于链表的长度。
 *      如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * 示例 :
 *      给定这个链表：1->2->3->4->5
 *      当 k = 2 时，应当返回: 2->1->4->3->5
 *      当 k = 3 时，应当返回: 3->2->1->4->5
 *
 * 链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group
 */

public class ReverseKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummyHead = new ListNode(-999);   // 构造 dummyHead 节点, 指向第一个元素
        dummyHead.next = head;

        ListNode pre = dummyHead; // 本次反转的前驱节点
        ListNode start;           // 本次反转的第一个节点
        ListNode end = dummyHead; // 本次反转的最后一个节点 (初始化用于后续判断)
        ListNode next;            // 本次反转的后继结点

        // 进入翻转代码: 当翻转好的这一段后面没有节点, 就停止翻转
        while (end.next != null) {
            start = pre.next;     // 计算 start 节点
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;   // end 后推 k 次, 获得新的 end 节点
            }
            if (end == null)      // end 为 null, 说明剩余节点不足 k 个, 直接返回, 不用翻转
                break;
            next = end.next;      // 计算 next 节点

            end.next = null;      // 打断 end 节点后面的指向, 形成独立链表方便翻转
            pre.next = reverse(start);   // 返回翻转后的新头结点, 且传入的 start 称为翻转后的链尾
            start.next = next;

            // 进入下次循环前更新 pre 和 end 指针
            pre = start;
            end = start;
        }

        return dummyHead.next;    // 因为 dummyHead 是构造的用来返回最后的头结点
    }

    private ListNode reverse(ListNode node) {
        // 翻转链表, 要用3个指针
        ListNode pre = null;
        ListNode cur = node;
        ListNode next = null;
        while (cur.next != null) {  // 后面还有节点, 就进行翻转
            next = cur.next;  // 记录 next
            cur.next = pre;   // 翻转 cur 和 pre

            // 进入下一轮循环前, 更新 cur 和 pre 指针
            pre = cur;
            cur = next;
        }
        return pre;
    }
}


/**
 * 思路 :
 *  1. 要通过4个指针确定反转链表的连接:
 *      (1) pre: 待反转链表的前驱
 *      (2) next: 待反转链表的后继
 *      (3) start: 待反转链表的第一个节点
 *      (4) end: 待反转链表的最后一个节点
 *
 *  2. 某段start~end的链表在翻转后, start变成最后一个节点, end变成第一个节点, 因此, 维护链表连通性只要:
 *      pre.next = end;
 *      start.next = next;
 *
 *  3. 为了便于返回, 创建一个head指针, 指向翻转后的头结点
 * */
