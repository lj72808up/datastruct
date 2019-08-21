/**
 * 回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 *
 * 示例 1:
 *  输入: num1 = "2", num2 = "3"
 *  输出: "6"
 *
 * 示例 2:
 *  输入: num1 = "123", num2 = "456"
 *  输出: "56088"
 */
public class BigMultiply_43 {
    public String multiply(String num1, String num2) {
        if(num1.equals("0") || num2.equals("0"))
            return "0";
        if(num1==null || num2==null || num1.length()==0 || num2.length()==0)
            return "";
        char[] chs1 = num1.toCharArray();
        char[] chs2 = num2.toCharArray();

        // 结果数组
        int[] res = new int[chs1.length+chs2.length];  // 乘积结果的位数最多不超过2个长度之和
        /*for (int i = 0; i < res.length; i++) {
            res[i] = '0';
        }*/

        // 结果数组插入
        for (int i = chs2.length-1; i >= 0; i--) {
            for (int j = chs1.length-1; j >= 0; j--) {
                int extra = res[i+j+1];
                int curRes = (chs2[i]-'0') * (chs1[j]-'0') + extra;
                System.out.println(chs2[i]+"*"+chs1[j]+"+"+extra+"="+curRes);
                res[i+j+1] = curRes % 10;
                res[i+j] += curRes / 10;   // 算上上一位的计算的结果在该位置留下的记录
            }
        }

        // 去除前导0
        boolean flag = false;
        StringBuilder builder = new StringBuilder();
        for (int i=0; i < res.length; i++) {
            if(res[i]!=0 || flag ){
                flag = true;
                builder.append(res[i]);
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        System.out.println(new BigMultiply_43().multiply("0","0"));
    }
}
