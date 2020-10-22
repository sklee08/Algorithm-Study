package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Problem_BOJ_8972_미친아두이노 {

	static int R, C;
	static char[][] map;
	static int[] dr = { 0, 1, 1, 1, 0, 0, 0, -1, -1, -1 };
	static int[] dc = { 0, -1, 0, 1, -1, 0, 1, -1, 0, 1 };
	static int[] directions;
	static Dot posI;
	static int ans;
	static boolean[][] dup;

	static class Dot {
		int r;
		int c;
		int cnt;

		public Dot(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];

		for (int i = 0; i < R; i++) {
			String tmp = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = tmp.charAt(j);
				if (map[i][j] == ('I')) {
					posI = new Dot(i, j, 0);
				}
			}
		}
		String tmp = br.readLine();
		directions = new int[tmp.length()];
		for (int i = 0; i < tmp.length(); i++) {
			directions[i] = Integer.parseInt(Character.toString(tmp.charAt(i)));
		}
		// 입력완료

		getAns();

	}

	public static void getAns() {
		for (int i = 0; i < directions.length; i++) {
			ans++;
			moveI(i);

			move();

		}
		print();
	}

	public static boolean isIn(int r, int c) {
		return (0 <= r && r < R && 0 <= c && c < C);
	}

	public static void print() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				System.out.print(map[i][j]);
			}
			if (i != R - 1)
				System.out.println();
		}
	}

	public static void moveI(int idx) {
		outer: for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == 'I') {
					int nr = i + dr[directions[idx]];
					int nc = j + dc[directions[idx]];
					map[i][j] = '.';
					if (map[nr][nc] == ('R')) {
						System.out.println("kraj " + ans);
						System.exit(0);
					}
					map[nr][nc] = 'I';
					posI = new Dot(nr, nc, 0);
					break outer;
				}
			}
		}
	}

	public static void move() {
		int[][] tmp = new int[R][C];
		char[][] tmpStr = new char[R][C];
		dup = new boolean[R][C];

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				tmpStr[i][j] = map[i][j];
			}
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == 'R') {
					PriorityQueue<Dot> pq = new PriorityQueue<Dot>(new Comparator<Dot>() {

						@Override
						public int compare(Dot d1, Dot d2) {
							Integer r1 = Math.abs(d1.r - posI.r) + Math.abs(d1.c - posI.c);
							Integer r2 = Math.abs(d2.r - posI.r) + Math.abs(d2.c - posI.c);
							return r1.compareTo(r2);
						}
					});
					for (int k = 1; k <= 9; k++) {
						int nr = i + dr[k];
						int nc = j + dc[k];
						if (isIn(nr, nc)) {
							pq.add(new Dot(nr, nc, 0));
						}
					}
					Dot next = pq.poll();
					if (map[next.r][next.c] == 'I') {
						System.out.println("kraj " + ans);
						System.exit(0);
					}
					tmpStr[next.r][next.c] = 'R';
					tmp[next.r][next.c]++;
				}
			}
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (tmp[i][j] > 1) {
					tmpStr[i][j] = '.';
				}
				if (tmpStr[i][j] == 'R' && tmp[i][j] == 0) {
					tmpStr[i][j] = '.';
				}
			}
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				map[i][j] = tmpStr[i][j];
			}
		}
	}

}
