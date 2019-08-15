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
        DoubleLinkedNode newNode = new DoubleLinkedNode(key, value);
        if (this.curSize<this.cacheSize){
            move2Head(newNode,true);  // 头部插入新节点
            this.curSize++;
        }else{  // cache已经满了
            deleteTail();
            move2Head(newNode,true);
        }
        hashMap.put(key,newNode);
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
            node.next.pre = head;
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

        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // 返回  1
        cache.put(3, 3);    // 该操作会使得密钥 2 作废
        System.out.println(cache.get(2));       // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得密钥 1 作废
        System.out.println(cache.get(1));       // 返回 -1 (未找到)
        System.out.println(cache.get(3));       // 返回  3
        System.out.println(cache.get(4));       // 返回  4

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
 *   (1) 用哈希表作为缓存, 提供查询功能
 *   (2) 哈希表中的每个元素, 都作为双向链表中的一个节点连接起来
 *   (3) add操作时, 创建顶点都在双向链表的头部创建(头哦不表示刚刚操作过该节点), 同时, 如果发现插入时双向链表的size==缓存大小, 则应该先删除tail指向的元素,
 *       然后再创建节点在头部
 *   (4) get操作, 从哈希表里get数据后, 把该节点move到链表头部
 * */