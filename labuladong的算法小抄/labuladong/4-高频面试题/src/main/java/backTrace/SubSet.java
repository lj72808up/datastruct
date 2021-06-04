package backTrace;

import java.util.ArrayList;
import java.util.List;

public class SubSet {
    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        ArrayList<Integer> trace = new ArrayList<>();
        subset(nums, 0, trace);
        return res;
    }

    /**
     * 递归方法
     */
    private void subset(int[] nums, int start, ArrayList<Integer> trace) {
        /*if (trace.size() == 2) {    // 如果判断 trace 长度为2才加入 res, 则 res 表示长度为2的组合
            res.add(new ArrayList<>(trace));
        }*/
        res.add(new ArrayList<>(trace));

        for (int i = start; i < nums.length; i++) {
            trace.add(nums[i]);
            subset(nums, i + 1, trace);     // 这里一定是 i, 不是 start. start 作为起始, 限制的是第一个元素的起始位置
            trace.remove(trace.size() - 1);   // 移除最后一个元素
        }
    }


    public static void main(String[] args) {
        System.out.println(new SubSet().subsets(new int[]{1, 2, 3}));
    }
}






class SubSet2 {
    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        ArrayList<Integer> trace = new ArrayList<>();
        subset(nums, 0, trace);
        return res;
    }

    /**
     * 递归方法
     */
    private void subset(int[] nums, int start, ArrayList<Integer> trace) {
        res.add(new ArrayList<>(trace));

        for (int i = 0; i < nums.length; i++) {
            trace.add(nums[i]);
            subset(nums, i + 1, trace);     // 这里一定是 i, 不是 start. start 作为起始, 限制的是第一个元素的起始位置
            trace.remove(trace.size() - 1);   // 移除最后一个元素
        }
    }


    public static void main(String[] args) {
        System.out.println(new SubSet2().subsets(new int[]{1, 2, 3}));
    }
}