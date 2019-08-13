package other;

/**
 * 题目1:
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 如果你只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxProfit_63 {
    public int maxProfit(int[] prices) {
        if(prices.length<=1)
            return 0;
        int minValue = Integer.MAX_VALUE;
        int maxIncome = 0;
        for (int i = 0; i < prices.length; i++) {
            if(prices[i]-minValue > maxIncome)
                maxIncome = prices[i]-minValue;
            if(prices[i]<minValue)
                minValue = prices[i];
        }
        return maxIncome;
    }
}

/**
 * 思路:
 *   假设获取利润最大时的交易卖出点是当前点, 则只要找到前面接个元素中最小的时候最为买入即可;
 *   因此, 可以值遍历数组一次, 每个节点都作为假设的卖出点; 使用MinValue变量记录目前为止的价格最低点; 使用maxValue记录价格最高点
 * */





/**
 * 题目2:
 *  给定一个数组，它的第i个元素是一支给定股票第i天的价格。
 *  设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 * */
class MaxProfit2 {
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int i = 0;
        while(i<prices.length){
            while(i+1<=prices.length-1 && prices[i]>=prices[i+1])
                i++;
            int valley = i;
            while(i+1<=prices.length-1 && prices[i]<=prices[i+1])
                i++;
            int peak = i;
            maxProfit += prices[peak]-prices[valley];
            i++; // 找到波峰波谷后, 指针+1进入下次比较
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};
        System.out.println(new MaxProfit2().maxProfit(prices));
    }
}

/**
 * 思路:
 *  leetcode官方题解: https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/solution/mai-mai-gu-piao-de-zui-jia-shi-ji-ii-by-leetcode/
 *  因为可以尽可能多的进行交易, 所以数字绘制到图表中, 会形成n个波峰辟谷, 只要在所有的波峰波谷处进行交易, 就能获得利润最大值; 而且我们不能缺少某一组波峰波谷上的计算,
 *  否则会使得利润缺少这次的交易差额
 * ^              .
 * |         .   . .
 * |  .     . . .   .
 * | . .   .   .     .
 * |.   . .           .
 * |     .             . .
 * |                    .
 * |-------------------------->
 *
 * 注意:
 *    while循环中, 在找到波峰辟谷后, 指针+1进入下次比较
 * */






/**
 * 题目三:
 *
 * */