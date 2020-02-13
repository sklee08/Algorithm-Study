package algo_study;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Problem_SWEA_1258_행렬찾기 {
	
	static int n;
	static ArrayList<Arr> list;
	
	static class Arr{
		int row;
		int col;
		
		public Arr(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
	}
	
	public static boolean isIn(int x, int y) {
		return (0<= x && x < n && 0<= y && y < n);
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tc = scan.nextInt();
		
		for(int t = 1; t <= tc; t++) {
			n = scan.nextInt();
			int [][]arr = new int[n][n];
			boolean [][]visit =new boolean[n][n];
			list = new ArrayList<>();
			
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					arr[i][j] = scan.nextInt();
				}
			}
			
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					if(arr[i][j] != 0 && !visit[i][j]) {
						int nx = i+1;
						int ny = j;
						// 행부터
						visit[i][j] = true;
						int row = 1;
						while(isIn(nx, ny) && arr[nx][ny] != 0) {
							nx += 1;
							row++;
						}
						int col = 1;
						nx = i;
						ny = j + 1;
						while(isIn(nx, ny) && arr[nx][ny] != 0) {
							ny += 1;
							col++;
						}
						for(int k = i; k< i + row; k++) {
							for(int l = j; l < j + col; l++) {
								visit[k][l] = true;
							}
						}
						list.add(new Arr(row,col));
					}
				}
			}
			
			list.sort(new Comparator<Arr>() {

				@Override
				public int compare(Arr o1, Arr o2) {
					// TODO Auto-generated method stub
					Integer row1 = o1.row;
					Integer row2 = o2.row;
					Integer col1 = o1.col;
					Integer col2 = o2.col;
					Integer ret1 = (row1*col1);
					Integer ret2 = (row2*col2);
					if(row1 * col1 == row2 * col2) {
						return row1.compareTo(row2);
					}else {
						return ret1.compareTo(ret2);
					}
				}
			});
			System.out.printf("#%d %d ", t, list.size());
			for(int i = 0; i < list.size(); i++) {
				System.out.printf("%d %d ",list.get(i).row, list.get(i).col);
			}
			System.out.println();
		}
	}

}
