import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Problem_BOJ_17140_이차원배열과연산 {
	
	static int r,c,k;
	static int row;
	static int col;
	static int[][] arr;
	static int ans;
	final static int LIMIT = 100;
	static class Dot{
		int num;
		int cnt;
		
		public Dot(int n, int c) {
			this.num = n;
			this.cnt = c;
		}

		@Override
		public String toString() {
			return "Dot [num=" + num + ", cnt=" + cnt + "]";
		}	
		
	}
	
	public static void calR() {
		
		PriorityQueue<Dot>[] list = new PriorityQueue[row];
		for(int i = 0; i < row; i++) {
			list[i] = new PriorityQueue<>(new Comparator<Dot>() {

				@Override
				public int compare(Dot o1, Dot o2) {
					Integer c1 = o1.cnt;
					Integer c2 = o2.cnt;
					if(c2.equals(c1)) {
						Integer n1 = o1.num;
						Integer n2 = o2.num;
						
						return n1.compareTo(n2);
					}else {
						return c1.compareTo(c2);
					}
					
				}
			});
		}
		for(int i = 0; i < row; i++) {
			PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {

				@Override
				public int compare(Integer o1, Integer o2) {
					// TODO Auto-generated method stub
					return o2.compareTo(o1);
				}
			});
			for(int j = 0; j < col; j++) {
				if(arr[i][j] != 0)
					pq.add(arr[i][j]);
			}
			int max = pq.peek();
			int[] idxArr = new int[max+1];
			while(!pq.isEmpty()) {
				int tmp = pq.poll();
				idxArr[tmp]++;
				//System.out.println(tmp);
			}
			for(int j = max; j >= 1; j--) {
				if(idxArr[j] != 0) {
					list[i].add(new Dot(j, idxArr[j]));
				}
			}
		}
		int max = 0;
		for(int i = 0; i < row; i++) {
			max = Math.max(max, list[i].size());
		}
		// max에 최대 개수 -- 실제는 2 x max
		if(2 * max > 100) {
			col = 100;
		}else {
			col = 2 * max;
		}
		arr = new int[row][col];
		for(int i = 0; i < row; i++) {
			int j = 0;
			while(!list[i].isEmpty()) {
				Dot tmp = list[i].poll();
				arr[i][j*2] = tmp.num;
				arr[i][j*2+1] = tmp.cnt;
				j++;
			}
		}
	}
	
	public static void calC() {
		//List<Dot>[] list = new ArrayList[col];
		PriorityQueue<Dot>[] list = new PriorityQueue[col];
		for(int i = 0; i < col; i++) {
			list[i] = new PriorityQueue<>(new Comparator<Dot>() {

				@Override
				public int compare(Dot o1, Dot o2) {
					Integer c1 = o1.cnt;
					Integer c2 = o2.cnt;
					if(c2.equals(c1)) {
						Integer n1 = o1.num;
						Integer n2 = o2.num;
						
						return n1.compareTo(n2);
					}else {
						return c1.compareTo(c2);
					}
					
				}
			});
		}
		for(int i = 0; i < col; i++) {
			PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {

				@Override
				public int compare(Integer o1, Integer o2) {
					// TODO Auto-generated method stub
					return o2.compareTo(o1);
				}
			});
			for(int j = 0; j < row; j++) {
				if(arr[j][i] != 0)
					pq.add(arr[j][i]);
			}
			int max = pq.peek();
			int[] idxArr = new int[max+1];
			while(!pq.isEmpty()) {
				int tmp = pq.poll();
				idxArr[tmp]++;
				//System.out.println(tmp);
			}
			for(int j = max; j >= 1; j--) {
				if(idxArr[j] != 0) {
					list[i].add(new Dot(j, idxArr[j]));
				}
			}
		}
		int max = 0;
		for(int i = 0; i < col; i++) {
			max = Math.max(max, list[i].size());
		}
		// max에 최대 개수 -- 실제는 2 x max
		if(2 * max > 100) {
			row = 100;
		}else {
			row = 2 * max;
		}
		arr = new int[row][col];
		for(int i = 0; i < col; i++) {
			int j = 0;
			while(!list[i].isEmpty()) {
				Dot tmp = list[i].poll();
				arr[j*2][i] = tmp.num;
				arr[j*2+1][i] = tmp.cnt;
				j++;
			}
		}
	}
	
	public static void print() {
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static boolean isIn(int r, int c) {
		return (0 <= r && r < row && 0 <= c && c < col);
	}
	
	public static void getAns() {
		while(true) {
			//print();
			if(isIn(r,c) && arr[r][c] == k) {
				break;
			}
			if(ans > 100) {
				ans = -1;
				break;
			}
			if(row >= col) {
				calR();
			}else {
				calC();
			}
			ans++;			
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		r = Integer.parseInt(st.nextToken()) - 1;
		c = Integer.parseInt(st.nextToken()) - 1;
		k = Integer.parseInt(st.nextToken());
		row = 3;
		col = 3;
		arr = new int[row][col];
		
		for(int i = 0; i < row; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < col; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		getAns();
		calR();
		
		System.out.println(ans);
	}
}
