package strings;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 * 示例:
 *      输入: "25525511135"
 *      输出: ["255.255.11.135", "255.255.111.35"]
 */
public class RestoreIpAddresses_93 {

    public List<String> restoreIpAddresses(String s) {
        /** 入口方法 */
        int[] ip = new int[4];
        ArrayList<String> res = new ArrayList<>();
        helper(s,res,0,ip,0);
        System.out.println();
        return res;
    }

    private int parseInt(String str,int startIndex,int endIndex){
        /** 将字符串解析成数字 */
        if ((startIndex!=endIndex) && str.charAt(startIndex)=='0')
            return -1;    // 例如"011"开头是0, 这种是不合法的
        return Integer.parseInt(str.substring(startIndex, endIndex+1));
    }

    private String arr2Ip(int[] ip){
        /** 将4个元素的数组形成ip字符串 */
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < ip.length; i++) {
            builder.append(ip[i]);
            if(i<ip.length-1)
                builder.append(".");
        }
        return builder.toString();
    }

    private void helper(String str, ArrayList<String> res, int startIndex, int[] ip, int k){
        /** 帮助类 */
        if(k==4 && startIndex==str.length())
            res.add(arr2Ip(ip));
        if(k==4)   // 已满足ip的4个部分,但是字符串仍有剩余
            return;

        for (int i = 0; i <= 2; i++) {
            int endIndex = startIndex + i;
            if(endIndex>=str.length())
                return;
            int item = parseInt(str, startIndex, endIndex);
            if(item>=0 && item<=255){
                ip[k] = item;
                helper(str, res, endIndex+1,ip, k+1);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new RestoreIpAddresses_93().restoreIpAddresses("25525511135"));;
    }
}


/**
 * 思路:
 *    (1)设置start和end角标, 用来解析ip中的某个段:
 *       a. 如果这个数字是在0-255之间则合法
 *       b. 如果开头是0,且start!=end, 则是非法的. (eg:"011")
 *    (2) start设置为end+1, 进入新一轮的递归
 *       a. 如果已经找到4个数字, 且start==str.length; 则找到一个合法ip
 *       b. 如果已经找到4个数字, 但start小于str.length, 则本次查找ip失败, 直接返回;
 * */