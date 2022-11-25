package com.yj.nz.sort;

import java.util.Arrays;

public class d3_InsertionSort {

	public static void insertionSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		// 0~0 有序的
		// 0~i 想有序
		for (int i = 1; i < arr.length; i++) { // 0 ~ i 做到有序
			for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
				swap(arr, j, j + 1);
			}
		}
	}

	// i和j是一个位置的话，会出错
	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	// for test
	public static void main(String[] args) {
		int[] arr = new int[]{1,4,3,7,5};
		System.out.println("冒泡排序前：" + Arrays.toString(arr) );
		insertionSort(arr);
		System.out.println("冒泡排序后：" + Arrays.toString(arr));
	}

}
