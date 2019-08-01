package linklist;

/**
 * 题目描述
 * 输入一个链表，输出该链表中倒数第k个结点。
 */
public class FindKthToTail {
    public ListNode FindKthToTail(ListNode head,int k) {
        if (head==null || k==0)
            return null;

        ListNode index1 = head;
        ListNode index2 = index1.next;

        for (int i = 0; i < k-1; i++) {
            if(index2!=null)
                index2 = index2.next;
            else
                return null;
        }

        while (index2!=null){  // 此时index1与index2以就位
            index2 = index2.next;
            index1 = index1.next;
        }
        return index1;
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        a.next = b;
        b.next = c;
        System.out.println(new FindKthToTail().FindKthToTail(a,1).val);
    }
}
/**
 * 思路:
 *  2个指针相差k个, 在后一个指针指向空时, 前一个指针指向的就是倒数第二个元素
*/