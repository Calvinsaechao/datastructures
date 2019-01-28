/**
 * Created by Calvin on 4/2/18.
 */
import java.lang.Math;
public class AVLTree<E extends Comparable<? super E>> extends BinarySearchTree<E> {
    @Override
    public void incCount(E data){
        super.incCount(data);
        overallRoot = balancing(data, overallRoot);
    }

    public int checkBalance(BSTNode t) throws Exception{
        if (t == null) return -1;
        if (t != null) {
            int leftHeight = checkBalance(t.left);
            int rightHeight = checkBalance(t.right);
            if ((Math.abs(height(t.left) - height(t.right)) >1)
                    || ((height(t.left)) != leftHeight)
                    || ((height(t.right)) != rightHeight)) {
                throw new Exception("Unbalanced Trees.");
            }
        }
        return height(t);
    }

    public BSTNode balancing(E data, BSTNode t){
        int cmp = data.compareTo(t.data);
        if (cmp == 0){
        }
        else if (cmp > 0){
            t.right = balancing(data, t.right);
        }
        else if (cmp < 0){
            t.left = balancing(data, t.left);
        }
        t = balance(t);
        return t;
    }

    private BSTNode balance(BSTNode node){
        node.height = Math.max(height(node.left), height(node.right)) +1;
        if (height(node.left) - height(node.right) > 1){
            if (height(node.left.left) >= height(node.left.right)){
                node = singleRightRotation(node);
            }
            else{
                node = doubleLeftRightRotation(node);
            }
        }
        else if (height(node.right) - height(node.left) > 1){
            if (height(node.right.right) >= height(node.right.left)){
                node = singleLeftRotation(node);
            }
            else{
                node = doubleRightLeftRotation(node);
            }
        }
        node.height = Math.max(height(node.left), height(node.right)) +1;
        return node;
    }

    private BSTNode singleRightRotation(BSTNode k2){
        BSTNode k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left), height(k1.right)) + 1;

        return k1;
    }

    private BSTNode singleLeftRotation(BSTNode k1){
        BSTNode k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = Math.max(height(k1.left), height(k1.right)) +1;
        k2.height = Math.max(height(k2.right), height(k2.left))+1;

        return k2;
    }

    private BSTNode doubleLeftRightRotation(BSTNode k3){
        k3.left = singleLeftRotation(k3.left);
        return singleRightRotation(k3);
    }

    private BSTNode doubleRightLeftRotation(BSTNode k1){
        k1.right = singleRightRotation(k1.right);
        return singleLeftRotation(k1);
    }
}
