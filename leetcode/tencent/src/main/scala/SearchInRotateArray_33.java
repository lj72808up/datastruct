/**
 * 题目描述:
 *   假设按照升序排序的数组在预先未知的某个点上进行了旋转。例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 *   搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 *   你可以假设数组中不存在重复的元素。
 *   你的算法时间复杂度必须是 O(log n) 级别。
 * 示例 1:
 *   输入: nums = [4,5,6,7,0,1,2], target = 0
 *   输出: 4
 * 示例 2:
 *   输入: nums = [4,5,6,7,0,1,2], target = 3
 *   输出: -1
 */
public class SearchInRotateArray_33 {
    public int search(int[] nums, int target) {
        if(nums.length == 0)
            return -1;
        int rotateIndex = searchMinInRotate(nums);

        if(nums[rotateIndex] == target){
            return rotateIndex;
        }else{
            if(nums[nums.length-1]>=target){
                return binarySearch(nums,rotateIndex,nums.length-1, target);
            }else{
                return binarySearch(nums,0,rotateIndex,target);
            }
        }
    }

    private int searchMinInRotate(int[] nums){
        int index1 = 0;
        int index2 = nums.length - 1;
        int mid = index1;

        while(nums[index1]>=nums[index2]){
            mid = (index1+index2)>>1;
            if (nums[index1]==nums[mid] && nums[index2]==nums[mid])
                return searchSequence(nums,index1,index2);
            if(nums[mid]>=nums[index1]){ // 前半段
                index1 = mid;
            }
            if(nums[mid]<=nums[index2])
                mid = index2;
        }

        return mid;
    }

    private int searchSequence(int[] nums, int index1, int index2){
        int min = nums[index1];
        int minIndex = index1;
        for (int i = index1; i <= index2; i++) {
            if(nums[i]<min){
                min = nums[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    private int binarySearch(int[] nums, int index1, int index2, int target){
        while (index1<index2){
            int mid = (index1+index2)>>1;
            if(nums[mid]==target)
                return mid;
            if(nums[mid]<target){
                index1 = mid;
            }else{
                index2 = mid;
            }
        }

        if(nums[index1] == target)
            return index1;
        else
            return -1;
    }

    public static void main(String[] args) {
        int[] nums = {4,5,6,7,0,1,2};
        int target = 0;
        System.out.println(new SearchInRotateArray_33().search(nums,target));
    }
}



/**
 * 思路:
 *  (1) 旋转数组中查找数字, 时间复杂度要到达O(logN), 暗示了二分查找. 可以先找到旋转点; 在判断在旋转数组的哪一段进行第二次二分查找
 *  (2) 旋转点查找: 设置2个指针, index1=0, index2=length-1
 *      1. 旋转点也是数组中的最小点:
 *          如果数组经过旋转, 则数组的第1个元素大于等于数组的最后一个元素, 即arr[index1]>=arr[index2];
 *          如果数组没经过旋转, 则数组的第一个元素小于数组的最后一个元素
 *      2. 利用二分查找侧颁发, 如果arr[mid]>=arr[index1], 则mid属于前半段;
 *                          如果arr[mid]<=arr[index2], 则mid属于后半段;
 *      3. 有一种情况无法判断, 就是arr[mid]==arr[index1] == arr[index2], 这种只能顺序查找
 * */