public class IsPowerOfTwo_231 {
    public boolean isPowerOfTwo(int n) {
        /**
         2的幂, 其二进制对应只有首位为1, 其余为0的数; 即n&(n-1)的结果为0
         */
        if (n<=0)
            return false;
        else{
            int res = n&(n-1);
            System.out.println(res);
            return  res == 0;
        }
    }


    public static void main(String[] args) {
        System.out.println(new IsPowerOfTwo_231().isPowerOfTwo(0));
    }
}


/**
 * 思路:
 *  (1) 2的幂, 其二进制对应只有首位为1, 其余为0的数; 即n&(n-1)的结果为0
 *  (2) 0和负数不能按照上述规则判断, 且都不是2的幂
 * */