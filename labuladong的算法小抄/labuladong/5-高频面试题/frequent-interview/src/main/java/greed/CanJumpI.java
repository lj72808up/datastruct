package greed;

/**
 * 能否调到最后一格
 */
public class CanJumpI {
    public boolean canJump(int[] nums) {
        int farthest = 0;
        for (int i = 0; i < nums.length; i++) {
            // 站在每一个向前跳到的最远距离，和当前最远距离作比较更新
            farthest = Math.max(farthest, i + nums[i]);
            if (farthest <= i) {  // <= i 说明碰到了0或负数的跳跃步
                return false;
            }
        }
        return farthest >= nums.length - 1;
    }

    public static void main(String[] args) {
        int[] jumSteps = new int[]{2,3,1,1,4};
        System.out.println(new CanJumpI().canJump(jumSteps));
    }
}
