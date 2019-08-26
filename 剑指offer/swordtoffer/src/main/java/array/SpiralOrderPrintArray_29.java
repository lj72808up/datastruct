package array;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目描述:
 * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 *
 * 输入:
 * [
 *  [ 1, 2, 3 ],
 *  [ 4, 5, 6 ],
 *  [ 7, 8, 9 ]
 * ]
 * 输出: [1,2,3,6,9,8,7,4,5]
 * 链接：https://leetcode-cn.com/problems/spiral-matrix
 */
public class SpiralOrderPrintArray_29 {
    public List<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> res = new ArrayList<>();
        if(matrix.length==0)
            return res;
        int r1 = 0;
        int c1 = 0;
        int r2 = matrix.length-1;
        int c2 = matrix[0].length-1;

        while (r1<=r2 && c1<=c2){
            // up
            for (int i = c1; i <=c2 ; i++)
                res.add(matrix[r1][i]);
            // right
            for (int i = r1+1; i <=r2 ; i++)
                res.add(matrix[i][c2]);
            // down
            if(r2>r1){  // c的限制由for循环自带
                for (int i = c2-1; i >=c1 ; i--)
                    res.add(matrix[r2][i]);
            }
            // left
            if(c2>c1){ // r的限制由for循环自带
                for (int i = r2-1; i >=r1+1 ; i--) {
                    res.add(matrix[i][c1]);
                }
            }

            r1++;
            c1++;
            r2--;
            c2--;
        }
        return res;
    }
}

/**
 * 思路:
 *  (1) 顺时针打印矩阵, 就是分为4个方向的遍历数组, 上,右,下,左; 每次的螺旋打印可以看成分层打印.
 *  (2) 对于每层, 我们从左上角开始打印, 假设左上角是(r1,c1), 右下角是(r2,c2), 循环的条件为r1<=r2&&c1<=c2;
 *    若用r,c分别表示行坐标和列坐标, 则四个方向上的打印有
 *      上: r=r1不动,c从c1,递增到c2
 *      右: c=c2不动,r从r1+1递增到r2
 *      下: 由于"上"已经打印了一行, 所以要判断r2>=r1+1,且c2-1>=c1的时候才打印这一行
 *          r=r2不变, c从c2-1一直到c1
 *      左: 由于"上右左"都已打印完毕, 所以要判断(r2-1>r1,且c2-1>c1)的时候才打印这一行
 *          c=c1不变, r从r2-1递减到r2+1
 *  (3) 整体循环后r1和c1均++; r2和c2均--;
 */
