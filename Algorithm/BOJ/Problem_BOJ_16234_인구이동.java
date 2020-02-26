import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem_BOJ_16234_인구이동 {
	
	static int N,L,R;
	static int[][]land;
	static int[][]copyLand;
	static boolean[][]visit;
	static int[]dr = {0,0,1,-1};
	static int[]dc = {1,-1,0,0};
	static int ans;
	
	static class Country{
		int row;
		int col;
		
		public Country(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
		
	}
	
	
	public static boolean isPossibleMove() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				int pop = land[i][j];
				for(int k = 0; k < 4; k++) {
					int nr = i + dr[k];
					int nc = j + dc[k];
					if(isIn(nr,nc)) {
						if(Math.abs(pop-land[nr][nc]) >= L 
								&& Math.abs(pop-land[nr][nc]) <= R) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	public static void openBoard() {
		
		visit = new boolean[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(!visit[i][j]) {
					bfs(i,j);
				}
			}
		}
		
	}
	
	public static void getAns() {
		while(isPossibleMove()) {
			openBoard();
			//print();
			copy();
			ans++;
		}
	}
	
	public static void copy() {
		for(int i = 0; i < N; i++) {
			for(int j= 0; j < N; j++) {
				land[i][j] = copyLand[i][j];
			}
		}
	}
	
	public static void bfs(int r, int c) {
		visit[r][c] = true;
		int sum = 0;
		int index = 0;
		Queue<Country> q = new LinkedList<>();
		ArrayList<Country> list = new ArrayList();
		q.add(new Country(r,c));
		
		while(!q.isEmpty()) {
			Country tmp = q.poll();
			int row= tmp.row;
			int col= tmp.col;
			int pop = land[row][col];
			list.add(new Country(row,col));
			sum += pop;
			index++;
			
			for(int i = 0; i < 4; i++) {
				int nr = row + dr[i];
				int nc = col + dc[i];
				if(isIn(nr,nc) && !visit[nr][nc] && Math.abs(pop-land[nr][nc]) >= L
						&& Math.abs(pop-land[nr][nc]) <= R) {
					visit[nr][nc] = true;
					q.add(new Country(nr,nc));
				}
			}
		}
		sum = sum / index;
		
		for(int i = 0; i < list.size(); i++) {
			int row = list.get(i).row;
			int col = list.get(i).col;
			copyLand[row][col] = sum;
		}
	}
	
	public static void print() {
		for(int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(land[i]));
		}
		System.out.println();
	}
	
	public static boolean isIn(int r, int c) {
		return (0<= r && r < N && 0<= c && c < N);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String tmp = br.readLine();
		StringTokenizer st = new StringTokenizer(tmp, " ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		land = new int[N][N];
		copyLand = new int[N][N];
		for(int i = 0; i < N; i++) {
			tmp = br.readLine();
			st = new StringTokenizer(tmp, " ");
			for(int j = 0; j < N; j++) {
				land[i][j] = Integer.parseInt(st.nextToken());
				copyLand[i][j] = land[i][j];
			}
		}
		getAns();
		System.out.println(ans);
	}
}
