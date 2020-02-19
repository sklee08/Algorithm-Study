package algo_study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem_BOJ_1697_숨바꼭질 {

	static int N, K;
	static boolean[] visit;
	static int time;
	static int[] status;
	
	static class Sum{
		int pos;
		int time;
		
		public Sum(int pos, int time) {
			super();
			this.pos = pos;
			this.time = time;
		}
	}
	
	public static int bfs() {
		int nextN = N;
		status = new int[3];
		Queue<Sum> q = new LinkedList<Sum>();
		q.add(new Sum(nextN, 0));
		Sum tmp = new Sum(0,0);
		
		while(!q.isEmpty() && nextN != K) {
			tmp = q.poll();
			nextN = tmp.pos;
			status[0] = nextN -1;
			status[1] = nextN +1;
			status[2] = nextN * 2;
			
			for(int i = 0; i < 3; i++) {
				if(isIn(status[i]) && !visit[status[i]]) {
					visit[status[i]] = true;
					q.add(new Sum(status[i], tmp.time+1));
				}
			}
		}
		
		return tmp.time;
	}
	
	public static boolean isIn(int x) {
		return (x >= 0 && x <= 100000);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String tmp = br.readLine();
		StringTokenizer st= new StringTokenizer(tmp, " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		visit = new boolean[100001];
		visit[N] = true;
		
		System.out.println(bfs());
	}

}
