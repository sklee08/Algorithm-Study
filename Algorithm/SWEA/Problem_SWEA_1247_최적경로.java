package algo_study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_SWEA_1247_최적경로 {
	
	static int ans;
	static int min;
	static int N;
	static Dot[] arr;
	static Dot company;
	static Dot home;

	
	static class Dot{
		
		int row;
		int col;
		
		public Dot(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
	}
	
	public static void swap(int i, int j) {
		Dot temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	public static void makePerSwap(int r, int depth) {
		if(depth == r) {
			ans = getDistBetween(company, arr[0]);
			for(int i = 0; i < N-1; i++) {
				ans += getDistBetween(arr[i], arr[i+1]);
			}
			ans += getDistBetween(arr[N-1], home);
			
			if(min > ans) {
				min = ans;
			}
			ans = 0;
			return;
		}
		for(int i = depth; i < arr.length; i ++) {
			swap(depth, i);
			makePerSwap(r, depth+1);
			swap(depth, i);
		}
	}
	
	public static int getDistBetween(Dot tmp1, Dot tmp2) {
		return (Math.abs(tmp1.row - tmp2.row) + Math.abs(tmp2.col - tmp1.col));
	}

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= tc; t++) {
			ans = 0;
			min = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			arr = new Dot[N];
			String tmp = br.readLine();
			StringTokenizer st = new StringTokenizer(tmp, " ");
			for(int i = 0; i < N+2; i++) {
				int row = Integer.parseInt(st.nextToken());
				int col = Integer.parseInt(st.nextToken());
				if(i < 2) {
					if(i == 0) {
						// 회사
						company = new Dot(row,col);
					}else {
						// 집
						home = new Dot(row, col);
					}
				}else {
					arr[i-2]= new Dot(row,col);
				}				
			}
			 
			// 입력 완료
			makePerSwap(N,0);
			
			System.out.println("#" +t+" " + min);
		}
	}

}
