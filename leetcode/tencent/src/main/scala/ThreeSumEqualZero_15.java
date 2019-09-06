import java.util.*;
/**
 * 题目描述:
 *   给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 *  链接：https://leetcode-cn.com/problems/3sum
 */
class ThreeSumEqualZero_15 {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums.length<3)
            return res;

        Arrays.sort(nums);  // 让数组排序
        for (int i = 0; i <= nums.length-3; i++) {
            if(nums[i]>0)   // 这个数>0. 则后面的数全都大于0, 3数之和不可能等于0
                break;
            if (i-1>=0 && nums[i-1]==nums[i])
                continue;

            int index1 = i+1;
            int index2 = nums.length-1;

            while(index1<index2){
                int sum = nums[i]+nums[index1]+nums[index2];

                if(sum==0){
                    ArrayList<Integer> resCur = new ArrayList<>();
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
//        int[] nums = {-1,0,1,2,-1,-4};
        int[] nums = {0,0,0,0};
        System.out.println(new ThreeSumEqualZero_15().threeSum(nums));
    }
}
/**
 * 思路:
 *   (1)题目中的数组无序, 则没有任何已知条件的情况下, 只能暴力求解, 会使得算法复杂度达到O(n^3)
 *   (2)如果先对数组排序, 则就会得到1个条件, 前面的数字比后面的数字小; 因此:
 *      a. 从头到尾遍历每个排序后的数字, 以当前数字为基准, index1指向当前元素的后一个位置, index2指向最后一个元素
 *      b. 若cur+arr[index1]+arr[index2]==0, 则找到一组解;
 *          若结果>0, 则index2--;
 *          若结果<0, 则index1++;
 * [注意]:
 *   找到3数之和为0的时候, index1或index2中任选一个仍要移动, 进入下次循环, 否则会陷入死循环;
 * */