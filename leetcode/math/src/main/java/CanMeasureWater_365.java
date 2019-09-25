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
                        copys[fromIndex] = 0;
                        copys[toIndex] = copys[toIndex] + copys[fromIndex];
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


/*
class State {

    private final static int MaxFirst = 3;
    private final static int MaxSecond = 5;
    private final static int MaxThird = 8;


    int second;
    int num[] = {MaxFirst, MaxSecond, MaxThird};
    State preState;
    HashMap<Integer, Integer> mapping = new LinkedHashMap(); // 记录状态

*/
/*    public State(int second, int[] num, State preState, HashMap<Integer, Integer> mapping) {
        this.second = second;
        this.num = num;
        this.preState = preState;
        this.mapping = mapping;
    }

    public State(int first, int second, int third) {
        num[0] = first;
        num[1] = second;
        num[2] = third;
    }

    void init() {
    }*//*


    boolean canPour(int from, int to)//判断是否可以从from水壶中倒水到to水壶中
    {
        if (num[from] == 0)
            return false;

        if (num[to] == mapping.get(to))
            return false;
        else
            return true;
    }

    void pour(int from, int to)//倒水过程
    {
        if (num[from] + num[to] > mapping.get(to)) {
            num[from] = num[from] - (mapping.get(to) - num[to]);
            num[to] = mapping.get(to);
        } else {
            num[to] = num[to] + num[from];
            num[from] = 0;
        }
    }


};


*/
/**
 * 倒水杯水状态类：只负责倒水，以及状态的保存
 * <p>
 * 杯状态公共属性：可被所有Cup对象使用
 * <p>
 * 杯状态私有属性
 * <p>
 * 倒水操作
 *
 * @param fromIndex 倒水杯下标
 * @param toIndex   被倒水杯下标
 * @return返回下一个状态。若不存在，则返回null 显示各水杯水量
 * <p>
 * 倒水操作类型接口
 * <p>
 * 从A杯往B杯倒
 * <p>
 * 从A杯往C杯倒
 * <p>
 * 从B杯往A杯倒
 * <p>
 * 从B杯往C杯倒
 * <p>
 * 从C杯往A杯倒
 * <p>
 * 从C杯往B杯倒
 * @author Lance
 * @description倒水测试类 检查输入的杯水量是否合法
 * @param maxA    A杯最大容量
 * @param maxB    B杯最大容量
 * @param maxC    C杯最大容量
 * @param initA   A杯初始容量
 * @param initB   B杯初始容量
 * @param initC   C杯初始容量
 * @param targetA A杯目标容量
 * @param targetB B杯目标容量
 * @param targetC C杯目标容量
 * @return若合法则返回true，否则返回false 杯状态公共属性：可被所有Cup对象使用
 * <p>
 * 杯状态私有属性
 * <p>
 * 倒水操作
 * @param fromIndex 倒水杯下标
 * @param toIndex   被倒水杯下标
 * @return返回下一个状态。若不存在，则返回null 显示各水杯水量
 * <p>
 * 倒水操作类型接口
 * <p>
 * 从A杯往B杯倒
 * <p>
 * 从A杯往C杯倒
 * <p>
 * 从B杯往A杯倒
 * <p>
 * 从B杯往C杯倒
 * <p>
 * 从C杯往A杯倒
 * <p>
 * 从C杯往B杯倒
 * @author Lance
 * @description倒水测试类 检查输入的杯水量是否合法
 * @param maxA    A杯最大容量
 * @param maxB    B杯最大容量
 * @param maxC    C杯最大容量
 * @param initA   A杯初始容量
 * @param initB   B杯初始容量
 * @param initC   C杯初始容量
 * @param targetA A杯目标容量
 * @param targetB B杯目标容量
 * @param targetC C杯目标容量
 * @return若合法则返回true，否则返回false
 *//*

class Cup {
    */
/**
 * 杯状态公共属性：可被所有Cup对象使用
 *//*

    private static int[] max; // 最大容量
    public static boolean[][][] isArrive; // 已达：true已达，false未达, 记录已到3杯水的状态是否已到达过

    */
/**
 * 杯状态私有属性
 *//*

    private int[] cups; // 杯水
    private int action = 0;// 操作类型：参考Action接口

    public Cup(int[] cups) {
        this.cups = cups;
    }

    */
/**
 * 倒水操作
 *
 * @param fromIndex 倒水杯下标
 * @param toIndex   被倒水杯下标
 * @return返回下一个状态。若不存在，则返回null
 *//*

    public Cup pour(int fromIndex, int toIndex) {
        if (fromIndex < 0 || fromIndex > 3 || toIndex < 0 || toIndex > 3) {
            throw new ArrayIndexOutOfBoundsException();
        }

        if (fromIndex == toIndex) {// 倒水杯与被倒水杯相同
            return this;
        }

        isArrive[cups[0]][cups[1]][cups[2]] = true; //设置已达，并更改状态位
        action++;

        // 倒水
        int[] tempCups = cups.clone();

        if (tempCups[fromIndex] + tempCups[toIndex] > max[toIndex]) {  // 部分倒
            tempCups[fromIndex] = tempCups[fromIndex] - (max[toIndex] - tempCups[toIndex]);
            tempCups[toIndex] = max[toIndex];
        } else {   // 全倒
            tempCups[toIndex] = tempCups[fromIndex] + tempCups[toIndex];
            tempCups[fromIndex] = 0;
        }

        // 判断是否已遍历
        if (isArrive[tempCups[0]][tempCups[1]][tempCups[2]]) { // 已遍历
            return null;
        }

        return new Cup(tempCups);
    }

    public int[] getCups() {
        return cups;
    }

    public int getAction() {
        return action;
    }

    public static void setMax(int[] max) {
        Cup.max = max;
    }

    public static void setIsArrive(boolean[][][] isArrive) {
        Cup.isArrive = isArrive;
    }

    */
/**
 * 显示各水杯水量
 *//*

    public void display() {
        System.out.println(Arrays.toString(cups));
    }
}


*/
/**
 * 倒水操作类型接口
 *//*

class Action {
    */
/**
 * 从A杯往B杯倒
 *//*

    public static final int AToB = 0;
    */
/**
 * 从A杯往C杯倒
 *//*

    public static final int AToC = 1;
    */
/**
 * 从B杯往A杯倒
 *//*

    public static final int BToA = 2;
    */
/**
 * 从B杯往C杯倒
 *//*

    public static final int BToC = 3;
    */
/**
 * 从C杯往A杯倒
 *//*

    public static final int CToA = 4;
    */
/**
 * 从C杯往B杯倒
 *//*

    public static final int CToB = 5;
}


*/
/**
 * @author Lance
 * @description倒水测试类
 *//*

class PourWater {
    public static void main(String[] args) {

        System.out.println("请分别输入A杯、B杯、C杯的最大容量：");
        int maxA = 8;
        int maxB = 5;
        int maxC = 3;

        System.out.println("请分别输入A杯、B杯、C杯的初始容量：");
        int initA = 9;
        int initB = 0;
        int initC = 0;

        System.out.println("请分别输入A杯、B杯、C杯的目标容量：");
        int targetA = 4;
        int targetB = 4;
        int targetC = 0;

        // 判断所输入的信息是否合法
        if (!isLegal(maxA, maxB, maxC, initA, initB, initC, targetA, targetB, targetC)) {
//        scan.close();
            return;
        }

        int[] cup_max = {maxA, maxB, maxC};// 设置初始条件
        int[] init = {initA, initB, initC};
        int[] target = {targetA, targetB, targetC};
        boolean[][][] isArri = new boolean[maxA + 1][maxB + 1][maxC + 1];

        Cup.setMax(cup_max);
        Cup.setIsArrive(isArri);

        Stack<Cup> stack = new Stack<Cup>();// 创建栈，将初始顶点入栈
        Cup top = new Cup(init);
        stack.push(top);

        while (!stack.isEmpty()) {
            Cup cup = stack.peek();//取出顶部状态

            int[] cups = cup.getCups();
            if (cups[0] == target[0] && cups[1] == target[1] && cups[2] == target[2]) {// 若到达目标状态，退出
                break;
            }

            Cup temp = null;
            switch (cup.getAction()) {// 判断条件：若已经到达终态，则将栈顶出栈；否则执行相应的操作
                case Action.AToB:
                    temp = cup.pour(0, 1);
                    break;
                case Action.AToC:
                    temp = cup.pour(0, 2);
                    break;
                case Action.BToA:
                    temp = cup.pour(1, 0);
                    break;
                case Action.BToC:
                    temp = cup.pour(1, 2);
                    break;
                case Action.CToA:
                    temp = cup.pour(2, 0);
                    break;
                case Action.CToB:
                    temp = cup.pour(2, 1);
                    break;
                default:
                    stack.pop();
            }

            // 若能成功倒水，则将新节点入栈
            if (temp != null) {
                stack.push(temp);
            }
        }

        if (stack.isEmpty()) {
            System.out.println("无结果");
        }

        // 显示操作流程
        for (int i = 0; i < stack.size(); i++) {
            stack.get(i).display();
        }

    }

    */
/**
 * 检查输入的杯水量是否合法
 *
 * @param maxA    A杯最大容量
 * @param maxB    B杯最大容量
 * @param maxC    C杯最大容量
 * @param initA   A杯初始容量
 * @param initB   B杯初始容量
 * @param initC   C杯初始容量
 * @param targetA A杯目标容量
 * @param targetB B杯目标容量
 * @param targetC C杯目标容量
 * @return若合法则返回true，否则返回false
 *//*

    public static boolean isLegal(int maxA, int maxB, int maxC, int initA,
                                  int initB, int initC, int targetA, int targetB, int targetC) {
        // 筛选条件
        if (maxA < initA || maxB < initB || maxC < initC || maxA < targetA
                || maxB < targetB || maxC < targetC) {
            System.out.println("初始容量或者目标容量超出最大容量");
            return false;
        }

        if ((initA + initB + initC) != (targetA + targetB + targetC)) {
            System.out.println("初始杯水量与目标杯水量不匹配");
            return false;
        }

        if (maxA < 0 || maxB < 0 || maxC < 0 || initA < 0 || initB < 0
                || initC < 0 || targetA < 0 || targetB < 0 || targetC < 0) {
            System.out.println("杯水量不能小于0");
            return false;
        }

        if ((initA + initB + initC) == 0) {
            System.out.println("请输入初始杯水量");
            return false;
        }

        return true;
    }
}*/
