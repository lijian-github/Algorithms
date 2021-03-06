package com.ljnt.search;

import java.util.ArrayList;
import java.util.List;

public class BinarySearch {

	public static void main(String[] args) {
		int arr[] = { 1, 8, 10, 89,1000,1000, 1234 };
		List<Integer> resIndexList = binarySearch(arr, 0, arr.length - 1, 1000);
		System.out.println("resIndexList=" + resIndexList);

	}
	
	public static List<Integer> binarySearch(int[] arr, int left, int right, int findVal) {
		if (left>right) {
			return new ArrayList<Integer>();
		}
		int mid=(left+right)/2;
		if (findVal>arr[mid]) {
			return binarySearch(arr,mid+1,right,findVal);
		}else if(findVal==arr[mid]) {
			List<Integer> findindexlist=new ArrayList<Integer>();
			int temp=mid-1;
			while(temp>0&&findVal==arr[temp]) {
				findindexlist.add(temp);
				temp--;
			}
			temp=mid;
			while(temp<arr.length&&findVal==arr[temp]) {
				findindexlist.add(temp);
				temp++;
			}
			
			return findindexlist;
		}else {
			return binarySearch(arr,left,mid-1,findVal);
		}
		
	}

}
