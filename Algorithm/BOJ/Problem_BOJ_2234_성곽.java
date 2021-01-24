import java.io.*;
import java.util.*;

public class Problem_BOJ_2234_성곽 {
	static class Player {
		int row, col;

		public Player(int row, int col) {
			this.col = col;
			this.row = row;
		}
	} // 서북동남

	static int[] dx = { 0, -1, 0, 1 };
	static int[] dy = { -1, 0, 1, 0 };
	static int[][] map;
	static boolean[][] visit;
	static boolean[][][] wall;
	static int R, C, cnt, ans, roomLarge, roomCnt, roomNum, maxLarge;
	static Queue<Player> q;
	static int room[] = new int[2500];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken()); // 1011
		map = new int[R][C];
		wall = new boolean[4][R][C];
		visit = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				for (int k = 3; k >= 0; k--) {
					if ((map[i][j] & 1 << k) >= 1)
						wall[k][i][j] = true;
				}
			}
		}
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (!visit[i][j]) {
					bfs(i, j, roomNum);
					room[roomNum++] = cnt;
					roomLarge = Integer.max(roomLarge, cnt);
					maxLarge = Math.max(roomLarge, maxLarge);
				}
			}
		}
		visit = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (!visit[i][j]) {
					bfs2(i, j);
				}
			}
		}
		System.out.println(roomCnt);
		System.out.println(roomLarge);
		System.out.println(maxLarge);
	}

	private static void bfs2(int i, int j) {
		q = new LinkedList<>();
		visit[i][j] = true;
		q.add(new Player(i, j));
		while (!q.isEmpty()) {
			Player player = q.poll();
			for (int dir = 0; dir < 4; dir++) {
				int nx = player.row + dx[dir];
				int ny = player.col + dy[dir];
				if (check(nx, ny))
					continue;
				if (!visit[nx][ny] && map[player.row][player.col] != map[nx][ny]) {
					maxLarge = Math.max(maxLarge, room[map[player.row][player.col]] + room[map[nx][ny]]);
				}
			}
		}
	}

	private static void bfs(int i, int j, int roomNum) {
		q = new LinkedList<>();
		cnt = 1;
		roomCnt++;
		visit[i][j] = true;
		map[i][j] = roomNum;
		q.add(new Player(i, j));
		while (!q.isEmpty()) {
			Player player = q.poll();
			for (int dir = 0; dir < 4; dir++) {
				int nx = player.row + dx[dir];
				int ny = player.col + dy[dir];
				if (check(nx, ny))
					continue; // 방문하지 않았고
				if (!visit[nx][ny]) { // 가려는곳 방향의 벽이 막혀있지 않으면
					if (!wall[dir][player.row][player.col]) {
						cnt++;
						q.add(new Player(nx, ny));
						visit[nx][ny] = true;
						map[nx][ny] = roomNum;
					}
				}
			}
		}
	}

	private static boolean check(int nx, int ny) {
		return nx < 0 || nx >= R || ny < 0 || ny >= C;
	}
}
