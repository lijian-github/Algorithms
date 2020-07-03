package com.ljnt.tree;

/**
 * @ Program       :  com.ljnt.tree.BinarySortTreeDemo
 * @ Description   :  二叉排序树
 * @ Author        :  lj
 * @ CreateDate    :  2020-6-2 21:51
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree=new BinarySortTree();
        for (int i=0;i<arr.length;i++){
            binarySortTree.add(new BinarySortNode(arr[i]));
        }
        binarySortTree.inOrder(binarySortTree.root);
        System.out.println();
        System.out.println(binarySortTree.findParent(7));
        System.out.println();
        System.out.println(binarySortTree.findMax(binarySortTree.root));
        System.out.println();
        binarySortTree.remove(7);
        binarySortTree.inOrder(binarySortTree.root);
    }

}

class BinarySortTree{
    BinarySortNode root;
    public void add(BinarySortNode node){
        add(root,node);
    }

    private void add(BinarySortNode root,BinarySortNode node) {
        if (root==null){
            root=node;
            this.root=node;
            return;
        }
        if (node.value<root.value){
            if (root.left==null){
                root.left=node;
                return;
            }
            add(root.left,node);
        }else {
            if (root.right==null){
                root.right=node;
                return;
            }
            add(root.right,node);
        }

    }

    public void inOrder(BinarySortNode root){
        if (root==null){
            return;
        }
        if (root.left!=null){
            inOrder(root.left);
        }
        System.out.println(root);
        if (root.right!=null){
            inOrder(root.right);
        }
    }

    public BinarySortNode findParent(int findval){
        return findParent(root,findval);
    }

    private BinarySortNode findParent(BinarySortNode root,int findval){
        if (root==null){
            return null;
        }
        if (root.left!=null) {
            if (root.left.value == findval ) {
                return root;
            }
        }
        if (root.right!=null){
            if (root.right.value == findval){
                return root;
            }
        }
        if (findval<root.value){
            return findParent(root.left,findval);
        }else {
            return findParent(root.right,findval);
        }
    }

    public BinarySortNode findMax(BinarySortNode node){
        if (node==null){
            return null;
        }
        if (node.right==null){
            return node;
        }else {
            return findMax(node.right);
        }
    }

    public void remove(int delval){
        remove(root,delval);
    }

    private void remove(BinarySortNode root,int delval){
        BinarySortNode parent=findParent(delval);
        if (parent!=null){
            BinarySortNode nodetemp=null;
            if (parent.left!=null&&parent.left.value==delval){
                nodetemp=parent.left;
            }
            if (parent.right!=null&&parent.right.value==delval){
                nodetemp=parent.right;
            }
            if (nodetemp.left==null&&nodetemp.right==null){
                if (parent.left==nodetemp){
                    parent.left=null;
                }
                if(parent.right==nodetemp){
                    parent.right=null;
                }
            }else if(nodetemp.left!=null&&nodetemp.right==null){
                if (parent.left==nodetemp){
                    parent.left=nodetemp.left;
                }
                if (parent.right==nodetemp){
                    parent.right=nodetemp.left;
                }
            }else if(nodetemp.left==null&&nodetemp.right!=null){
                if (parent.left==nodetemp){
                    parent.left=nodetemp.right;
                }
                if (parent.right==nodetemp){
                    parent.right=nodetemp.right;
                }
            }else {
                BinarySortNode leftmax=findMax(nodetemp.left);
                remove(root,leftmax.value);
                nodetemp.value=leftmax.value;
            }
        }else {//删除根节点
            if (root.left==null&&root.right==null){
                root=null;
            }else if (root.left!=null&&root.right==null){
                root=root.left;
            }else if (root.left==null&&root.right!=null){
                root=root.right;
            }else {
                BinarySortNode leftmax=findMax(root.left);
                remove(root,leftmax.value);
                root.value=leftmax.value;
            }
        }

    }

}

class BinarySortNode{
    int value;
    BinarySortNode left;
    BinarySortNode right;

    public BinarySortNode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public BinarySortNode getLeft() {
        return left;
    }

    public void setLeft(BinarySortNode left) {
        this.left = left;
    }

    public BinarySortNode getRight() {
        return right;
    }

    public void setRight(BinarySortNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "BinarySortNode{" +
                "value=" + value +
                '}';
    }
}
