import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_SWEA_9659_다항식계산 {
	
	static int N, M;
	static final long DIV = 998244353;	
	static Cal[] cals;
	static long[] xArr;
	static long[] answers;
	
	static class Cal{
		private int t;
		private int a;
		private int b;
		public int getT() {
			return t;
		}
		public void setT(int t) {
			this.t = t;
		}
		public int getA() {
			return a;
		}
		public void setA(int a) {
			this.a = a;
		}
		public int getB() {
			return b;
		}
		public void setB(int b) {
			this.b = b;
		}
		public Cal(int t, int a, int b) {
			super();
			this.t = t;
			this.a = a;
			this.b = b;
		}		
	}
	
	public static void getAns(long x) {
		for(int j = 0; j <= N; j++) {
			function(j,x);
		}		
	}
	
	public static void function(int i, long x) {
		if(i == 0) {
			answers[0] = 1;
			return;
		}
		if(i == 1) {
			answers[1] = x;
			return;
		}
		
		int t = cals[i-2].getT();
		int a = cals[i-2].getA();
		int b = cals[i-2].getB();
		switch(t) {
		case 1:
			// return (function(a,x) + function(b,x)) % DIV;
			answers[i] = (answers[a] + answers[b]) % DIV;
			break;
		case 2:
			// return (a * function(b,x)) % DIV;
			answers[i] = (a * answers[b]) % DIV;
			break;
		case 3:
			// return (function(a,x) * function(b,x)) % DIV;
			answers[i] = (answers[a] * answers[b]) % DIV;
			break;
		}
		
	}
	
	public static void clear() {
		for(int i = 0; i < answers.length; i++) {
			answers[i] = 0;
		}
	}
	
	public static void print(int tc) {
		System.out.print("#"+ tc + " ");
		for(int i = 0; i < M; i++) {
			getAns(xArr[i+1]);
			if(i == M-1) {
				System.out.print(answers[N]);
			}else {
				System.out.print(answers[N]+" ");
			}			
			clear();
		}
	}
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		
		for(int t=1; t <= tc; t++) {
			N = Integer.parseInt(br.readLine());
			cals = new Cal[N-1];
			for(int i = 0; i < N-1; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				int te = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				cals[i] = new Cal(te,a,b);
			}
			M = Integer.parseInt(br.readLine());
			xArr = new long[M+1];
			answers = new long[N+1];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int i = 1; i <= M; i++) {
				xArr[i] = Integer.parseInt(st.nextToken());
				//answers[i][0] = 1;
			}
			
			// 입력 완료
			print(t);
			System.out.println();
		}
	}
}
