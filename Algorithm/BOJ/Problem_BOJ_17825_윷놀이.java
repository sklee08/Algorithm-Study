import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem_BOJ_17825_윷놀이 {

	static int arr[];
	static int ans;

	static class Node {
		int idx;
		int point;
		Node rNext;
		Node bNext;
		int dot;

		public Node(int point, Node rNext, Node bNext, int dot) {
			super();
			this.point = point;
			this.rNext = rNext;
			this.bNext = bNext;
			this.dot = dot;
		}

		public Node(int po, int dot) {
			this.point = po;
			this.dot = dot;
		}

		@Override
		public String toString() {
			if (this.bNext != null) {
				return "this Node's point : " + point + ", and next Node's point : " + this.bNext.point;
			} else {
				if (this.rNext != null) {
					return "this Node's point : " + point + ", and next Node's point : " + this.rNext.point;
				} else {
					return "connect to Nothing with point : " + point;
				}
			}

		}
	}

	static Node[] nodes;
	static Node start, end;

	public static void getAns() {
		dfs(new int[4], 0, 0);
	}

	public static void dfs(int[] pos, int total, int cnt) {
		if (cnt == 10) {
			ans = Math.max(ans, total);
			//System.out.println(Arrays.toString(pos));
			return;
		}
		if (pos[0] == 32 && pos[1] == 32 && pos[2] == 32 && pos[3] == 32) {
			ans = Math.max(ans, total);
			return;
		}
		int dice = arr[cnt];
		// 주사위 값

		int[] res = new int[4];
		for (int i = 0; i < 4; i++) {
			res[i] = pos[i];
		}
		// 배열 복사

		for (int i = 0; i < 4; i++) {
			Node tmp = nodes[pos[i]];
			if (tmp.idx == 32) {
				// 도착한 말이면 생략
				continue;
			}
			if (tmp.bNext != null) {
				tmp = tmp.bNext;
				for (int j = 0; j < dice - 1; j++) {
					if (tmp.idx == 32) {
						// 가는 도중 도착
						break;
					}
					tmp = tmp.rNext;
				}
			} else {
				for (int j = 0; j < dice; j++) {
					if (tmp.idx == 32) {
						// 가는 도중 도착
						break;
					}
					tmp = tmp.rNext;
				}
			}
			// 이동

			if (nodes[tmp.idx].dot != 0 && tmp.idx != 32) {
				// 이동하려는 칸에 말이 존재하고 끝이 아닌 경우
				continue;
			}
			nodes[pos[i]].dot--;
			nodes[tmp.idx].dot++;
			int tm = res[i];
			res[i] = tmp.idx;
			// 위치 변경
			dfs(res, total + nodes[tmp.idx].point, cnt + 1);
			res[i] = tm;
			nodes[pos[i]].dot++;

			nodes[tmp.idx].dot--;
		}
	}

	public static void init() {
		start = new Node(0, 4);
		end = new Node(0, 0);
		nodes = new Node[33];
		for (int i = 1; i <= 20; i++) {
			nodes[i] = new Node(i * 2, 0);
			nodes[i].idx = i;
		}
		for (int i = 21; i <= 23; i++) {
			nodes[i] = new Node(13 + 3 * (i - 21), 0);
			nodes[i].idx = i;
		}
		for (int i = 24; i <= 25; i++) {
			nodes[i] = new Node(22 + 2 * (i - 24), 0);
			nodes[i].idx = i;
		}
		for (int i = 26; i <= 28; i++) {
			nodes[i] = new Node(28 - (i - 26), 0);
			nodes[i].idx = i;
		}
		for (int i = 29; i <= 31; i++) {
			nodes[i] = new Node(25 + 5 * (i - 29), 0);
			nodes[i].idx = i;
		}
		start.rNext = nodes[1];
		nodes[0] = start;
		nodes[0].idx = 0;
		nodes[32] = end;
		nodes[32].idx = 32;
		// 생성

		for (int i = 1; i <= 19; i++) {
			nodes[i].rNext = nodes[i + 1];
			if (nodes[i].point == 10) {
				nodes[i].bNext = nodes[21];
			}

			if (nodes[i].point == 20) {
				nodes[i].bNext = nodes[24];
			}
			if (nodes[i].point == 30) {
				nodes[i].bNext = nodes[26];
			}
		}
		nodes[20].rNext = end;
		for (int i = 21; i <= 22; i++) {
			nodes[i].rNext = nodes[i + 1];
		}
		nodes[23].rNext = nodes[29];
		nodes[24].rNext = nodes[25];
		nodes[25].rNext = nodes[29];

		for (int i = 26; i <= 27; i++) {
			nodes[i].rNext = nodes[i + 1];
		}
		nodes[28].rNext = nodes[29];
		for (int i = 29; i <= 30; i++) {
			nodes[i].rNext = nodes[i + 1];
		}
		nodes[31].rNext = nodes[20];

		// 연결

	}

	public static void print() {
		for (int i = 0; i <= 32; i++) {
			System.out.println(nodes[i].toString());
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		arr = new int[10];
		for (int i = 0; i < 10; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		init();
		// print();

		getAns();
		System.out.println(ans);
	}
}
