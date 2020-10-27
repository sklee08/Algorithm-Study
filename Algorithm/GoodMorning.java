package studying;

import java.util.Arrays;

public class GoodMorning {

	static int[] arr = { 1, 2, 3, 4, 5 };
	static boolean[] check = new boolean[5];

	public static void main(String[] args) {
		System.out.println("subset");
		subset(new boolean[5], 0);
		System.out.println();

		System.out.println("Permutation");
		Permutation(new int[2], 0);
		System.out.println();

		System.out.println("Repermutation");
		Repermutation(new int[2], 0);
		System.out.println();

		System.out.println("Combination");
		Combination(new int[2], 0, 0);
		System.out.println();

		System.out.println("Recombination");
		Recombination(new int[2], 0, 0);
		System.out.println();
	}

	public static void subset(boolean[] tmp, int idx) {
		if(idx == tmp.length) {
			for(int i = 0; i < idx; i++) {
				if(tmp[i]) {
					System.out.print(arr[i] + " ");
				}
			}
			System.out.println();
			return;
		}
		
		tmp[idx] = true;
		subset(tmp, idx+1);
		tmp[idx] = false;
		subset(tmp, idx+1);
	}

	public static void Permutation(int[] tmp, int idx) {
		if(idx == tmp.length) {
			System.out.println(Arrays.toString(tmp));
			return;
		}
		for(int i = 0; i < arr.length; i++) {
			if(!check[i]) {
				check[i] = true;
				tmp[idx] = arr[i];
				Permutation(tmp, idx+1);
				check[i] = false;
			}
		}
	}

	public static void Repermutation(int[] tmp, int idx) {
		if(idx == tmp.length) {
			System.out.println(Arrays.toString(tmp));
			return;
		}
		for(int i = 0; i < arr.length; i++) {
			tmp[idx] = arr[i];
			Repermutation(tmp, idx+1);
		}
	}

	public static void Combination(int[] tmp, int idx, int k) {
		if(k == tmp.length) {
			System.out.println(Arrays.toString(tmp));
			return;
		}
		if(idx == arr.length) return;
		
		tmp[k] = arr[idx];
		Combination(tmp, idx+1, k+1);
		Combination(tmp, idx+1, k);
	}

	public static void Recombination(int[] tmp, int idx, int k) {
		if(k == tmp.length) {
			System.out.println(Arrays.toString(tmp));
			return;
		}
		if(idx == arr.length) return;
		
		tmp[k] = arr[idx];
		Recombination(tmp, idx, k+1);
		Recombination(tmp, idx+1, k);
		
	}
}
