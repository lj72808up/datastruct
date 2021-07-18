package backtrack;

import java.util.ArrayList;
import java.util.List;

public class SolveNQueens {
    public List<List<String>> solveNQueens(int n) {
        return new ArrayList<>();
    }

    /** 检测此位置放置皇后是否存在冲突 */
    private boolean isValid(ArrayList<StringBuilder> track, int row, int col){
        int  n = track.size();
        // 检查列是否有皇后冲突
        for(int i = 0; i < n; i++){
            if(track.get(i).charAt(col) == 'Q')
                return false;
        }
        // 检查右上方是否有皇后冲突
        for(int i = row-1, j = col+1; i>=0 && j <n; i--,j++){
            if(track.get(i).charAt(j) == 'Q')
                return false;
        }
        // 检查左上方是否有皇后冲突
        for(int i= row-1, j = col-1; i>=0 && j >=0; i--,j--){
            if(track.get(i).charAt(j) == 'Q')
                return false;
        }
        return true;
    }

    public static void main(String[] args) {

    }
}