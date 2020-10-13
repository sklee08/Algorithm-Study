import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem_BOJ_17822_원판돌리기 {

	static int N, M, T;
	static int[][] dart;
	static int ans;
	static int[] dr = { 0, 0, 1, -1 };
	static int[] dc = { 1, -1, 0, 0 };
	static Dart[] dartArr;

	static class Dart {
		int x;
		int d;
		int k;

		public Dart(int x, int d, int k) {
			this.x = x;
			this.d = d;
			this.k = k;
		}
	}

	static class Dot {
		int r;
		int c;

		public Dot(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void changeNum() {
		// 겹친 숫자 지우기
		boolean flag = false;
		boolean[][] visit = new boolean[N][M];
		Queue<Dot> q = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visit[i][j] && dart[i][j] != 0) {
					int val = dart[i][j];
					q.add(new Dot(i, j));
					visit[i][j] = true;
					while (!q.isEmpty()) {
						Dot tmp = q.poll();
						int r = tmp.r;
						int c = tmp.c;
						for (int k = 0; k < 4; k++) {
							int nr = r + dr[k];
							int nc = c + dc[k];

							if (nr < 0) {
								continue;
							}
							if (nr >= N) {
								continue;
							}
							if (nc < 0) {
								nc = M - 1;
							}
							if (nc >= M) {
								nc = 0;
							}
							if (dart[nr][nc] == val && !visit[nr][nc]) {
								flag = true;
								dart[r][c] = 0;
								q.offer(new Dot(nr, nc));
								visit[nr][nc] = true;
								dart[nr][nc] = 0;
							}
						}
					}
				}
			}
		}

		if (!flag) {
			int sum = 0;
			int idx = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (dart[i][j] != 0) {
						sum += dart[i][j];
						idx++;
					}
				}
			}
			if (idx == 0) {
				return;
			}
			float avg = (float) sum / idx;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (dart[i][j] != 0) {
						if (dart[i][j] > avg) {
							dart[i][j]--;
						} else if (dart[i][j] < avg) {
							dart[i][j]++;
						}
					}
				}
			}
		}
	}

	public static void rotate(Dart d) {
		int x = d.x;
		int i = x;
		while (i <= N) {
			int[] tmp = new int[M];
			if (d.d == 0) {
				// 시계 방향 회전
				
				int start = M - d.k;			
				for(int j = 0; j < M; j++) {
					int idx = start % M;
					tmp[j] = dart[i-1][idx];
					start++;
				}
				
			} else {
				// 반시계 방향 회전
				int start = d.k;			
				for(int j = 0; j < M; j++) {
					tmp[j] = dart[i-1][(start++) % M];
				}
			}

			for (int j = 0; j < M; j++) {
				dart[i-1][j] = tmp[j];
			}
			i += x;
		}

	}

	public static void getAns() {

		for (int i = 0; i < T; i++) {
			// print();
			rotate(dartArr[i]);
			changeNum();
			// print();
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				ans += dart[i][j];
			}
		}

	}

	public static void print() {
		for (int i = 0; i < N; i++) {
			System.out.println(i + "'s dart");
			for (int j = 0; j < M; j++) {
				System.out.print(dart[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		dart = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				dart[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dartArr = new Dart[T];

		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			dartArr[i] = new Dart(x, d, k);
		}
		// 입력완료

		getAns();
		//print();
		System.out.println(ans);
	}
}
