import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Queue;

public class Problem_BOJ_2589_보물섬 {
	
	static int R,C;
	static int[] dr = {0,0,1,-1};
	static int[] dc = {1,-1,0,0};
	static boolean[][] map;
	static boolean[][] visited;
	static int ans = Integer.MIN_VALUE;
	
	static class LandInfo{
		int r;
		int c;
		int cnt;
		
		public LandInfo(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
		
		public LandInfo() {
			
		}
	}
	
	public static boolean isIn(int r, int c) {
		return (0 <= r && r < R && 0 <= c && c < C);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new boolean[R][C];
		visited = new boolean[R][C];
		for(int i = 0; i < R; i++) {
			String tmp = br.readLine();
			for(int j = 0; j < C; j++) {
				if(tmp.charAt(j) == 'L') {
					map[i][j] = true;
				}
			}
		}
		// 입력완료 
		// map : true가 육지 false가 바다
		
		getAns();
		
		System.out.println(ans);
	}
	
	public static void getAns() {
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(map[i][j]) {
					// 육지 찾음
					ans = Math.max(ans, bfs(i,j));
				}
			}
		}
	}
	
	public static int bfs(int r, int c) {
		Queue<LandInfo> q = new LinkedList<>();
		q.add(new LandInfo(r, c, 0));
		visited = new boolean[R][C];
		visited[r][c] = true;
		LandInfo tmp = new LandInfo();
		while(!q.isEmpty()) {
			tmp = q.poll();
			int row = tmp.r;
			int col = tmp.c;
			int cnt = tmp.cnt;
			
			for(int i = 0; i < 4; i++) {
				int nr = row + dr[i];
				int nc = col + dc[i];
				if(isIn(nr,nc) && map[nr][nc] && !visited[nr][nc]) {
					visited[nr][nc] = true;
					q.add(new LandInfo(nr,nc, cnt+1));
				}
			}
		}
		
		return tmp.cnt;
	}
}
