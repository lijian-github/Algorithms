package com.ljnt.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class HeapSort {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
//		int arr[] = {4, 6, 8, 5, 9};
		int arrLenght=8000000;
		int arr[]=new int[arrLenght];
		for (int i=0;i<arrLenght;i++) {
			arr[i]=(int) (Math.random()*arrLenght);
		}
		Date date1=new Date();
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(simpleDateFormat.format(date1));
		heapSort(arr);
		Date date2=new Date();
		System.out.println(simpleDateFormat.format(date2));
		System.out.println("运行时间："+(date2.getTime()-date1.getTime())+"ms");
//		System.out.println(Arrays.toString(arr));

	}

	public static void heapSort(int[] arr) {
		for (int i=arr.length/2-1;i>=0;i--) {
			getHeap(arr, i, arr.length);
		}
		int temp=0;
		for (int i=arr.length-1;i>0;i--) {
			temp=arr[i];
			arr[i]=arr[0];
			arr[0]=temp;
			getHeap(arr, 0, i);
		}
	}

	public static void getHeap(int[] arr,int i,int length) {
		int temp=arr[i];
		for(int k=2*i+1;k<length;k=2*k+1) {
			if (k+1<length&&arr[k]<arr[k+1]) {
				k++;
			}
			if (arr[k]>temp) {
				arr[i]=arr[k];
				i=k;
			}else {
				break;
			}
		}
		arr[i]=temp;
	}

}

