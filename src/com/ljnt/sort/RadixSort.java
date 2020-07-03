package com.ljnt.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ Program       :  com.ljnt.sort.RadixSort
 * @ Description   :  基数排序
 * @ Author        :  lj
 * @ CreateDate    :  2020-4-25 15:37
 */
public class RadixSort {
    public static void main(String[] args) {
//        int[] arr={5,3,6,4,1,20,151,77,85265,9666};
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        Date d1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        String date1Str = simpleDateFormat.format(d1);
        System.out.println("排序前时间：" + date1Str);
        doRadixSort(arr);
//        show(arr);
        Date d2 = new Date();
        String date2Str = simpleDateFormat.format(d2);
        System.out.println("排序后时间：" + date2Str);
        System.out.println("排序用时：" +(d2.getTime()-d1.getTime())+"ms");

    }

    public static void doRadixSort(int[] arr) {
        int[][] radixarr = new int[10][arr.length];
        int[] ranum = new int[10];
        int max = arr[0];
        for (int p = 0; p < arr.length; p++) {
            if (max < arr[p]) {
                max = arr[p];
            }
        }
        int not = String.valueOf(max).length();
        for (int k = 0,n=1; k < not; k++,n*=10) {
            for (int i = 0; i < arr.length; i++) {
                int r = (arr[i] / n) % 10;
                radixarr[r][ranum[r]++] = arr[i];
            }

            int t = 0;
            for (int i = 0; i < 10; i++) {
                if (ranum[i] > 0) {
                    for (int j = 0; j < ranum[i]; j++) {
                        arr[t] = radixarr[i][j];
                        t++;
                    }
                }
            }
            for (int i=0;i<ranum.length;i++){
                ranum[i]=0;
            }
        }

    }


    public static void show(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
