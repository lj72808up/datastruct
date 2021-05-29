import java.util.Arrays;

public class CountPrimes {
    public static void main(String[] args) {
        new CountPrimes().solve(10);
    }

    public int solve(int n) {
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) {
                for (int j = 2 * i; j < n; j = j + i) {
                    isPrime[j] = false;
                }
            }
        }

        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i])
                count++;
        }

        //打印
        for (int i = 0; i < isPrime.length; i++) {
            System.out.println((i) + ":" + isPrime[i]);
        }
        return count;
    }
}
