import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Problem_BOJ_2331_반복수열 {
	static int A, P;
	static int ans;
	static ArrayList<Integer> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		A = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());

		getAns();

		System.out.println(ans);
	}

	public static void getAns() {
		dfs(A);
	}

	public static void dfs(int n) {
		
		int size = list.size();
		for (int i = 0; i < size; i++) {
			if(list.get(i) == n) {
				ans = i;
				return;
			}
		}
		
		list.add(n);
		
		dfs(getNext(n));
	}

	public static int getNext(int p) {
		int ret = 0;
		while (p > 0) {
			int div = p % 10;
			ret += (Math.pow(div, P));
			p /= 10;
		}

		return ret;
	}
}
