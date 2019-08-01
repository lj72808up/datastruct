package linklist;

/**
 * 题目描述
 * 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
 */
public class MergeTwoSortedList_25 {
    public ListNode Merge(ListNode list1,ListNode list2) {
        ListNode n1 = list1;
        ListNode n2 = list2;

        ListNode Head = new ListNode(-1);
        ListNode cur = Head;
        
        while (n1!=null || n2!=null){
            if (n1!=null && n2!=null){
                ListNode smaller = null;
                if(n1.val<=n2.val){
                    smaller = n1;
                    n1 = n1.next; // 维护2个有序列表的连接
                }else{
                    smaller = n2;
                    n2 = n2.next; // 维护2个有序列表的连接
                }
                cur.next = smaller;
                cur = cur.next;
            }else if(n1==null){
                cur.next = n2;
                break;
            }else if (n2==null){
                cur.next = n1;
                break;
            }
        }

        return Head.next;
    }
}
/**
 * 注意:
 *  注意无意义的Head节点构造, 和另一个链表为空时, 急时break
 */