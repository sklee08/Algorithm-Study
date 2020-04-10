import java.io.BufferedReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Problem_BOJ_4485_젤다 {
	
	static int N;
	static int [][]arr;
	static int [][]dist;
	static int[] dr = {0,0,1,-1};
	static int[] dc = {1,-1,0,0};
	static int min;
	
	static class Rupee implements Comparable{
		int row;
		int col;
		int money;
		
		public Rupee(int row, int col, int money) {
			super();
			this.row = row;
			this.col = col;
			this.money = money;
		}

		@Override
		public int compareTo(Object o) {
			// TODO Auto-generated method stub
			int money = ((Rupee)o).money;
			return this.money - money;
		}
		
	}
	
	public static int daijkstra() {
		PriorityQueue<Rupee> pq = new PriorityQueue<>();
		dist[0][0] = arr[0][0];
		pq.add(new Rupee(0, 0, arr[0][0]));
		
		while(!pq.isEmpty()) {
			Rupee tmp = pq.poll();
			int row = tmp.row;
			int col = tmp.col;
			int money = tmp.money;
			if(dist[row][col] < money) {
				continue;
			}
			
			for(int i = 0; i < 4; i++) {
				int nr = row + dr[i];
				int nc = col + dc[i];
				
				if(isIn(nr,nc)) {
					if(dist[nr][nc] > dist[row][col] + arr[nr][nc]) {
						dist[nr][nc] = dist[row][col] + arr[nr][nc];
						pq.add(new Rupee(nr, nc, dist[nr][nc]));
					}
				}
			}
		}
		
		return dist[N-1][N-1];
		
	}
	
	public static boolean isIn(int r, int c) {
		return (0<= r && r < N && 0 <= c && c < N);
	}
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
		int tc = 1;
		while(true) {
			N = Integer.parseInt(br.readLine());
			if(N == 0) break;
			arr = new int[N][N];	
			dist = new int[N][N];	
			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					dist[i][j] = Integer.MAX_VALUE;
				}
			}			
			// 입력완료
			
			System.out.println("Problem "+tc+": "+daijkstra());
			tc++;
		}
	}
}
