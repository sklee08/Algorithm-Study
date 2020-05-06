import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem_BOJ_6987_월드컵 {
	
	static int[][] score;
	static boolean isAns;
	static int games;
	static int []arr= {0,1,2,3,4,5};
	static int []g1= {0,0,0,0,0,1,1,1,1,2,2,2,3,3,4};
	static int []g2= {1,2,3,4,5,2,3,4,5,3,4,5,4,5,5};
	
	public static void getAns(int game) {
		
		 // team 1의 승리가 가능하다면
		
		if(isAns) return;
		
		if(game == 15) {
			isAns = true;
			return;
		}
		
		int t1 = g1[game];
		int t2 = g2[game];
		
        if(score[t1][0] > 0 && score[t2][2] >0) {
        	score[t1][0]--;
        	score[t2][2]--;
        	getAns(game+1);
        	score[t1][0]++;
        	score[t2][2]++;
        	
        }

        if(score[t1][2] > 0 && score[t2][0] >0) {
        	score[t1][2]--;
        	score[t2][0]--;
        	getAns(game+1);
        	score[t1][2]++;
        	score[t2][0]++;
        }
        // team 1,2 가 무승부가 가능하다면
        if(score[t1][1] > 0 && score[t2][1] >0) {
            score[t1][1]--;
            score[t2][1]--;
            getAns(game+1);
            score[t1][1]++;
        	score[t2][1]++;
        } 
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int t = 0; t < 4; t++) {
			StringTokenizer st =new StringTokenizer(br.readLine(), " ");
			score = new int[6][3];
			isAns = false;
			int total = 0;
			games = 0;
			for(int i = 0; i < 6; i++) {
				for(int j = 0; j < 3; j++) {
					score[i][j] = Integer.parseInt(st.nextToken());
					total += score[i][j];
				}
			}
			// 입력완료
			if(total != 30) {
				isAns = false;
			}else {
				getAns(0);
			}
			if(t == 3) {
				if(isAns) {
					System.out.print(1);
				}else {
					System.out.print(0);
				}
			}else {
				if(isAns) {
					System.out.print(1+" ");
				}else {
					System.out.print(0+" ");
				}
			}
			
		}
	}
}
