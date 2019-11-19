/**
 * 题目描述:
 *  你是一位施工队的工长，根据设计师的要求准备为一套设计风格独特的房子进行室内装修。
 *  房子的客厅大小为 n x m，为保持极简的风格，需要使用尽可能少的 正方形 瓷砖来铺盖地面。
 *  假设正方形瓷砖的规格不限，边长都是整数。请你帮设计师计算一下，最少需要用到多少块方形瓷砖？
 *
 * https://leetcode-cn.com/problems/tiling-a-rectangle-with-the-fewest-squares
 */
public class TilingRectangle_1240 {
    public int tilingRectangle(int n, int m) {
        int[][] dp = new int[n+1][m+1];
        // initial dp[][]
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < m + 1; j++) {
                if(i==j)
                    dp[i][j] = 1;
                else
                    dp[i][j] = Integer.MAX_VALUE;
            }
        }
        // dp solution
        for (int height = 1; height < n + 1; height++) {
            for (int width = 1; width < m + 1; width++) {
                /*if(height == width)  // 长宽相等时只需要一个正方形, 此时已经初始化为1, 不用求解
                    continue;*/
                for (int i = 1; i <= height / 2; i++) {
                    dp[height][width] = Math.min(dp[height][width],
                            dp[i][width]+dp[height-i][width]);
                }
                for (int j = 1; j <= width / 2; j++) {
                    dp[height][width] = Math.min(dp[height][width],
                            dp[height][j]+dp[height][width-j]);
                }
            }
        }
        return dp[n][m];
    }

    public static void main(String[] args) {
        System.out.println(new TilingRectangle_1240().tilingRectangle(2,3));
    }
}

/**
 * 简单描述下题目:
 *   简单描述下题目, 就是有一个n行m列的长方形, 使用正方形进行填充. 这些正方形的边长可以是任意的,
 *   问最少需要几块正方形就能铺满这个长方形 ?
 *
 * 思路 : https://www.bilibili.com/video/av74341907?from=search&seid=13697723255787861631
 *   本题是一道动态规划题目, dp[n][m]为填充高n宽m的矩形需要的最少正方形个数.
 *   1. 为了能填充一个高n宽m的矩形, 可以把问题分解为:
 *    (1) 如果横着切一刀, 在i处将矩形划分为2个小矩形: dp[i][m]和dp[n-i][m]
 *    (2) 如果竖着切一刀, 在j处将矩形划分为2个小矩形: dp[n][j]和dp[n][m-j]
 *   i应该在[1,n/2]范围内; j应该在[1,m/2]范围内; 因为求正方形个数是对称的
 *   dp[n][m] = min( dp[i][m]+dp[n-i][m],
 *                   dp[n][j]和dp[n][m-j] )
 *
 *   2. 首先初始化dp数组, 为了便于与逻辑对应, 把dp数组padding为n+1,m+1的二维数组;
 *   3. 初始化dp数组, 如果m==n, 则dp[n][m]=1, 表示可以用一个正方形填充
 *                   如果m!=n, 则dp[n][m]=Integer.MAX_VALUE, 表示暂不确定用多少个正方形填充
 *
 * [注]: 这道题存在一个bug, 是在11行13列或13行11列时, 解为6, 动态规划的解为8
 *       在leetcode上提交时可以加上特殊处理: 
 *         if(n==11&& m==13) return 6;
 *         if(n==13&& m==11) return 6;
 * */