package com.ljnt.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ Program       :  com.ljnt.sort.MergetSort
 * @ Description   :  归并排序
 * @ Author        :  lj
 * @ CreateDate    :  2020-4-25 11:55
 */
public class MergetSort {
    public static void main(String[] args) {
//        int[] arr={5,3,6,4,1,2,151,77,85265,9,-5,-88,-99};
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        Date d1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        String date1Str = simpleDateFormat.format(d1);
        System.out.println("排序前时间：" + date1Str);
        int[] temp=new int[arr.length];
        doMergetSort(arr,0,arr.length-1,temp);
//        show(arr);
        Date d2 = new Date();
        String date2Str = simpleDateFormat.format(d2);
        System.out.println("排序后时间：" + date2Str);
        System.out.println("排序用时：" +(d2.getTime()-d1.getTime())+"ms");
    }

    public static void doMergetSort(int[] arr,int left,int right,int[] temp) {
        if (left<right){
            int mid =(left+right)/2;
            doMergetSort(arr,left,mid,temp);
            doMergetSort(arr,mid+1,right,temp);


            int i=left;
            int j=mid+1;
            int t=0;
            while(i<=mid&&j<=right){
                if (arr[i]<arr[j]){
                    temp[t]=arr[i];
                    t++;
                    i++;
                }else {
                    temp[t]=arr[j];
                    t++;
                    j++;
                }
            }

            while(i<=mid){
                temp[t]=arr[i];
                t++;
                i++;
            }

            while(j<=right){
                temp[t]=arr[j];
                t++;
                j++;
            }

            t=0;
            int tp=left;
            while (tp<=right){
                arr[tp]=temp[t];
                t++;
                tp++;
            }



        }
    }
    public static void show(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
