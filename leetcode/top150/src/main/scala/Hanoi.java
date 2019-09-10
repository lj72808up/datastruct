import java.util.ArrayList;
import java.util.List;

/**
 * 题目描述: 汉诺塔
 *  n个圆盘从上到下看是半径依次增大圆环, 这些圆环放在柱子A上, 求一个顺序, 将这些圆盘以相同的顺序移动到柱子C, 柱子B可以作为缓冲区
 */
public class Hanoi {

    public  void move(int n, String from ,String buffer, String to){
        if(n==1) {
            System.out.println("move from " + from + " to " + to);
        }else{
            move(n-1,from,to,buffer);
            System.out.println("move from " + from + " to " + to);
            move(n-1,buffer,from,to);
        }
    }

    public static void main(String[] args) {
        new Hanoi().move(5,"A","B","C");
    }
}
/**
 * 思路:
 *  假设最上面的圆环编号是1, 最下的大圆环编号是n;
 *  要将最下面编号n的圆环从A移动到C, 就需要:
 *      (1) 先将上面1到n-1编号的圆环从A移动到B(缓冲区)
 *      (2) 将n从A移动到C
 *      (3) 将1到n-1从B移动到C
 *
 *   边界条件是只有一个圆环时, 直接从A移动到C
 * */