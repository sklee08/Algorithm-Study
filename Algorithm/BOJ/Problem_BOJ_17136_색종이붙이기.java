import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem_BOJ_17136_색종이붙이기 {

	static boolean[][]paper;
	static boolean[][]copyPaper;
	final static int N = 10;
	static int ans;
	static int min = Integer.MAX_VALUE;
	static int []numPaper = new int[6];
	
	
	public static void setPaperNum() {
		for(int i =1; i < 6; i++) {
			numPaper[i] = 5;
		}
	}
	
	public static void copy() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				copyPaper[i][j] = paper[i][j];
			}
		}
	}
	
	public static boolean isRect(int row, int col, int size) {
		for(int i = row; i < row+size; i++) {
			for(int j = col; j < col + size; j++) {
				if(!isIn(i,j)) {
					return false;
				}
				if(!copyPaper[i][j]) {
					return false;
				}
			}
		}
		
		return true;
	}
		
	public static void dfs(int startR, int startC) {
		
		for(int i = 5; i > 0; i--) {
			if(isRect(startR, startC, i)) {
				if(numPaper[i] <= 0) continue;
				drawRect(startR,startC,i,false);
				// 그리기
				
				int row = 0;
				int col = 0;
				boolean isFound = false;
				outer : for(row = startR; row < N; row++) {
					for(col = 0; col < N; col++) {
						if(copyPaper[row][col]) {
							isFound = true;
							break outer;
						}
					}
				}
				if(isFound) {
					// 찾음
					dfs(row, col);
					drawRect(startR,startC,i,true);
				}else {
					// base
					min = Math.min(ans, min);
					drawRect(startR,startC,i,true);
				}				
			}
		}
	}
	
	public static void drawRect(int startR, int startC, int size, boolean b) {
		for(int i = startR; i < startR + size; i++) {
			for(int j = startC; j < startC + size; j++) {
				copyPaper[i][j] = b;
			}
		}
		if(b) {
			ans--;
			numPaper[size]++;
		}else {
			ans++;
			numPaper[size]--;
		}
		
	}
	
	public static boolean isIn(int r, int c) {
		return (0 <= r && r < N && 0 <= c && c < N);
	}
	
	public static void countPaper() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(copyPaper[i][j]) {
					dfs(i,j);
					return;
				}
			}
		}
		System.out.println(0);
		System.exit(0);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		paper = new boolean[N][N];
		copyPaper = new boolean[N][N];
		for(int i = 0; i < N; i++) {
			String tmp  = br.readLine();
			StringTokenizer st = new StringTokenizer(tmp, " ");
			for(int j = 0; j < N; j++) {
				if(st.nextToken().equals("1")) {
					paper[i][j] = true;
					copyPaper[i][j] = true;
				}
			}
		}
		// 입력완료
		
		setPaperNum();
		countPaper();
		if(min == Integer.MAX_VALUE) {
			min = -1;
		}
		System.out.println(min);
	}

}
