import java.util.ArrayList;
import java.util.List;

/**
 * 编码:
 * 格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个位数的差异。
 * 给定一个代表编码总位数的非负整数 n，打印其格雷编码序列。格雷编码序列必须以 0 开头。
 *
 * 链接：https://leetcode-cn.com/problems/gray-code
 */
public class GrayCode_89 {
    public List<Integer> grayCode(int n) {
        ArrayList<Integer> res = new ArrayList<>();
        res.add(0);   // n=0
        int head = 1; // 头部+1
        for (int i = 1; i <=n ; i++) {
            int lastLength = res.size();
            for (int j = lastLength-1; j >=0 ; j--) {
                int tmp = res.get(j);
                tmp = tmp + head;
                res.add(tmp);
            }
            head <<= 1;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new GrayCode_89().grayCode(2));
    }
}
/**
 * 思路:
 *   (1) 格列编码的特征:
 *       a. n位的格列编码, 表示数字的二进制表示有n位, 且相邻的2个数字其二进制只有1bit不同, 且格列编码规定以0开头
 *       b. 综上所述, n位格列编码中, 一定只有2^n个数, 且0时第一个数
 *   (2) 格列码的产生过程:
 *       设n-1位格雷码的集合位f(n-1), 则f(n)集合中的数字个数时f(n-1)的2倍, 因此可以这样从f(n-1)构造f(n)
 *       a. f(n-1)的每个数字, 二进制前面加上0(数值不变), 对f(n-1)这个集合做镜像, 每个数字的二进制在最前面+1
 *       b. 图示:
 *          n=0     n=1     n=2     n=3
 *          0        0      00      000
 *                   1      01      001
 *                          11      011
 *                          10      010
 *                                  110
 *                                  111
 *                                  101
 *                                  100
 *
 * */