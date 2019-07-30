package array;

/**
 * 题目描述
 * 统计一个数字在排序数组中出现的次数。
 */
public class GetNumberOfSortedArray_53 {
    public int GetNumberOfK(int[] array , int target) {
        if (array.length==0 || target>array[array.length-1])  // 代码上看, 由于后面使用array[array.length-1], 所以先检查array.length==0
            return 0;
        int first = getFirstIndexOfTarget(array,target,0,array.length-1);
        int last = getLastIndexOfTarget(array,target,0,array.length-1);
        System.out.println(first+":"+last);
        if (first==-1 || last==-1)
            return 0;
        else
            return last-first+1;
    }

    private int getFirstIndexOfTarget(int[] array , int target, int index1, int index2) {

        while(index1<index2){
            int mid = (index1+index2)>>1;
            if(array[mid]>target){
                index2 = mid-1;
            }else if(array[mid]<target){
                index1 = mid+1;
            }else if (array[mid]==target){
                if (mid-1>=index1 && array[mid-1]==array[mid]){  // 由于要取数组的mid-1,注意检查数组角标, 不能小于下边界
                    index2=mid-1;
                }else{
                    index2=mid;
                    break;
                }
            }
        }

        return array[index2]==target? index2:-1;
    }

    private int getLastIndexOfTarget(int[] array , int target, int index1, int index2) {

        while(index1<index2){
            int mid = (index1+index2)>>1;
            if(array[mid]>target){
                index2 = mid-1;
            }else if(array[mid]<target){
                index1 = mid+1;
            }else if (array[mid]==target){
                if (mid+1<=index2 && array[mid+1]==array[mid]){ // 由于要取数组的mid+1,注意检查数组角标, 不能小于上边界
                    index1=mid+1;
                }else{
                    index2=mid;
                    break;
                }
            }
        }

        return array[index2]==target? index2:-1;
    }


    public static void main(String[] args) {
        int[] arr = {1,3,3,3,3,4,5};
        int site = new GetNumberOfSortedArray_53().GetNumberOfK(arr,6);
        System.out.println(site);
    }
}

/**
 * 思路:
 *  有序数组中查找元素出现的次数, 逐个查找花费O(n), 想到使用二分查找加速
 *      (1)只要使用2次二分查找, 分别找出target第一次出现的位置和target最后一次出现的位置, 2个相减就是数字出现的次数
 *      (2)二分查找第一次出现的位置, 需要在原二分查找上做出修改: 在发现arr[mid]==target时,不能直接退出, 要看前一个元素arr[mid-1]是否也等于target
 *         是的话应当改变index2=mid-1, 进入下次循环. 不是的话才可以直接跳出循环;
 *         二分查找找到最后一次出现的位置仍然利用这个思想
 *      (3)原版二分查找在BinarySearch中
 *
 * [注意点]:
 *  (1)数组的边界条件, 用角标取数组时, 要先检查交表是否会出现outofbounds异常
 *  (2)注意检查边界条件
 */