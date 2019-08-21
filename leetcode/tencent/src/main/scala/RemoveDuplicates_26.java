/**
 * 题目描述:
 *  给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
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
            return 0;
        int duplicatNum = 0;
        int index = 0;
        while(index<=length-2){
            while (index<=length-2 && nums[index] == nums[index+1]) {
                duplicatNum++;
                index++;
                nums[index-duplicatNum] = nums[index];
            }
            index++;
        }
        return length-duplicatNum;
    }

    public static void main(String[] args) {
        int[] nums = {1,1,2};
        System.out.println(new RemoveDuplicates_26().removeDuplicates(nums));
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]+",");
        }
    }
}
