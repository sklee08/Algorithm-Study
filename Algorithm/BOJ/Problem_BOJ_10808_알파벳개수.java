package algorithm_study;

import java.util.Scanner;

public class Problem_BOJ_10808 {
	
	public static void main(String [] args) {
		Scanner scan = new Scanner(System.in);
		
		String s = scan.next();
		int[] alpa = new int[26];
		for(int i = 0; i < s.length(); i++) {
			alpa[s.charAt(i)-'a']++;
		}
		
		for(int i =0; i< 26; i++) {
			System.out.print(alpa[i] + " ");
		}
	}
}
