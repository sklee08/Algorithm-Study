package algo_study;

import java.util.Scanner;

public class Problem_SWEA_1231_중위순회 {

	private static Node root;
	
	static class Node{
		private int v;
		private char ch;
		private Node left, right;
		
		
		public Node(int v, char ch) {
			super();
			this.v = v;
			this.ch = ch;
		}
		
	}
	
	public static Node getNode(Node[] tree, int idx, char ch) {
		if(tree[idx] == null) {
			tree[idx] = new Node(idx, ch);
		}
		
		return tree[idx];
	}
	
	
	
	public static void inOrder(Node node) {
		if(node == null) {
			return;
		}
		inOrder(node.left);		
		System.out.print(node.ch);
		inOrder(node.right);		
	}
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		for(int t= 1; t<=10; t++) {
			int N = scan.nextInt();
			scan.nextLine();
			Node[] tree = new Node[N+1];
			int []l = new int[N+1];
			int []r = new int[N+1];
			for(int i = 0; i < N; i++) {
				String tmp = scan.nextLine();
				String[] split = tmp.split(" ");
				char ch;
				
				if(split.length == 4) {
					// 4개인 경우
					ch = split[1].charAt(0);
					l[i+1] = Integer.parseInt(split[2]);
					r[i+1] = Integer.parseInt(split[3]);
				}else if(split.length == 3) {
					ch = split[1].charAt(0);
					l[i+1] = Integer.parseInt(split[2]);
					r[i+1] = 0;
				}else {
					ch = split[1].charAt(0);
					l[i+1] = 0;
					r[i+1] = 0;
				}
				getNode(tree,i+1,ch);
				// 입력 완료
				
			}
			for(int i = 1; i <= N; i++) {
				
				if(l[i] != 0) {
					Node cNode = tree[l[i]];
					tree[i].left = cNode;
				}else {
					tree[i].left = null;
				}
				if(r[i] != 0) {
					Node cNode = tree[r[i]];
					tree[i].right = cNode;
				}else {
					tree[i].right = null;
				}
			}
			
			System.out.print("#" + t+" ");
			inOrder(tree[1]);
			System.out.println();
		}
	}

}
