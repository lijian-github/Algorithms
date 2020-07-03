package com.ljnt.sort;

/**
 * @ Program       :  com.ljnt.sort.InsertSort
 * @ Description   :  插入排序
 * @ Author        :  lj
 * @ CreateDate    :  2020-4-23 21:22
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {5, 0, 8, 9, 6, 4, 7, 0};
        doInsertSort(arr);
        show(arr);
    }

    public static int[] doInsertSort(int[] arr) {
        int insertindex = 0;//插入的位置前一位索引
        int insertvalue = 0;//待插入值
        for (int i = 1; i < arr.length; i++) {
            insertindex = i - 1;
            insertvalue = arr[i];
            //待插入值与有序序列每个值比较（从后到前），如果该有序序列的值比插入值大就向后移动一位
            while (insertindex >= 0 && insertvalue < arr[insertindex]) {
                arr[insertindex + 1] = arr[insertindex];
                insertindex--;
            }
            if (insertindex + 1 != i) {
                arr[insertindex + 1] = insertvalue;
            }

        }
        return arr;
    }

    public static void show(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
