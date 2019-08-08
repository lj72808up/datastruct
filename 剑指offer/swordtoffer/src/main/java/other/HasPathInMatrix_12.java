package other;

import array.ArrayUtil;

/**
 * 题目描述
 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
 * 路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。
 * 如果一条路径经过了矩阵中的某一个格子，则之后不能再次进入这个格子。
 * 例如 a b c
 *      e s f
 *      c s a
 *      d e e
 * 这样的3 X 4 矩阵中包含一条字符串"bsae"的路径，但是矩阵中不包含"absb"路径，因为字符串的第2个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入该格子。
 */
public class HasPathInMatrix_12 {
    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        if(matrix.length<str.length || matrix.length==0 || str.length==0)
            return false;
        boolean[] visited = new boolean[matrix.length];
        for (int i = 0; i < visited.length; i++) {
            visited[i] = false;
        }

        for (int i = 0; i < matrix.length; i++) {
            if(matrix[i]==str[0]) {
                int row = i / cols;
                int col = i % cols;
                if (this.hasPathRerc(matrix,rows,cols,row,col,str,0,visited,"start"))
                    return true;
            }
        }
        return false;
    }

    /**
     * @param matrix: 字符矩阵
     * @param rows: 矩阵的行数
     * @param cols: 矩阵的列数
     * @param row: 矩阵的哪一行
     * @param col: 矩阵的哪一列
     * @param str: 待查找字符串
     * @param index: 待查找字符串中的第几个字符
     * @param visited: 矩阵相应位置是否已经访问
     * @return
     */
    private boolean hasPathRerc(char[] matrix, int rows, int cols, int row, int col, char[] str, int index, boolean[] visited, String orient){
        if(index>=str.length){
            System.out.println("找完了");
            return true; // 找完了
        }
        if (row>=rows || col>=cols || row<0 || col<0) {
            System.out.println(orient+":查找位置越界了:"+row+","+col);
            return false;  // 查找位置越界了
        }
        System.out.println(matrix[row*cols+col]+"<===>"+str[index]+":"+index+"="+orient+",index:"+index+"col:"+col+"row:"+row+"visited:"+visited[row*cols+col]);

        if(matrix[row*cols+col]==str[index] && !visited[row*cols+col]){
            visited[row*cols+col] = true;
            boolean up = hasPathRerc(matrix, rows, cols, row-1,col,str, index+1,visited,"up");
            boolean down = hasPathRerc(matrix, rows, cols, row+1,col,str, index+1,visited,"down");
            boolean left = hasPathRerc(matrix, rows, cols, row,col-1,str, index+1,visited,"LEFT");
            boolean right = hasPathRerc(matrix, rows, cols, row,col+1,str, index+1,visited,"RIGHT");
            boolean hasPath =  up||down||left||right;
            if(!hasPath) {
                visited[row * cols + col] = false;
                return false; //这条路径走不通, 该节点的visited还原成false
            }else{
                return true;
            }
        }else{
            return false;
        }
    }

    public static void main(String[] args) {
        String matrix = "ABCEHJIGZFCZLOPQADEEMNOEADIDEJFMVCEIFGGS";
        String search = "SGGFIECVAASABCEHJIGQEM";
        System.out.println(new HasPathInMatrix_12().hasPath(matrix.toCharArray(),5,8,search.toCharArray()));
    }
}

/**
 * [注意]:
 *     如果某条路径走不通, 则该递归走过的所有节点的visited要还原成false
 * */