package other;

/**
 * 题目描述
 * 把只包含质因子2、3和5的数称作丑数（Ugly Number）。例如6、8都是丑数，但14不是，因为它包含质因子7。
 * 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
 */
public class UglyNumber_49 {
    public int GetUglyNumber_Solution(int index) {
        if(index<=0)
            return 0;

        int[] uglyNums = new int[index];
        uglyNums[0] = 1;
        int index2 = 0;
        int index3 = 0;
        int index5 = 0;

        int i = 1;
        while(i<index){
            int min = Math.min(Math.min(uglyNums[index2]*2,uglyNums[index3]*3),uglyNums[index5]*5);
            uglyNums[i] = min;
            if(uglyNums[index2]*2==min)
                index2++;
            if(uglyNums[index3]*3==min)
                index3++;
            if(uglyNums[index5]*5==min)
                index5++;

            i++;
        }
        return uglyNums[index-1];
    }

    /**
     * 判断一个数是否为抽数
     */
    private boolean isUgly(int num) {
        if(num==0)
            return false;

        while(num%2==0)
            num = num/2;
        while(num%3==0)
            num = num/3;
        while(num%5==0)
            num = num/5;
        return num==1;
    }

    public static void main(String[] args) {
        System.out.println(new UglyNumber_49().isUgly(2));
    }
}

/**
 * 一般思路:
 *  (1)最简单的, 我们只要找到如何判断一个数是不是丑数, 然后从1开始每次+1的判断丑数, 知道找到第N个丑数即可:
 *      判断丑数的方法为: 只要1个数能除以2(余数为0), 就连续除以2; 能除以3(余数为0), 就连续除以3; 能除以5(余数为0), 就连续除以5; 最后看整除后的数是不是1即可
 *      [注意]: num=0时要单独判断, 否则0对任何数的余数都是0,会陷入死循环
 *  (2)该方法在N特大的时候, 需要判断N次, 需要1中更快速的方法
 *     (该方法没有用到一个隐藏条件: 1个丑数, 必然是另一个丑数*2/*3/*5的结果)
 *
 * DP的思路:
 *   (1) 用f(i)表示第i个丑数,
 *     则f(i) = min(2*f(j),3*f(k),5*f(l)), (j,k,l>0)
 *       j,k,l: 为第(i-1)个丑数中, 通过哪个数*2/*3/*5得到的;
 *   (2) 输出f(N-1)即可
 * */