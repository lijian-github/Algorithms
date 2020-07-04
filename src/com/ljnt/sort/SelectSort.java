package com.ljnt.sort;

/**
 * @ Program       :  com.ljnt.sort.SelectSort
 * @ Description   :  选择排序
 * @ Author        :  lj
 * @ CreateDate    :  2020-4-23 21:16
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] arr={-5,8,9,6,4,7,0};
        doSelectSort(arr);
        show(arr);
    }
    public static int[] doSelectSort(int[] arr){
//        boolean flag=false;
        for (int i=0;i<arr.length-1;i++){
            int min=arr[i];
            int minindex=i;
            for(int j=i+1;j<arr.length;j++){
                if (arr[j]<min){
                    min=arr[j];
                    minindex=j;
                }
            }
            if (minindex!=i){
//                flag=true;
                arr[minindex]=arr[i];
                arr[i]=min;
            }
//            if(!flag){
//                break;
//            }else {
//                flag=false;
//            }
        }
        return arr;
    }
    public static void show(int[] arr){
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
    }
}
