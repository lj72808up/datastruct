import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 有两个容量分别为 x升 和 y升 的水壶以及无限多的水。请判断能否通过使用这两个水壶，从而可以得到恰好 z升 的水？
 * 如果可以，最后请用以上水壶中的一或两个来盛放取得的 z升 水。
 * <p>
 * 你允许：
 * 装满任意一个水壶
 * 清空任意一个水壶
 * 从一个水壶向另外一个水壶倒水，直到装满或者倒空
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/water-and-jug-problem
 */
public class CanMeasureWater_365 {

    public static int generateKey(int[] arr) {
        return 100 * arr[0] + 10 * arr[1] + arr[2];
    }

    public static boolean isFind(int[] arr, int target) {
        boolean flag = false;
        for (int elem : arr) {
            if (elem == target) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public static void main(String[] args) throws InterruptedException {

        int[] capacity = {8, 3, 5};  // 3个水杯的容量
        int[] waters = {8, 0, 0};      // 水杯中存在的水量
        int target = 2; //

        class Cups {
            int[] warters;
            Cups pre = null;

            public Cups(int[] warters) {
                this.warters = warters;
            }

            public Cups generateNextCups(int fromIndex, int toIndex) {
                int[] copys = Arrays.copyOf(warters, warters.length);
                if (warters[fromIndex] == 0) {
                    return new Cups(copys);
                } else {
                    if (copys[fromIndex] + copys[toIndex] > capacity[toIndex]) {
                        copys[fromIndex] = copys[fromIndex] - (capacity[toIndex] - copys[toIndex]);
                        copys[toIndex] = capacity[toIndex];
                    } else {
                        copys[toIndex] = copys[toIndex] + copys[fromIndex];
                        copys[fromIndex] = 0;
                    }
                }
                return new Cups(copys);
            }

            @Override
            public String toString() {
                return Arrays.toString(warters);
            }
        }

        boolean flag = false;
        LinkedHashMap<Integer, Integer> exists = new LinkedHashMap<>();

        exists.put(generateKey(waters), 1); // 记录这种状态已经达到

        LinkedBlockingQueue<Cups> queue = new LinkedBlockingQueue<>();  // 用于广度遍历
        queue.offer(new Cups(waters));

        Cups finalCups = null;

        label:
        while (queue.size() != 0) {
            Cups curCups = queue.poll();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (j != i) {
                        Cups nextCups = curCups.generateNextCups(i, j);  // 第i杯导入第j杯
                        System.out.println(nextCups.warters[0] + nextCups.warters[1] + nextCups.warters[2]);
                        if (exists.get(generateKey(nextCups.warters)) == null) {  // 新状态未存在过
                            exists.put(generateKey(nextCups.warters), 1);

                            nextCups.pre = curCups;

                            if (isFind(nextCups.warters, target)) { // 找到目标
                                finalCups = nextCups;
                                break label;
                            } else {
                                queue.offer(nextCups);
                            }

                        }

                    }
                }
            }
        }

        System.out.println(finalCups);

    }
}
