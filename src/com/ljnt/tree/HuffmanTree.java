package com.ljnt.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ Program       :  com.ljnt.tree.HuffmanTree
 * @ Description   :  赫夫曼树
 * @ Author        :  lj
 * @ CreateDate    :  2020-5-31 17:35
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int arr[] = { 13, 7, 8, 3, 29, 6, 1 };
        TreeNode root=createHuffmanTree(arr);
        preOrder(root);
    }

    public static TreeNode createHuffmanTree(int[] arr){
        List<TreeNode> nodelist=new ArrayList<>();
        for (int i=0;i<arr.length;i++){
            nodelist.add(new TreeNode(arr[i]));
        }
        Collections.sort(nodelist);
        while(nodelist.size()>1){
            TreeNode node1=nodelist.get(0);
            TreeNode node2=nodelist.get(1);
            TreeNode newnode=new TreeNode(node1.weight+node2.weight);
            newnode.setLeft(node1);
            newnode.setRight(node2);
            nodelist.remove(node1);
            nodelist.remove(node2);
            nodelist.add(newnode);
            Collections.sort(nodelist);
        }
        return nodelist.get(0);
    }

    public static void preOrder(TreeNode root){
        if (root!=null){
            System.out.println(root);
            if (root.left!=null){
                preOrder(root.left);
            }
            if (root.right!=null){
                preOrder(root.right);
            }
        }
    }


}



class TreeNode implements Comparable<TreeNode>{
    int weight;
    TreeNode left;
    TreeNode right;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public TreeNode(int weight) {
        this.weight = weight;
    }

    @Override
    public int compareTo(TreeNode o) {
        return this.weight-o.weight;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "weight=" + weight +
                '}';
    }
}
