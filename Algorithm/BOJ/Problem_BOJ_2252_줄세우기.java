import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem_BOJ_2252_줄세우기 {
	
	static int N, M;
	static List<Integer>[] list;
	static int[] arr;
	
	public static void getAns() {
		Queue<Integer> q = new LinkedList<Integer>();
		
		for(int i=1; i <= N; i++) {
			if(arr[i] == 0) {
				q.add(i);
			}
		}
		
		while(!q.isEmpty()) {
			int curr = q.poll();						
			
			for(int i = 0; i < list[curr].size(); i++) {
				int next = list[curr].get(i);
				arr[next]--;
				if(arr[next] == 0) {
					q.add(next);
				}
			}
			
			if(q.isEmpty()) {
				System.out.print(curr);
			}else {
				System.out.print(curr+" ");
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		list = new ArrayList[N+1];
		arr = new int[N+1];
		for(int i =1; i < N+1; i++) {
			list[i] = new ArrayList<>();
		}
		M = Integer.parseInt(st.nextToken());
		
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int small = Integer.parseInt(st.nextToken());
			int high = Integer.parseInt(st.nextToken());
			list[small].add(high);
			arr[high]++;
		}
		getAns();
		
	}
}
