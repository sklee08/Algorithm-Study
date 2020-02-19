package algo_study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem_BOJ_2606_바이러스 {
	
	static int N;
	static ArrayList<Integer> [] list;
	static boolean[] visit;
	static int ans;
	
	public static void getCom() {
		visit = new boolean[N+1];
		visit[1] = true;
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(1);
		
		while(!q.isEmpty()) {
			int tmp = q.poll();
			
			ArrayList<Integer> ls = list[tmp];
			for(int i = 0; i < ls.size(); i++) {
				if(!visit[ls.get(i)]) {
					ans++;
					visit[ls.get(i)] = true;
					q.add(ls.get(i));
				}
			}
		}
	}
		
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		list = new ArrayList[N+1];
		
		for(int i = 1; i < list.length; i++) {
			list[i] = new ArrayList<>();
		}
		
		int connected = Integer.parseInt(br.readLine());
		for(int i = 0; i < connected; i++) {
			String tmp = br.readLine();
			StringTokenizer st = new StringTokenizer(tmp, " "); 
			int tmp1 = Integer.parseInt(st.nextToken());
			int tmp2 = Integer.parseInt(st.nextToken());
			list[tmp1].add(tmp2);
			list[tmp2].add(tmp1);
		}
		// 입력완료
		getCom();
		System.out.println(ans);
	}
}
