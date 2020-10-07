import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Problem_BOJ_15685_드래곤커브 {

	static int N;
	static int ans;
	static int[] dr = { 0, -1, 0, 1 };
	static int[] dc = { 1, 0, -1, 0 };
	static Dragon[] drx;
	static boolean[][] arr = new boolean[101][101];

	static class Dragon {
		int x;
		int y;
		int d;
		int g;

		public Dragon(int x, int y, int d, int g) {
			super();
			this.x = x;
			this.y = y;
			this.d = d;
			this.g = g;
		}

	}

	public static int rotateAndReverse(int dir) {

		if (dir == 0) {
			dir = 3;
		} else {
			dir--;
		}
		// 90 도 회전
		dir += 2;
		dir %= 4;

		return dir;
	}

	public static void generateDrx(Dragon dragon) {
		int x = dragon.x;
		int y = dragon.y;
		int d = dragon.d;
		int g = dragon.g;
		List<Integer> dir = new ArrayList<>();
		dir.add(d);
		arr[x][y] = true;
		arr[x + dr[d]][y + dc[d]] = true;
		int nx = x + dr[d];
		int ny = y + dc[d];

		for (int i = 1; i <= g; i++) {
			int size = dir.size();
			for (int j = size - 1; j >= 0; j--) {
				int tmp = dir.get(j);
				tmp = rotateAndReverse(tmp);
				nx += dr[tmp];
				ny += dc[tmp];
				arr[nx][ny] = true;
				dir.add(tmp);
			}
		}
	}

	public static void getAns() {

		for (int i = 0; i < N; i++) {
			generateDrx(drx[i]);
		}

		for (int i = 0; i <= 100; i++) {
			for (int j = 0; j <= 100; j++) {
				if (isIn(i + 1, j + 1)) {
					// 사각형 생성 가능
					if (arr[i][j] && arr[i][j + 1] && arr[i + 1][j] && arr[i + 1][j + 1]) {
						ans++;
					}
				}
			}
		}
	}

	public static void print() {
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static boolean isIn(int r, int c) {
		return (0 <= r && r <= 100 && 0 <= c && c <= 100);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		drx = new Dragon[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			drx[i] = new Dragon(x, y, d, g);
		}

		// 입력완료
		getAns();
		// print();

		System.out.println(ans);
	}
}
