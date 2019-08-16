public class NextHighestPowerOf2 {
    public int nextHighestPowerOf2(int num){
        /**
         * 大于给定数num的最小2的次幂
         */
        int tmp = num - 1;
        tmp |= tmp >> 1;
        tmp |= tmp >> 2;
        tmp |= tmp >> 4;
        tmp |= tmp >> 8;
        tmp |= tmp >> 16;
        return tmp+1;
    }

    public static void main(String[] args) {
        System.out.println(new NextHighestPowerOf2().nextHighestPowerOf2(0));
        System.out.println(new NextHighestPowerOf2().nextHighestPowerOf2(15));
    }
}
