package com.yj.nz.sort;

import java.util.Arrays;

public class d2_BubbleSort {

	// 冒泡排序
	public static void bubbleSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		// 0 ~ N-1
		// 0 ~ N-2
		// 0 ~ N-3
		for (int end = arr.length - 1; end > 0; end--) { // 0 ~ end
			for (int i = 0; i < end; i++) {
				if (arr[i] > arr[i + 1]) {
					swap(arr, i, i + 1);
				}
			}
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
		System.out.println("冒泡排序前：" + Arrays.toString(arr) );
		bubbleSort(arr);
		System.out.println("冒泡排序后：" + Arrays.toString(arr));
	}

}
