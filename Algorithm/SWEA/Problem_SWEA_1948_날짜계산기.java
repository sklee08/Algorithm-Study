package algo_study;

import java.util.Scanner;

public class Problem_SWEA_1948_날짜계산기 {

	static int[] months = {0, 31,28,31,30,31,30, 31,31,30,31,30,31};
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tc =scan.nextInt();
		
		for(int t=1; t<=tc; t++) {
			int ret = 0;
			
			int []input1 = new int[2];
			int []input2 = new int[2];
			
			input1[0] = scan.nextInt();
			input1[1] = scan.nextInt();
			input2[0] = scan.nextInt();
			input2[1] = scan.nextInt();
			if(input1[0] == input2[0])
				ret = (input2[1] - input1[1] + 1);
			else {
				ret += input2[1];
				for(int i = input2[0]-1; i>= input1[0] +1; i--) {
					ret+=months[i];
				}
				ret += (months[input1[0]] - input1[1] + 1);
			}
			
			System.out.println("#"+t+" "+ret);
		}
	}

}
