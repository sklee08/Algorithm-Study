package algorithm_study;

import java.util.Stack;


public class Problem_Programmers_Baseball {

	public static void main(String[] args) {
		
		int [][] test = {{123,1,1}, {356,1,0}, {327,2,0}};
		
		System.out.println(solution(test));
	}
	
	public static int getStrike(String cond, String num) {
		int cnt = 0;
		for(int i = 0; i< 3; i++) {
			cnt += cond.charAt(i) == num.charAt(i)? 1 : 0;
		}
		return cnt;
	}
	
	public static int getBall(String cond, String num) {
		int cnt = 0;
		for(int i = 0; i < 3; i++) {
			if(cond.contains(String.valueOf(num.charAt(i)))) {
				cnt += cond.indexOf(num.charAt(i)) == i? 0 : 1;
			}
		}
		return cnt;
	}
	
	public static int solution(int[][] baseball) {
		int answer = 0;
		int cnt;
		
		Stack stack = new Stack<>();
		for(int i = 1; i< 10; i++) {
			for(int j = 1; j <10; j++) {
				for(int k =1; k < 10; k++) {
					if(i!=j && j!=k && k!=i) {
						stack.add(String.valueOf(i*100+j*10+k));
					}
				}
			}
		}
		// generate stack
		
		while(!stack.isEmpty()) {
			String tmp = (String)stack.pop();
			cnt = 0;
			for(int i = 0; i< baseball.length && cnt < baseball.length; i++) {
				int strike = getStrike(tmp, String.valueOf(baseball[i][0]));
				int ball = getBall(tmp, String.valueOf(baseball[i][0]));
				
				if(strike == baseball[i][1] && ball == baseball[i][2]) {
					cnt++;
				}
			}
			if(cnt == baseball.length)
				answer++;
		}
		return answer;
	}
	
}
