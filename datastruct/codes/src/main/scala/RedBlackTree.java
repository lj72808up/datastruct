public class RedBlackTree {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node{
        String key;
        String value;
        Node left, right;
        int N;
        boolean color;

        Node(String key, String value, int n, boolean color) {
            this.key = key;
            this.value = value;
            N = n;
            this.color = color;
        }

        private boolean isRed(Node x){
            if(x==null)
                return false;
            else
                return x.color==RED;
        }

        Node rotateLeft(Node h){
            Node x = h.right;
            h.right = x.left;
            x.left = h;

            x.color = h.color;
            h.color = RED;

            x.N = h.N;
            h.N = 1 + h.left.N + h.right.N;

            return x;
        }
    }
}
