package multiThread;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class HorseRace {
    static final int RACE_LENGTH = 20;   // 跑道长度
    ArrayList<Horse> horses = new ArrayList<Horse>();
    private CyclicBarrier barrier;        // 用于同步的栅栏
    private ExecutorService service;

    public HorseRace(int horseCount) {
        // 初始化线程池
        this.service = Executors.newFixedThreadPool(horseCount);
        // 初始化 CyclicBarrier
        this.barrier = this.initBarrier(horseCount);
        // 初始化马匹
        for (int i = 0; i < horseCount; i++) {
            horses.add(new Horse(this.barrier));
        }
    }

    public void start() {
        for (Horse horse : horses) {
            service.execute(horse);
        }
    }

    private CyclicBarrier initBarrier(int horseCount) {
        return new CyclicBarrier(horseCount, new Runnable() {  // CyclicBarrier 一次循环归0后执行的操作
            @Override
            public void run() {
                System.out.println("[本轮跑步结束 ---------------------------------------------]");
                // 一轮结束后, 打印马匹的轨迹
                for (Horse horse : horses) {
                    horse.printTrace();
                }
                // 判断是否有马匹胜出
                for (Horse horse : horses) {
                    if (horse.getStrides() >= RACE_LENGTH) {
                        System.out.println(horse + "胜出");
                        service.shutdownNow();
                        return;
                    }
                }
                // 每轮跑步有停顿时间
                try {
                    Thread.sleep(2 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        new HorseRace(7).start();
    }

}


class Horse implements Runnable {
    static AtomicInteger count = new AtomicInteger();
    static Random r = new Random(47);
    private final int id = count.getAndIncrement();

    private int strides; // 已经跑了多少步
    private CyclicBarrier barrier;

    public Horse(CyclicBarrier barrier) {
        this.barrier = barrier;
        this.strides = 0;
    }

    public int getStrides() {
        return strides;
    }

    @Override
    public void run() {
        try {
            // 一次循环向前跑几步, 然后等待所有马向前跑一次
            while (!Thread.interrupted()) {
                strides += r.nextInt(10);
                barrier.await();   // 跑完一次, 进入等待
            }
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Horse{" +
                "id=" + id +
                '}';
    }

    // 打印该匹马已经跑过的距离
    public void printTrace() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.strides; i++) {
            sb.append("*");
        }
        sb.append(this.id);
        System.out.println(sb.toString());
    }
}