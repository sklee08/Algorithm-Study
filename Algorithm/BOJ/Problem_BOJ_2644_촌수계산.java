package algo_study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem_BOJ_2644_촌수계산 {
	
	static int N,start,end, m;
	static int ans;
	static ArrayList<Integer> [] list;
	static boolean[] visit;
	
	static class Cousin{
		int person;
		int cnt;
		public Cousin(int person, int cnt) {
			super();
			this.person = person;
			this.cnt = cnt;
		}		
	}
	
	public static void bfs() {
		Queue<Cousin> q = new LinkedList<Cousin>();
		visit = new boolean[N+1];
		q.add(new Cousin(start, 0));
		visit[start] = true;
		
		while(!q.isEmpty()) {
			Cousin cs = q.poll();
			start = cs.person;
			
			if(start == end) {
				ans = cs.cnt;
				break;
			}
			
			ArrayList<Integer> ls = list[start];
			for(int i = 0; i < ls.size(); i++) {
				int tmp = ls.get(i);
				if(!visit[tmp]) {
					visit[tmp] = true;
					q.add(new Cousin(tmp, cs.cnt + 1));
				}
			}
		}
		
		if(ans == 0) ans = -1;
	}

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		list = new ArrayList[N+1];
		for(int i = 1; i < list.length; i++) {
			list[i] = new ArrayList<>();
		}
		String tmp = br.readLine();
		StringTokenizer st = new StringTokenizer(tmp, " ");
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(br.readLine());
		for(int i = 0; i < m; i++) {
			tmp = br.readLine();
			st = new StringTokenizer(tmp, " ");
			int tmp1 = Integer.parseInt(st.nextToken());
			int tmp2 = Integer.parseInt(st.nextToken());
			list[tmp1].add(tmp2);
			list[tmp2].add(tmp1);
		}
		// 입력 완료
		bfs();
		
		System.out.println(ans);
		
	}

}
