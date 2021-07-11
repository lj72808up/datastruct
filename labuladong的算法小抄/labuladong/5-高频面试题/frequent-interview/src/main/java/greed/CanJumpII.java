package greed;

public class CanJumpII {
    public int minJumpStep(int[] nums) {
        int farthest = 0;
        int stepNum = 0;
        int end = 0;
        for (int i = 0; i < nums.length; i++) {
            // 站在每一个向前跳到的最远距离，和当前最远距离作比较更新
            farthest = Math.max(farthest, i + nums[i]);
            if (end == i) {
                stepNum++;
                end = farthest;
            }
        }
        return stepNum;
    }

    public static void main(String[] args) {
//        int[] jumSteps = new int[]{2, 3, 1, 1, 4};
        int[] jumSteps = new int[]{1, 2, 1, 1, 1};
        System.out.println(new CanJumpII().minJumpStep(jumSteps));
    }
}
