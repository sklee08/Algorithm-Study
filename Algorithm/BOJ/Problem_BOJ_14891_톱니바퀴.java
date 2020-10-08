import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Problem_BOJ_14891_톱니바퀴 {

	static int K;
	static int ans;
	static Rotate[] rotates;

	static class Rotate {
		int wNum;
		int dir;

		public Rotate(int wNum, int dir) {
			this.wNum = wNum;
			this.dir = dir;
		}
	}

	static boolean[][] wheels;

	public static void getAns() {
		for (int i = 0; i < K; i++) {
			// 회전 횟수

			Rotate tmp = rotates[i];
			rotate(tmp);
		}
		score();
	}

	public static void rotateRight(int idx) {
		boolean[] tmp = new boolean[8];

		for (int i = 1; i < wheels[idx].length; i++) {
			tmp[i] = wheels[idx][i - 1];
		}
		tmp[0] = wheels[idx][7];

		for (int i = 0; i < tmp.length; i++) {
			wheels[idx][i] = tmp[i];
		}
	}

	public static void rotateLeft(int idx) {
		boolean[] tmp = new boolean[8];

		for (int i = 0; i < wheels[idx].length - 1; i++) {
			tmp[i] = wheels[idx][i + 1];
		}
		tmp[7] = wheels[idx][0];

		for (int i = 0; i < tmp.length; i++) {
			wheels[idx][i] = tmp[i];
		}
	}

	public static void rotate(Rotate tmp) {
		int wNum = tmp.wNum;
		int dir = tmp.dir;

		switch (wNum) {
		case 0:
			if (dir == 1) {
				// 시계 방향
				int[] flags = new int[4];
				flags[0] = 1;
				if (wheels[0][2] != wheels[1][6]) {
					flags[1] = -1;
					if (wheels[1][2] != wheels[2][6]) {
						flags[2] = 1;
						if (wheels[2][2] != wheels[3][6]) {
							flags[3] = -1;
						}
					}
				}
				for (int i = 0; i < 4; i++) {
					if (flags[i] != 0) {
						if (flags[i] == 1) {
							rotateRight(i);
						} else {
							rotateLeft(i);
						}
					}
				}
			} else {
				int[] flags = new int[4];
				flags[0] = -1;
				if (wheels[0][2] != wheels[1][6]) {
					flags[1] = 1;
					if (wheels[1][2] != wheels[2][6]) {
						flags[2] = -1;
						if (wheels[2][2] != wheels[3][6]) {
							flags[3] = 1;
						}
					}
				}
				for (int i = 0; i < 4; i++) {
					if (flags[i] != 0) {
						if (flags[i] == 1) {
							rotateRight(i);
						} else {
							rotateLeft(i);
						}
					}
				}
			}
			break;
		case 1:
			if (dir == 1) {
				// 시계 방향
				int[] flags = new int[4];
				flags[1] = 1;

				if (wheels[1][6] != wheels[0][2]) {
					flags[0] = -1;
				}
				if (wheels[1][2] != wheels[2][6]) {
					flags[2] = -1;
					if (wheels[2][2] != wheels[3][6]) {
						flags[3] = 1;
					}
				}

				for (int i = 0; i < 4; i++) {
					if (flags[i] != 0) {
						if (flags[i] == 1) {
							rotateRight(i);
						} else {
							rotateLeft(i);
						}
					}
				}
			} else {
				int[] flags = new int[4];
				flags[1] = -1;

				if (wheels[1][6] != wheels[0][2]) {
					flags[0] = 1;
				}
				if (wheels[1][2] != wheels[2][6]) {
					flags[2] = 1;
					if (wheels[2][2] != wheels[3][6]) {
						flags[3] = -1;
					}
				}

				for (int i = 0; i < 4; i++) {
					if (flags[i] != 0) {
						if (flags[i] == 1) {
							rotateRight(i);
						} else {
							rotateLeft(i);
						}
					}
				}
			}

			break;
		case 2:

			if (dir == 1) {
				// 시계 방향
				int[] flags = new int[4];
				flags[2] = 1;

				if (wheels[3][6] != wheels[2][2]) {
					flags[3] = -1;
				}
				if (wheels[2][6] != wheels[1][2]) {
					flags[1] = -1;
					if (wheels[1][6] != wheels[0][2]) {
						flags[0] = 1;
					}
				}

				for (int i = 0; i < 4; i++) {
					if (flags[i] != 0) {
						if (flags[i] == 1) {
							rotateRight(i);
						} else {
							rotateLeft(i);
						}
					}
				}
			} else {
				int[] flags = new int[4];
				flags[2] = -1;

				if (wheels[3][6] != wheels[2][2]) {
					flags[3] = 1;
				}
				if (wheels[2][6] != wheels[1][2]) {
					flags[1] = 1;
					if (wheels[1][6] != wheels[0][2]) {
						flags[0] = -1;
					}
				}

				for (int i = 0; i < 4; i++) {
					if (flags[i] != 0) {
						if (flags[i] == 1) {
							rotateRight(i);
						} else {
							rotateLeft(i);
						}
					}
				}
			}

			break;
		case 3:

			if (dir == 1) {
				// 시계 방향
				int[] flags = new int[4];
				flags[3] = 1;
				if (wheels[2][2] != wheels[3][6]) {
					flags[2] = -1;
					if (wheels[1][2] != wheels[2][6]) {
						flags[1] = 1;
						if (wheels[0][2] != wheels[1][6]) {
							flags[0] = -1;
						}
					}
				}
				for (int i = 0; i < 4; i++) {
					if (flags[i] != 0) {
						if (flags[i] == 1) {
							rotateRight(i);
						} else {
							rotateLeft(i);
						}
					}
				}
			} else {
				int[] flags = new int[4];
				flags[3] = -1;
				if (wheels[2][2] != wheels[3][6]) {
					flags[2] = 1;
					if (wheels[1][2] != wheels[2][6]) {
						flags[1] = -1;
						if (wheels[0][2] != wheels[1][6]) {
							flags[0] = 1;
						}
					}
				}
				for (int i = 0; i < 4; i++) {
					if (flags[i] != 0) {
						if (flags[i] == 1) {
							rotateRight(i);
						} else {
							rotateLeft(i);
						}
					}
				}
			}

			break;
		default:
			break;
		}
	}

	public static void score() {
		for (int i = 0; i < 4; i++) {
			if (wheels[i][0]) {
				switch (i) {
				case 0:
					ans += 1;
					break;
				case 1:
					ans += 2;
					break;
				case 2:
					ans += 4;
					break;
				case 3:
					ans += 8;
					break;

				default:
					break;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		wheels = new boolean[4][8];
		for (int i = 0; i < 4; i++) {
			String tmp = br.readLine();
			for (int j = 0; j < tmp.length(); j++) {
				if (tmp.charAt(j) == '1') {
					wheels[i][j] = true;
				}
			}
		}
		K = Integer.parseInt(br.readLine());
		rotates = new Rotate[K];

		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int wNum = Integer.parseInt(st.nextToken()) - 1;
			int dir = Integer.parseInt(st.nextToken());
			rotates[i] = new Rotate(wNum, dir);
		}
		// 입력완료

		getAns();

		System.out.println(ans);
	}
}
