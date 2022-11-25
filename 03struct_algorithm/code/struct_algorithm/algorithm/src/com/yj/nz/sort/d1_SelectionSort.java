package com.yj.nz.sort;

import java.util.Arrays;

public class d1_SelectionSort {

	// 选择排序
	public static void selectionSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}

		for (int i = 0; i < arr.length - 1; i++) { // i ~ N-1
			// 最小值在哪个位置上  i～n-1
			int minIndex = i;
			for (int j = i + 1; j < arr.length; j++) { // i ~ N-1 上找最小值的下标
				minIndex = arr[j] < arr[minIndex] ? j : minIndex;
			}
			swap(arr, i, minIndex);
		}
	}

	// 交换arr的i和j位置上的值
	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}


	public static void main(String[] args) {
		int[] arr = new int[]{1,4,3,7,5};
		System.out.println("选择排序前：" + Arrays.toString(arr) );
		selectionSort(arr);
		System.out.println("选择排序后：" + Arrays.toString(arr));
	}

}
