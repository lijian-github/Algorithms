package com.ljnt.tree;

/**
 * @ Program       :  com.ljnt.tree.AVLTreeDemo
 * @ Description   :  平衡二叉树
 * @ Author        :  lj
 * @ CreateDate    :  2020-6-3 12:26
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
//        int[] arr = {4,3,6,5,7,8};
//        int[] arr = { 10, 12, 8, 9, 7, 6 };
        int[] arr = {10, 11, 7, 6, 8, 9};
        AVLTree avlTree = new AVLTree();
        for (int i = 0; i < arr.length; i++) {
            avlTree.avladd(new BinarySortNode(arr[i]));
        }
        avlTree.inOrder(avlTree.root);
        System.out.println();
        System.out.println(avlTree.getHeight(avlTree.root, 0));
        System.out.println(avlTree.getLRSubtreeHeightDif(avlTree.root));
    }
}

class AVLTree extends BinarySortTree {

    public void preOrder(BinarySortNode root) {
        if (root == null) {
            return;
        }
        System.out.println(root);
        if (root.left != null) {
            preOrder(root.left);
        }
        if (root.right != null) {
            preOrder(root.right);
        }
    }

    public Integer getLRSubtreeHeightDif(BinarySortNode node) {
        if (node != null) {
            return getHeight(node.left, 0) - getHeight(node.right, 0);
        } else {
            return null;
        }
    }

    public int getHeight(BinarySortNode node, int height) {
        if (node == null) {
            return height;
        }
        height++;
        return Math.max(getHeight(node.left, height), getHeight(node.right, height));
    }

    /**
     * @param : [node]
     * @return : void
     * @throws :
     * @Description ：左旋转
     * @author : lj
     * @date : 2020-6-3 15:59
     */
    public void rotateLeft(BinarySortNode node) {
        if (getLRSubtreeHeightDif(node.right) > 0) {//是否符合双旋转
            rotateRight(node.right);
        }
        BinarySortNode nrtemp = new BinarySortNode(node.value);
        node.value = node.right.value;
        nrtemp.left = node.left;
        nrtemp.right = node.right.left;
        node.right = node.right.right;
        node.left = nrtemp;

    }

    /**
     * @param : [node]
     * @return : void
     * @throws :
     * @Description ：右旋转
     * @author : lj
     * @date : 2020-6-3 16:00
     */
    public void rotateRight(BinarySortNode node) {
        if (getLRSubtreeHeightDif(node.left) < 0) {//是否符合双旋转
            rotateLeft(node.left);
        }
        BinarySortNode nrtemp = new BinarySortNode(node.value);
        node.value = node.left.value;
        nrtemp.right = node.right;
        nrtemp.left = node.left.right;
        node.left = node.left.left;
        node.right = nrtemp;
    }

    public void avladd(BinarySortNode node) {
        add(node);
        if (getLRSubtreeHeightDif(root) < -1) {
            rotateLeft(root);
        }
        if (getLRSubtreeHeightDif(root) > 1) {
            rotateRight(root);
        }
    }
}
