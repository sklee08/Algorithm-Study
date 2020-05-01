import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Problem_SWEA_1949_등산로 {
	
	static int N, K;
	static int[][]arr;
	static int ans;
	static int[] dr = {0,0,1,-1};
	static int[] dc = {1,-1,0,0};
	static List<Dot> high;
	static boolean isCon;
	static boolean visit[][];
	
	static class Dot{
		int r;
		int c;
		int height;
		int road;
		
		public Dot(int r, int c, int height, int road) {
			super();
			this.r = r;
			this.c = c;
			this.height = height;
			this.road = road;
		}		
	}
	
	public static void getRoad(Dot dot) {
		int r = dot.r;
		int c = dot.c;
		int height = dot.height;
		ans = Math.max(ans, dot.road);
		
		for(int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if(isIn(nr,nc) && !visit[nr][nc]) {
				if(height > arr[nr][nc]) {
					visit[nr][nc] = true;
					getRoad(new Dot(nr, nc, arr[nr][nc], dot.road+1));
					visit[nr][nc] = false;
				}else {
					if(!isCon && height > arr[nr][nc] - K) {
						for(int j =arr[nr][nc]-height+1; j <= K; j++) {
							isCon = true;
							arr[nr][nc] -=j;
							visit[nr][nc] = true;
							getRoad(new Dot(nr, nc, arr[nr][nc], dot.road+1));
							visit[nr][nc] = false;
							arr[nr][nc] += j;
							isCon = false;
						}						
					}
				}
			}
		}
	}
	
	public static void getAns() {
		getHigh();
		
		for(int i = 0; i < high.size(); i++) {
			Dot tmp = high.get(i);
			visit = new boolean[N][N];
			visit[tmp.r][tmp.c] = true;
			getRoad(tmp);
		}
	}
	
	public static void getHigh() {
		int max = Integer.MIN_VALUE;
		for(int i = 0 ; i < N; i++) {
			for(int j = 0; j < N; j++) {
				max = Math.max(max, arr[i][j]);
			}
		}
		
		for(int i = 0 ; i < N; i++) {
			for(int j = 0 ; j < N; j++) {
				if(arr[i][j] == max) {
					high.add(new Dot(i, j, max, 1));
				}
			}
		}
	}
	
	public static boolean isIn(int r, int c) {
		return (0 <= r && r < N && 0 <= c && c < N);
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		
		for(int t= 1; t <= tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			arr = new int[N][N];			
			ans = Integer.MIN_VALUE;
			high = new ArrayList<>();
			
			for(int i = 0 ; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// 입력완료
			
			getAns();
			
			
			System.out.println("#"+t+" "+ans);
		}
	}
}
