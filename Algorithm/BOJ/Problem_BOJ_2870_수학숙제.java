package algo_study;

import java.util.List;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

public class Problem_BOJ_2870_수학숙제 {

	static List<String> list;
	static String[] arr;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		
		int N = scan.nextInt();
		scan.nextLine();
		arr= new String[N];
		list = new ArrayList<>();
		
		for(int i = 0; i < N; i ++) {
			arr[i] = scan.nextLine();
		}
		
		getNum();
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	
	public static void delZero() {
		
		
		for(int i = 0; i < list.size(); i++) {
			int idx = 0;
			String tmp = list.get(i);
			if(tmp.charAt(0) == '0') {
				if(tmp.length() == 1) {
					// 숫자 0
					continue;
				}else {
					// 숫자 002 같은 것들
					// list에서 삭제		
					System.out.println(tmp);
					while(tmp.charAt(idx) == '0') {
						if(idx == tmp.length() - 1) break;
						idx++;
					}
					list.set(i,tmp.substring(idx));
				}
				
			}			
		}
	}
	
	public static void getNum() {
		// list에 숫자 모두 삽입

		for(int i = 0; i < arr.length; i++) {
			Queue<Character> q = new LinkedList<>();
			for(int j = 0; j <arr[i].length(); j++) {
				
				if(isNum(arr[i].charAt(j))) {
					// 숫자일 경우
					//if(arr[i].charAt(j) == '0')
					q.add(arr[i].charAt(j));
				}else {
					// 숫자가 아닌놈
					String ss = "";
					while(!q.isEmpty()) {
						ss  += q.poll();
					}
					if(ss.equals("")) {
						continue;
					}
					list.add(ss);
				}
				
			}
			if(!q.isEmpty()) {
				String ss = "";
				while(!q.isEmpty()) {
					ss  += q.poll();
				}
				list.add(ss);
			}
		}
		// list에 002같은것들 삭제
		delZero();		
		// list 정렬하기
		
		list.sort(new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				if(o1.length() == o2.length()) {
					return o1.compareTo(o2);
				}else {
					Integer len1 = o1.length();
					Integer len2 = o2.length();
					return len1.compareTo(len2);
				}				
			}
		});
	}
	
	public static boolean isNum(char ch) {
		return (0 <= ch - '0' && ch - '0' <= 9);
	}

}
