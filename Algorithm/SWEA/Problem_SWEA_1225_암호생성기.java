package algo_study;

import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

public class Problem_SWEA_1225_암호생성기 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		for(int t = 1; t <= 10; t++) {
			Queue<Integer> queue = new LinkedList<>();
			
			int dum = scan.nextInt();
			// test case index 
			
			for(int i = 0; i < 8; i++) {
				int n = scan.nextInt();
				queue.add(n);
			}
			outer : while(true) {
				
				for(int i = 1; i < 6; i++) {
					int k = queue.poll();
					if(k - i <= 0) {
						queue.add(0);
						break outer;
					}else {
						queue.add((k - i));
					}
				}
				
			}
			String s = "#" + t + " ";
			while(!queue.isEmpty()) {
				s+= queue.poll();
				s+= " ";
			}
			System.out.println(s);			
		}

	}

}
