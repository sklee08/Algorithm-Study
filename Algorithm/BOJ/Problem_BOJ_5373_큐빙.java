package studying;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem_BOJ_5373_큐빙 {

	static int N;
	static Cube cube;

	static class Cube {
		char[][] top = new char[3][3];
		char[][] front = new char[3][3];
		char[][] back = new char[3][3];
		char[][] down = new char[3][3];
		char[][] right = new char[3][3];
		char[][] left = new char[3][3];

		public Cube() {
			for (int i = 0; i < 3; i++) {
				Arrays.fill(top[i], 'w');
				Arrays.fill(front[i], 'r');
				Arrays.fill(back[i], 'o');
				Arrays.fill(down[i], 'y');
				Arrays.fill(right[i], 'b');
				Arrays.fill(left[i], 'g');
			}

		}

		public void rotate(String direction) {
			if (direction.length() != 2) {
				return;
			}
			char point = direction.charAt(0);
			char dir = direction.charAt(1);
			if (dir == '+') {
				this.rotateAll(point);
			} else {
				for (int i = 0; i < 3; i++) {
					this.rotateAll(point);
				}
			}
		}

		public void printUp() {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					System.out.print(this.top[i][j]);
				}
				if (i != 2) {
					System.out.println();
				}
			}
		}

		public void rotateAll(char point) {
			char[][] tmp = new char[3][3];
			switch (point) {
			case 'F':
				tmp = new char[3][3];

				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						tmp[i][j] = this.front[2 - j][i];
					}
				}

				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						this.front[i][j] = tmp[i][j];
					}
				}

				char[] topBottom = new char[3];
				for (int i = 0; i < 3; i++) {
					topBottom[i] = this.top[2][i];
				}
				for (int i = 0; i < 3; i++) {
					this.top[2][i] = this.left[2 - i][2];
					this.left[2 - i][2] = this.down[0][2 - i];
					this.down[0][2 - i] = this.right[i][0];
					this.right[i][0] = topBottom[i];
				}

				break;
			case 'B':
				tmp = new char[3][3];

				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						tmp[i][j] = this.back[2 - j][i];
					}
				}

				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						this.back[i][j] = tmp[i][j];
					}
				}

				char[] topLine = new char[3];
				for (int i = 0; i < 3; i++) {
					topLine[i] = this.top[0][i];
				}
				for (int i = 0; i < 3; i++) {
					this.top[0][i] = this.right[i][2];
					this.right[i][2] = this.down[2][2 - i];
					this.down[2][2 - i] = this.left[2 - i][0];
					this.left[2 - i][0] = topLine[i];
				}

				break;
			case 'L':

				tmp = new char[3][3];

				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						tmp[i][j] = this.left[2 - j][i];
					}
				}

				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						this.left[i][j] = tmp[i][j];
					}
				}

				char[] topLeft = new char[3];
				for (int i = 0; i < 3; i++) {
					topLeft[i] = this.top[i][0];
				}
				for (int i = 0; i < 3; i++) {
					this.top[i][0] = this.back[2 - i][2];
					this.back[2 - i][2] = this.down[i][0];
					this.down[i][0] = this.front[i][0];
					this.front[i][0] = topLeft[i];
				}

				break;
			case 'R':

				tmp = new char[3][3];

				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						tmp[i][j] = this.right[2 - j][i];
					}
				}

				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						this.right[i][j] = tmp[i][j];
					}
				}

				char[] topRight = new char[3];
				for (int i = 0; i < 3; i++) {
					topRight[i] = this.top[i][2];
				}
				for (int i = 0; i < 3; i++) {
					this.top[i][2] = this.front[i][2];
					this.front[i][2] = this.down[i][2];
					this.down[i][2] = this.back[2 - i][0];
					this.back[2 - i][0] = topRight[i];
				}

				break;

			case 'D':

				tmp = new char[3][3];

				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						tmp[i][j] = this.down[2 - j][i];
					}
				}

				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						this.down[i][j] = tmp[i][j];
					}
				}

				char[] topDown = new char[3];
				for (int i = 0; i < 3; i++) {
					topDown[i] = this.front[2][i];
				}
				for (int i = 0; i < 3; i++) {
					this.front[2][i] = this.left[2][i];
					this.left[2][i] = this.back[2][i];
					this.back[2][i] = this.right[2][i];
					this.right[2][i] = topDown[i];
				}

				break;
			case 'U':

				tmp = new char[3][3];

				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						tmp[i][j] = this.top[2 - j][i];
					}
				}

				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						this.top[i][j] = tmp[i][j];
					}
				}

				char[] frontTop = new char[3];
				for (int i = 0; i < 3; i++) {
					frontTop[i] = this.front[0][i];
				}
				for (int i = 0; i < 3; i++) {
					this.front[0][i] = this.right[0][i];
					this.right[0][i] = this.back[0][i];
					this.back[0][i] = this.left[0][i];
					this.left[0][i] = frontTop[i];
				}

				break;

			default:
				break;
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		Cube[] cubes = new Cube[N];
		for (int i = 0; i < N; i++) {
			int tmp = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			cubes[i] = new Cube();
			for (int j = 0; j < tmp; j++) {
				String tmStr = st.nextToken();
				cubes[i].rotate(tmStr);
			}
		}
		for (int i = 0; i < N; i++) {
			cubes[i].printUp();
			if (i != N - 1)
				System.out.println();
		}
	}
}
