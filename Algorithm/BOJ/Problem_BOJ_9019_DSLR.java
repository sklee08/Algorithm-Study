package algo_study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem_BOJ_9019_DSLR{
	
	static int N;
	static ArrayList<String> ans = new ArrayList<>();
	static String[] command = {"D", "S", "L", "R"};
	static boolean[] visit;
	
	static class Cmd{
		int data;
		String cmd;
		
		public Cmd(int data, String cmd) {
			super();
			this.data = data;
			this.cmd = cmd;
		}
		
	}
	
	public static int getCmd(int data, int cmdNum) {
		switch(cmdNum) {
		case 0:			
			if(data * 2 > 9999) {
				return (data*2 ) % 10000;
			}else
				return data * 2;
		case 1:
			if(data == 0) {		
				return 9999;
			}else {
				return data - 1;
			}
		case 2:
			// L
			if(data % 1000 != 0) {
				return (data % 1000) * 10 + data / 1000;
			}else {
				return data / 1000;
			}
		case 3:
			if(data % 10 != 0) {
				return data / 10 + (data %10) * 1000;
			}else {
				return data / 10;
			}
		}
		
		return -1;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < N; i++) {
			String tmp = br.readLine();
			StringTokenizer st = new StringTokenizer(tmp, " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			bfs(start, end);
		}
		for(int i = 0; i < ans.size(); i++) {
			System.out.println(ans.get(i));
		}
	}
	
	public static void bfs(int start, int end) {
		Queue<Cmd> q = new LinkedList<>();
		q.add(new Cmd(start, ""));
		visit = new boolean[10000];
		visit[start] = true;
		
		while(!q.isEmpty()) {
			Cmd tmp = q.poll();
			if(tmp.data == end) {
				ans.add(tmp.cmd);
				break;
			}
			for(int i = 0; i < 4; i++) {
				int temp = getCmd(tmp.data, i);
				if(!visit[temp]) {
					q.add(new Cmd(temp, tmp.cmd.concat(command[i])));
					visit[temp] = true;
				}				
			}
		}
	}
}
