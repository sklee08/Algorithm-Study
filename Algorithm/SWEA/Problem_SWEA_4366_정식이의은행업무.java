import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem_SWEA_4366_정식이의은행업무 {
	
	static String binary;
	static String three;
	static int ans;
	
	
	public static int getNum(char[] num, int d) {
		int index = 0;
		int ret = 0;
		for(int i = num.length-1; i >= 0; i--) {
			
			ret += ((num[i]- '0') * ((int)(Math.pow(d, index))));			
			index++;
		}
		
		return ret;
	}
	
	public static String getThree(int num) {
		String ret = "";
		while(num >0) {
			int div = num % 3;
			ret += div;
			num /= 3;
		}
		String r = "";
		for(int i = ret.length()-1; i >= 0; i--) {
			r += ret.charAt(i);
		}
		return r;
	}
	
	public static void getAns() {
		int len = binary.length();
		for(int i = 0; i < len; i++) {
			char[] bchar = binary.toCharArray();
			if(bchar[i] == '0') {
				bchar[i] = '1';
			}else {
				bchar[i] = '0';
			}
			// 변경
			int num = getNum(bchar,2);
			char[] tmp = getThree(num).toCharArray();
			if(isAns(three.toCharArray(),tmp)) {
				ans = num;
				break;
			}
		}
	}
	
	public static boolean isAns(char[] tmp1, char[] tmp2) {
		if(tmp1.length != tmp2.length) return false;
		int len = tmp1.length;
		int index = 0;
		
		for(int i = 0; i < len; i++) {
			if(tmp1[i] != tmp2[i]) {
				index++;
			}
			if(index > 1) {
				return false;
			}
		}
		
		if(index == 1) {
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= tc; t++) {
			binary = br.readLine();
			three = br.readLine();
			ans = 0;
			
			getAns();
			System.out.println("#"+t+" "+ans);
		}
	}
}
