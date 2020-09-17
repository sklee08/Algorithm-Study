import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem_BOJ_구슬탈출2 {

	static int N, M;
	static char[][] board;
	static int ans;
	static int[] dr = { 0, 0, 1, -1 };
	static int[] dc = { 1, -1, 0, 0 };
	static Dot red;
	static Dot blue;
	static Dot hole;

	static class Board {
		Dot red;
		Dot blue;
		int cnt;

		public Board(Dot r, Dot b, int cnt) {
			this.red = r;
			this.blue = b;
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return "Board [red=" + red.toString() + ", blue=" + blue.toString() + ", cnt=" + cnt + "]";
		}

	}

	static class Dot {
		int row;
		int col;

		public Dot(int r, int c) {
			this.row = r;
			this.col = c;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + col;
			result = prime * result + row;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Dot other = (Dot) obj;
			if (col != other.col)
				return false;
			if (row != other.row)
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Dot [row=" + row + ", col=" + col + "]";
		}

		public Dot() {
		};
	}

	public static Board nextStatus(Board b, int dir) {
		int redRow = b.red.row;
		int redCol = b.red.col;
		int nrr = redRow + dr[dir];
		int nrc = redCol + dc[dir];
		int blueRow = b.blue.row;
		int blueCol = b.blue.col;
		int nbr = blueRow + dr[dir];
		int nbc = blueCol + dc[dir];
		int cnt = b.cnt;
		Dot retRed = b.red;
		Dot retBlue = b.blue;

		boolean isCrashedRB = false;
		boolean isCrashedBR = false;

		// 충돌 체크 R/B

		while (isIn(nrr, nrc) && board[nrr][nrc] != '#') {
			if (nrr == blueRow && nrc == blueCol) {
				// 가는길에 파란돌이 존재하는 경우
				isCrashedRB = true;
				break;
			}

			nrr += dr[dir];
			nrc += dc[dir];
		}

		nrr = redRow + dr[dir];
		nrc = redCol + dc[dir];

		while (isIn(nbr, nbc) && board[nbr][nbc] != '#') {
			if (nbr == redRow && nbc == redCol) {
				// 가는길에 빨강돌이 존재하는 경우
				isCrashedBR = true;
				break;
			}
			nbr += dr[dir];
			nbc += dc[dir];
		}
		nbr = blueRow + dr[dir];
		nbc = blueCol + dc[dir];

		// 확인

		if (!isCrashedRB && !isCrashedBR) {
			// 양쪽 모두 충돌 없음
			retRed = move(retRed, dir);
			retBlue = move(retBlue, dir);

		} else if (isCrashedRB && !isCrashedBR) {
			// 빨강 -> 파랑 순서 충돌
			// 파랑부터 옮기기
			// System.out.println("R -> B");
			retBlue = move(retBlue, dir);
			if (retBlue == null) {
				retRed = move(retRed, dir);
			} else {
				if (move(retRed, dir) == null) {
					retRed = null;
				} else {
					retRed = new Dot(retBlue.row - dr[dir], retBlue.col - dc[dir]);
				}
			}
		} else if (!isCrashedRB && isCrashedBR) {
			// 파랑 -> 빨강 순서 충돌
			// System.out.println("B -> R");
			retRed = move(retRed, dir);
			if (retRed == null) {
				retBlue = move(retBlue, dir);
			} else {
				if (move(retBlue, dir) == null) {
					retBlue = null;
				} else {
					retBlue = new Dot(retRed.row - dr[dir], retRed.col - dc[dir]);
				}
			}
		}

		if (b.red.equals(retRed) && b.blue.equals(retBlue)) {
			return null;
		}
		return new Board(retRed, retBlue, cnt + 1);
	}

	public static Dot move(Dot start, int dir) {
		int r = start.row;
		int c = start.col;

		int nr = r + dr[dir];
		int nc = c + dc[dir];

		boolean flag = false;

		while (isIn(nr, nc) && board[nr][nc] != '#') {
			if (nr == hole.row && nc == hole.col) {
				// 가는길에 구멍이 존재하는 경우
				flag = true;
				break;
			}
			nr += dr[dir];
			nc += dc[dir];
		}
		if (flag) {
			return null;
		} else {
			return new Dot(nr - dr[dir], nc - dc[dir]);
		}
	}

	public static void bfs() {
		Queue<Board> q = new LinkedList<>();
		q.add(new Board(red, blue, 0));
		boolean isFinOnTime = true;
		boolean check = false;
		ans = -1;

		while (!q.isEmpty()) {
			Board tmp = q.poll();
			Dot r = tmp.red;
			Dot b = tmp.blue;
			int cnt = tmp.cnt;

			if (cnt > 10) {
				isFinOnTime = false;
				break;
			}
			if (r == null && b != null) {
				// 끝남
				check = false;
				ans = cnt;
				break;
			}
			if (b == null) {
				// check = true;
				continue;
			}
			for (int i = 0; i < 4; i++) {

				Board next = nextStatus(tmp, i);
				if (next != null) {
					q.add(next);
				}
			}

		}
		if (!isFinOnTime || check) {
			ans = -1;
		}
	}

	public static boolean isIn(int r, int c) {
		return (1 <= r && r < N - 1 && 1 <= c && c < M - 1);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new char[N][M];

		for (int i = 0; i < N; i++) {
			String tmp = br.readLine();
			for (int j = 0; j < M; j++) {
				if (tmp.charAt(j) == 'R') {
					board[i][j] = '.';
					red = new Dot(i, j);
				} else if (tmp.charAt(j) == 'B') {
					blue = new Dot(i, j);
					board[i][j] = '.';
				} else if (tmp.charAt(j) == 'O') {
					hole = new Dot(i, j);
					board[i][j] = tmp.charAt(j);
				} else {
					board[i][j] = tmp.charAt(j);
				}
			}
		}

		// 입력완료
		bfs();

		System.out.println(ans);
	}
}
