package com.ljnt.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ Program       :  com.ljnt.sort.ShellSort
 * @ Description   :  希尔排序
 * @ Author        :  lj
 * @ CreateDate    :  2020-4-24 10:42
 */
public class ShellSort {
    public static void main(String[] args) {
//        int[] arr = {-88,5, 0, 8, 9, 6, 4, 7, 0,-8,-1};
//        doShellSortBySwitch(arr);
//        doShellSortByInsert(arr);
//        show(arr);
//
//        int [] parr=new int[80000];
//        for (int i=0;i<80000;i++){
//            parr[i]=(int)(Math.random()*8000000);
//        }
//        int[] arr={5,3,6,4,1,2,151,77,85265,9,-5,88,-99};
        int[] parr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            parr[i] = (int) (Math.random() * 8000000);
        }
        Date d1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(d1);
        System.out.println("排序前时间：" + date1Str);
        doShellSortByInsert(parr);
        Date d2 = new Date();
        String date2Str = simpleDateFormat.format(d2);
        System.out.println("排序后时间：" + date2Str);
    }


    /**
     * @param : [arr]
     * @return : int[]
     * @throws :
     * @Description ：希尔排序时， 对有序序列在插入时采用交换法，较慢
     * @author : lj
     * @date : 2020-4-24 11:12
     */
    public static int[] doShellSortBySwitch(int[] arr) {
        int temp;
        for (int i = arr.length / 2; i > 0; i /= 2) {
            for (int j = i; j < arr.length; j++) {
                for (int k = j - i; k >= 0; k -= i) {
                    if (arr[k] > arr[k + i]) {
                        temp = arr[k];
                        arr[k] = arr[k + i];
                        arr[k + i] = temp;

                    }
                }
            }
        }
        return arr;
    }

    public static int[] doShellSortByInsert(int[] arr) {
        int insertIndex = 0;
        int insertValue = 0;
        for (int i = arr.length / 2; i > 0; i /= 2) {
            for (int j = i; j < arr.length; j++) {
                insertIndex = j - i;
                insertValue = arr[j];
                while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
                    arr[insertIndex + i] = arr[insertIndex];
                    insertIndex -= i;
                }
                if (insertIndex + i != j) {
                    arr[insertIndex + i] = insertValue;
                }
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
