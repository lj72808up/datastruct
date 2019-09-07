package binaryTree;

/**
 * 题目描述
 * 给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。
 * 注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
 */
public class GetNextTreeNode_08 {
    public TreeLinkNode GetNext(TreeLinkNode pNode){
        if(pNode==null)
            return null;

        if(pNode.right!=null){
            TreeLinkNode next = pNode.right;
            while(next.left!=null){
                next = next.left;
            }
            return next;
        }else if(pNode.next!=null && pNode==pNode.next.left){  // next是父指针
            return pNode.next;
        }else if(pNode.next!=null && pNode==pNode.next.right){
            TreeLinkNode parent = pNode.next;
            while(parent!=null && !isLeftChild(parent))
                parent = parent.next;
            if(parent!=null)
                return parent.next;
            else
                return null;

        }else {
            return null;
        }
    }
    private boolean isLeftChild(TreeLinkNode pNode){
        if (pNode!=null && pNode.next != null)
            return pNode==pNode.next.left;
        else
            return false;
    }
}


/**
 * 思路:
 *  (1) 如果该节点有右孩子, 则该节点的下一个节点是其右子树的最左节点
 *  (2) 如果该节点没有右孩子, 且是其父节点的左孩子, 则其下一个节点是其父节点
 *  (3) 如果该节点没有右孩子, 且是其父节点的右孩子, 则其下一个节点是沿着父节点走, 一致走到作为左孩子出现的祖先节点, 这个祖先节点的父节点就是下一个节点
 *
 * 注意:
 *  二叉树在选取一个节点的孩子节点时, 要先判断该节点是否为空
 * */
