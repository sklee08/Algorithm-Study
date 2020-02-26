import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class Problem_BOJ_16637_괄호추가하기 {

	static int N;
	static char[] math;
	static int ans;
	static int max = Integer.MIN_VALUE;
	static boolean[] cutline;
	static ArrayList<Integer> list;
	static Queue<String> queue;
	
	public static boolean isNum(char ch) {
		if('0' <= ch && ch <= '9') return true;
		return false;
	}
	
	public static void getComb(int start, int curr) {
		if(curr == cutline.length) {
			for(int i = 0; i < cutline.length-1; i++) {
				if(cutline[i] == cutline[i+1] && cutline[i] == true) {
					return;
				}
			}
			getResult();
		}else {
			for(int i = start; i < cutline.length; i++) {
				cutline[i] = true;
				getComb(i+1, curr+1);
				cutline[i] = false;
			}
		}
	}
	
	public static void getResult() {
		boolean[] visit = new boolean[N];
		Queue<Integer> q = new LinkedList<>();
		queue = new LinkedList<>();
		for(int i = 0; i < math.length; i++) {
			if(i % 2 == 0) {
				// 숫자인경우
				
			}else {
				// 기호인 경우
				if(cutline[i/2]) {
					visit[i-1] = true;
					visit[i] = true;
					visit[i+1] = true;
					q.offer(getRes(i/2));
				}
			}
		}
		for(int i = 0; i < N; i++) {
			if(!visit[i]) {
				queue.offer(Character.toString(math[i]));
			}
			if(i-1 >= 0 && i+1 < N && visit[i] && visit[i+1] && visit[i-1]) {
				queue.offer(Integer.toString(q.poll()));
			}
		}

		getAns();
	}
	
	public static void getAns() {
		ans = Integer.parseInt(queue.poll());
		while(!queue.isEmpty()) {
			String tmp = queue.poll();
			if(tmp.equals("*") || tmp.equals("+") || tmp.equals("-")) {
				// 기호
				switch(tmp) {
				case "*":
					ans *= Integer.parseInt(queue.poll());
					break;
				case "+":
					ans += Integer.parseInt(queue.poll());
					break;
				case "-":
					ans -= Integer.parseInt(queue.poll());
					break;
				}
			}
		}
		if(ans > max) {
			max = ans;
		}
		ans = 0;
	}
	
	public static int getRes(int index) {
		int ret = 0;
		switch(math[index*2+1]) {
		case '-':
			ret = (math[index*2]-'0') - (math[index*2+2]-'0');
			break;
		case '+':
			ret = (math[index*2]-'0') + (math[index*2+2]-'0');
			break;
		case '*':
			ret = (math[index*2]-'0') * (math[index*2+2]-'0');
			break;
		}		
		return ret;
	}
	
	
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		math = new char[N];
		cutline = new boolean[N/2];
		String tmp = br.readLine();
		for(int i = 0; i < N; i++) {
			math[i] = tmp.charAt(i);
		}
		for(int i = 0; i <= cutline.length; i++) {
			getComb(0, i);
		}
		
		System.out.println(max);
	}

}
