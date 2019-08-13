package other;

/**
 * 题目描述:
 *  给你一根长度为 n 绳子，请把绳子剪成 m 段（m、n 都是整数，2≤n≤58 并且 m≥2）。
 *  每段的绳子的长度记为k[0]、k[1]、……、k[m]。k[0]k[1] … k[m] 可能的最大乘积是多少？
 *  例如当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到最大的乘积18。
 */
public class MaxProductAfterCutting_14 {
    public int maxProductAfterCutting(int length) {
        if(length<=1)
            return 0;
        if(length==2)
            return 1;
        if(length==3)
            return 2;
        int[] res = new int[length+1];
        res[0] = 1;
        res[1] = 1;
        res[2] = 2;
        res[3] = 3;
        for (int i = 4; i <=length ; i++) {
            int product = 0;
            for (int j = 0; j <=i/2 ; j++) {
                int tmp = res[j]*res[i-j];
                if (tmp>product)
                    product = tmp;
            }
            res[i] = product;
        }
        return res[length];
    }
}
//https://www.acwing.com/problem/content/description/24/


/**
 * 思路:
 *   动态规划的思想: f(n)=max(f(k)*f(n-k)) , (n>3)
 *   用长度为n+1的一维数组存储每个长度的最优解
 * */