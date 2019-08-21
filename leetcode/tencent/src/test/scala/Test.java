import java.util.Arrays;

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        if(nums.length<3)
            return 0;
        int dif = Integer.MAX_VALUE;
        int res = 0;
        Arrays.sort(nums);
        for (int i = 0; i <= nums.length-3; i++) {
            int index1 = i+1;
            int index2 = nums.length-1;
            while(index2>index1) {
                int sum = nums[i] + nums[index1] + nums[index2];
                if (Math.abs(sum - target) < dif) {
                    res = sum;
                    dif = Math.abs(sum - target);
                }
                if (sum > target) {
                    index2--;
                } else if(sum < target){
                    index1++;
                }else{
                    res = target;
                    break;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1,1,1,0};
        System.out.println(new Solution().threeSumClosest(nums,-100));
    }
}