package com.yj.nz.bit;

public class d3_KM {

	// 请保证数组中，只有一种数出现了K次，其他数都出现了M次
	public static int km(int[] arr, int k, int m) {
		int[] help = new int[32];
		// hepl[0] 0位置上的 1 出现了几个
		// hepl[1] 1位置上的 1 出现了几个
		for (int num : arr) {
			for (int i = 0; i < 32; i++) {
				help[i] += (num >> i) & 1;
			}
		}
		int ans = 0;
		for (int i = 0; i < 32; i++) {
			help[i] %= m;
			if (help[i] != 0) {
				ans |= 1 << i;
			}
		}
		return ans;
	}



	// 测试
	public static void main(String[] args) {
		int[] arr = {-1,3,1,3,3,1,1,-1};
		int k = 2;
		int m = 3;
		System.out.println(km(arr,k,m));
	}

}
