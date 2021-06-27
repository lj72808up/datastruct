package monotonous;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * 单调队列
 */
class MonotonousQueue {
    // 双向链表
    private LinkedList<Integer> queue = new LinkedList<Integer>();

    // 尾插
    public void push(int num) {
        while (!this.queue.isEmpty() && (this.queue.getLast() < num)) {
            this.queue.pollLast();
        }
        this.queue.offerLast(num);
    }

    // 单调队列是递减的, max 在 head 处
    public int getMax() {
        return queue.getFirst();
    }

    // 如果开头的元素是 target, 就出队; 否则, 说名单调队列头部元素, 已经被后来元素压扁
    public void pop(int target) {
        if (this.queue.getFirst() == target) {
            this.queue.pollFirst();
        }
    }
}

public class MaxInSlideWindow {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        System.out.println(Arrays.toString(maxSlidingWindow(nums, k)));
    }

    /**
     * 滑动窗口内的最大值
     *
     * @param nums: 一系列数字
     * @param k:    滑动窗口大小
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        MonotonousQueue monoQueue = new MonotonousQueue();
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i < k - 1) {  // 先让前 k-1 个数进入窗口, 后面再一个一个滑动
                monoQueue.push(nums[i]);
            } else {
                // 滑动
                monoQueue.push(nums[i]);     // 进入窗口
                res.add(monoQueue.getMax());
                monoQueue.pop(nums[i - k + 1]);  // 窗口内最开始的元素滑出(该int值可能已经在单调队列中被压扁;MonoQueue中会处理), 空出最后一个位置等待下一次滑入
            }
        }
        int[] arr = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            arr[i] = res.get(i);
        }
        return arr;
    }
}