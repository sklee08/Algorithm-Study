package algo_study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem_BOJ_17472_다리만들기2 {

	static int N,M;
	static boolean[][] bridge;
	static int[][] map;
	static int ans;
	static boolean [][]visit;
	static int numIsland;
	static int[]dr = {0,0,1,-1};
	static int[]dc = {1,-1,0,0};
	static int[][]connect;
	
	static class Dot{
		int row;
		int col;
		
		public Dot(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}	
	}
	
	public static void setIsland() {
		int island = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(bridge[i][j]) {
					bfs(new Dot(i,j), ++island);
				}
			}
		}		
		numIsland = island;
	}
	
	public static boolean isIn(int r, int c) {
		return (0<=r && r< N && 0 <=c && c < M);
	}
	
	public static void bfs(Dot start, int island) {
		Queue<Dot> q = new LinkedList<>();
		visit = new boolean[N][M];
		visit[start.row][start.col] = true;
		bridge[start.row][start.col] = false;
		map[start.row][start.col] = island;
		q.add(start);
		
		while(!q.isEmpty()) {
			Dot tmp = q.poll();
			int row = tmp.row;
			int col = tmp.col;
			
			for(int i = 0; i < 4; i++) {
				int nr = row +dr[i];
				int nc = col +dc[i];
				if(isIn(nr,nc) && !visit[nr][nc] && bridge[nr][nc]) {
					bridge[nr][nc] = false;
					map[nr][nc] = island;
					visit[nr][nc] = true;
					q.add(new Dot(nr,nc));
				}
			}
		}
	}	
	
	public static void setBridge() {
		for(int k = 1; k <= numIsland; k++) {
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(map[i][j] == k) {
						findNextIsland(i, j);
					}
				}
			}
		}
	}
	
	public static void findNextIsland(int r, int c) {
		int start = map[r][c];
		
		
		for(int i = 0; i < 4; i++) {
			int nr = r +dr[i];
			int nc = c + dc[i];
			int cnt = 0;
			while(isIn(nr, nc) && map[nr][nc] == 0) {
				cnt++;
				nr += dr[i];
				nc += dc[i];
			}
			if(cnt < 2) {
				continue;
			}
			if(!isIn(nr, nc)) {
				continue;
			}
			// 찾음
			
			int end = map[nr][nc];
			if(start != end) {
				if(connect[start][end] != 0) {
					connect[start][end] = Math.min(connect[start][end], cnt);
				}else {
					connect[start][end] = cnt;	
				}					
			}				
		}
	}
	
	public static void print() {
		for(int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
		System.out.println();
	}
	
	public static void getMin() {
		
		if(getNum() < numIsland - 1) {
			ans = -1;
			return;
		}

		ArrayList<Integer> ls = new ArrayList<>();
		boolean[] visited = new boolean[numIsland + 1];
		outer : for(int j = 1; j <= numIsland; j++) {
			for(int k = j; k <= numIsland; k++) {
				if(connect[j][k] != 0) {
					ls.add(j);
					visited[j] = true;
					break outer;
				}
			}
		}
		
		while(true) {			
			// 전체 리스트에서 가장 작은 다음 지점을 찾아 리스트에 추가
			PriorityQueue<Dot> pq = new PriorityQueue<>(new Comparator<Dot>() {

				@Override
				public int compare(Dot o1, Dot o2) {
					Integer i1 = o1.col;
					Integer i2 = o2.col;
					
					return i1.compareTo(i2);
				}
			});
			for(int i = 0; i < ls.size(); i++) {
				// 리스트를 순회
				int start = ls.get(i);
				for(int j = 1; j <= numIsland; j++) {
					if(connect[start][j] != 0 && !visited[j]) {
						pq.add(new Dot(j, connect[start][j]));
					}
				}
			}
			Dot tmp = pq.poll();
			if(tmp == null) {
				ans = -1;
				break;
			}
			ls.add(tmp.row);
			visited[tmp.row] = true;
			ans += tmp.col;	
			if(ls.size() == numIsland) break;
		}
		
	}
	
	public static int getNum() {
		int ret = 0;
		for(int i = 1; i <= numIsland; i++) {
			for(int j = i; j <= numIsland; j++) {
				if(connect[i][j] != 0) {
					ret++;
				}
			}
		}		
		return ret;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		bridge = new boolean[N][M];
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < M; j++) {
				if(st.nextToken().equals("1")) {
					bridge[i][j] = true;
				}
			}
		}
		// 입력완료
		setIsland();
		// map을 설정
		connect = new int[numIsland + 1][numIsland + 1];
		setBridge();		
		getMin();
		if(ans == Integer.MAX_VALUE || ans == 0) {
			ans = -1;
		}
		System.out.println(ans);
	}

}
