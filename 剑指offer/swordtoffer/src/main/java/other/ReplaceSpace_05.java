package other;

/**
 * 题目描述
 * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 */
public class ReplaceSpace_05 {
    public String replaceSpace(StringBuffer str) {
<<<<<<< HEAD
        char[] src= str.toString().toCharArray();
        int cnt = 0;
        for (int i = 0; i < src.length; i++) {
            if(src[i]==' ')
                cnt++;
        }
        int expend = cnt*2;
        char[] dist = new char[src.length+expend];
        int index = dist.length-1;
        for (int i = src.length-1; i >=0; i--) {
            if(src[i]==' ') {
                dist[index] = '0';
                dist[index - 1] = '2';
                dist[index - 2] = '%';
                index -= 3;
            }else{
                dist[index] = src[i];
                index--;
            }
        }
        return new String(dist);
=======
        return "";
>>>>>>> 3894867fe710d33c6218b61fb514c544d9656dd4
    }
}

/**
 * 思路:
 *   1. 替换后有1个占位的' '变成3个占位的'%20', 多出2个占位; 所以先计算出
 * */
