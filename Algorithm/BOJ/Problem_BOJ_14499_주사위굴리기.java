import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_BOJ_14499_주사위굴리기 {

	private static int N, M, x, y, K;
	private static int[][] map;
	private static int[] dr = { 0, 0, 0, -1, 1 };
	private static int[] dc = { 0, 1, -1, 0, 0 };
	private static int[] cmd;
	private static Dice dice;

	static class Dice {
		int top;
		int midBack;
		int midFront;
		int midRight;
		int midLeft;
		int bottom;

		public Dice(int top, int midBack, int midFront, int midRight, int midLeft, int bottom) {
			super();
			this.top = top;
			this.midBack = midBack;
			this.midFront = midFront;
			this.midRight = midRight;
			this.midLeft = midLeft;
			this.bottom = bottom;
		}

		@Override
		public String toString() {
			return "Dice [top=" + top + ", midBack=" + midBack + ", midFront=" + midFront + ", midRight=" + midRight
					+ ", midLeft=" + midLeft + ", bottom=" + bottom + "]";
		}
		
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		cmd = new int[K];
		dice = new Dice(0, 0, 0, 0, 0, 0);

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < K; i++) {
			cmd[i] = Integer.parseInt(st.nextToken());
		}
		// 입력 완료

		for (int i = 0; i < K; i++) {
			int ret = moveDice(cmd[i]);
			if(ret != -1) {
				System.out.println(ret);
			}
		}
	}

	public static boolean isIn(int r, int c) {
		return (0 <= r && r < N && 0 <= c && c < M);
	}

	public static int moveDice(int dir) {
		int ret = 0;
		int nr = x;
		int nc = y;

		nr += dr[dir];
		nc += dc[dir];
		if (!isIn(nr, nc)) {
			return -1;
		}
		int bottom = dice.bottom;
		int top = dice.top;
		int midFront = dice.midFront;
		int midBack = dice.midBack;
		int midRight = dice.midRight;
		int midLeft = dice.midLeft;

		switch (dir) {
		case 1:
			// 동쪽으로
			if (map[nr][nc] == 0) {
				// 다음 칸이 0일 경우
				map[nr][nc] = midRight;
				dice.bottom = midRight;
				dice.top = midLeft;
				dice.midLeft = bottom;
				dice.midRight = top;
			} else {
				// 다음 칸이 0이 아닌 경우
				dice.bottom = map[nr][nc];
				// 복사
				map[nr][nc] = 0;
				dice.top = midLeft;
				dice.midLeft = bottom;
				dice.midRight = top;
			}
			// 주사위 변경
			break;
		case 2:
			// 서쪽으로
			if (map[nr][nc] == 0) {
				// 다음 칸이 0일 경우
				map[nr][nc] = midLeft;
				dice.bottom = midLeft;
				dice.top = midRight;
				dice.midLeft = top;
				dice.midRight = bottom;
			} else {
				// 다음 칸이 0이 아닌 경우
				dice.bottom = map[nr][nc];
				// 복사
				map[nr][nc] = 0;
				dice.top = midRight;
				dice.midLeft = top;
				dice.midRight = bottom;
			}
			// 주사위 변경
			break;
		case 3:
			// 북쪽으로
			if (map[nr][nc] == 0) {
				// 다음 칸이 0일 경우
				map[nr][nc] = midBack;
				dice.bottom = midBack;
				dice.top = midFront;
				dice.midFront = bottom;
				dice.midBack = top;
			} else {
				// 다음 칸이 0이 아닌 경우
				dice.bottom = map[nr][nc];
				// 복사
				map[nr][nc] = 0;
				dice.top = midFront;
				dice.midFront = bottom;
				dice.midBack = top;
			}
			break;
		case 4:
			// 남쪽으로
			if (map[nr][nc] == 0) {
				// 다음 칸이 0일 경우
				map[nr][nc] = midFront;
				dice.bottom = midFront;
				dice.top = midBack;
				dice.midFront = top;
				dice.midBack = bottom;
			} else {
				// 다음 칸이 0이 아닌 경우
				dice.bottom = map[nr][nc];
				// 복사
				map[nr][nc] = 0;
				dice.top = midBack;
				dice.midFront = top;
				dice.midBack = bottom;
			}
			break;
		}
		x = nr;
		y = nc;
		return dice.top;
	}

}
