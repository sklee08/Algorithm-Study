package algo_study;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
public class Problem_BOJ_9946_단어퍼즐 {

	public static boolean isSame(String tmp1, String tmp2) {
		if(tmp1.length() != tmp2.length()) {
			return false;
		}else {
			ArrayList<Character> arr1 = new ArrayList<>();
			ArrayList<Character> arr2 = new ArrayList<>();
			for(int i = 0; i < tmp1.length(); i++) {
				arr1.add(tmp1.charAt(i));
				arr2.add(tmp2.charAt(i));
			}
			arr1.sort(new Comparator<Character>() {

				@Override
				public int compare(Character o1, Character o2) {
					// TODO Auto-generated method stub
					return o2.compareTo(o1);
				}
			});
			
			arr2.sort(new Comparator<Character>() {

				@Override
				public int compare(Character o1, Character o2) {
					// TODO Auto-generated method stub
					return o2.compareTo(o1);
				}
			});
			
			for(int i = 0; i < tmp1.length(); i++) {
				if(arr1.get(i) != arr2.get(i)) {
					return false;
				}
			}
			return true;
		}
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tc = 1;
		while(true) {
			String tmp1 = scan.next();
			String tmp2 = scan.next();
			if(tmp1.equals("END") && tmp2.equals("END")) {
				break;
			}
			
			if(isSame(tmp1,tmp2)) {
				System.out.println("Case " + tc++ + ": same");
			}else {
				System.out.println("Case " + tc++ + ": different");
			}
		}
	}

}
