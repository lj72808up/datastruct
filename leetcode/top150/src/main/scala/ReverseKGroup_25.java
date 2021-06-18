/**
 * 题目描述:
 *      给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 *      k 是一个正整数，它的值小于或等于链表的长度。
 *      如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * 示例 :
 *      给定这个链表：1->2->3->4->5
 *      当 k = 2 时，应当返回: 2->1->4->3->5
 *      当 k = 3 时，应当返回: 3->2->1->4->5
 *
 * 链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group
 */
public class ReverseKGroup_25 {
    public ListNode reverseKGroup(ListNode node, int k) {
        ListNode head = new ListNode(-1);
        head.next = node;

        ListNode pre = head;    // 本次反转的前驱节点
        ListNode next;   // 本次反转的后继结点
        ListNode start;  // 本次反转的第一个节点
        ListNode end = head;    // 本次反转的最后一个节点

        while(end.next != null){
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            if(end == null)   // 剩余链表长度不足k, 不用再反转
                break;

            start = pre.next;
            next = end.next;

            end.next = null; // 打断end成独立的链表, 方便调用后面的reverse方法
            pre.next = reverse(start);
            start.next = next;

            pre = start;
            end = start;
        }

        return head.next;
    }

    private ListNode reverse(ListNode node) {
        ListNode pre = null;  // (1) 进入循环前记录 pre
        ListNode cur = node;
        ListNode next;

        while(cur!=null){
            next = cur.next;  // (2) 循环中获取 next
            cur.next = pre;   // (3) 循环更新 cur 的 next 指针

            pre = cur;        // (4) 更新 pre
            cur = next;       // (5) 更新 cur, 前进一步

        }
        return pre;
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(4);
        ListNode e = new ListNode(5);
        ListNode f = new ListNode(6);
        ListNode g = new ListNode(7);
        ListNode h = new ListNode(8);

        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        e.next = f;
        f.next = g;
        g.next = h;


        ListNode node = new ReverseKGroup_25().reverseKGroup(a, 3);
        while (node!=null){
            System.out.print(node.val+",");
            node = node.next;
        }
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