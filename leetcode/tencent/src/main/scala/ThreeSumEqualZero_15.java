import java.util.*;

class ThreeSumEqualZero_15 {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums.length<3)
            return res;

        Arrays.sort(nums);  // 让数组排序
        for (int i = 0; i <= nums.length-3; i++) {
            if(nums[i]>0)
                break;
            if (i-1>=0 && nums[i-1]==nums[i])
                continue;

            int index1 = i+1;
            int index2 = nums.length-1;

            while(index1<index2){
                ArrayList<Integer> resCur = new ArrayList<>();
                int sum = nums[i]+nums[index1]+nums[index2];
                if(sum==0){
                    resCur.add(nums[i]);
                    resCur.add(nums[index1]);
                    resCur.add(nums[index2]);

                    res.add(resCur);
                    index2--;
                }else if (sum<0){
                    index1++;
                }else{
                    index2--;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {-1,0,1,2,-1,-4};
        System.out.println(new ThreeSumEqualZero_15().threeSum(nums));
    }
}
/**
 * 思路:
 *   (1)题目中的数组无序, 则没有任何已知条件的情况下, 只能暴力求解, 会使得算法复杂度达到O(n^3)
 *   (2)如果先对数组排序, 则就会得到1个条件, 前面的苏子比后面的数字小, 因此, 可以逐个固定每个元素, 和2个index, index1指向元素的后一个位置, index2指向数组的最后一个元素
 *      如果3数字和大于0, 则小脚index2; 如果3数之和小于0, 则增加index1. 如果等于0 , 可以任选index1++或index2--, 让循环进行下去.
 *
 * 注意:
 *   找到2数之和为0的时候, 角标仍要移动, 进入下次循环, 否则会陷入死循环;
 * */