package com.yj.nz.bisection;

import java.util.Arrays;

public class d3_BSNearRight {

	public static int nearestIndex(int[] arr, int value) {
		int L = 0;
		int R = arr.length - 1;
		int index = -1; // 记录最右的对号
		while (L <= R) {
			int mid = L + ((R - L) >> 1);
			if (arr[mid] <= value) {
				index = mid;
				L = mid + 1;
			} else {
				R = mid - 1;
			}
		}
		return index;
	}

}
