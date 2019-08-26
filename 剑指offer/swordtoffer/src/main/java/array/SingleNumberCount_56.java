package array;

import java.util.ArrayList;

public class SingleNumberCount_56 {
    /** 题目描述:
     *      给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     * 示例 1:
     *      输入: [2,2,1] , 输出: 1
     * 链接：https://leetcode-cn.com/problems/single-number
     */
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res = res ^ num;
        }
        return res;
    }

    /** 题目描述:
     *      给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。
     * 示例 1:
     *      输入: [2,2,3,2] , 输出: 3
     * 链接: https://leetcode-cn.com/problems/single-number-ii/
     */
    public int singleNumberII(int[] nums) {
        int[] bitSum = new int[32];
        for (int i = 0; i < nums.length; i++) {
            int mask = 1;
            for (int j = 31; j >= 0; j--) {
                if((nums[i] & mask) != 0)
                    bitSum[j] = bitSum[j] + 1;
                mask = mask << 1;
            }
        }
        int res = 0;
        for (int i = 0; i < 32; i++) {
            if(bitSum[i] % 3 != 0)
                res += 1 << (31-i);
        }
        return res;
    }

    /** 题目描述:
     *      给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。
     * 示例 :
     *      输入: [1,2,1,3,2,5] , 输出: [3,5]
     * 链接：https://leetcode-cn.com/problems/single-number-iii
     */
    public int[] singleNumberIII(int[] nums) {
        int xorRres = singleNumber(nums);  // 最终结果是2个只出现1次的数字进行xor的结果
        int oneBitSite = findFirst1bit(xorRres); // 找到结果中倒数第一个1bit的位置
        int compare = 1 << oneBitSite;

        int[] res = new int[2];
        for (int i = 0; i < nums.length; i++) {
            if((nums[i]&compare) == 0)
                res[0] = res[0] ^ nums[i];
            else
                res[1] = res[1] ^ nums[i];
        }

        return res;
    }
    private int findFirst1bit(int num){
        /** 找到倒数第1个1bit的位置 */
        int compare = 1;
        int i = 0;
        for (; i < 32; i++) {
            if ((num & compare) != 0)
                break;
            compare = compare << 1;
        }
        return i;
    }
}

/**
 * 思路1:
 *   1. xor算法: (不带进位的加法: 1+1=0,1+0=1.0+0=0)
 *      (1) 0 xor 任何数 = 任何数
 *      (2) 任何数 xor 任何数 = 0
 *
 * 思路2:
 *   1.
 *
 * 思路3:
 *   1.
 * */
