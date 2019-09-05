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





/**
 * 题目描述:(2个有序数组的中位数-leetcode中第四题)
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * 你可以假设 nums1 和 nums2 不会同时为空。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class FindMedianSortedArrays_04 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        // 如果m+n为基数, 则下面k1=k2; 如果m+n为偶数, 则下面k1<k2, 最后取两个平均值
        int k1 = (m+n+1)/2;
        int k2 = (m+n+2)/2;

        int k1Num = findKth(nums1,0,m-1,nums2,0,n-1,k1);
        int k2Num = findKth(nums1,0,m-1,nums2,0,n-1,k2);

        return 1.0*(k1Num+k2Num)/2;
    }

    /**
     * start,end都是角标; k是第几个,对应数组的角标为k-1
     */
    private int findKth(int[] arr1,int start1,int end1, int[] arr2, int start2, int end2, int k){
        int length1 = end1-start1+1;
        int length2 = end2-start2+1;

        if(length1 > length2)  // 确保arr1是短的数组
            return findKth(arr2,start2,end2,arr1,start1,end1,k);

        if(length1==0){
            return arr2[start2+k-1];
        }

        if(k==1){
            return Math.min(arr1[start1],arr2[start2]);
        }

        int mid = k/2;
        int i = Math.min(length1,mid) - 1 + start1 ;
        int j = Math.min(length2,mid) - 1 + start2 ;

        if(arr1[i]<arr2[j])
            return findKth(arr1,i+1,end1,arr2,start2,end2,k-(i-start1+1));
        else
            return findKth(arr1,start1,end1,arr2,j+1,end2,k-(j-start2+1));
    }




    public static void main(String[] args) {
        int[] B = {2};
        int[] A = {1,3,4};
        System.out.println(new FindMedianSortedArrays_04().findKth(A,0,A.length-1,B,0,B.length-1,3));
    }
}