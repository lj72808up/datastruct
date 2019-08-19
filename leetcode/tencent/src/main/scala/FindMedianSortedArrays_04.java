import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * 题目描述:
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * 你可以假设 nums1 和 nums2 不会同时为空。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindMedianSortedArrays_04 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        // 如果m+n为基数, 则下面k1=k2; 如果m+n为偶数, 则下面k1<k2, 最后取两个平均值
        int k1 = (m+n+1)/2;
        int k2 = (m+n+2)/2;

        int k1Num = findKth(nums1,0,m-1,nums2,0,n-1,k1);
        int k2Num = findKth(nums1,0,m-1,nums2,0,n-1,k2);

        return 1.0*(k1Num+k2Num)/2;
    }

    /**
     * start,end都是角标; k是第几个,对应数组的角标为k-1
     */
    private int findKth(int[] arr1,int start1,int end1, int[] arr2, int start2, int end2, int k){
        int length1 = end1-start1+1;
        int length2 = end2-start2+1;

        if(length1 > length2)  // 确保arr1是短的数组
            return findKth(arr2,start2,end2,arr1,start1,end1,k);

        if(length1==0){
            return arr2[start2+k-1];
        }

        if(k==1){
            return Math.min(arr1[start1],arr2[start2]);
        }

        int mid = k/2;
        int i = Math.min(length1,mid) - 1 + start1 ;
        int j = Math.min(length2,mid) - 1 + start2 ;

        if(arr1[i]<arr2[j])
            return findKth(arr1,i+1,end1,arr2,start2,end2,k-(i-start1+1));
        else
            return findKth(arr1,start1,end1,arr2,j+1,end2,k-(j-start2+1));
    }




    public static void main(String[] args) {
        int[] B = {2};
        int[] A = {1,3,4};
        System.out.println(new FindMedianSortedArrays_04().findKth(A,0,A.length-1,B,0,B.length-1,3));
    }
}
