package linklist;

/**
 * 题目描述
 * 给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null。
 */
public class EntryNodeOfLoop_23 {
    public ListNode EntryNodeOfLoop(ListNode pHead) {
        ListNode meetingNode = isLoop(pHead); // 是否有环, 有环则返回快慢指针相遇的节点
        if(meetingNode==null){
            return null;
        }else{
            int loopCount = getLoopCount(meetingNode);  // 环中节点的个数
            ListNode index1 = pHead;
            ListNode index2 = pHead;
            while (loopCount>0){     // 快指针县前进n步
                index2 = index2.next;
                loopCount--;
            }
            while (index1 != index2){ // 寻找环的入口
                index1 = index1.next;
                index2 = index2.next;
            }
            return index1;
        }
    }

    private ListNode isLoop(ListNode pHead){
        if (pHead==null)
            return null;
        ListNode fast = pHead;
        ListNode slow = pHead;
        while (fast != null){
            if(fast.next != null){
                fast = fast.next.next;
            }else{
                return null;
            }
            slow = slow.next;
            if(fast == slow)
                return fast;
        }
        return null;
    }
    private int getLoopCount(ListNode pHead){
        int count = 1;
        ListNode node = pHead.next;
        while (node!= pHead){
            count++;
            node = node.next;
        }
        return count;
    }
}

/**
 * 思路:
 *  1. 先确定链表中是否有环:
 *      设定快慢2个指针, 都从头结点出发; 快指针每次前进2不, 慢指针每次前进1步, 如果快指针追上了慢指针, 则说明链表中存在环.
 *      如果快指针走到了链表的结尾(next指向null), 则说明链表不包含环
 *  2. 确定环的入口
 *      如果我们知道环中有n个节点, 则可以设置2个指针, 其中一个指针先前进n步, 2个指针再以相同的速度每次前进1步, 则当这2个指针相遇,
 *      先走n步的指针已经绕环走了一圈, 2个指针恰好在换的入口处相遇
 *  3. 如何确定环中有多少个节点
 *      第一步中, 如果确定存在环, 则快慢2个指针必然会在环内相遇, 所以可以从这个相遇的节点开始, 每次前进一步, 直到又回到自己,
 *      就可以知道环中有几个节点了
 */