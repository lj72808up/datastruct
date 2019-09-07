/**
 * 题目描述:
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 * 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。
 *
 * 进阶:
 * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lru-cache
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */



import java.util.HashMap;

public class LRUCache_146 {
    private HashMap<Integer,DoubleLinkedNode> hashMap = new HashMap<>();  // 存储节点
    private DoubleLinkedNode head = new DoubleLinkedNode();
    private DoubleLinkedNode tail = new DoubleLinkedNode();

    private int cacheSize = 0;
    private int curSize = 0;

    public LRUCache_146(int cacheSize) {  // 构造器
        this.cacheSize = cacheSize;
        this.head.next = this.tail;
        this.tail.pre = this.head;
    }

    public int get(int key) {
        DoubleLinkedNode node = hashMap.get(key);
        if(node!=null){
            move2Head(node,false);
            return node.value;
        }else{
            return -1;
        }
    }

    public void put(int key, int value) {
        /**
         * put: 如果能已存在key, 就更新, 否则作为新节点插入; 两种情况都要movetohead
         */
        DoubleLinkedNode node = hashMap.get(key);
        if(node==null) {
            DoubleLinkedNode newNode = new DoubleLinkedNode(key, value);
            if (this.curSize < this.cacheSize) {
                move2Head(newNode, true);  // 头部插入新节点
                this.curSize++;
            } else {  // cache已经满了
                deleteTail();
                move2Head(newNode, true);
            }
            hashMap.put(key, newNode);
        }else{
            node.value = value;
            move2Head(node,false);
        }
    }

    private void move2Head(DoubleLinkedNode node,boolean isNew){
        if(node != null){
            // 在原位置补齐连接
            if(!isNew) {
                node.next.pre = node.pre;
                node.pre.next = node.next;
            }
            // 在head位置补齐连接
            node.next = head.next;
            node.next.pre = node;
            node.pre = head;
            head.next = node;
        }
    }

    private void deleteTail(){
        if (tail.pre!=head){
            // 1. 从hashmap中删除
            DoubleLinkedNode node = tail.pre;
            hashMap.remove(node.key);
            // 2. 从双向链表关系中删除
            tail.pre = tail.pre.pre;
            tail.pre.next = tail;
        }
    }


    public static void main(String[] args) {
        LRUCache_146 cache = new LRUCache_146( 2 );

        System.out.println(cache.get(2));       // 返回  1
        cache.put(2, 6);
        System.out.println(cache.get(1));     // 该操作会使得密钥 2 作废
        cache.put(1, 5);       // 返回 -1 (未找到)
        cache.put(1, 2);    // 该操作会使得密钥 1 作废
        System.out.println(cache.get(1));       // 返回 -1 (未找到)
        System.out.println(cache.get(2));       // 返回  3
    }
}


class DoubleLinkedNode {
    protected int key;
    protected int value;
    DoubleLinkedNode pre;
    DoubleLinkedNode next;

    public DoubleLinkedNode(int key, int value) {
        this.key = key;
        this.value = value;
    }

    public DoubleLinkedNode() { }
}

/**
 * LRUCache 对象会以如下语句构造和调用:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

/**
 * 思路: 双向链表 + 哈希表 (HashMap)  ==> 哈希表和双向链表使得查询和调整最近使用关系的操作变为o(1)
 *   (1) 首先, LRU缓存是一个根据key, 查找value的数据结构, 因此应该使用哈希表存储数据
 *   (2) 其次, 由于LRU要删除最近没有使用的节点, 因此, 可以使用链表表示节点操作的顺序:
 *          链表的表头是本次操作的节点, 下一个位置是上次操作过的, 在下个位置是上上次操作过的节点.
 *       由于链表的第一个元素会发生变化, 所以应该使用指针head, 指向链表的第一个节点;
 *       而且, 当LRU缓存满了以后, 还要删除最久未被使用的元素, 即UI奥德最后一个节点, 所以应该设置一个tail指针,
 *       让tail.pre指向链表的最后一个节点
 *   (3) get()操作: 首先从哈希表里查找key对应的value, 没有则返回null, 有则需要讲这个节点移到链表的头部
 *   (4) put()操作: 首先调用get, 如果找到该节点, 则更新对应的value, 若果没找到, 则创建新节点并加入链表头部
 *       (同时判断LRU的长度是否超过限制, 如果超过, 则应该先删除tail节点)
 * */