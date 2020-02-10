package algo_day8;

import java.util.Scanner;
import java.util.LinkedList;

public class Problem_SWEA_1229_암호문2 {

	
	
	public static void getInsert(LinkedList<String> l, String[] arr, int index) {
		// I 삽입
		int len = arr.length;
		for(int i = index; i < index+len; i++) {
			l.add(i, arr[i-index]);
		}
	}
	
	public static void getDelete(LinkedList<String> l, int index, int orderNum) {
		for(int i = 0; i < orderNum; i++) {
			l.remove(index);
		}
	}
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		for(int t = 1; t <= 10; t++) {
			// test case
			
			int N = scan.nextInt();
			LinkedList<String> list = new LinkedList<>();
			
			for(int i = 0; i<N; i++) {
				String tmp = scan.next();
				list.add(tmp);
			}
			int order = scan.nextInt();
			for(int i = 0; i < order; i++) {
				String orderChar = scan.next();
				if(orderChar.equals("I")) {
					int idx = scan.nextInt();
					int orderNum = scan.nextInt();
					String[] arr= new String[orderNum];
					for(int j = 0; j < orderNum; j++) {
						arr[j] = scan.next();
					}
					getInsert(list, arr, idx);
				}else if(orderChar.equals("D")) {
					int idx = scan.nextInt();
					int orderNum = scan.nextInt();
					getDelete(list,idx,orderNum);
				}
				
			}
			
			System.out.print("#" +t+ " ");
			for(int i = 0; i < 10; i++) {
				System.out.print(list.get(i) + " ");
			}
			System.out.println();
		}
	}

}
