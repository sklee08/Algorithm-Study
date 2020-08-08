import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Problem_BOJ_17837_새로운게임2 {

	static int N, K;
	static int[][] colorMap;
	static ChessDotMap[][] stackMap;
	static ChessDot[] chess;
	static int turn;
	static int[] dr = { 0, 0, 0, -1, 1 };
	static int[] dc = { 0, 1, -1, 0, 0 };
	static Queue<ChessDot> q;

	static class ChessDotMap {
		int row;
		int col;
		Stack<ChessDot> stack;

		public ChessDotMap(int row, int col) {
			this.row = row;
			this.col = col;
			stack = new Stack<>();
		}

		public void add(ChessDot tmp) {
			this.stack.push(tmp);
		}

		public int getSize() {
			return this.stack.size();
		}

		public ChessDot popChess() {
			return this.stack.pop();
		}
	}

	static class ChessDot {
		int index;
		int row;
		int col;
		int dir;

		@Override
		public String toString() {
			return "ChessDot [index=" + index + ", row=" + row + ", col=" + col + ", dir=" + dir + "]";
		}

		public ChessDot(int index, int row, int col, int dir) {
			this.index = index;
			this.row = row;
			this.col = col;
			this.dir = dir;
		}

		public ChessDot() {

		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + col;
			result = prime * result + dir;
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
			ChessDot other = (ChessDot) obj;
			if (col != other.col)
				return false;
			if (dir != other.dir)
				return false;
			if (row != other.row)
				return false;
			if (index != other.index)
				return false;
			return true;
		}

	}

	public static void gameStart() {

		outer: while (true) {
			if (turn > 1000) {
				turn = -1;
				break;
			}
			turn++;
			for (int i = 0; i < K; i++) {
				ChessDot tmp = chess[i];
				int r = tmp.row;
				int c = tmp.col;
				int dir = tmp.dir;

				int nr = r + dr[dir];
				int nc = c + dc[dir];

				if (isIn(nr, nc) && colorMap[nr][nc] == 0) {
					// 다음 칸이 흰색이고 안에 있을 때

					// 기존 stackMap에 있는 돌 꺼내기
					List<ChessDot> list = new ArrayList<>();
					while (!stackMap[r][c].stack.isEmpty()) {
						ChessDot cd = stackMap[r][c].popChess();
						list.add(cd);
						if (cd.equals(tmp)) {
							break;
						}
					}

					// 움직이려는 방향으로 꺼낸 돌들 옮기기
					for (int j = list.size() - 1; j >= 0; j--) {
						ChessDot cd = list.get(j);
						int index = cd.index;
						chess[index].row = nr;
						chess[index].col = nc;
						// cd.row = nr;
						// cd.col = nc;
						stackMap[nr][nc].add(chess[index]);
					}
					if (stackMap[nr][nc].getSize() >= 4) {
						break outer;
					}
					continue;
				}
				if (isIn(nr, nc) && colorMap[nr][nc] == 1) {
					// 다음 칸이 빨간색이고 안에 있을 때
					List<ChessDot> list = new ArrayList<>();
					while (!stackMap[r][c].stack.isEmpty()) {
						ChessDot cd = stackMap[r][c].popChess();
						list.add(cd);
						if (cd.equals(tmp)) {
							break;
						}
					}
					for (int j = 0; j < list.size(); j++) {
						ChessDot cd = list.get(j);
						int index = cd.index;
						chess[index].row = nr;
						chess[index].col = nc;
						/*
						 * cd.row = nr; cd.col = nc;
						 */
						stackMap[nr][nc].add(chess[index]);
					}
					if (stackMap[nr][nc].getSize() >= 4) {
						break outer;
					}
					continue;
				}
				if (isIn(nr, nc) && colorMap[nr][nc] == 2) {
					// 다음 칸이 파란색이고 안에 있을 때
					int opp = getOpposite(dir);
					int nnr = r + dr[opp];
					int nnc = c + dc[opp];
					if (!isIn(nnr, nnc)) {
						// 반대 방향이 범위 벗어남
						chess[i].dir = opp;
						int size = stackMap[r][c].getSize();
						for (int k = 0; k < size; k++) {
							ChessDot ches = stackMap[r][c].stack.get(k);
							if (ches.index == i) {
								stackMap[r][c].stack.get(k).dir = opp;
							}
						}
						continue;
					}
					if (isIn(nnr, nnc) && colorMap[nnr][nnc] == 2) {
						// 반대 방향이 범위 안에 있고 또 파란색일 때
						chess[i].dir = opp;
						int size = stackMap[r][c].getSize();
						for (int k = 0; k < size; k++) {
							ChessDot ches = stackMap[r][c].stack.get(k);
							if (ches.index == i) {
								stackMap[r][c].stack.get(k).dir = opp;
							}
						}
						continue;
					}
					if (isIn(nnr, nnc) && colorMap[nnr][nnc] == 0) {
						// 반대 방향이 범위 안에 있고 흰색인 경우
						List<ChessDot> list = new ArrayList<>();
						while (!stackMap[r][c].stack.isEmpty()) {
							ChessDot cd = stackMap[r][c].popChess();
							list.add(cd);
							if (cd.equals(tmp)) {
								break;
							}
						}
						tmp.dir = opp;
						for (int j = list.size() - 1; j >= 0; j--) {
							ChessDot cd = list.get(j);
							int index = cd.index;
							chess[index].row = nnr;
							chess[index].col = nnc;
							/* chess[index].dir = opp; */
							/*
							 * cd.row = nnr; cd.col = nnc; cd.dir = opp;
							 */
							stackMap[nnr][nnc].add(chess[index]);
						}
						if (stackMap[nnr][nnc].getSize() >= 4) {
							break outer;
						}
						continue;
					}
					if (isIn(nnr, nnc) && colorMap[nnr][nnc] == 1) {
						// 반대 방향이 범위 안에 있고 빨간색인 경우
						List<ChessDot> list = new ArrayList<>();
						while (!stackMap[r][c].stack.isEmpty()) {
							ChessDot cd = stackMap[r][c].popChess();
							list.add(cd);
							if (cd.equals(tmp)) {
								break;
							}
						}
						tmp.dir = opp;
						for (int j = 0; j < list.size(); j++) {
							ChessDot cd = list.get(j);
							int index = cd.index;
							chess[index].row = nnr;
							chess[index].col = nnc;
							/*
							 * chess[index].dir = opp; cd.row = nnr; cd.col = nnc; cd.dir = opp;
							 */
							stackMap[nnr][nnc].add(chess[index]);
						}
						if (stackMap[nnr][nnc].getSize() >= 4) {
							break outer;
						}
						continue;
					}

				}
				if (!isIn(nr, nc)) {
					// 범위 벗어날 때
					int opp = getOpposite(dir);
					int nnr = r + dr[opp];
					int nnc = c + dc[opp];
					if (!isIn(nnr, nnc)) {
						chess[i].dir = opp;
						int size = stackMap[r][c].getSize();
						for (int k = 0; k < size; k++) {
							ChessDot ches = stackMap[r][c].stack.get(k);
							if (ches.index == i) {
								stackMap[r][c].stack.get(k).dir = opp;
							}
						}
						continue;
					}
					if (isIn(nnr, nnc) && colorMap[nnr][nnc] == 2) {
						chess[i].dir = opp;
						int size = stackMap[r][c].getSize();
						for (int k = 0; k < size; k++) {
							ChessDot ches = stackMap[r][c].stack.get(k);
							if (ches.index == i) {
								stackMap[r][c].stack.get(k).dir = opp;
							}
						}
						continue;
					}
					if (isIn(nnr, nnc) && colorMap[nnr][nnc] == 0) {
						List<ChessDot> list = new ArrayList<>();
						while (!stackMap[r][c].stack.isEmpty()) {
							ChessDot cd = stackMap[r][c].popChess();
							list.add(cd);
							if (cd.equals(tmp)) {
								break;
							}
						}
						tmp.dir = opp;
						for (int j = list.size() - 1; j >= 0; j--) {
							ChessDot cd = list.get(j);
							int index = cd.index;
							chess[index].row = nnr;
							chess[index].col = nnc;
							/*
							 * chess[index].dir = opp; cd.row = nnr; cd.col = nnc; cd.dir = opp;
							 */
							stackMap[nnr][nnc].add(cd);
						}
						if (stackMap[nnr][nnc].getSize() >= 4) {
							break outer;
						}
						continue;
					}
					if (isIn(nnr, nnc) && colorMap[nnr][nnc] == 1) {
						List<ChessDot> list = new ArrayList<>();
						while (!stackMap[r][c].stack.isEmpty()) {
							ChessDot cd = stackMap[r][c].popChess();
							list.add(cd);
							if (cd.equals(tmp)) {
								break;
							}
						}
						tmp.dir = opp;
						for (int j = 0; j < list.size(); j++) {
							ChessDot cd = list.get(j);
							int index = cd.index;
							chess[index].row = nnr;
							chess[index].col = nnc;
							/*
							 * chess[index].dir = opp; cd.row = nnr; cd.col = nnc; cd.dir = opp;
							 */
							stackMap[nnr][nnc].add(cd);
						}
						if (stackMap[nnr][nnc].getSize() >= 4) {
							break outer;
						}
						continue;
					}
				}
			}
			// print();
		}
	}

	public static int getOpposite(int dir) {
		if (dir <= 2) {
			return 3 - dir;
		} else if (dir > 2) {
			return 7 - dir;
		}
		return 0;
	}

	public static boolean isIn(int r, int c) {
		return (0 <= r && r < N && 0 <= c && c < N);
	}

	public static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {

				ChessDotMap tmp = stackMap[i][j];
				if (tmp.stack.size() != 0) {
					System.out.println("--------");
					System.out.println("row : " + i + ", col : " + j);
					for (int k = tmp.stack.size() - 1; k >= 0; k--) {
						System.out.println(tmp.stack.get(k).toString());
					}
					System.out.println("--------");
				}
			}
		}
		System.out.println();
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		colorMap = new int[N][N];
		stackMap = new ChessDotMap[N][N];
		chess = new ChessDot[K];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				colorMap[i][j] = Integer.parseInt(st.nextToken());
				stackMap[i][j] = new ChessDotMap(i, j);
			}
		}
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken());
			chess[i] = new ChessDot(i, r, c, d);
			stackMap[r][c].add(chess[i]);
		}
		// 입력 완료

		gameStart();

		System.out.println(turn);
	}
}
