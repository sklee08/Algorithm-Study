import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem_BOJ_19238_스타트택시 {

	static int N, M, fuel;
	static boolean[][] map;
	static int[] dr = { 0, 0, 1, -1 };
	static int[] dc = { 1, -1, 0, 0 };
	static Dot cap;
	static Passenger[] passengers;
	static int ans;
	static int[] distArr;

	static class Dot {
		int row;
		int col;
		int cnt;

		public Dot(int r, int c) {
			this.row = r;
			this.col = c;
		}

		public Dot(int r, int c, int cnt) {
			this.row = r;
			this.col = c;
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return "Dot [row=" + row + ", col=" + col + ", cnt=" + cnt + "]";
		}

	}

	static class Passenger {
		Dot start;
		Dot end;
		boolean isFin;

		public Passenger(Dot start, Dot end, boolean is) {
			this.start = start;
			this.end = end;
			this.isFin = is;
		}

		@Override
		public String toString() {
			return "Passenger [start=" + start.toString() + ", end=" + end.toString() + "]";
		}

	}

	public static int bfs(Dot start, Dot end) {
		boolean[][] visit = new boolean[N][N];
		int ret = -1;
		int sr = start.row;
		int sc = start.col;
		start.cnt = 0;
		int er = end.row;
		int ec = end.col;
		visit[sr][sc] = true;
		Queue<Dot> q = new LinkedList<>();
		q.add(new Dot(sr, sc, 0));

		while (!q.isEmpty()) {
			Dot tmp = q.poll();
			int r = tmp.row;
			int c = tmp.col;
			int cnt = tmp.cnt;
			if (r == er && c == ec) {
				ret = cnt;
				break;
			}

			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if (isIn(nr, nc) && map[nr][nc] && !visit[nr][nc]) {
					visit[nr][nc] = true;
					q.add(new Dot(nr, nc, cnt + 1));
				}
			}
		}
		return ret;
	}

	public static boolean isFinished() {

		for (int i = 0; i < passengers.length; i++) {
			if (!passengers[i].isFin) {
				return false;
			}
		}
		return true;
	}

	public static int getDistMin(Dot start) {
		distArr = new int[M];
		for (int i = 0; i < M; i++) {
			distArr[i] = Integer.MAX_VALUE;
		}

		for (int i = 0; i < M; i++) {
			if (!passengers[i].isFin) {
				distArr[i] = bfs(start, passengers[i].start);
			}
		}

		int min = Integer.MAX_VALUE;

		for (int i = 0; i < M; i++) {
			min = Math.min(min, distArr[i]);
		}

		return min;
	}

	public static void getAns() {
		while (true) {
			if (isFinished()) {
				break;
			}
			int min = getDistMin(cap);
			if (min == Integer.MAX_VALUE) {
				ans = -1;
				break;
			}
			int cnt = 0;
			List<Dot> list = new ArrayList<>();
			for (int i = 0; i < M; i++) {
				if (distArr[i] == min) {
					passengers[i].start.cnt = i;
					list.add(passengers[i].start);
					cnt++;
				}
			}
			Passenger tmp = null;
			if (cnt > 1) {
				list.sort(new Comparator<Dot>() {

					@Override
					public int compare(Dot o1, Dot o2) {
						Integer r1 = o1.row;
						Integer r2 = o2.row;
						if (r1.equals(r2)) {
							Integer c1 = o1.col;
							Integer c2 = o2.col;
							return c1.compareTo(c2);
						} else {
							return r1.compareTo(r2);
						}
					}
				});
			}
			tmp = passengers[list.get(0).cnt];

			Dot start = tmp.start;
			Dot end = tmp.end;
			int capToStart = bfs(cap, start);
			fuel -= capToStart;
			if (fuel < 0 || capToStart == -1) {
				fuel = -1;
				break;
			}
			int tmpFuel = bfs(start, end);
			fuel -= tmpFuel;
			if (fuel < 0 || tmpFuel == -1) {
				fuel = -1;
				break;
			}
			fuel += tmpFuel * 2;
			tmp.isFin = true;

			cap = new Dot(end.row, end.col);

		}

		if (fuel < 0) {
			ans = -1;
		} else {
			ans = fuel;
		}
	}

	public static void print() {
		int len = passengers.length;
		for (int i = 0; i < len; i++) {
			System.out.println(passengers[i].toString());
		}
		System.out.println();
	}

	public static boolean isIn(int r, int c) {
		return (0 <= r && r < N && 0 <= c && c < N);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		fuel = Integer.parseInt(st.nextToken());
		map = new boolean[N][N];
		passengers = new Passenger[M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				if (Integer.parseInt(st.nextToken()) == 0) {
					map[i][j] = true;
				}
			}
		}
		st = new StringTokenizer(br.readLine(), " ");
		int r = Integer.parseInt(st.nextToken()) - 1;
		int c = Integer.parseInt(st.nextToken()) - 1;
		cap = new Dot(r, c);

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int sr = Integer.parseInt(st.nextToken()) - 1;
			int sc = Integer.parseInt(st.nextToken()) - 1;
			int er = Integer.parseInt(st.nextToken()) - 1;
			int ec = Integer.parseInt(st.nextToken()) - 1;
			passengers[i] = new Passenger(new Dot(sr, sc), new Dot(er, ec), false);
		}
		// 입력 완료
		getAns();

		System.out.println(ans);
	}
}
