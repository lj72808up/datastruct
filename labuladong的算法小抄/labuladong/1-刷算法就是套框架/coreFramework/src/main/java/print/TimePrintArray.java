package print;

public class TimePrintArray {
    public void print(int[][] arr) {
        if (arr == null || arr.length == 0 || arr[0].length == 0)
            return;

        int left = 0;
        int right = arr[0].length - 1;
        int up = 0;
        int down = arr.length - 1;

        for (; ; ) {
            // 从左到右打印
            for (int i = left; i <= right; i++) {
                System.out.print(arr[up][i] + ",");
            }
            up++;
            if (up > down) break;
            // 从上打到下
            for (int i = up; i <= down; i++) {
                System.out.print(arr[i][right] + ",");
            }
            right--;
            if (left > right) break;
            // 从右到左
            for (int i = right; i >= left; i--) {
                System.out.print(arr[down][i] + ",");
            }
            down--;
            if (up > down) break;
            // 从下到上
            for (int i = down; i >= up; i--) {
                System.out.print(arr[i][left] + ",");
            }
            left++;
            if (left > right) break;
        }
    }

    public static void main(String[] args) {
        int[][] arr = {new int[]{1, 2, 3, 4},
                new int[]{5, 6, 7, 8},
                new int[]{9, 10, 11, 12}
        };
        new TimePrintArray().print(arr);

    }
}
