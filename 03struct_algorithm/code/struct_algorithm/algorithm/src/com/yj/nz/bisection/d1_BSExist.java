package com.yj.nz.bisection;

public class d1_BSExist {

	public static boolean exist(int[] arr, int num) {
		if (arr == null || arr.length == 0) {
			return false;
		}
		int L = 0;
		int R = arr.length - 1;
		int mid = 0;
		// L..R
		while (L < R) {
			mid = L + ((R - L) >> 1); // mid = (L + R) / 2
			if (arr[mid] == num) {
				return true;
			} else if (arr[mid] > num) {
				R = mid - 1;
			} else {
				L = mid + 1;
			}
		}
		return arr[L] == num;
	}

}
