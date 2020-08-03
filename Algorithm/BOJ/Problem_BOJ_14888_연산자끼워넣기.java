import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_BOJ_14888_연산자끼워넣기 {

	static int N, max, min;
	static int A[];
	static int cal[] = new int[4];
	static boolean[] visited;

	public static int getCal(int idx) {
		idx++;
		for (int i = 0; i < 4; i++) {
			int tmp = cal[i];
			if (tmp >= idx) {
				return i;
			} else {
				idx -= tmp;
			}
		}

		return -1;
	}

	public static void perm(int[] tmp, int depth, int r) {
		if (depth == r) {
			// System.out.println(Arrays.toString(tmp));
			// 계산
			int ret = 0;
			for (int i = 0; i < N; i++) {
				if (i == 0) {
					ret = A[i];
				} else {
					int a = A[i];
					int cal = tmp[i - 1];
					switch (cal) {
					case 0:
						ret += a;
						break;
					case 1:
						ret -= a;
						break;
					case 2:
						ret *= a;
						break;
					case 3:
						ret /= a;
						break;
					}
				}
			}
			max = Math.max(max, ret);
			min = Math.min(min, ret);
			return;
		}

		for (int i = 0; i < N - 1; i++) {
			if (!visited[i]) {
				visited[i] = true;
				tmp[depth] = getCal(i);
				perm(tmp, depth + 1, r);
				visited[i] = false;
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			cal[i] = Integer.parseInt(st.nextToken());
		}

		// 입력 완료
		visited = new boolean[N - 1];
		int[] tmp = new int[N - 1];
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;

		perm(tmp, 0, N - 1);

		System.out.println(max);
		System.out.println(min);

	}
}
