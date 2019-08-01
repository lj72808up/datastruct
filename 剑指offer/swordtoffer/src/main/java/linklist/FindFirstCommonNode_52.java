package linklist;

/**
 * 题目描述
 * 输入两个链表，找出它们的第一个公共结点。
 */
public class FindFirstCommonNode_52 {
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if(pHead1==null || pHead2==null)
            return null;
        int length1 = getLength(pHead1);
        int length2 = getLength(pHead2);
        int dif = 0;

        ListNode longList = null;
        ListNode shortList = null;

        if(length1>=length2){
            shortList = pHead2;
            longList = pHead1;
            dif = length1-length2;
        }else{
            shortList = pHead1;
            longList = pHead2;
            dif = length2-length1;
        }

        while (dif>0){
            longList = longList.next;
            dif--;
        }

        while (longList!=null){
            if(longList==shortList)
                break;
            longList = longList.next;
            shortList = shortList.next;
        }
        return longList;
    }

    private int getLength(ListNode pHead){
        int length = 0;
        while (pHead!=null){
            length++;
            pHead = pHead.next;
        }
        return length;
    }
}

/**
 * 思路: 如果2个链表有公共节点,则其公共节点后的所有节点都相同, 即会一直向通道2个链表的末尾
 *  因此, 只要从后向前遍历2个链表, 依次比较每个位置的两个节点, 找到最后一个相同的节点, 就是2个链表的第一个公共节点
 *  这听上去要使用栈, 这样的解法需要空间复杂度O(m+n)
 *
 *  从另一个角度看, 要想顺序遍历节点, 只要保证能2个链表同时遍历到结尾, 则这种比较过程, 就是在找第一个公共节点.
 *  如何做到2个链表同时遍历到结尾? 这需要先知道2个链表表达长度, 让短的链表先遍历n步, 之后的遍历就能比较得到公共节点
 * */
