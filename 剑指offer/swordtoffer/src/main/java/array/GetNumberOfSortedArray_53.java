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









/**
 * 题目描述: 0~n-1中缺失的数字
 * 1个长度为n-1的递增数组中所有数字都是唯一的, 并且每个数字的范围都是0~n-1之内. 在范围0~n-1中有且只有一个数字不在数组中, 求该数字
 */

class MissingNumberInIncreaseArray{
    public int solution(int[] arr){
        if(arr.length==0)
            return -1;

        int index1=0;
        int index2 = arr.length-1;

        while(index1<index2){
            int mid = (index1+index2)>>1;
            if(arr[mid]==mid){
                index1 = mid+1;
            }else{
                index2 = mid;
            }
            System.out.println(index1+"::"+index2);
        }
        return index2;
    }

    public static void main(String[] args) {
        int[] arr = {0,1,2,3,4,6};
        System.out.println(new MissingNumberInIncreaseArray().solution(arr));
    }
}

/**
 * 思路:
 *  已知数组有2个特点: 长度为n-1, 数组中元素范围是(0~n-1这n个数), 数组是递增的
 *  (1) 首先想到, 只要计算出0~n-1的和s1, 再计算出数组中元素的和s2, 则s1-s2的差就是缺失的数字.
 *      但这种方法显然没利用到数组是递增的这个条件
 *  (2) 再想, 既然数组是递增有序的, 且元素范围是0~n-1, 角标范围是0~n-2, 数字唯一, 因此数组中开始的一些元素值和其角标是相同的,
 *      当遇到1个元素值和角标不同的数字时, 则因为递增的缘故, 后面所有元素的角标和元素值都不同; 因此问题准换为找到第一个元素值和角标不同的位置, 其角标就是缺失的值.
 *      因此想到利用二分查找找出第一个角标和元素值不同的位置:
 *          a. 如果mid角标和元素值不同, 则第一个不同的位置应该在其前面, 所以在(start和mid中找);
 *          b. 如果mid角标和元素值相同, 则第一个不同的位置应该在其后面, 所以在(mid+1和end中找);
 * */



/**
 * 题目描述: 数组中数值和下标形同的元素
 * 假设1个递增数组中, 每个元素都是整数且唯一的, 请在这个数组中找到任意一个数值等于其下标的元素.
 * 例如: {-3,-1,1,3,5}中, 3和它的下标相同
 * */
class FindSameNumberAndIndex{
    public int solution(int[] arr) throws Exception {
        if(arr.length==0)
            throw new Exception("cannot find in null array");

        int index1=0;
        int index2 = arr.length-1;

        while(index1<index2){
            int mid = (index1+index2)>>1;
            if(arr[mid]>mid){   //元素比角标大, 则其后面的所有元素都会比角标大
                index2 = mid-1;
            }else if(arr[mid]<mid){
                index1 = mid+1;
            }else{
                return mid;
            }
        }
        return index2;
    }

    public static void main(String[] args) throws Exception {
        int[] arr = {-3,-1,1,3,5};
        System.out.println(new FindSameNumberAndIndex().solution(arr));
    }
}