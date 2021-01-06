import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_BOJ_11048_이동하기 {

	static int N, M;
	static int[][] candy;
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		candy = new int[N + 1][M + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= M; j++) {
				candy[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		getAns();

		System.out.println(ans);
	}

	public static void getAns() {

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				candy[i][j] = candy[i][j]+ getMax(candy[i - 1][j], candy[i][j - 1], candy[i - 1][j - 1]);
			}
		}
		
		ans = candy[N][M];
	}

	public static int getMax(int a, int b, int c) {
		return (Math.max(Math.max(a, b), c));
	}
}
