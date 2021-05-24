package monotonous;


import java.util.Arrays;
import java.util.LinkedList;

public class NextGreaterNum {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 3};
        System.out.println(Arrays.toString(nextGreaterElement(arr)));
    }

    public static int[] nextGreaterElement(int[] nums) {
        LinkedList<Integer> stack = new LinkedList<>();
        int[] res = new int[2 * nums.length];
        // 单调栈, 因为是后进先出, 为了保证当前元素都是和后面的元素比较, 要从后面开始遍历元素
        for (int i = 2 * nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && (stack.getLast() <= nums[i % nums.length])) {
                stack.pollLast();
            }
            if (stack.isEmpty()) {
                res[i] = -1;
            } else {
                res[i] = stack.getLast();
            }
            stack.offerLast(nums[i % nums.length]);  // 判断后当前元素入单调栈
        }
        System.out.println(Arrays.toString(res));
        int[] splitRes = new int[nums.length];
        for (int i = 0; i < splitRes.length; i++) {
            splitRes[i] = res[i];
        }
        return splitRes;
    }
}


