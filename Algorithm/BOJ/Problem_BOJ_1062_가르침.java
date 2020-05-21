import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem_BOJ_1062_가르침 {
	
	static int N, K;
	static String[] words;
	static int ans;
	static ArrayList<Character> list;
	static boolean[] alpa;
	
	
	public static void getAlpa() {
		
		alpa[0] = true;
		alpa['c' - 'a'] = true;
		alpa['n' - 'a'] = true;
		alpa['t' - 'a'] = true;
		alpa['i' - 'a'] = true;

	}
		
	public static void getMin() {
		int cnt = 0;
		boolean isDone;
		
		for(int i = 0; i < N; i++) {
			isDone = true;
			for(int j = 0; j < words[i].length(); j++) {
				char ch = words[i].charAt(j);
				if(!alpa[ch-'a']) {
					isDone = false;
					break;
				}
			}
			if(isDone) cnt++;
		}
		
		ans = Math.max(ans, cnt);
	}
	
	public static void getComb(int start, int depth) {
		if(depth == K) {
			getMin();
			return;
		}
		
		for(int i = start; i < list.size(); i++) {
			char ch = list.get(i);
			alpa[ch - 'a'] = true;
			getComb(start+1, depth+1);
			alpa[ch - 'a'] = false;
		}
	}
	
	public static void getList() {
		list = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < words[i].length(); j++) {
				char ch = words[i].charAt(j);
				if(!alpa[ch-'a']) list.add(ch);
			}
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken()) - 5;
		words = new String[N];
		alpa= new boolean[26];
		
		ans = Integer.MIN_VALUE;
		
		for(int i = 0; i < N; i++) {
			words[i] = br.readLine();
			int len = words[i].length();
			words[i] = words[i].substring(4, len -4);
		}
		getAlpa();
		
		getList();
		// 리스트 초기화
		
		if(list.size() <= K) ans = N;
		else {
			getComb(0,0);
		}
		
		System.out.println(ans);
	}
}
