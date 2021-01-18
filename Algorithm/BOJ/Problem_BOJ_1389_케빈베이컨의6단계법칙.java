import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem_BOJ_1389_케빈베이컨의6단계법칙 {
	static int N, M;
	static int ans;
	static boolean[][] connected;
	static boolean[] visited;

	static class Dot {
		int num;
		int kb;

		public Dot(int n, int k) {
			this.num = n;
			this.kb = k;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		connected = new boolean[N + 1][N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			connected[a][b] = true;
			connected[b][a] = true;
		}

		getAns();

		System.out.println(ans);
	}

	public static void getAns() {
		PriorityQueue<Dot> pq = new PriorityQueue<>(new Comparator<Dot>() {

			@Override
			public int compare(Dot o1, Dot o2) {
				Integer k1 = o1.kb;
				Integer k2 = o2.kb;
				if (k1.equals(k2)) {
					Integer n1 = o1.num;
					Integer n2 = o2.num;
					return n1.compareTo(n2);
				} else {
					return k1.compareTo(k2);
				}

			}
		});
		for (int i = 1; i <= N; i++) {
			int ret = bfs(i);
			pq.add(new Dot(i, ret));
		}

		ans = pq.poll().num;
	}

	public static int bfs(int n) {

		int ret = 0;
		for (int i = 1; i <= N; i++) {
			if (i == n)
				continue;
			visited = new boolean[N + 1];
			ret += bfs(n, i);
		}
		return ret;
	}

	public static int bfs(int from, int to) {
		Queue<Dot> q = new LinkedList<>();
		visited[from] = true;
		q.add(new Dot(from, 0));

		while (!q.isEmpty()) {
			Dot tmp = q.poll();
			int num = tmp.num;
			int cnt = tmp.kb;

			if (num == to) {
				return cnt;
			}

			for (int i = 1; i <= N; i++) {
				if (i == num)
					continue;
				if (!visited[i] && connected[num][i]) {
					visited[i] = true;
					q.add(new Dot(i, cnt + 1));
				}
			}
		}
		
		return 0;
	}
}
