package algo_study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Problem_SWEA_1240_단순2진암호코드 {
	
	static int N, M;
	static char[][] password;
	static String [] nums = {"0001101", "0011001", "0010011", "0111101", "0100011",
			"0110001", "0101111","0111011", "0110111", "0001011"};
	static ArrayList<Integer> list;
	
	public static int getSum() {
		int sum = 0;
		for(int i = 0; i < list.size(); i+=2) {
			//
			sum += list.get(i);
		}
		sum *= 3;
		for(int i =1; i < list.size()-1; i+=2) {
			sum += list.get(i);
		}
		sum += list.get(list.size()-1);
		if(sum % 10 == 0) {
			// 올바름
			int ret = 0;
			for(int i = 0; i < list.size(); i++) {
				ret += list.get(i);
			}
			return ret;
		}else {
			return 0;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String tmp = br.readLine();
		int tc = Integer.parseInt(tmp);
		for(int t = 1; t <= tc; t++) {
			tmp = br.readLine();
			StringTokenizer st = new StringTokenizer(tmp, " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			password = new char[N][M];
			list = new ArrayList<>();
			
			for(int i = 0; i < N; i ++) {
				tmp = br.readLine();
				for(int j = 0; j < M; j++) {
					password[i][j] = tmp.charAt(j);
				}
			}
			int lastR = 0;
			int lastC = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(password[i][j] == '1') {
						lastR = i;
						lastC = j;
					}
				}
			}
			for(int i = lastC-55; i<= lastC; i+=7) {
				tmp = "";
				tmp += password[lastR][i];
				tmp += password[lastR][i+1];
				tmp += password[lastR][i+2];
				tmp += password[lastR][i+3];
				tmp += password[lastR][i+4];
				tmp += password[lastR][i+5];
				tmp += password[lastR][i+6];
				list.add(getNum(tmp));
			}
		//	System.out.println(list);
			System.out.println("#" + t + " " + getSum());
		}		
	}
	
	public static int getNum(String pw) {
		for(int i = 0; i < nums.length; i++) {
			if(pw.equals(nums[i])) {
				return i;
			}
		}
		return -1;
	}
}
