import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Problem_BOJ_11005_진법변환 {
	
	static int N, B;
	static String ans;
	static char[] radix;
	
	public static void getAns() {
		Stack<Character> stack = new Stack<>();
		
		while(N > 0) {
			int div = N % B;
			stack.push(radix[div]);			
			N /= B;
		}
		
		while(!stack.isEmpty()) {
			ans += stack.pop();
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		radix = new char[36];
		ans = "";
		char ch = 'A';
		for(int i = 10; i < 36; i++) {
			radix[i] = ch++;
		}
		ch = '0';
		for(int i  = 0; i < 10; i++) {
			radix[i] = ch++;
		}
		getAns();
		System.out.println(ans);
	}
}
