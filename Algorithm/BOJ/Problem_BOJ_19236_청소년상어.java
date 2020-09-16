import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_BOJ_19236_청소년상어 {

	static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dc = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int ans = Integer.MIN_VALUE;

	static class Pair {
		int r;
		int c;
		int dir;

		public Pair(int r, int c, int dir) {
			this.r = r;
			this.c = c;
			this.dir = dir;
		}
	}

	public static void dfs(int[][] map, Pair[] fish, int eat, Pair shark) {
		int[][] tmpMap = new int[4][4];
		for (int i = 0; i < 4; i++) {
			System.arraycopy(map[i], 0, tmpMap[i], 0, 4);
		}
		Pair[] tmpFish = new Pair[17];

		for (int i = 1; i <= 16; i++) {
			tmpFish[i] = new Pair(fish[i].r, fish[i].c, fish[i].dir);
		}

		for (int i = 1; i <= 16; i++) {
			if (tmpFish[i].dir != -1) {
				Pair f = new Pair(tmpFish[i].r, tmpFish[i].c, tmpFish[i].dir);
				int nd = f.dir;

				for (int j = 0; j < 8; j++) {
					nd %= 8;

					int nr = f.r + dr[nd];
					int nc = f.c + dc[nd];

					if (!isIn(nr, nc) || map[nr][nc] == -1) {
						nd++;
						continue;
					}

					if (tmpMap[nr][nc] == 0) {
						tmpMap[f.r][f.c] = 0;
						tmpMap[nr][nc] = i;
						tmpFish[i] = new Pair(nr, nc, nd);
						break;
					} else if (tmpMap[nr][nc] > 0) {
						tmpMap[f.r][f.c] = tmpMap[nr][nc];
						tmpFish[tmpMap[nr][nc]] = new Pair(f.r, f.c, tmpFish[tmpMap[nr][nc]].dir);
						tmpMap[nr][nc] = i;
						tmpFish[i] = new Pair(nr, nc, nd);
						break;
					}
				}
			}
		}

		boolean flag = false;

		for (int i = 1; i <= 4; i++) {
			int nr = shark.r + i * dr[shark.dir];
			int nc = shark.c + i * dc[shark.dir];

			if (!isIn(nr, nc))
				break;

			int t = tmpMap[nr][nc];

			if (t > 0) {
				flag = true;

				tmpMap[shark.r][shark.c] = 0;
				Pair s = new Pair(nr, nc, tmpFish[t].dir);
				tmpFish[t] = new Pair(0, 0, -1);
				tmpMap[nr][nc] = -1;

				dfs(tmpMap, tmpFish, eat + t, s);

				tmpMap[nr][nc] = t;
				tmpFish[t] = new Pair(nr, nc, s.dir);
				tmpMap[shark.r][shark.c] = -1;
			}
		}

		if (!flag) {
			ans = Math.max(eat, ans);
		}
	}

	public static boolean isIn(int r, int c) {
		return (0 <= r && r < 4 && 0 <= c && c < 4);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ans = Integer.MIN_VALUE;
		int[][] map = new int[4][4];
		Pair[] fish = new Pair[17];

		for (int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 4; j++) {
				int num = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken()) - 1;
				map[i][j] = num;
				fish[num] = new Pair(i, j, dir);
			}
		}

		int eat = map[0][0];
		Pair shark = new Pair(0, 0, fish[map[0][0]].dir);
		fish[0] = new Pair(0, 0, -1);
		fish[map[0][0]] = new Pair(0, 0, -1);
		map[0][0] = -1;
		dfs(map, fish, eat, shark);

		System.out.println(ans);

	}
}
