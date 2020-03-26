import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;




public class Problem_BOJ_3085_사탕게임 {
	
	
	static int N;
	static char[][] candy;
	static char[][] copyCandy;
	static int[] dr = {0,0,1,-1};
	static int[] dc = {1,-1,0,0};
	
	static int max = 1;
	
	public static void getArrNum(char[] arr) {
		
		int ret = 1;
		for(int i = 0; i < arr.length - 1; i++) {
			if(arr[i] == arr[i+1]) {
				ret++;
			}else {
				max = Math.max(max, ret);
				ret = 1;
			}
		}
		max = Math.max(max, ret);
		
	}
	
	public static void getNum(int row, int col) {
		// 가로 확인
		for(int i = 0; i < N; i++) {
			char[] color = new char[N];
			for(int j = 0; j < N; j++) {
				color[j] = copyCandy[i][j];	// 가로
			}
			getArrNum(color);
		}
		
		for(int i = 0; i < N; i++) {
			char[] color = new char[N];
			for(int j = 0; j < N; j++) {
				color[j] = copyCandy[j][i];	// 가로
			}
			getArrNum(color);
		}
	}
	
	public static boolean isIn(int r, int c) {
		return (0 <= r && r < N && 0 <= c && c < N);
	}
	
	public static void print() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(copyCandy[i][j] + " ");
			}
			System.out.println();
		}
		
		System.out.println();
	}
	
	public static void getCandy() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				// 멤버접근
				for(int k = 0; k < 4; k++) {
					int nr = i+ dr[k];
					int nc = j+ dc[k];
					if(isIn(nr,nc) && candy[i][j] != candy[nr][nc]) {
						
						char tmp = copyCandy[i][j];
						copyCandy[i][j] = copyCandy[nr][nc];
						copyCandy[nr][nc] = tmp;
						
						getNum(i,j);
						copy();
					}
				}
			}
		}
	}
	
	public static void copy() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				copyCandy[i][j] = candy[i][j];
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		candy = new char[N][N];
		copyCandy = new char[N][N];
		
		for(int i = 0; i < N; i++) {
			String tmp = br.readLine();
			for(int j = 0; j < N; j++) {
				candy[i][j] = tmp.charAt(j);
			}
		}
		copy();
		// 입력 완료
		
		getCandy();
		
		System.out.println(max);
	}
}
