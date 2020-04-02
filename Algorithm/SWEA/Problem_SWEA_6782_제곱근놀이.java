import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem_SWEA_6782_제곱근놀이 {
	
	static long N;
	static int ans;
	
	public static void getCnt() {
		while(N > 2) {
			long tmp = (long)Math.sqrt(N);
			if(tmp * tmp == N) {
				N = tmp;
				ans++;
			}else {
				ans += (tmp+1)*(tmp+1) - N;
				N = (tmp+1)*(tmp+1);
			}
		}
		
	}
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= tc; t++) {
			ans = 0;
			N = Long.parseLong(br.readLine());
			
			getCnt();
			System.out.println("#"+t+" "+ans);
		}
	}
}
