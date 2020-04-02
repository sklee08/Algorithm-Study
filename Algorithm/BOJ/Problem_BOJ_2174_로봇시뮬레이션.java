import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_BOJ_2174_로봇시뮬레이션 {
	
	static int A,B,N,M;
	static int[] dr = {0,-1,0,1};
	static int[] dc = {1,0,-1,0};
	static Robot[] robots;
	static Robot[] currPos;
	static boolean[][] field;
	static Cmd[] cmds;
	
	static final int EAST = 0;
	static final int WEST = 2;
	static final int SOUTH = 3;
	static final int NORTH = 1;
	
	static class Robot{
		private int row;
		private int col;
		private char dir;
		
		@Override
		public String toString() {
			return "Robot [row=" + row + ", col=" + col + ", dir=" + dir + "]";
		}

		public Robot(int row, int col, char dir) {
			super();
			this.row = row;
			this.col = col;
			this.dir = dir;
		}

		public int getRow() {
			return row;
		}

		public void setRow(int row) {
			this.row = row;
		}

		public int getCol() {
			return col;
		}

		public void setCol(int col) {
			this.col = col;
		}

		public char getDir() {
			return dir;
		}

		public void setDir(char dir) {
			this.dir = dir;
		}
		
	}
	
	static class Cmd{
		private int whichRobot;
		private char order;
		private int orderNum;
		public int getWhichRobot() {
			return whichRobot;
		}
		public void setWhichRobot(int whichRobot) {
			this.whichRobot = whichRobot;
		}
		public char getOrder() {
			return order;
		}
		public void setOrder(char order) {
			this.order = order;
		}
		public int getOrderNum() {
			return orderNum;
		}
		public void setOrderNum(int orderNum) {
			this.orderNum = orderNum;
		}
		public Cmd(int whichRobot, char order, int orderNum) {
			super();
			this.whichRobot = whichRobot;
			this.order = order;
			this.orderNum = orderNum;
		}
	}
	
	public static void print() {
		for(int i = 0 ; i < B; i++) {
			for(int j = 0; j < A; j++) {
				System.out.print(field[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static char setDirec(int n) {
		char ch = ' ';
		switch(n) {
		case 0:
			ch = 'E';
			break;
		case 1:
			ch = 'N';
			break;
		case 2:
			ch = 'W';
			break;
		case 3:
			ch = 'S';
			break;
		}
		
		return ch;
	}
	
	public static int getDirection(char ch) {
		switch(ch) {
		case 'E':
			return EAST;
		case 'S':
			return SOUTH;
		case 'N':
			return NORTH;
		case 'W':
			return WEST;
		default:
			return -1;
		}
	}
	
	public static void gameStart() {
		for(int i = 0; i < M; i++) {
			// 커맨드 갯수
			int robot = cmds[i].getWhichRobot();
			char chCmd = cmds[i].getOrder();
			//currPos = robots[robot-1];
			for(int j = 0; j < cmds[i].getOrderNum(); j++) {
				// 명령 적용하기 
				int tmp = 0;
				switch(chCmd) {
				case 'F':
					// 이동하기					
					char dir = robots[robot-1].getDir();
					int d = getDirection(dir);
					int nr = robots[robot-1].getRow() + dr[d];
					int nc = robots[robot-1].getCol() + dc[d];
					if(!isIn(nr,nc)) {
						// 밖으로 벗어남
						System.out.println("Robot "+robot+ " crashes into the wall");
						return;
					}
					if(field[nr][nc]) {
						// 다른 놈과 부딪힘
						for(int k = 0; k < N; k++) {
							if(robots[k].getRow() == nr && robots[k].getCol() == nc) {
								System.out.println("Robot "+robot+ " crashes into robot "+ (k+1));
								return;
							}
						}
					}
					// 둘다 아님
					field[robots[robot-1].getRow()][robots[robot-1].getCol()] = false;
					field[nr][nc] = true;
					robots[robot-1] = new Robot(nr,nc,dir);
					break;
				case 'L':
					
					tmp = getDirection(robots[robot-1].getDir());
					tmp = (tmp + 1) % 4;
					robots[robot-1].setDir(setDirec(tmp));
					break;
				case 'R':
					
					tmp = getDirection(robots[robot-1].getDir());
					tmp = (tmp + 3) % 4;
					robots[robot-1].setDir(setDirec(tmp));
					break;
				}
				
				
			}
		}
		
		System.out.println("OK");
	}
	
	public static boolean isIn(int r, int c) {
		return (0<= r && r < B && 0 <= c && c < A);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		field = new boolean[B][A];
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		robots = new Robot[N];
		cmds = new Cmd[M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			char ch = st.nextToken().charAt(0);
			field[B-n2][n1-1] = true;
			// 로봇들의 위치
			robots[i] = new Robot(B-n2, n1-1, ch);
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int n1 = Integer.parseInt(st.nextToken());
			char ch = st.nextToken().charAt(0);
			int n2 = Integer.parseInt(st.nextToken());
			cmds[i] = new Cmd(n1,ch,n2);
		}
		gameStart();
	}
}
