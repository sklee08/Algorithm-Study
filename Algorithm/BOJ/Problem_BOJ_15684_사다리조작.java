import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_BOJ_15684_사다리조작 {

	static int N, M, H;
	static int ans;
	static Point[][] map;
	static boolean[][] visited;

	static class Point {
		int row;
		int col;
		Point connected;

		public Point(int row, int col) {
			this.row = row;
			this.col = col;
			this.connected = null;
		}

		@Override
		public String toString() {
			if (this.connected == null) {
				return "X";
			} else {
				return "O";
			}
		}
	}

	public static void print() {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j].toString() + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void setLadder(int start, int depth, int sel) {
		// 남은 사다리 중에서 combination 하는 함수
		// 조합 후 copyMap 설정

		if (depth == sel) {
			// print();
			if (check()) {
				ans = Math.min(ans, sel);
			}
			return;
		}

		for (int i = start; i < N * H; i++) {
			int row = i / N;
			int col = i % N;

			if (!visited[row][col]) {
				visited[row][col] = true;
				if (map[row][col].connected == null) {
					// 연결 상태 없음
					if (isIn(row, col + 1) && map[row][col + 1].connected == null) {
						// 오른쪽 노드 확인
						map[row][col].connected = map[row][col + 1];
						map[row][col + 1].connected = map[row][col];
						// 연결
						setLadder(i + 1, depth + 1, sel);

						map[row][col].connected = null;
						map[row][col + 1].connected = null;
					}

				}
				visited[row][col] = false;
			}

		}
	}

	public static boolean isIn(int row, int col) {
		return (0 <= row && row < H && 0 <= col && col < N);
	}

	public static void getAns() {
		// ans를 구하는 함수

		// 0개일 때 체크 후 true면 끝
		if (check()) {
			ans = 0;
			return;
		}
		ans = Integer.MAX_VALUE;
		// 1개부터 3개까지
		int i = 1;
		for (i = 1; i <= 3; i++) {
			setLadder(0, 0, i);
		}
		if (ans == Integer.MAX_VALUE) {
			ans = -1;
		}
	}

	public static boolean check() {
		boolean isChecked = true;

		for (int i = 0; i < N; i++) {
			int currPos = i;
			for (int j = 0; j < H; j++) {
				Point tmp = map[j][currPos];
				if (tmp.connected != null) {
					// 연결되어 있는 상태면?
					currPos = tmp.connected.col;
				}
			}
			if (currPos != i) {
				isChecked = false;
				break;
			}
		}

		return isChecked;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new Point[H][N];
		visited = new boolean[H][N];
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = new Point(i, j);
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			map[a][b].connected = map[a][b + 1];
			map[a][b + 1].connected = map[a][b];
		}
		// 입력 완료

		getAns();

		System.out.println(ans);

	}
}
