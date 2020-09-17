import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_BOJ_13458_시험감독 {

	static int N, B, C;
	static int[] tests;
	static long ans;

	public static void getAns() {
		for (int i = 0; i < N; i++) {
			int test = tests[i];
			if (test <= B) {
				tests[i] = 0;
			} else {
				tests[i] -= B;
			}
			ans++;
		}
		
		for(int i = 0 ; i < N; i++) {
			if(tests[i] != 0) {
				if(tests[i] % C == 0) {
					ans += (tests[i] / C);
				}else {
					ans += (tests[i] / C + 1);
				}
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		tests = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			tests[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine(), " ");
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		// 입력완료

		getAns();

		System.out.println(ans);
	}
}
