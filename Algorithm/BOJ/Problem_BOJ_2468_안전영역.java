import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem_BOJ_2468_안전영역 {

	static int N;
	static int[][] area;
	static int height;
	static int ans;
	static boolean[][]safe;
	static int Max = Integer.MIN_VALUE;
	static int []dr = {0,0,1,-1};
	static int []dc = {1,-1,0,0};
	
	static class Dot {
		int row;
		int col;
		
		public Dot(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		area = new int[N][N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st =new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				area[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 입력완료
		getHighest();
		getSafeArea();
		if(Max == 0) {
			System.out.println(1);
		}else {
			System.out.println(Max);
		}
		
	}
	
	public static void getHighest() {
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(max < area[i][j]) {
					max = area[i][j];
				}
			}
		}
		height = max;
	}
	
	public static void setSafe(int h) {
		safe = new boolean[N][N];
		int cnt = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(area[i][j] > h && !safe[i][j]) {
					bfs(new Dot(i,j), h);
					cnt++;
				}
			}
		}
		
		ans = cnt;
	}
	
	public static boolean isIn(int r, int c) {
		return (0<= r && r < N && 0 <= c && c < N);
	}
	
	public static void bfs(Dot start, int h) {
		Queue<Dot> q = new LinkedList<>();
		safe[start.row][start.col] = true;
		q.add(new Dot(start.row,start.col));
		
		while(!q.isEmpty()) {
			Dot tmp = q.poll();
			int r = tmp.row;
			int c = tmp.col;
			
			for(int i = 0; i < 4; i++) {
				int nr = r +dr[i];
				int nc = c +dc[i];
				if(isIn(nr,nc) && !safe[nr][nc] && area[nr][nc] > h) {
					safe[nr][nc] = true;
					q.add(new Dot(nr,nc));
				}
			}
		}
	}
	
	public static void getSafeArea() {
		for(int h = 1; h<= height; h++) {
			setSafe(h);
			
			if(Max < ans) {
				Max = ans;
			}
		}
	}

}
