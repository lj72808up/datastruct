/**
 * 题目描述:
 *  给定一个排序数组，你需要在原地删除重复出现的元素(所有不重复的元素都集结在数组头部)，使得每个元素只出现一次，返回移除后数组的新长度。
 * [注]:
 *  不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RemoveDuplicates_26 {
    public int removeDuplicates(int[] nums) {
        int length = nums.length;
        if(length <= 1)
            return length;

        int index2 = 0;
        for (int index1 = 0; index1 < nums.length; index1++) {
            while(index1<nums.length && nums[index1] == nums[index2])
                index1++;
            if(index2+1<nums.length && index1<nums.length) {
                nums[index2 + 1] = nums[index1];
                index2++;
            }
        }

        return index2+1;
    }

    public static void main(String[] args) {
//        int[] nums = {1,1,1,2,2,3};
        int[] nums = {1};
        System.out.println(new RemoveDuplicates_26().removeDuplicates(nums));
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]+",");
        }
    }
}


/**
 * 思路:
 *  (1) 双指针, 指针index1负责从头到尾每次迭代, 另一个index2负责记录当前查找的是哪个数字是否有重复;
 *      如果发现有重复的, 则index1++, 跳出循环时, index1已经不一样, 将nums[index2+1]=nums[index1]
 * */