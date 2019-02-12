package arrays;

import java.util.*;
import java.lang.*;
import java.io.*;

class NextPermutation {
	public static void main(String[] args) {
		int[] arr = {62 ,92 ,96 ,43 ,28 ,37 ,92 ,5 ,3 ,54 ,93, 83, 22};
		nextPermutation(arr);
		for (int val : arr) {
			System.out.println(val);
		}
	}

	public static void nextPermutation(int[] arr) {
		for (int i = arr.length - 1; i >= 1; i--) {
			if (arr[i] <= arr[i - 1]) {
				if(i==1) {
					rearrange(0, arr);
				}
			} else {
				int k = findNextLargerIndex(i - 1, arr);
				swap(arr, k, i - 1);
				rearrange(i, arr);
				break;
			}
		}
	}

	public static void rearrange(int k,int[] arr){
	     for(int i=k,l=1;i<arr.length-1;i++,l++){
	         for(int j=k;j<arr.length-l;j++){
	             if(arr[j]>arr[j+1]){
	                 swap(arr,j,j+1);
	             }
	         }
	     }
	 }

	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	public static int findNextLargerIndex(int i, int[] arr) {
		int k = -1;
		for (int j = i + 1; j < arr.length; j++) {
			if (arr[j] > arr[i]) {
				if (k == -1) {
					k = j;
				} else {
					if (arr[k] > arr[j]) {
						k = j;
					}
				}
			}
		}

		return k;
	}
}