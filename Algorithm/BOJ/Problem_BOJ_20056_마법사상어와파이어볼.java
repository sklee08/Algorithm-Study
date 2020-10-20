import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Problem_BOJ_20056_마법사상어와파이어볼 {

	static int N, M, K;
	static int ans;

	static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static ArrayList<Planet>[][] map;

	static class Planet {
		int mass;
		int speed;
		int dir;

		public Planet(int mass, int speed, int dir) {
			super();
			this.mass = mass;
			this.speed = speed;
			this.dir = dir;
		}

		@Override
		public String toString() {
			return "Planet [mass=" + mass + ", speed=" + speed + ", dir=" + dir + "]";
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new ArrayList[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = new ArrayList<>();
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			map[r][c].add(new Planet(m, s, d));
		}
		// 입력완료

		getAns();

		System.out.println(ans);
	}

	public static void getAns() {
		print();
		for (int i = 0; i < K; i++) {
			move();

			collide();

			// print();
		}
		getMass();
	}

	public static boolean isIn(int r, int c) {
		return (0 <= r && r < N && 0 <= c && c < N);
	}

	public static void move() {
		ArrayList<Planet>[][] tmp = new ArrayList[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				tmp[i][j] = new ArrayList<>();
				for (int k = 0; k < map[i][j].size(); k++) {
					tmp[i][j].add(map[i][j].get(k));
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j].size() != 0) {
					tmp[i][j].clear();
					for (int k = 0; k < map[i][j].size(); k++) {
						Planet p = map[i][j].get(k);
						if (p.mass == 0)
							continue;
						int mass = p.mass;
						int speed = p.speed;
						int dir = p.dir;

						int nr = i;
						int nc = j;
						int cnt = 0;
						while (cnt < speed) {
							nr += dr[dir];
							nc += dc[dir];
							cnt++;
						}
						int div = 0;
						if (!isIn(nr, nc)) {
							if (nr < 0) {
								nr = Math.abs(nr);
								div = nr / N;
								nr %= N;
								nr = N - nr;
							}
							if (nc < 0) {
								div = (nc + 1) / N;
								nc %= N;
								nc += N;
							}
							if (nr >= N) {
								div = nr / N;
								nr %= N;
							}
							if (nc >= N) {
								div = nc / N;
								nc %= N;
							}
						}
						tmp[nr][nc].add(new Planet(mass, speed, dir));
					}
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = new ArrayList<>();
				for (int k = 0; k < tmp[i][j].size(); k++) {
					map[i][j].add(tmp[i][j].get(k));
				}
			}
		}
	}

	public static void collide() {
		ArrayList<Planet>[][] tmp = new ArrayList[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				tmp[i][j] = new ArrayList<>();
				for (int k = 0; k < map[i][j].size(); k++) {
					tmp[i][j].add(map[i][j].get(k));
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j].size() > 1) {
					tmp[i][j].clear();
					int speed = 0;
					int mass = 0;
					boolean flag = true;
					int div = map[i][j].get(0).dir % 2;
					int cnt = 0;
					for (int k = 0; k < map[i][j].size(); k++) {
						Planet p = map[i][j].get(k);
						if (p.mass == 0)
							continue;
						speed += p.speed;
						mass += p.mass;
						if (div != p.dir % 2) {
							flag = false;
						}
						cnt++;
					}

					// cnt 는 0이 아닌 행성 개수
					if (cnt == 0)
						continue;
					speed /= cnt;
					mass /= 5;
					if (mass == 0)
						continue;
					if (flag) {
						// 0,2,4,6

						for (int k = 0; k < 4; k++) {
							tmp[i][j].add(new Planet(mass, speed, k * 2));
						}
					} else {
						// 1,3,5,7

						for (int k = 0; k < 4; k++) {
							tmp[i][j].add(new Planet(mass, speed, k * 2 + 1));
						}
					}
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = new ArrayList<>();
				for (int k = 0; k < tmp[i][j].size(); k++) {
					map[i][j].add(tmp[i][j].get(k));
				}
			}
		}
	}

	public static void getMass() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j].size() != 0) {
					for (int k = 0; k < map[i][j].size(); k++) {
						ans += map[i][j].get(k).mass;
					}
				}
			}
		}
	}

	public static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j].size() + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void print2() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j].size() != 0) {
					System.out.println("row is : " + i + ", col is " + j);
				}
				for (int k = 0; k < map[i][j].size(); k++) {

					System.out.print(map[i][j].get(k).toString() + " ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}

}
