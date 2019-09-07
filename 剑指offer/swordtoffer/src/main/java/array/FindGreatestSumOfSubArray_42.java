package array;

/**
 * 题目描述
 * HZ偶尔会拿些专业问题来忽悠那些非计算机专业的同学。今天测试组开完会后,他又发话了:在古老的一维模式识别中,常常需要计算连续子向量的最大和,当向量全为正数的时候,问题很好解决。
 * 但是,如果向量中包含负数,是否应该包含某个负数,并期望旁边的正数会弥补它呢？例如:{6,-3,-2,7,-15,1,2,2},连续子向量的最大和为8(从第0个开始,到第3个为止)。
 * 给一个数组，返回它的最大连续子序列的和，你会不会被他忽悠住？(子向量的长度至少是1)
 */
public class FindGreatestSumOfSubArray_42 {
    public int FindGreatestSumOfSubArray(int[] arr) {
        int curSum = 0;
        int maxSum = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
            if (curSum<0)
                curSum = arr[i];
            else
                curSum = curSum + arr[i];
            maxSum = Math.max(maxSum,curSum);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int[] arr = {2,-99,8,1};
        int maxSum = new FindGreatestSumOfSubArray_42().FindGreatestSumOfSubArray(arr);
        System.out.println(maxSum);
    }
}

/**
 * 思路 :
 *   从动态规划的角度看, 如果用f(i)表示以arr[i]为结束的子序列的和,
 *      则f(i) = arr[i]          ,(f(i-1)<0)
 *             = f(i-1)+arr[i]   ,(f(i-1)>=0)
 *   要求最大子序列和, 就是求max(f(i))
 *
 */