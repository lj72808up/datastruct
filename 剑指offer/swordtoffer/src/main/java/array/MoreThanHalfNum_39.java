package array;

/**
 * 题目描述
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
 * 由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
 */
public class MoreThanHalfNum_39 {
    public int MoreThanHalfNum_Solution(int [] array) {
        int start=0;
        int end= array.length-1;
        int mid=(start+end)>>1;
        int pivot = partition(array, start, end);
        while (pivot!=mid){
            if (pivot>mid){
                end = pivot-1;
                pivot = partition(array, start, end);
            }else if (pivot<mid){
                start = pivot+1;
                pivot = partition(array, start, end);
            }
        }
        if (checkMoreThanHalf(array,array[pivot]))
            return array[pivot];
        else
            return 0;
    }

    private int partition(int[] arr,int start,int end){
        int pivot = arr[start];
        int index1 = start;
        int index2 = end;

        while(index1<index2){
            while (index1<index2 && arr[index2]>=pivot)
                index2 = index2-1;
            arr[index1] = arr[index2];
            while (index1<index2 && arr[index1]<=pivot)
                index1 = index1+1;
            arr[index2] = arr[index1];
        }

        arr[index1] = pivot;   // 结束条件: index1=index2
        return index1;
    }

    private boolean checkMoreThanHalf(int[] arr,int target){
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]==target)
                count ++;
        }
        return count*2>=arr.length;
    }
    public static void main(String[] args) {
        int[] arr = {1,1,1,1,2};
        int more = new MoreThanHalfNum_39().MoreThanHalfNum_Solution(arr);
        System.out.println(more);
    }
}


/**
 * 思路: 数组中有一个数字出现的次数超过数组长度的一半, 则该数字在排序后比出现在中位数位置, 所以只要找到中间那个数在排序后是什么, 再判断这个数是否出现次数大于一半,
 *     就可以得出结果.
 *     基于快排枢轴选取的办法, 再加上二分查找的递归思想来实现:
 *      快速排序枢轴选取后, 其左面所有数字小于数轴, 右面所有数字大于枢轴. 所以如果枢轴位置小于中位, 则target会出现在target+1位置到数组末尾;如果枢轴大于中位, target会出现在开头到target-1
 */