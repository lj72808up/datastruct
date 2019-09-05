package string;

/**
 * 题目描述
 * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
 * 例如，字符串"+100","5e2","-123","3.1416"和"-1E-16"都表示数值。 但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是。
 */
public class IsNumeric_20 {
    public boolean isNumeric(char[] str) {
        if(str==null)
            return false;
        int end = 0;
        end = scanInteger(str,0);
        if(end+1<str.length && str[end+1]=='.') {
            int decimal = scanDecimal(str, end + 1);
            if(decimal<=end) // 证明小数点后没有数字
                return false;
            else end = decimal;
        }
        if(end+1<str.length && (str[end+1]=='e' || str[end+1]=='E')) {
            if (end + 2 > str.length - 1)  // e后面没有数字了
                return false;
            else
                end = scanInteger(str, end + 2);
        }
        return !(end<str.length-1);
    }
    // 找到整数包含的最后一个下标
    private int scanInteger(char[] str, int start){
        if(start>=str.length)
            throw new RuntimeException("fail");
        int i = start;
        if(str[start]=='+' || str[start]=='-')
            i = start+1;
        while(i<str.length && str[i]<='9' && str[i]>='0')
            i++;
        return i-1;
    }
    private int scanDecimal(char[] str,int start){
        if(str[start]!='.')
            return start-1;
        int i = start+1;  // 小数点后面
        while(i<str.length && str[i]<='9' && str[i]>='0')
            i++;
        return i-1;
    }

    public static void main(String[] args) {
        String str = "2e2";
        System.out.println(new IsNumeric_20().isNumeric(str.toCharArray()));
    }
}

/**
 * 思路:
 *  判断一个字符串是否是数字, 需要找3部分: 整数部分,紧跟着小数点的小数部分,紧跟着'e'/'E'的指数部分; 且证书和指数部分都可能以(+/-)开头
 *  (1)首先尽可能多的三秒0~9数字(开头可能是+/-)
 *  (2)看接下来的小数部分
 *  (3)如果遇到'E'/'e', 则开始扫描指数部分
 * */
