package array;

/**
 * 题目描述
 * 统计一个数字在排序数组中出现的次数。
 */
public class GetNumberOfSortedArray_53 {
    public int getNumberOfTarget(int[] array , int target) {
        if (target>array[array.length-1])
            return -1;
    }

    private int getFirstIndexOfTarget(int[] array , int target, int index1, int index2) {
        int mid = (index1+index2)>>1;
        while(index1<index2){
            if(array[mid]>target){
                index2 = mid-1;
            }else if(array[mid]<target){
                index1 = mid+1;
            }else if (array[mid]==target){
                if (array[mid-1]==array[mid]){
                    index2=
                }
            }
        }
    }

    private int getLastIndexOfTarget(int[] array , int target, int index1, int index2) {

    }
}
