import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem_SWEA_3234_저울 {
	
	static int N;
	static int[] weight;
	static int ans;
	static boolean[] visited;
	// 0 ~ N-1 : right / N ~ : left
	
	public static void getAns() {
		
	}
	
	public static void getPer(int sel, int l, int r) {
		if(sel == N) {
			ans++;
		}
		for(int i = sel; i < N; i++) {
			int tmp = weight[sel];
			weight[sel] = weight[i];
			weight[i] = tmp;
			
			getPer(sel+1, l + weight[sel], r);
			if(l >= r + weight[sel]) {
				getPer(sel + 1, l, r + weight[sel]);
			}
			tmp = weight[sel];
			weight[sel] = weight[i];
			weight[i] = tmp;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		
		for(int t= 1; t <= tc; t++) {
			N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			weight = new int[N];
			ans = 0;
			for(int i = 0; i < N; i++) {
				weight[i] = Integer.parseInt(st.nextToken());
			}
			getPer(0,0,0);
			
			System.out.println("#"+ t+" "+ans);
		}
	}
}
