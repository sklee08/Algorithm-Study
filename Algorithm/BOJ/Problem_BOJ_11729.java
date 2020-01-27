
import java.util.Scanner;

public class Problem_BOJ_11729 {
	
	public static void getPathHanoi(int n, int from, int to, StringBuilder sb) {
		if(n == 0) return;
		getPathHanoi(n-1, from, 6 - from - to,sb);
		sb.append(from + " "+ to +"\n");
		getPathHanoi(n-1, 6 - from - to, to,sb);
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		StringBuilder sb = new StringBuilder();
		
		int index = (1 << n) - 1;
		System.out.println(index);
		getPathHanoi(n, 1, 3, sb);
		System.out.println(sb);
		scan.close();
	}

}
