import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem_BOJ_5014_스타트링크 {
	
	static int F,S,G,U,D;
	static boolean[] visit;
	static int ans;
	static int min = Integer.MAX_VALUE;
	
	static class Point{
		int point;
		int cnt;
		public Point(int point, int cnt) {
			super();
			this.point = point;
			this.cnt = cnt;
		}
		
	}
	
	public static boolean isIn(int point) {
		return (0 <= point && point <= F);
	}
	
	public static void bfs(Point start) {
		Queue<Point> q = new LinkedList<>();
		q.add(start);
		visit[start.point] = true;
	
		while(!q.isEmpty() && start.point != G) {
			start = q.poll();
			int []path = new int[2];
			path[0] = start.point + U;
			path[1] = start.point - D;
			int cnt = start.cnt;
			
			for(int i = 0; i < 2; i++) {
				if(isIn(path[i]) && !visit[path[i]]) {
					visit[path[i]] = true;
					q.offer(new Point(path[i], cnt+1));
				}
			}
		}
		if(start.point != G) {
			System.out.println("use the stairs");
		}else {
			System.out.println(start.cnt);
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st  = new StringTokenizer(br.readLine(), " ");
		F = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		visit = new boolean[F+1];
		// 입력 완료
		bfs(new Point(S, 0));
	}
}
