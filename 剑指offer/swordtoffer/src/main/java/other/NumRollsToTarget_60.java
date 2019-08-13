package other;

/**
 * 这里有 d 个一样的骰子，每个骰子上都有 f 个面，分别标号为 1, 2, ..., f。
 * 我们约定：掷骰子的得到总点数为各骰子面朝上的数字的总和。
 * 如果需要掷出的总点数为 target，请你计算出有多少种不同的组合情况（所有的组合情况总共有 f^d 种），模 10^9 + 7 后返回。(因为结果很大, 可能会超过64位正数所表示的最大值)
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-dice-rolls-with-target-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NumRollsToTarget_60 {

    public int numRollsToTarget(int d, int f, int target) {
        final int MOD = 1000000007;
        int dp[][] = new int[d+1][d*f+1];
        for (int i = 1; i <=f ; i++) {
            dp[1][i] = 1;
        }
        for (int i = 2; i <=d ; i++) {  // 第i个骰子
            for (int j = i; j <=i*f ; j++) { // 所有可能取到的和
                for (int k = 1; k <=f ; k++) {
                    if(j-k>0) // 这步一定要判断角标合法性
                        //dp[i][j] += dp[i-1][j-k];
                        dp[i][j] = (dp[i][j] + dp[i - 1][j - k]) % MOD;
                }
            }
        }
        if(target>=d && target<=d*f) {
            return dp[d][target];
        }else
            return 0;
    }


    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(new NumRollsToTarget_60().numRollsToTarget(30,60,500));
    }
}
