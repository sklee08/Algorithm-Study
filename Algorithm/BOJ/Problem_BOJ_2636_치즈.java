import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem_BOJ_2636_치즈 {
	
	static int N, M;
	static boolean[][] cheese;
	static boolean[][] edge;
	static int time;
	static int ans;
	static int[] dr = {0,0,1,-1};
	static int[] dc = {1,-1,0,0};
	
	static class Dot{
		int row;
		int col;
		
		public Dot(int row, int col){
			this.row = row;
			this.col = col;
		}
	}
	
	public static boolean isIn(int r, int c) {
		return (0<= r && r < N && 0<= c && c < M);
	}
	
	
	public static boolean isAllMelt() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(cheese[i][j]) return false;
			}
		}
		
		return true;
	}
	
	public static int getCh() {
		int ret = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(cheese[i][j]) ret++;
			}
		}
		
		return ret;
	}
	
	public static void getAns() {
		while(!isAllMelt()) {
			time++;
			getEdge();
			// 가장자리 배열 만들기
			ans = getCh();
			melt();
			//print();
		}
	}
	
	public static void melt() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(cheese[i][j]) {
					for(int k = 0; k < 4; k++) {
						int nr = i + dr[k];
						int nc = j + dc[k];
						if(isIn(nr,nc) && edge[nr][nc]) {
							cheese[i][j] = false;
							break;
						}
					}
				}
			}
		}
	}
	
	public static void print() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				System.out.print(cheese[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void getEdge() {
		// visit 배열에 가장자리 부분 (공기 부분) -> true로 표시
		edge = new boolean[N][M];
		Queue<Dot> q = new LinkedList<Dot>();
		edge[0][0] = true;
		q.add(new Dot(0,0));
		
		while(!q.isEmpty()) {
			Dot tmp = q.poll();
			int r = tmp.row;
			int c = tmp.col;
			
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(isIn(nr,nc) && !edge[nr][nc] && !cheese[nr][nc]) {
					edge[nr][nc] = true;
					q.add(new Dot(nr,nc));
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		cheese = new boolean[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < M; j++) {
				if(Integer.parseInt(st.nextToken()) == 1)
					cheese[i][j] = true;
			}
		}
		
		getAns();
		
		System.out.println(time);
		System.out.println(ans);
	}
}
