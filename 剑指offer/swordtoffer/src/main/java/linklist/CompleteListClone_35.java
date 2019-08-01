package linklist;

/**
 * 题目描述
 * 输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针指向任意一个节点），返回结果为复制后复杂链表的head。
 * （注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）
 */
public class CompleteListClone_35 {
    public RandomListNode Clone(RandomListNode pHead) {
        if(pHead==null)
            return null;
        RandomListNode cur = pHead;
        while(cur!=null){
            RandomListNode cloneNode = new RandomListNode(cur.label);
            cloneNode.next = cur.next;
            cur.next = cloneNode;
            cur = cloneNode.next;
        }

        cur = pHead;
        while(cur!=null){
            RandomListNode cloneNode = cur.next;
            if(cur.random!=null)
                cloneNode.random = cur.random.next;
            cur = cloneNode.next;
        }

        cur = pHead;
        RandomListNode cloneHead = pHead.next;
        while (cur!=null){ // 原链表找出
            RandomListNode cloneNode = cur.next;
            cur.next = cloneNode.next;
            cur = cur.next;
        }

        //TODO step3
        return pHead;
    }

    public static void main(String[] args) {

    }
}

/**
 * 思路:
 *  需要维护两种关系: next和random
 *      (1)next关系维护: 可以先复制一个节点, 插入到原节点和原节点的下一个节点之间, 这样从开头开始, 每个1个节点就是原链表中的节点
 *      (2)random关系维护: 找到每个新复制的节点, 将其random指向前一个节点的random节点的复制节点
 *      (3)奇数点的就是原链表;偶数点的就是新链表
 * */