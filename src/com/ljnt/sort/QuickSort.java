package com.ljnt.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ Program       :  com.ljnt.sort.QuickSort
 * @ Description   :  快速排序
 * @ Author        :  lj
 * @ CreateDate    :  2020-4-25 11:19
 */
public class QuickSort {
    public static void main(String[] args) {
//        int[] arr={5,3,6,4,1,2,151,77,85265,9,-5,88,-99};
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        Date d1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(d1);
        System.out.println("排序前时间：" + date1Str);
        doQuickSort(arr,0,arr.length-1);
//        show(arr);
        Date d2 = new Date();
        String date2Str = simpleDateFormat.format(d2);
        System.out.println("排序后时间：" + date2Str);

    }

    public static void doQuickSort(int[] arr,int left,int right){
        int l=left;
        int r=right;
        int mid=(left+right)/2;
        int mv=arr[mid];
        int temp;
        while(l<r){
            while (arr[l]<mv){
                l++;
            }
            while (arr[r]>mv){
                r--;
            }
            if (l >= r) {
                break;
            }
            temp=arr[l];
            arr[l]=arr[r];
            arr[r]=temp;
            if (arr[l]==mv){
                r--;
            }
            if (arr[r]==mv){
                l++;
            }

        }
        if (l==r){
            l++;
            r--;
        }

        if (left<r){
            doQuickSort(arr,left,r);
        }
        if (l < right) {
            doQuickSort(arr,l,right);
        }
    }

    public static void show(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
