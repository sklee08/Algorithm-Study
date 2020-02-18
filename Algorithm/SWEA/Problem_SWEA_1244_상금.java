package algo_study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_SWEA_1244_상금 {
	
	static int num;
	static String numS;
	static int ans;
	static char[]arr;
	
	
	public static void swap(int i, int j) {
		char tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
	
	
	public static void dfs(int current, int depth) {
		if(depth == num) {
			int res = 0;
			int len = arr.length - 1;
			
			for(int i = 0; i < arr.length; i++) {
				res+= (arr[i]-'0') * Math.pow(10, len);
				len--;
			}
			ans = Math.max(ans, res);
			return;
		}
		int size = numS.length();
		for(int i = current; i < size; i++) {
			for(int j = i+1; j < size; j++) {
				if(arr[i]-'0' <= arr[j]-'0') {
					swap(i,j);
					dfs(i,depth+1);
					swap(i,j);
				}
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= tc; t++) {
			ans = 0;
			String tmp = br.readLine();
			StringTokenizer st = new StringTokenizer(tmp, " ");
			numS = st.nextToken();
			arr= numS.toCharArray();
			num = Integer.parseInt(st.nextToken());
			dfs(0,0);
			
			System.out.print("#"+t+" " + ans);
			
			System.out.println();
		}
	}

}
