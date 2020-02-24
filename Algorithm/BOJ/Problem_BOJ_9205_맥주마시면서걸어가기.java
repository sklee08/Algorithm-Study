import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem_BOJ_9205_맥주마시면서걸어가기 {

	static int n;
	static Point start;
	static Point end;
	static Point[] cu;
	static int beer = 20;
	static boolean[] visit;
	static boolean isFin = false;
	
	static class Point{
		int x;
		int y;
		
		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}		
	}
	
	public static void bfs(Point st) {
		Queue<Point> q = new LinkedList<>();
		q.add(start);
		
		while(!q.isEmpty()) {
			st = q.poll();
			if(getDistance(st, end) <= 1000) {
				isFin = true;
				break;
			}
			for(int i = 0; i < n; i++) {
				if(!visit[i] && getDistance(st, cu[i]) <= 1000) {
					visit[i] = true;
					q.add(cu[i]);
				}
			}
		}		
	}
	
	public static int getDistance(Point st, Point en) {
		int difX = Math.abs(st.x - en.x);
		int difY = Math.abs(st.y - en.y);
		return difX + difY;
	}
	
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for(int i = 0; i < t; i++) {
			n = Integer.parseInt(br.readLine());
			cu = new Point[n];
			visit = new boolean[n];
			isFin = false;
			String tmp = br.readLine();
			StringTokenizer st = new StringTokenizer(tmp, " ");
			int tmp1 = Integer.parseInt(st.nextToken());
			int tmp2 = Integer.parseInt(st.nextToken());
			start = new Point(tmp1, tmp2);
			for(int j = 0; j < n; j++) {
				tmp = br.readLine();
				st = new StringTokenizer(tmp, " ");
				tmp1 = Integer.parseInt(st.nextToken());
				tmp2 = Integer.parseInt(st.nextToken());
				cu[j] = new Point(tmp1, tmp2);
			}
			
			tmp = br.readLine();
			st = new StringTokenizer(tmp, " ");
			tmp1 = Integer.parseInt(st.nextToken());
			tmp2 = Integer.parseInt(st.nextToken());
			end = new Point(tmp1, tmp2);
			
			// 입력 완료
			bfs(start);
			if(isFin) {
				System.out.println("happy");
			}else {
				System.out.println("sad");
			}
		}
	}

}
