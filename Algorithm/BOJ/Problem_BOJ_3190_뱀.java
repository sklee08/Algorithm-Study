import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_BOJ_3190_뱀 {
	
	static int N, K, L;
	static int[] dr = {0,0,1,0,-1};
	static int[] dc = {0,1,0,-1,0};
	static boolean[][] apples;
	static int[][] maps;
	static Snake[] snakes;
	static int ans;
	static int tailR;
	static int tailC;
	
	static final char LEFT = 'L';
	static final char RIGHT = 'D';
	
	static class Snake{
		int X;
		char C;
		
		public Snake(int X, char C) {
			this.X = X;
			this.C = C;
		}
	}
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		apples = new boolean[N][N];
		maps = new int[N][N];
		for(int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			apples[r][c] = true;
		}
		L = Integer.parseInt(br.readLine());
		snakes = new Snake[L];
		for(int i = 0; i < L; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			char c = st.nextToken().charAt(0);
			snakes[i] = new Snake(x,c);
		}
		// 입력 완료
		
		startGame(0,0);
		
		
		System.out.println(ans + 1);
		
	}
	
	public static boolean isIn(int r, int c) {
		return (0 <= r && r < N && 0 <= c && c < N);
	}
	
	public static int getRight(int dir) {
		if(dir == 4) {
			return 1;
		}else {
			return dir + 1;
		}
	}
	
	public static int getLeft(int dir) {
		if(dir == 1) {
			return 4;
		}else {
			return dir - 1;
		}
	}
	
	public static void print() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(maps[i][j]+" ");
			}
			System.out.println();
		}
		
	}
	
	public static void startGame(int r, int c) {
		int currDir = 1;
		maps[r][c] = 1;
		int nr = r;
		int nc = c;
		tailR = r;
		tailC = c;
		int indexL = 0;
		nr += dr[currDir];
		nc += dc[currDir];
		
		while(isIn(nr,nc) && maps[nr][nc] == 0) {
			ans++;
			if(apples[nr][nc]) {
				// 사과가 다음칸에 있을 때
				apples[nr][nc] = false;
				maps[nr][nc] = currDir;
			}else {
				// 사과가 다음칸에 없을 때
				int tailDir = maps[tailR][tailC];
				maps[tailR][tailC] = 0;
				tailR += dr[tailDir];
				tailC += dc[tailDir];
				// 꼬리 변경
				maps[nr][nc] = currDir;
			}
			if(indexL < L) {
				if(ans == snakes[indexL].X) {
					// 방향 변경 타이밍
					char ch = snakes[indexL].C;
					
					
					if(ch == LEFT) {
						currDir = getLeft(currDir);
					}else if(ch == RIGHT) {
						currDir = getRight(currDir);
					}
					
					maps[nr][nc] = currDir;
					indexL++;
				}
			}			
			nr += dr[currDir];
			nc += dc[currDir];
//			System.out.println("----------------------");
//			System.out.println(ans + "초 후 상황");
//			print();
//			System.out.println("----------------------");
			
		}
	}
	
}
