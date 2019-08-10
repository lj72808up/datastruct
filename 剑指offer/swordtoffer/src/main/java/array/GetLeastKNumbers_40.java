package array;

import java.util.ArrayList;

/**
 * 题目描述
 * 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。
 */
public class GetLeastKNumbers_40 {
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if(input==null || input.length==0 || k>input.length || k<=0)
            return res;
        int start = 0;
        int end = input.length-1;
        int index = pivot(input,start,end);
        while (index!=k-1){
            if(index>k-1){
                end = index-1;
                index = pivot(input,start,end);
            }else{
                start = index+1;
                index = pivot(input,start,end);
            }
        }
        for (int i = 0; i < k; i++) {
            res.add(input[i]);
        }
        return res;
    }

    private int pivot(int[] arr,int start,int end){
        int pivot = arr[start];
        int index1 = start;
        int index2 = end;
        while(index1<index2){
            System.out.println(index1+":"+index2);
            while(index1<index2 && arr[index2]>=pivot) // 不能是>, 否则遇到相同值会死循环
                index2--;
            if(index2>index1)
                arr[index1] = arr[index2];
            while(index1<index2 && arr[index1]<=pivot)
                index1++;
            if(index2>index1)
                arr[index2] = arr[index1];
        }
        arr[index2] = pivot;
        return index2;
    }

    public static void main(String[] args) {
        int[] input = {2,4,7,9,34,66,233,2,3,4,6,9,34,67,234,67,34};
        int k = 8;
        System.out.println(new GetLeastKNumbers_40().GetLeastNumbers_Solution(input,k));
    }
}
