import java.util.Arrays;

public class RemoveDuplicate {
    public int[] removeDuplicateInArr(int[] nums) {
        int n = nums.length;
        if (n == 0)
            return nums;
        int slow = 0, fast = 1;
        while (fast < n) {
            if (nums[fast] != nums[slow]) {
                slow++;
                System.out.println(Arrays.toString(nums));
                System.out.println("交换:"+slow+":"+fast);
                nums[slow] = nums[fast];
                System.out.println(Arrays.toString(nums));
            }
            fast++;
        }
        System.out.println("去重后的有效个数:"+(slow+1));
        return nums;
    }

    public static void main(String[] args) {
        int[]arr = {1,1,2,2,3,4};
        System.out.println(Arrays.toString(new RemoveDuplicate().removeDuplicateInArr(arr)));
    }
}
