import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Problem_BOJ_17143_낚시왕 {
	
	static int R,C,M;
	static int []dr= {0,-1,1,0,0};
	static int []dc= {0,0,0,1,-1};
	static int ans;
	static List<Shark> sharks;
	static int[][] field;
	
	static class Shark{
		private int r;
		private int c;
		private int s;
		private int dir;
		private int size;
		
		public int getR() {
			return r;
		}
		public void setR(int r) {
			this.r = r;
		}
		public int getC() {
			return c;
		}
		public void setC(int c) {
			this.c = c;
		}
		public int getS() {
			return s;
		}
		public void setS(int s) {
			this.s = s;
		}
		public int getDir() {
			return dir;
		}
		public void setDir(int dir) {
			this.dir = dir;
		}
		public int getSize() {
			return size;
		}
		public void setSize(int size) {
			this.size = size;
		}
		public Shark(int r, int c, int s, int dir, int size) {
			super();
			this.r = r;
			this.c = c;
			this.s = s;
			this.dir = dir;
			this.size = size;
		}		
	}
	
	public static void getAns() {
		for(int i = 0; i < C; i++) {
			// 컬럼 개수만큼 반복
			for(int j = 0; j < R; j++) {
				if(field[j][i] == 1) {
					Shark tmp = null;
					for(int k = 0; k < sharks.size(); k++) {
						tmp = sharks.get(k);
						if(tmp.getR() == j && tmp.getC() == i) {
							break;
						}
					}
					ans += tmp.getSize();
					sharks.remove(tmp);
					field[j][i] = 0;
					break;
				}
			}
			moveShark();
		}
	}
	
	public static void moveShark() {
		int len = sharks.size();
		for(int i = 0; i < len; i++) {
			Shark tmp = sharks.get(i);
			int r = tmp.getR();
			int c = tmp.getC();
			int s = tmp.getS();
			int dir = tmp.getDir();
			field[r][c]--;
			for(int j = 0; j < s; j++) {
				// 속력만큼 이동
				r += dr[dir];
				c += dc[dir];
				if(!isIn(r,c)) {
					// 벗어남
					r -= dr[dir];
					c -= dc[dir];
					switch(dir) {
					case 1:
						dir = 2;
						break;
					case 2:
						dir = 1;
						break;
					case 3:
						dir = 4;
						break;
					case 4:
						dir = 3;
						break;
					}
					r += dr[dir];
					c += dc[dir];
				}				
			}
			field[r][c]++;
			sharks.get(i).setC(c);
			sharks.get(i).setR(r);
			sharks.get(i).setDir(dir);
		}
		// 겹치는 상어 지우기
		duplicate();
	
	}
	
	public static void duplicate() {
		
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(field[i][j] > 1) {
					// 겹치는 i, j
					List<Shark> list = new ArrayList<>();
					int size = sharks.size();
					for(int k = 0; k < size; k++) {
						Shark tmp = sharks.get(k);
						if(tmp.getR() == i && tmp.getC() == j) {
							list.add(tmp);
						}
					}
					int max = Integer.MIN_VALUE;
					for(int k = 0; k< list.size(); k++) {
						max = Math.max(max, list.get(k).getSize());
					}
					for(int k = 0; k< list.size(); k++) {
						Shark tmp =list.get(k);
						if(tmp.getSize() != max) {
							sharks.remove(tmp);
						}
					}					
					field[i][j] = 1;
				}
			}
		}
	}
	
	public static boolean isIn(int r, int c) {
		return (0<=r && r < R && 0<= c && c< C);
	}
	
	public static void print() {
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				System.out.print(field[i][j]+ " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		sharks = new ArrayList<>();
		field = new int[R][C];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			int size = Integer.parseInt(st.nextToken());
			field[r-1][c-1] = 1;
			sharks.add(new Shark(r-1, c-1, s, dir, size));
		}
		// 입력완료
		getAns();
		System.out.println(ans);
	}
}
