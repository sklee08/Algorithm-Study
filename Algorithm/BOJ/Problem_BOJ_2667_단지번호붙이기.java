package algo_study;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
public class Problem_BOJ_2667_단지번호붙이기 {
	
	static int N;
	static int[][]arr;
	static int []dx = {0,0,1,-1};
	static int []dy = {1,-1,0,0};
	static ArrayList<Integer> answer;
	
	static class Dot{
		int x;
		int y;
		public Dot(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}		
	}
	
	public static boolean isIn(int x, int y) {
		return (0<=x && x < N && 0 <= y && y < N);
	}
	
	public static int bfs(int x, int y) {
		Queue<Dot> q = new LinkedList<>();
		
		Dot start = new Dot(x,y);
		q.add(start);
		int cnt = 0;
		while(!q.isEmpty()) {
			Dot tmp = q.poll();
			if(arr[tmp.x][tmp.y] == 1) {
				cnt++;
				arr[tmp.x][tmp.y] = 0;
				for(int i = 0; i < 4; i++) {
					int nx = tmp.x + dx[i];
					int ny = tmp.y + dy[i];
					if(isIn(nx,ny) && arr[nx][ny] == 1) {
						q.add(new Dot(nx,ny));
					}
				}
			}
		}
		
		return cnt;
	}
	
	public static void getRes() {
		for(int i = 0 ; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(arr[i][j] == 1) {
					answer.add(bfs(i,j));
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		N = scan.nextInt();
		arr= new int[N][N];
		answer = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			String tmp= scan.next();
			for(int j = 0; j< N; j++) {
				arr[i][j] = tmp.charAt(j) - '0';
			}
		}
		
		// 입력 완료
		getRes();
		
		answer.sort(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o1.compareTo(o2);
			}
		});
		System.out.println(answer.size());
		for(int i = 0; i < answer.size(); i++) {
			System.out.println(answer.get(i));
		}
	}
}
