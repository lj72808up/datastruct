package other;

/**
 * 题目描述: 礼物的最大值
 * 1个m*n的棋盘每一格都有一个礼物, 每个礼物有不同的价值; 你可以从左上角开始拿格子里的礼物, 每次只能向左或向下移动一格, 直到达到棋盘右下角;
 * 给定一个棋盘及其上面的礼物, 请计算最多能拿到多少价值的礼物.
 *
 * 输入：
 * [
 *   [2,3,1],
 *   [1,7,1],
 *   [4,6,1]
 * ]
 *
 * 输出：19
 *
 * 解释：沿着路径 2→3→7→6→1 可以得到拿到最大价值礼物。
 */
public class GiftMaxValue_47 {
    public int getMaxValue(int[][] grid) {
        int[] dp = new int[grid[0].length];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = grid[0][i];
        }

        for (int j = 0; j <=grid.length-1 ; j++) {
            for (int i = 0; i <= grid[0].length-1 ; i++) {
                int left = 0;
                int up = 0;
                if(i!=0)
                    left = dp[i-1];
                if(j>0)
                    up = dp[i];
                dp[i] = Math.max(up,left) + grid[j][i];
            }
        }
        return dp[dp.length-1];
    }

    public static void main(String[] args) {
        int[][] grid={{1,2},{3,4}};
        System.out.println(new GiftMaxValue_47().getMaxValue(grid));
    }
}


/**
 * 这是一个典型的动态规划问题:
 *      先用递归的思路来想问题, 定义函数f(i,j)为格子(i,j)达到的礼物最大和;
 *          f(i,j) = max{f(i-1,j),f(i,j-1)}+v(i,j)
 * */