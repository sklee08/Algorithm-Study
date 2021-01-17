import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem_BOJ_1525_퍼즐 {
	static int ans;
	static int[] dr = { 0, 0, 1, -1 };
	static int[] dc = { 1, -1, 0, 0 };

	final static int DONE = 123456789;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int status = 0;

		for (int i = 0; i < 3; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 3; j++) {
				int k = Integer.parseInt(st.nextToken());
				if (k == 0) {
					k = 9;
				}
				status = (status * 10) + k;
			}
		}

		ans = bfs(status);

		System.out.println(ans);
	}

	public static int bfs(int start) {
		Queue<Integer> q = new LinkedList<>();
		Map<Integer, Integer> m = new HashMap<>();
		m.put(start, 0);
		q.add(start);

		while (!q.isEmpty()) {
			int nowNum = q.poll();
			String now = String.valueOf(nowNum);
			int nine = now.indexOf("9");
			int r = nine / 3;
			int c = nine % 3;

			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				int move = nr * 3 + nc;
				if (isIn(nr, nc)) {
					StringBuilder next = new StringBuilder(now);
					char temp = next.charAt(move);
					next.setCharAt(move, '9'); // 이동할 인덱스에 9를
					next.setCharAt(nine, temp); // 원래 9자리에 이동한 곳의 수를
					int nextNum = Integer.parseInt(next.toString());
					if (!m.containsKey(nextNum)) {
						m.put(nextNum, m.get(nowNum) + 1);
						q.add(nextNum);
					}
				}
			}
		}

		if (m.containsKey(DONE)) {
			return m.get(123456789);
		} else {
			return -1;
		}
	}

	public static boolean isIn(int r, int c) {
		return (0 <= r && r < 3 && 0 <= c && c < 3);
	}
}
