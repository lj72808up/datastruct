/**
 * 题目描述:
 *  给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
 *  找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。说明：你不能倾斜容器，且 n 的值至少为 2。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/container-with-most-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxArea_11 {
    public int maxArea1(int[] height) {
        /**思路一*/
        int maxAera = 0;
        for (int i = 0; i <= height.length-2; i++) {
            for (int j = i+1; j < height.length; j++) {
                int w = j-i;
                int h = Math.min(height[i],height[j]);
                maxAera = Math.max(maxAera,w*h);
            }
        }
        return maxAera;
    }

    public int maxArea2(int[] height) {
        /**思路二*/
        int index1 = 0;
        int index2 = height.length-1;
        int maxAera = Math.min(height[index1],height[index2])*(index2-index1);

        while(index1<=index2){
            if(height[index1]<=height[index2]){
                index1++;
            }else{
                index2--;
            }
            if(index1<=index2)
                maxAera = Math.max(maxAera,Math.min(height[index1],height[index2])*(index2-index1));
        }
        return maxAera;
    }
}

/**
 * 思路一:
 *   要找到容积最大的值, 我们知道, 面积等于画出该区域的2条直线中较短的那条, 和2条边的距离的乘积 , 因此, 首先想到暴力迭代, 让每条边和其余变组成区域计算面积, 求出最大值
 *
 * 思路二:
 *   如果使用2个指针分别指向数组的开头和结尾, 此时形成的区域, 底边最长, 假设还存在更大的区域, 则一定存在比这2个指针指向的元素的较小值更大的边, 来平衡底边减小的影响;
 *   因此, 可以让2个指针中, 指向较小数值的指针, 向指向较大数值的指针移动
 *   [注意]:
 *     在while循环内部计算面积时, 由于index1和index2发生变化了, 所以mexAera的计算要先判断条件
 * */