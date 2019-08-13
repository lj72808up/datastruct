package other;
import linklist.ListNode;

/**
 * 题目描述:
 * 0~n-1这n个数排成一个圆圈, 从数字0开始, 每次从元去哪里删除第m个数字, 求出这个圆圈中剩下的最后一个数字
 */
public class LastRemainingInCycle_62 {
    public int LastRemaining_Solution(int n, int m) {
        if(n<=0 || m<=0)
            return -1;
        int count = n;
        // 组织环形链表
        ListNode first = new ListNode(0);
        ListNode pre = first;
        for (int i = 1; i <=n-1 ; i++) {
            ListNode node = new ListNode(i);
            pre.next = node;
            pre = node;
        }
        pre.next = first;

        while(count!=1){
            // 执行删除
            ListNode checkNode = first;
            for (int i = 1; i <= m-2; i++) {
                checkNode = checkNode.next;
            }
            checkNode.next = checkNode.next.next;
            first = checkNode.next;

            count--;
        }

        return first.val;
    }
}

/**
 * 思路:
 *  制作循环链表, 模拟每次删除第m个节点的操作, 直到链表中只剩1个节点
 * */
