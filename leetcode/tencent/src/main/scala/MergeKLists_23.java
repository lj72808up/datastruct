import java.util.List;

/**
 * 题目描述:
 *  合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 *
 * 示例:
 * 输入:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-k-sorted-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class MergeKLists_23 {
    public ListNode mergeKLists(ListNode[] lists) {

        ListNode head = new ListNode(-1);
        ListNode last = head;

        while(!isFinish(lists)){
            int minIndex = -1;
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < lists.length; i++) {
                if(lists[i]!=null && lists[i].val<=min){
                    minIndex = i;
                    min = lists[i].val;
                }
            }

            ListNode tmp = lists[minIndex];
            last.next = tmp;
            last = tmp;
            lists[minIndex] = lists[minIndex].next;
        }

        return head.next;
    }

    private boolean isFinish(ListNode[] lists){
        for (int i = 0; i < lists.length; i++) {
            if(lists[i]!=null){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);

        listNode1.next = listNode4;
        listNode4.next = listNode5;

        listNode2.next = listNode3;

        ListNode[] lists = {listNode1,listNode2};
        ListNode root = new MergeKLists_23().mergeKLists(lists);
        while (root!=null){
            System.out.print(root.val+"->");
            root = root.next;
        }
    }
}


class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

/**
 * 注意:
 *   链表问题, 往往是迭代问题, 当不好返回头结点的时候, 可以先设置一个空的head节点, 和一个用于迭代的tmp节点;
 *     (1) 初始化时让tmp = head;
 *     (2) 每次迭代更改tmp的连接, 则会把head节点的连接也一同脸上; 因此, 结尾返回head.next即可
 * */