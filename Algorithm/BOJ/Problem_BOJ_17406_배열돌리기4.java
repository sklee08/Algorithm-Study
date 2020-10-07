import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem_BOJ_17406_배열돌리기4 {

	static int N, M, K;
	static int[][] arr;
	static int[][] copyArr;
	static Cal[] cals;
	static int ans;
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };

	static class Cal {
		int r;
		int c;
		int s;

		public Cal() {
		}

		public Cal(int r, int c, int s) {
			this.r = r;
			this.c = c;
			this.s = s;
		}
	}

	public static int getArrNum() {
		int ret = Integer.MAX_VALUE;

		for (int i = 0; i < N; i++) {
			int tmp = 0;
			for (int j = 0; j < M; j++) {
				tmp += copyArr[i][j];
			}
			ret = Math.min(ret, tmp);
		}

		return ret;
	}

	public static void per(int[] tmp, int idx, int[] arr, boolean[] check) {
		if (tmp.length == idx) {
			copy();
			for (int i = 0; i < tmp.length; i++) {
				rotate(cals[tmp[i]]);

			}

			ans = Math.min(ans, getArrNum());
			return;
		}

		for (int i = 0; i < arr.length; i++) {
			if (!check[i]) {
				check[i] = true;
				tmp[idx] = arr[i];
				per(tmp, idx + 1, arr, check);
				check[i] = false;
			}
		}
	}

	public static void getAns() {
		int[] tmp = new int[K];
		for (int i = 0; i < K; i++) {
			tmp[i] = i;
		}

		per(new int[K], 0, tmp, new boolean[K]);
	}

	public static void copy() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copyArr[i][j] = arr[i][j];
			}
		}
	}

	public static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(copyArr[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void rotate(Cal cal) {
		int r = cal.r;
		int c = cal.c;
		int s = cal.s;

		for (int i = s; i > 0; i--) {
			int startR = r - i;
			int startC = c - i;

			int tmp = copyArr[startR][startC];
			int dir = 0;
			while (true) {
				if (dir == 4)
					break;
				startR += dr[dir];
				startC += dc[dir];
				if (isRectIn(startR, startC, r, c, i)) {
					int t = copyArr[startR][startC];
					copyArr[startR][startC] = tmp;
					tmp = t;
				} else {
					startR -= dr[dir];
					startC -= dc[dir];
					dir++;
				}
				if (startR == r - i && startC == c - i) {
					break;
				}
			}
		}

	}

	public static boolean isRectIn(int sr, int sc, int r, int c, int s) {
		return (r - s <= sr && sr <= r + s && c - s <= sc && sc <= c + s);
	}

	public static boolean isIn(int r, int c) {
		return (0 <= r && r < N && 0 <= c && c < M);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		copyArr = new int[N][M];
		cals = new Cal[K];
		ans = Integer.MAX_VALUE;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			cals[i] = new Cal(r, c, s);
		}

		// 입력완료
		copy();

		getAns();
		System.out.println(ans);
	}
}
