package array;

import java.util.ArrayList;

/**
 * 题目描述
 * 输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，如果有多对数字的和等于S，输出两个数的乘积最小的。
 * 输出描述:
 */
public class FindNumbersWithSum_57 {
    public ArrayList<Integer> FindNumbersWithSum(int [] array, int sum) {

        int index1 = 0;
        int index2 = array.length-1;
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();

        while(index1<index2){
            if(array[index1]+array[index2] == sum){
                ArrayList<Integer> subRes = new ArrayList<Integer>();
                subRes.add(array[index1]);
                subRes.add(array[index2]);
                res.add(subRes);
                index1++;
            }else if(array[index1]+array[index2]>sum){
                index2--;
            }else if(array[index1]+array[index2]<sum){
                index1++;
            }
        }
        // 以下代码找到乘积最小的
        int min = Integer.MAX_VALUE;
        int site = 0;  // 第几个结果的乘积最小
        for (int i = 0; i < res.size(); i++) {
            int product = res.get(i).get(0) * res.get(i).get(1);
            if(product<min){
                min = product;
                site = i;
            }
        }
        System.out.println(res+":"+site);
        return res.size()>0 ? res.get(site):new ArrayList<>();
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
        System.out.println(new FindNumbersWithSum_57().FindNumbersWithSum(arr,21));
    }
}

/**
 * 思路:
 *   从递增数组中找到2个数字的和为s, 如果任选2个数字, 其和可能大于s也可能小于s, 若大于s和小于s的操作不同则会很方便操作
 *   于是想到用idex1指向0, index2指向arr.length-1, 若和大于s, 则可减小index2, 若和小于s可增大index1.
 *
 * */


/**
 * 变种-题目描述:
 * 小明很喜欢数学,有一天他在做数学作业时,要求计算出9~16的和,他马上就写出了正确答案是100。但是他并不满足于此,他在想究竟有多少种连续的正数序列的和为100(至少包括两个数)。
 * 没多久,他就得到另一组连续正数和为100的序列:18,19,20,21,22。现在把问题交给你,你能不能也很快的找出所有和为S的连续正数序列? Good Luck!
 * 输出描述:
 * 输出所有和为S的连续正数序列。序列内按照从小至大的顺序，序列间按照开始数字从小到大的顺序
 */
class FindContinuousSequence{
    public ArrayList<ArrayList<Integer> > FindContinuousSequence(int sum) {
        int index1 = 1;
        int index2 = 2;
        int curSum= index1 + index2;
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();

        while(index1<(sum+1)/2){
            if (curSum == sum){
                ArrayList<Integer> subRes = new ArrayList<Integer>();
                subRes.add(index1);
                subRes.add(index2);
                res.add(subRes);

                index2++;
                curSum += index2;
            }else if(curSum < sum){
                index2++;
                curSum += index2;
            }else if(curSum > sum){
                curSum -= index1;
                index1++;
            }
        }

        ArrayList<ArrayList<Integer>> arrRes = new ArrayList<ArrayList<Integer>>();
        for (ArrayList<Integer> re : res) {
            arrRes.add(yield(re));
        }
        return arrRes;
    }
    private ArrayList<Integer> yield(ArrayList<Integer> src){
        int start = src.get(0);
        int end = src.get(1);
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = start; i <=end ; i++) {
            res.add(i);
        }
        return res;
    }

    public static void main(String[] args) {
        int sum = 9;
        System.out.println(new FindContinuousSequence().FindContinuousSequence(sum));
    }
}

/**
 * 思路: 求连续数字的和为s, 得知连续数字的数组也是从小到大排列的, 基本思路仍采用2个index标记:
 *      和大于s时,去掉最小的数,即增大index1
 *      和小于s时,增加1个更大的数, 即增大index2
 * 但index的选择应该从最小的1和2开始.
 * 又因为题中要求连续数组中至少包含有2个数字, 所以当small增加到(s+1)/2时就不必增加
 *
 * 注: 因为每次求序列的和时,只是增加1个index2指示的数或减少一个index1指示的数, 所以每次求和不必从头开始求, 只要在原有和的基础上增加减伤数字即可
 */