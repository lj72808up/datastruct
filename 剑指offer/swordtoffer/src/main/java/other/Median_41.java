package other;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 题目描述
 * 如何得到一个数据流中的中位数？
 * 如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。
 * 如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。我们使用Insert()方法读取数据流，使用GetMedian()方法获取当前读取数据的中位数。
 */
public class Median_41 {
    private PriorityQueue<Integer> smallQueue = new PriorityQueue<Integer>();
    private PriorityQueue<Integer> bigQueue = new PriorityQueue<Integer>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2-o1;
        }
    });
    private int cnt = 0;

    public void Insert(Integer num) {
        if (this.cnt % 2 == 0){
            if(smallQueue.size()>0 && num>this.smallQueue.peek()){
                this.smallQueue.add(num);
                int tmp = this.smallQueue.poll();
                this.bigQueue.add(tmp);
            }else
                this.bigQueue.add(num);
        }else{
            if(bigQueue.size()>0 && num<this.bigQueue.peek()){
                this.bigQueue.add(num);
                int tmp = this.bigQueue.poll();
                this.smallQueue.add(tmp);
            }else
                this.smallQueue.add(num);
        }

    }

    public Double GetMedian() {
        if (this.cnt==0)
            return 0.0;
        if (this.cnt%2==1)
            return new Double(bigQueue.peek());
        else
            return 1.0*(bigQueue.peek()+smallQueue.peek())/2;
    }

    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        queue.add(11);
        queue.add(99);
        queue.add(3);

        System.out.println(queue.poll());
        System.out.println(queue.peek());
        System.out.println(queue.poll());
    }
}


/**
 * 思路:
 *  (1)设置两个堆,最大堆和最小堆, 为保证数据平均分配到两个堆中, 可以在数据总数是偶数时插入bigQueue, 数据总数是奇数时插入smallQueue
 *  (2)为了保证bigQueue的数据总是小于等于smallQueue, 当该插入bigQueue时, 却发现改数字比smallQueue的最小值还大, 该怎么办呢?
 *      此时可以先插入smallQueue, 然后再把smallQueue中的最小值弹出插入到bigQueue中.
 *
 * 注意:
 *  PriorityQueue默认是小顶堆, 复写Comparator的compare后返回正数, 则是大顶堆
 * */
