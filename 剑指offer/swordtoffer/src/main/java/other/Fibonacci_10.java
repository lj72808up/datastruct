package other;

/**
 * 题目描述
 * 大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。
 * n<=39
 */
public class Fibonacci_10 {
    public int Fibonacci(int n) {
        if (n==0)
            return 0;
        if (n==1)
            return 1;
        // f(n) = f(n-1)+f(n-2) (n>=2)
        int i = 0; // f(0)
        int j = 1; // f(1)
        for (int k = 2; k <=n ; k++) {
            int tmp = i+j;
            i = j;
            j = tmp;
        }

        return j;
    }
}
