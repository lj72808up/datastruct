package other;

import java.util.Arrays;

/**
 * 题目描述
 * LL今天心情特别好,因为他去买了一副扑克牌,发现里面居然有2个大王,2个小王(一副牌原本是54张^_^)...
 * 他随机从中抽出了5张牌,想测测自己的手气,看看能不能抽到顺子,如果抽到的话,他决定去买体育彩票,嘿嘿！！“红心A,黑桃3,小王,大王,方片5”,
 * “Oh My God!”不是顺子.....LL不高兴了,他想了想,决定大\小 王可以看成任何数字,并且A看作1,J为11,Q为12,K为13。
 * 上面的5张牌就可以变成“1,2,3,4,5”(大小王分别看作2和4),“So Lucky!”。LL决定去买体育彩票啦。
 * 现在,要求你使用这幅牌模拟上面的过程,然后告诉我们LL的运气如何，
 * 如果牌能组成顺子就输出true，否则就输出false。为了方便起见,你可以认为大小王是0。
 */
public class IsContinuous_61 {
    public boolean isContinuous(int[] numbers) {
        if(numbers==null || numbers.length==0)
            return false;
        if(numbers.length==1)
            return true;

        Arrays.sort(numbers);  // 此后numbers就是排序数组了

        int zeroNum = 0;
        int requestCnt = 0;

        // 0的个数
        for (int i = 0; i < numbers.length; i++) {
            if(numbers[i]==0)
                zeroNum++;
        }
        // 需要的0个数
        for (int i = zeroNum; i<=numbers.length - 1; i++) {
            if(i+1<=numbers.length-1) {
                int dif = numbers[i + 1] - numbers[i];
                if (dif == 0)   // 对子不是顺子
                    return false;
                if (dif > 1)
                    requestCnt += dif - 1;
            }
        }
        return requestCnt<=zeroNum;
    }
}


/**
 * 思路:
 *  (1) 所有牌排序
 *  (2) 统计0的个数, 0可以代替任意牌
 *  (3) 从非0的位置开始, 每个元素和一下个未知的元素做差, 需要0的个数为dif-1 (eg:2,4中间只须一个3, 4-2-1得来)
 *  (4) 对子不能作为顺子, 且最后比较0的个数和所需填补空缺的个数
 *
 * 注意:
 *  (1) 非0开始的位置, 就是0的个数
 * */