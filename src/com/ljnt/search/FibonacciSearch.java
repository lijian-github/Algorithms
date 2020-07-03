package com.ljnt.search;

import java.util.Arrays;

public class FibonacciSearch {
	static int MaxSize=20;

	public static void main(String[] args) {
		int [] arr = {1,8,10,89,1000,1234,1235,18999,88815};
		System.out.println("index=" + fibonacciSearch(arr, 1));

	}
	
	public static int[] fibonacciArr() {
		int[] i=new int[MaxSize];
		i[0]=1;
		i[1]=1;
		for (int k=2;k<MaxSize;k++) {
			i[k]=i[k-1]+i[k-2];
		}
		return i;
	}
	
	
	public static int fibonacciSearch(int arr[],int findVal) {
		int [] F=fibonacciArr();
		int low=0;
		int hight=arr.length-1;
		int k=0;
		while(hight>F[k]-1) {
			k++;
		}
		int[] temp=Arrays.copyOf(arr, F[k]);
		for (int i=hight+1;i<temp.length;i++) {
			temp[i]=temp[hight];
		}
		while(low<=hight) {
			int mid=low+F[k-1]-1;
			if (temp[mid]>findVal) {
				hight=mid-1;
				k--;//F[k]=F[k-1]+F[k-2]
			}else if (temp[mid]<findVal) {
				low=mid+1;
				k-=2;
			}else {
				if(mid<hight) {
					return mid;
				}else {
					return hight;
				}
			}
		}
		return -1;
	}

}
