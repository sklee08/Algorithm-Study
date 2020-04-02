import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_SWEA_6719_프로그래밍강좌 {
	
	static int N, K;
	static int[] M;
	static int[] chArr;
	static boolean[] watched;
	static double ans;
	
	public static void getCnt() {
		chooseArr();
		for(int i = K-1; i >= 0; i--) {
			// K번 시청
			int tmp = chArr[i];
			ans = (tmp+ans) / 2;
		}
	}
	
	public static void chooseArr() {
		chArr = new int[K];
		for(int i = 0; i < K; i++) {
			chArr[i] = getMax();
		}
	}
	
	public static int getMax() {
		int max = Integer.MIN_VALUE;
		int i = 0;
		for(i = 0; i < M.length; i++) {
			max = Math.max(max, M[i]);
		}
		for(i = 0; i < M.length; i++) {
			if(max == M[i]) {
				M[i] = 0;
				break;
			}
		}
		
		return max;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			M = new int[N];
			watched = new boolean[N];
			ans = 0.0;
			
			st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < N; i++) {
				M[i] = Integer.parseInt(st.nextToken());
			}
			
			getCnt();
			
			System.out.println("#"+t+" "+ans);
		}
	}
}
