import java.util.ArrayList;
import java.util.Random;

/** 问题描述:
 *  1. 这是一个被称做"蓄水池抽样"的经典问题, 问题主要描述为:
 *   有一个长度未知的数据流, 要从中抽取m个元素. 要求:
 *    a. 抽到的元素不能有重复的, 且每个元素被抽到的概率相等;
 *    b. 这个数据流的长度可能非常非常长(导致无法先将数据流全部缓存在内存内随机取角标)
 *
 *  2. leetCode的382题可采用该思路进行求解, 其描述如下:
 *    给定一个单链表，随机选择链表的一个节点，并返回相应的节点值。保证每个节点被选的概率一样。
 *    [注意]:该链表十分大且长度未知，如何解决这个问题？你能否使用常数级空间复杂度实现？
 *    https://leetcode-cn.com/problems/linked-list-random-node/
 * */
public class ReservoirSample_382 {
    class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }
    private ListNode head;
    private Random r = new Random();
    /** @param head The linked list's head.
    Note that the head is guaranteed to be not null, so it contains at least one node. */
    public ReservoirSample_382(ListNode head) {
        this.head = head;
    }

    /** Returns a random node's value. */
    public int getRandom() {
        ListNode[] res = getRandom(1);
        return res[0].val;
    }

    /** 抽取m个元素的通用方法 */
    public ListNode[] getRandom(int m) {
        ListNode[] res = new ListNode[m];
        ListNode cur = head;
        // 前m个元素插入蓄水池
        for (int i = 0; i < m; i++) {
            res[i] = cur;
            cur = cur.next;
        }
        int i = m;
        // 后面的元素开始替换
        while (cur != null){
            int rand = r.nextInt(i + 1);
            if(rand<m)
                res[rand] = cur;

            cur = cur.next;
        }
        return res;
    }
}


/** 思路: leetcode的题目是蓄水池抽样在m=1时的简化版本
 *  从leetcode的描述上可以知道, 链表非常长且长度未可知. 这意味着, 一般的对数组随机抽样的做法:
 *     " 把数组全部装进内存, 随机选择一个角标, 按照角标抽样数组元素" 的做法是不可取的.
 *   (1) 一个原因是因为元素个数太大无法一次性放入内存;
 *   (2) 另一个原因是leetcode中只要求抽样一个元素, 如果要求抽样多个元素, 有可能重复抽到一个元素;
 *
 *  蓄水池抽样的思路:
 *   (1) 先给出实现代码, 再给出每个元素抽样的概率相等的证明
 *     ```
 *     init: 初始化蓄水池arr, 大小m
 *     将数据流中的前m个元素插入到蓄水池arr中
 *     for i=m+1 to n{
 *         r = rand(1,i)
 *         if(r<m)
 *            arr(m) = dataFlow(i)
 *     }
 *     ```
 *    上述伪代码的意思为2步: 选择 + 替换
 *      先从数据流中选择1~m的元素, 作为被抽样到的元素;
 *      对后面m+1开始的元素做如下操作:
 *        这些元素都有m/i的概率被选中, 然后等概率的(1/m)替换掉被选中的元素; i为元素在数据流中的序号
 *
 *   (2) 概率证明: 证明每个元素被选中的概率为m/n
 *     a) 当i>m时
 *      P(第i个对象被选中) = P(第i个对象可以放入蓄水池) *
 *                          Product(
*                              P(其后面元素不放入蓄水池) +
*                              P(其后面元素放入蓄水池) * P(该后面的元素没有替换掉第i个元素)
 *                          )
 *      即: P(i) = m/i * [ (1 - m/(i+1)) + m/i+1 * (1-1/m)
 *                       * (1 - m/(i+2)) + m/i+2 * (1-1/m)
 *                       * ...
 *                       * (1 - m/n) + m/n * (1-1/m) ]
 *               = m/i * [ (i+1-m)/(i+1) + (m-1)/(i+1)
 *                       * (i+2-m)/(i+2) + (m-1)/(i+2)
 *                       * ...
 *                       * (n-m)/n + (m-1)/n ]
 *               = m/i * ( i/i+1
 *                       * i+1/i+2
 *                       * ...
 *                       * n-1/n
 *                       )
 *               = m/i * i/n
 *               = m/n
 *
 *      b) 当i<=m时
 *       P(第i个对象被选中) = P(第i个对象可以放入蓄水池|i<m) *
 *  *                          Product(
 * *                              P(m后面元素不放入蓄水池) +
 * *                              P(m后面元素放入蓄水池) * P(该后面的元素没有替换掉第i个元素)
 *       其中P(第i个对象可以放入蓄水池|i<m) = 1
 *       即: P(i) = 1 * [(1 - m/m+1) + m/m+1 * (1-1/m)
 *                        * (1 - m/(m+2)) + m/m+2 * (1-1/m)
 *                        * ...
 *                        * (1 - m/n) + m/n * (1-1/m)]
 *                = m/n
 * */

