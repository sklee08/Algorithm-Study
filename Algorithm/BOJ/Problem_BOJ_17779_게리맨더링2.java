import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem_BOJ_17779_게리맨더링2 {

	static int N;
	static int[][] arr;
	static int[] dr = { 0, 0, 1, -1 };
	static int[] dc = { 1, -1, 0, 0 };
	static int ans;
	static int[][] area;
	static boolean[][] visit;
	static int[] idxArr;

	static class Dot {
		int r;
		int c;

		public Dot(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void divideArea(int r, int c, int d1, int d2) {
		// 선거구 표시하기

		initArea();
		// 초기화
		visit = new boolean[N][N];

		for (int i = 0; i <= d1; i++) {
			int tr = r + i;
			int tc = c - i;
			int ttr = r + d2 + i;
			int ttc = c + d2 - i;
			if(isIn(tr, tc) && isIn(ttr, ttc)) {
				area[tr][tc] = 5;
				area[ttr][ttc] = 5;
				visit[tr][tc] = true;
				visit[ttr][ttc] = true;
			}			
		}
		for (int i = 0; i <= d2; i++) {
			int tr = r + i;
			int tc = c + i;
			int ttr = r + d1 + i;
			int ttc = c - d1 + i;
			if(isIn(tr, tc) && isIn(ttr, ttc)) {
				area[tr][tc] = 5;
				area[ttr][ttc] = 5;
				visit[tr][tc] = true;
				visit[ttr][ttc] = true;
			}
		}

		outer: for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visit[i][j]) {
					bfs(i, j);
					break outer;
				}
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visit[i][j]) {
					area[i][j] = 5;
				}
			}
		}
		// print();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i >= 0 && i < r + d1 && 0 <= j && j <= c && area[i][j] != 5) {
					area[i][j] = 1;
				} else if (0 <= i && i <= r + d2 && c < j && j < N && area[i][j] != 5) {
					area[i][j] = 2;
				} else if (r + d1 <= i && i < N && 0 <= j && j < c - d1 + d2 && area[i][j] != 5) {
					area[i][j] = 3;
				} else if (r + d2 < i && i < N && c - d1 + d2 <= j && j < N && area[i][j] != 5) {
					area[i][j] = 4;
				}
			}
		}


		ans = Math.min(ans, getDiff());
	}

	public static int getDiff() {

		int[] tmpArr = new int[6];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int tmp = area[i][j];
				switch (tmp) {
				case 1:
					tmpArr[1] += arr[i][j];
					break;
				case 2:
					tmpArr[2] += arr[i][j];
					break;
				case 3:
					tmpArr[3] += arr[i][j];
					break;
				case 4:
					tmpArr[4] += arr[i][j];
					break;
				case 5:
					tmpArr[5] += arr[i][j];
					break;
				}
			}
		}
		
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (int i = 1; i <= 5; i++) {
			min = Math.min(min, tmpArr[i]);
			max = Math.max(max, tmpArr[i]);
		}
		return max - min;
	}

	public static void bfs(int i, int j) {
		Queue<Dot> q = new LinkedList<>();
		q.add(new Dot(i, j));
		visit[i][j] = true;

		while (!q.isEmpty()) {
			Dot tmp = q.poll();
			int r = tmp.r;
			int c = tmp.c;

			for (int k = 0; k < 4; k++) {
				int nr = r + dr[k];
				int nc = c + dc[k];
				if (isIn(nr, nc) && !visit[nr][nc]) {
					visit[nr][nc] = true;
					q.add(new Dot(nr, nc));
				}
			}
		}
	}

	public static void initArea() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				area[i][j] = 0;
			}
		}
	}

	public static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(area[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void getAns() {

	}

	public static void reper(int[] tmp, int idx) {
		if (idx == tmp.length) {

			if (tmp[2] == 0 || tmp[3] == 0) {
				return;
			}
			if (tmp[0] + tmp[2] + tmp[3] >= N) {
				return;
			}
			if (tmp[1] - tmp[2] < 0 || tmp[1] + tmp[3] >= N) {
				return;
			}
			
			divideArea(tmp[0], tmp[1], tmp[2], tmp[3]);

			return;
		}

		for (int i = 0; i < idxArr.length; i++) {
			tmp[idx] = idxArr[i];
			reper(tmp, idx + 1);
		}
	}

	public static boolean isIn(int r, int c) {
		return (0 <= r && r < N && 0 <= c && c < N);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		area = new int[N][N];
		idxArr = new int[N];
		for (int i = 0; i < N; i++) {
			idxArr[i] = i;
		}
		ans = Integer.MAX_VALUE;

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 입력완료
		// getAns();
		// divideArea(1, 3, 3, 2);
		// print();
		reper(new int[4], 0);
		divideArea(3, 2, 1, 1);

		System.out.println(ans);

	}
}
