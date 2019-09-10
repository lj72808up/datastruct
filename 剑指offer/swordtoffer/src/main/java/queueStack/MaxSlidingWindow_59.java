package queueStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * 返回滑动窗口中的最大值。

 * 示例:
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 *
 *   滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sliding-window-maximum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxSlidingWindow_59 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums.length==0 || k==0 || k>nums.length)
            return new int[0];

        int[] res = new int[nums.length-k+1];
        LinkedBlockingDeque<Integer> queue = new LinkedBlockingDeque<>();

        for (int i = 0; i < nums.length; i++) {
            while(queue.size()>0 && nums[queue.peekLast()] < nums[i]){   // 从尾巴开始, 队列中存在比带插入小的元素
                queue.pollLast();
            }
            queue.offerLast(i); // 将角标插入

            while(queue.size()>0 && i-queue.peekFirst()>=k){  // 删除角标超过范围的元素
                queue.pollFirst();
            }

            if(i>=k-1 && queue.size()>0){  // 当边里的元素到达窗口大小时, 开始输出窗口内的最大值
                res[i+1-k] = nums[queue.peekFirst()];
            }
        }
        return res;
    }
}


/**
 * 思路:
 *   (1) 使用一个双端对列模拟滑动窗口, 当像滑动中依次划入数据时, 就是从双端对列的尾部插入数据;
 *   (2) 由于滑动窗口中只要保留可能的最大值即可 , 因此每次从尾部插入数据时, 还要从尾向头依次判断双端队列上的元素是否比待插元素小,
 *       如果是的话, 说明双端对列上的这些元素已不可能成为滑动窗口中的最大值, 应该将其从双端对列中删除(这样保证队列的头部是滑动窗口中的最大值)
 *   (3) 由于滑动窗口在滑动时, 部分数据也需要滑出窗口; 那如何判断哪些元素已经滑出窗口了呢?
 *       为了达到这个目的, 双端队列中的元素不应该存储元素值, 而应该存储元素在原数组中的下标, 如果下标i超过限定范围了, 则应该将元素从队列头部删除
 *   (4) 当遍历过的元素个数超过窗口大小时, 就可以记录滑动窗口的最大值
 * */