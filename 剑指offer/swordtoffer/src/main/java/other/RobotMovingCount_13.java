package other;

/**
 * 题目描述
 * 地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，但是不能进入行坐标和列坐标的数位之和大于k的格子。
 * 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。但是，它不能进入方格（35,38），因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？
 */
public class RobotMovingCount_13 {
    public int movingCount(int threshold, int rows, int cols) {
        if (threshold<0 || rows<=0 || cols<=0)
            return 0;
        boolean[] visited = new boolean[rows*cols];
        return movingCountRec(threshold,rows,cols,0,0,visited);
    }
    private int movingCountRec(int threshold, int rows, int cols, int row , int col ,boolean[] visited) {
        if(isLegal(row,col,rows,cols,threshold) && !visited[row*cols+col]){
            visited[row*cols+col] = true;
            return 1 + movingCountRec(threshold, rows, cols, row-1 , col ,visited)
                    + movingCountRec(threshold, rows, cols, row+1 , col ,visited)
                    + movingCountRec(threshold, rows, cols, row , col-1 ,visited)
                    + movingCountRec(threshold, rows, cols, row , col+1 ,visited);
        }else{
            return 0;
        }
    }
    private boolean isLegal(int row,int col, int rows, int cols, int threshold){
        if(row<0 || col<0 || row>=rows || col>=cols)
            return false;
        return getNumber(row)+getNumber(col)<=threshold;

    }
    private int getNumber(int number){
        // 从个位开始加,
        int res = 0;
        while(number>0){
            res += number % 10;
            number = number / 10;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new RobotMovingCount_13().movingCount(10,1,100));
    }
}
/**
 * 思路: 机器人可以走到的方格满足几个条件:
 *    (1) threshold
 *    (2) 矩阵的边界
 *    (3) 每个方格只能计算一次, 因此visited=false
 * */