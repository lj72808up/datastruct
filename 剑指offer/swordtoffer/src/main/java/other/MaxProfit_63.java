package other;


public class MaxProfit_63 {
    /**
     * 题目1:
     * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     * 如果你只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
     *
     * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock
     */
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






class MaxProfit2_122 {
    /**
     * 题目2:
     *  给定一个数组，它的第i个元素是一支给定股票第i天的价格。
     *  设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
     * */
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
        System.out.println(new MaxProfit2_122().maxProfit(prices));
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







class MaxProfit_309{
    /**
     * 题目三:
     *    给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
     *    设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
     *
     * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown
     * */
    public int maxProfit(int[] prices) {
        int days = prices.length;  // 多少天
        
        int hold = Integer.MIN_VALUE;  // hold(0)
        int sell = 0; // sell(0)
        int rest = 0; // rest(0)

        for (int i = 1; i <= days; i++) {
            int pre_sell = sell;
            sell = hold + prices[i-1];
            hold = Math.max(hold,rest-prices[i-1]);
            rest = Math.max(rest,pre_sell); // rest
        }
        return Math.max(sell,rest);
    }
}

/**
 * 思路:
 *   1. 首先. 对于某天i, 股票有3种状态:
 *        (1) 前几天存在买入, 现在持有股票: hold
 *        (2) 前几天该出手的出手了, 现在不进行任何操作: rest
 *        (3) 前几天存在买入, 今天卖出股票: sell
 *   2. 状态转移示意图如下:
 *                +←-(前一天持有,←-←-←+
 *                |   今天还持有)     |
 *                +-------hold-------+
 *               ↙                   ↖
 *    (前一天持有,今天卖出)       (前一天什么也不做, 今天买入)
 *            ↙                         ↖
 *            ↓                           ↑
 *           sell -→-→→-→-→-→-→→-→-→ +-rest-+
 *                  (前一天卖出,      ↑      ↓
 *               今天休息不能操作)     +←-←-←-+
 *                              (前一天休息,今天仍休息)
 *
 *   3. 动态规划方程为:
 *      hold(i) = max(hold(i-1),res(i-1)-prices[i])
 *      sell(i) = hold(i-1) + prices[i]
 *      rest(i) = max(rest(i-1),sell(i-1))
 *
 *   4. hold初始化为MIN_VALUE, 因为等式中是max
 *      sell初始化为0
 *      rest初始化为0
 *      结果为max(sell(i),rest(i))
 * */








class MaxProfit_123{
    /** 题目描述:
     *      给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。设计一个算法来计算你所能获取的最大利润,你最多可以完成 两笔 交易。
     *  注意: 
     *      你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     *      同一天, 可以寄卖出股票,有买入股票
     *
     * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii
     */
    public int maxProfit(int[] prices) {
        if(prices.length<=1)
            return 0;

        int maxProfit = 0;
        int minValue = prices[0];
        int[] res1 = new int[prices.length];

        // 正向: 截止到第i天, 1次完整的交易获得的利润
        for (int i = 1; i < prices.length; i++) {
            maxProfit = Math.max(maxProfit,prices[i]-minValue);
            minValue = Math.min(minValue,prices[i]);
            res1[i] = maxProfit;
        }

        // 反向: 如果第i天后买入,得到的最大收益
        maxProfit = 0;
        int[] res2 = new int[prices.length];
        int maxValue = prices[prices.length-1];
        for (int i = prices.length-2; i >=0; i--) {
            maxProfit = Math.max(maxProfit,maxValue-prices[i]);
            maxValue = Math.max(maxValue,prices[i]);
            res2[i] = maxProfit;
        }

        // 获得2次交易下的最大收益
        maxProfit = 0;
        for (int i = 1; i <= prices.length-2; i++) {
            int splitValue = res1[i] + res2[i];
            maxProfit = Math.max(maxProfit,splitValue);
        }

        // 对比2次交易和1次交易, 哪一种可以获得最大利益
        for (int i : res1) {
            maxProfit = Math.max(maxProfit,i);
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        int[] prices = {1,2};
        System.out.println(new MaxProfit_123().maxProfit(prices));
    }
}
/**
 * 思路:
 *   1. 最多进行两次交易: 可以先用MaxProfit_63中的方法计算出1次交易的最大收益, 在计算出2次交易下的最大收益, 取2者中的较大值
 *   2. 两次交易下收益的最大值
 *      (1) 2次交易, 就是把数组划分为2段;
 *          如果从i处划分, 2次交易的结果为截止到第i天的收益+从第i天开始向最后一天为止的交易最大值
 *      (2) 从第i天开始向最后一天为止的交易最大值:
 *          借鉴正向计算的思路: 假设第i天卖出, 只要找到前面几天的最小值, 2者做差就是本次卖出交易的最大值; 用1个maxProfit记录最大值
 *          现在从反向计算: 假设第1天买入, 从最后一天开始到i为止最大的那天卖出, 则产生最大收益; 同样用1个maxProfit记录最大值
 *      (3) 正向结果和反向结果的和就是在i出分割的2次交易的最大利润和
 * */