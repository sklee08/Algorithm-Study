package algo_study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Problem_BOJ_11399_ATM {

	static int N;
	static int[]time;
	static int ans;
	
	public static int getMin() {
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < time.length; i++) {
			if(time[i] < min) {
				min = time[i];
			}				
		}
		
		for(int i = 0; i < time.length; i++) {
			if(time[i] == min) {
				time[i] = Integer.MAX_VALUE;
				break;
			}
		}
		
		return min;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String tmp = br.readLine();
		StringTokenizer st= new StringTokenizer(tmp, " ");
		time = new int[N];
		for(int i = 0; i < N; i++) {
			time[i] = Integer.parseInt(st.nextToken());
		}
		ArrayList<Integer> minList = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			ans += getMin();
			minList.add(ans);
		}
		int ret= 0;
		for(int i = 0; i < minList.size(); i++) {
			ret += minList.get(i);
		}
		
		System.out.println(ret);
	}

}
