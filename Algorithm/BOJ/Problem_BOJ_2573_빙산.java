import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem_BOJ_2573_ºù»ê {
	
	static int N, M;
	static int[][]glacial;
	static int[][]copyGlacial;
	static int ans;
	static boolean[][]visit;
	static int[] dr = {0,0,1,-1};
	static int[] dc = {1,-1,0,0};
	
	public static void print() {
		for(int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(glacial[i]));
		}
		System.out.println();
	}
	
	public static boolean getDfs() {
		int cnt = 0;
		visit = new boolean[N][M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(!visit[i][j] && glacial[i][j] != 0) {
					visit[i][j] = true;
					dfs(i,j);
					cnt++;
				}
			}
		}
		
		if(cnt >= 2) {
			return true;
		}else if(cnt == 0) {
			System.out.println(0);
			System.exit(0);
			return true;
		}else {
			return false;
		}
	}
	
	public static void dfs(int r, int c) {
		for(int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if(isIn(nr,nc) && glacial[nr][nc] != 0 && !visit[nr][nc]) {
				visit[nr][nc] = true;
				dfs(nr,nc);
			}
		}
	}
	
	public static void melt() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				int cnt = 0;
				for(int k = 0; k < 4; k++) {
					int nr = i + dr[k];
					int nc = j + dc[k];
					if(isIn(nr,nc) && glacial[nr][nc] == 0) {
						cnt++;
					}
				}
				copyGlacial[i][j] -= cnt;
				if(copyGlacial[i][j] < 0) copyGlacial[i][j] = 0;
			}
		}
	}
	
	public static void getAns() {
		while(!getDfs()) {
			melt();
			copy();
			
			ans++;					
		}
	}
	
	public static void copy() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				glacial[i][j] = copyGlacial[i][j];
			}
		}
	}
	
	public static boolean isIn(int r, int c) {
		return (0<= r && r < N && 0<= c && c < M);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String tmp = br.readLine();
		StringTokenizer st = new StringTokenizer(tmp, " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		glacial = new int[N][M];
		copyGlacial = new int[N][M];
		for(int i = 0; i < N; i++) {
			tmp = br.readLine();
			st = new StringTokenizer(tmp, " ");
			for(int j = 0; j < M; j++) {
				glacial[i][j] = Integer.parseInt(st.nextToken());
				copyGlacial[i][j] = glacial[i][j];
			}
		}
		getAns();
		System.out.println(ans);
		// Input Complete
		
	}
}
