import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_BOJ_14889_스타트와링크 {
	
	static int N;
	static int[][] arr;
	static int ans;
	static int[] idxArr;
	static int sSum;
	static int lSum;
	
	
	public static void Permutation(int[] tmp, int idx, int[] ar, boolean[] check, boolean flag) {
		if(idx == tmp.length) {
			// 2개씩 고름
			if(flag) {
				sSum += arr[tmp[0]][tmp[1]];
			}else {
				lSum += arr[tmp[0]][tmp[1]];
			}
			return;
		}
		
		for(int i = 0; i < ar.length; i++) {
			if(!check[i]) {
				check[i] = true;
				tmp[idx] = ar[i];
				Permutation(tmp, idx+1, ar, check, flag);
				check[i] = false;
			}
		}
	}
	
	public static void Combination(int[] tmp, int idx, int r) {
		if(r == tmp.length) {
			boolean[] selected = new boolean[N];
			int[] left = new int[N / 2];
			sSum = 0;
			lSum = 0;
			for(int i = 0; i < tmp.length; i++) {
				selected[tmp[i]] = true;
			}
			int cnt = 0;
			for(int i = 0; i < N; i++) {
				if(!selected[i]) {
					left[cnt] = i;
					cnt++;
				}
			}
			Permutation(new int[2], 0, tmp, new boolean[tmp.length], true);
			Permutation(new int[2], 0, left, new boolean[left.length], false);
			ans = Math.min(ans, Math.abs(sSum - lSum));
			
			return;
		}
		if(idx == idxArr.length) return;
		
		tmp[r] = idxArr[idx];
		Combination(tmp, idx+1, r+1);
		Combination(tmp, idx+1, r);
	}
	
	public static void getAns() {
		// N C N /2 
		int sel = N / 2; 
		
		Combination(new int[sel], 0, 0);
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		ans = Integer.MAX_VALUE;
		idxArr = new int[N];
		for(int i = 0; i < N; i++) {
			idxArr[i] = i;
		}
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 입력 완료
		
		getAns();
		
		System.out.println(ans);
	}
}
