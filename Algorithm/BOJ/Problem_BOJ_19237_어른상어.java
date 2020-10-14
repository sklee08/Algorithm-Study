import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem_BOJ_19237_어른상어 {

	static int N, M, k;
	static int[] dr = { 0, -1, 1, 0, 0 };
	static int[] dc = { 0, 0, 0, -1, 1 };
	final static int LIMIT = 1000;
	static int[][] map;
	static Smell[][] smell;
	static Shark[] sharks;
	static int ans;
	static ArrayList<Dot> list = new ArrayList<>();

	static class Smell {
		int idx;
		int leftTime;

		public Smell(int i, int l) {
			this.idx = i;
			this.leftTime = l;
		}

		public Smell() {
			this.leftTime = k;
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

	static class Shark {
		int[] up = new int[4];
		int[] down = new int[4];
		int[] left = new int[4];
		int[] right = new int[4];
		int firstDir;
		int row;
		int col;

		public void setFirstDir(int dir) {
			this.firstDir = dir;
		}

		public void setDir(int[] up, int[] down, int[] left, int[] right) {
			for (int i = 0; i < 4; i++) {
				this.up[i] = up[i];
				this.down[i] = down[i];
				this.left[i] = left[i];
				this.right[i] = right[i];
			}
		}

		public Shark() {
		}
	}

	public static void move() {
		for (int i = 1; i <= M; i++) {
			// 1번 상어부터 이동
			if (sharks[i] == null)
				continue;
			int[] prio = getPriority(sharks[i]);
			boolean isFound = false;
			// 주변 체크
			for (int j = 0; j < 4; j++) {
				int nr = sharks[i].row + dr[prio[j]];
				int nc = sharks[i].col + dc[prio[j]];
				if (isIn(nr, nc) && smell[nr][nc] == null) {
					// 주변에 아무 냄새없는 곳 리스트를 찾음
					isFound = true;
					sharks[i].row = nr;
					sharks[i].col = nc;
					sharks[i].firstDir = prio[j];
					// 상어 이동
					break;
				}
			}

			if (!isFound) {
				// 상어가 이동할 곳이 없음.
				for (int j = 0; j < 4; j++) {
					int nr = sharks[i].row + dr[prio[j]];
					int nc = sharks[i].col + dc[prio[j]];
					if (isIn(nr, nc) && smell[nr][nc].idx == i) {
						sharks[i].row = nr;
						sharks[i].col = nc;
						sharks[i].firstDir = prio[j];
						for (int k = 0; k < list.size(); k++) {
							if (list.get(k).r == nr && list.get(k).c == nc) {
								list.remove(k);
							}
						}
						break;
					}
				}
			}
		}
	}

	public static void decreaseSmell() {
		for (int i = 0; i < list.size(); i++) {
			Dot p = list.get(i);
			if (smell[p.r][p.c].leftTime - 1 == 0) {
				smell[p.r][p.c] = null;
				list.remove(i);
				i--;
			} else {
				smell[p.r][p.c].leftTime--;
			}
		}
	}

	public static void eat() {
		int[][] visited = new int[N][N];

		for (int i = 1; i <= M; i++) {
			if (sharks[i] == null)
				continue;
			Shark tmp = sharks[i];
			if (visited[tmp.row][tmp.col] == 0) {
				visited[tmp.row][tmp.col] = i;
			} else {
				sharks[i] = null;
			}
		}
	}

	public static int[] getPriority(Shark s) {
		int[] ret = new int[4];
		int firstDir = s.firstDir;
		switch (firstDir) {
		case 1:
			// 처음 방향이 위
			for (int i = 0; i < 4; i++) {
				ret[i] = s.up[i];
			}
			break;
		case 2:
			for (int i = 0; i < 4; i++) {
				ret[i] = s.down[i];
			}
			break;
		case 3:
			for (int i = 0; i < 4; i++) {
				ret[i] = s.left[i];
			}
			break;
		case 4:
			for (int i = 0; i < 4; i++) {
				ret[i] = s.right[i];
			}
			break;

		default:
			break;
		}

		return ret;
	}

	public static boolean isIn(int r, int c) {
		return (0 <= r && r < N && 0 <= c && c < N);
	}

	public static void getAns() {
		while (true) {
			ans++;

			if (ans > 1000) {
				ans = -1;
				break;
			}

			// 1. 현재 상어 위치에 냄새 남기고 넣기
			for (int i = 1; i <= M; i++) {
				if (sharks[i] == null)
					continue;
				Shark tmp = sharks[i];

				smell[tmp.row][tmp.col] = new Smell(i, k);
				list.add(new Dot(tmp.row, tmp.col));
			}

			move();
			decreaseSmell();
			eat();

			boolean out = true;

			for (int i = 2; i <= M; i++) {
				if (sharks[i] != null) {
					out = false;
				}
			}

			if (out)
				break;
		}
	}

	public static boolean isFin() {
		boolean ret = false;

		return ret;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		smell = new Smell[N][N];
		sharks = new Shark[M + 1];

		for (int i = 1; i <= M; i++) {
			sharks[i] = new Shark();
		}

		int idx = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				if (tmp != 0) {
					map[i][j] = 1; // 상어 개수
					sharks[tmp].row = i;
					sharks[tmp].col = j;
				}

			}
		}
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= M; i++) {
			int dir = Integer.parseInt(st.nextToken());
			sharks[i].setFirstDir(dir);
		}

		for (int i = 1; i <= M; i++) {
			int[][] tmp = new int[4][4];
			for (int j = 0; j < 4; j++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int k = 0; k < 4; k++) {
					tmp[j][k] = Integer.parseInt(st.nextToken());
				}
			}
			sharks[i].setDir(tmp[0], tmp[1], tmp[2], tmp[3]);
		}
		// 입력완료

		getAns();

		System.out.println(ans);
	}
}
