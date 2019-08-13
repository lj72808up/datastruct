package other;

/**
 * 题目描述
 * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。(不考虑大数情况)
 */
public class PowerInteger_16 {
    public double Power(double base, int exponent) {
        if(doubleEqual(base,0.0)&&exponent<0)
            return 0.0;  // 也可抛出异常
        if(doubleEqual(base,0.0))
            return 0.0;
        if(exponent==0)
            return 1;
        if(exponent>0){
            return positivePower(base,exponent);
        }

        if(exponent<0){
            double positiveRes = positivePower(base,-exponent);
            return 1.0/positiveRes;
        }
        return 0.0;
    }
    private double positivePower(double base, int exponent){
        double res = 1.0;
        for (int i = 0; i < exponent; i++) {
            res = res*base;
        }
        return res;
    }
    private boolean doubleEqual(double a, double b) {
        return Math.abs(a-b)<0.0000000000000003;  //(小数后16位)
    }


    public static void main(String[] args) {
        System.out.println(new PowerInteger_16().Power(2,-3));
    }
}


/**
 * 思路:
 *  不考虑大数情况下, 仍然有很多值得注意的地方:
 *  (1) 指数为负数的情况
 *  (2) 底数/指数为0的情况, (0的负指数问题)
 *  (3) double判等的情况
 * */