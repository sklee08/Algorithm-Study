package algo_study;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Problem_BOJ_17135_캐슬디펜스 {
	
	static int N,M,D;
	static boolean[][] hostile;
	static boolean[][] origin;
	static int ans;
	static int max = Integer.MIN_VALUE;
	static int[] dr = {0,-1,0};
	static int[] dc = {-1,0,1};
	static Stack<Integer> list;
	
	static class Dot{
		int row;
		int col;
		int cnt;		

		public Dot(int row, int col, int cnt) {
			super();
			this.row = row;
			this.col = col;
			this.cnt = cnt;
		}		
	}
	
	public static void setAttacker(int start, int select) {
		if(select == 3) {
			startGame();
			// 3명의 궁수를 지정한뒤 게임 시작
			if(ans > max) {
				max = ans;
			}
			ans = 0;
			copy();
			return;
		}else {
			for(int i = start; i < M; i++) {
				
				list.push(i);
				setAttacker(i+1, select+1);
				list.pop();
			}
		}
	}
	
	public static Dot checkDistance(Dot start, int n) {
		// 시작지점 부터 n거리에 적이 있는지 확인
		Queue<Dot> q = new LinkedList<>();
		q.add(start);
		boolean [][]visit = new boolean[N+1][M];
		visit[start.row][start.col] = true;
		
		while(!q.isEmpty()) {
			Dot tmp = q.poll();
			int r= tmp.row;
			int c= tmp.col;
			int cnt = tmp.cnt;
			if(cnt == n+1) {
				break;
			}		
			if(isIn(r,c) && hostile[r][c]) {
				// 적 발견
				return new Dot(r,c,cnt);
			}
			for(int i = 0; i < 3; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if(isIn(nr,nc) && !visit[nr][nc]) {
					q.add(new Dot(nr,nc, cnt+1));
					visit[nr][nc] = true;
				}
			}
		}
		
		return null;
	}
	
	public static void startGame() {
		// 게임이 시작 -> 궁수가 한 명씩 죽임
		// 중복 가능
		// 처음에는 자기 자신부터	
		int n = N;
		while(n > 0) {
			// 끝날 때까지
			
			boolean[][] arr = new boolean[N][M];
			// 쏘고 난 결과를 저장하는 배열 선언
			
			for(int i = 0; i <3; i++) {
				int c = list.get(i);
				Dot tmp = checkDistance(new Dot(n,c,0), D);
				if(tmp == null) continue;
				else {
					arr[tmp.row][tmp.col] = true;
				}				
			}
			
			// 공격 완료
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(arr[i][j] == hostile[i][j] && hostile[i][j]== true) {
						hostile[i][j] = false;
						ans++;
					}
				}
			}
			//print();
			// 적 위치 옮기기
			//moveHostile();
			n--;
			for(int i = 0; i < M; i++) {
				hostile[n][i] = false;
			}
		}		
	}
	
	public static boolean isIn(int r, int c) {
		return (0<= r && r < N && 0<= c && c <M);
	}
	
	public static void copy() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				hostile[i][j] = origin[i][j];
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		N = scan.nextInt();
		M = scan.nextInt();
		D = scan.nextInt();
		
		origin = new boolean[N][M];
		hostile = new boolean[N][M];
		list = new Stack<>();
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(scan.nextInt() == 1) {
					// 적이 있으면 true
					origin[i][j] = true;
					hostile[i][j] = true;
				}					
			}
		}
		setAttacker(0, 0);		
		
		System.out.println(max);
		scan.close();
	}
}
