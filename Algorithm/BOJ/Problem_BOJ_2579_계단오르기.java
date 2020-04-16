import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem_BOJ_2579_계단오르기 {
	
	static int N;
	static int []stairs;
	static int []ans;			// 계단별 최대의 값
	
	
	public static void getAns() {
		if(N == 1) {
			System.out.println(stairs[1]);
			return;
		}
		if(N == 2) {
			System.out.println(stairs[2] + stairs[1]);
			return;
		}
		
		ans[1] = stairs[1];
		ans[2] = stairs[2] + stairs[1];
		ans[3] = Math.max(stairs[1]+stairs[3], stairs[3]+ stairs[2]);
		for(int i = 4; i <= N; i++) {
			ans[i] = Math.max(ans[i-3]+ stairs[i] + stairs[i-1], ans[i-2] + stairs[i]);
		}
		
		System.out.println(ans[N]);
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		stairs = new int[N+1];
		ans = new int[N+1];
		for(int i = 1; i <= N; i++) {
			stairs[i] = Integer.parseInt(br.readLine());
		}
		getAns();
	}
}
