package com.ljnt.sort;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ Program       :  com.ljnt.sort.BubbleSort
 * @ Description   :  冒泡排序
 * @ Author        :  lj
 * @ CreateDate    :  2020-4-23 17:45
 */
public class BubbleSort {
    public static void main(String[] args) {
        int [] parr=new int[80000];
        for (int i=0;i<80000;i++){
            parr[i]=(int)(Math.random()*8000000);
        }
//        int[] arr={5,3,6,4,1,2,151,77,85265,9,-5,88,-99};
        Date d1=new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(d1);
        System.out.println("排序前时间："+date1Str);
        doBubbleSort(parr);
        Date d2=new Date();
        String date2Str = simpleDateFormat.format(d2);
        System.out.println("排序后时间："+date2Str);
    }

    public static int[] doBubbleSort(int[] arr){
        int temp;
        boolean flag=false;
        for (int i=0;i<arr.length-1;i++){
            for(int j=0;j<arr.length-i-1;j++){
                if (arr[j]>arr[j+1]){
                    flag=true;
                    temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
            if(!flag){
                break;
            }else {
                flag=false;
            }
        }
        return arr;
    }

    public static void show(int[] arr){
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
    }

}
