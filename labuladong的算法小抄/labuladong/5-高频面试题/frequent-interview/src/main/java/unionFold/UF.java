package unionFold;

/**
 * 用数组模拟图的动态联通
 */
public class UF {

    private int[] parent;
    private int[] size;    // 记录数的权重
    private int count;     // 联通分量个数

    public UF(int n) {     // n 为图中节点个数, Union-Fold 中的节点为 index
        this.parent = new int[n];
        this.size = new int[n];
        // 父节点指向自己
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    /**
     * union,connected 的依赖方法, 返回节点的根节点
     */
    private int findRoot(int x) {
        while (x != parent[x]) {
            parent[x] = parent[parent[x]];   // 每次 findRoot 都会降低树的高度
            x = parent[x];
        }
        return x;
    }

    /**
     * 联通 p,q
     */
    public void union(int p, int q) {
        int rootP = findRoot(p);
        int rootQ = findRoot(q);
        if (rootP == rootQ) {
            return; // 说明已经联通
        } else {
            if (size[rootP] < size[rootQ]) {  // 权重低的作为 root
                parent[rootP] = rootQ;
                size[rootP] += size[rootQ];
            } else {
                parent[rootQ] = rootP;
                size[rootQ] += size[rootP];
            }

            this.count--;
        }
    }

    /**
     * 是否连通
     */
    public boolean connected(int p, int q) {
        return findRoot(p) == findRoot(q);
    }

    /**
     * 联通分量个数
     */
    public int count() {
        return this.count;
    }
}
