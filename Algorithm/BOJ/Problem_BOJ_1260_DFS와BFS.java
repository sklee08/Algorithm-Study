package algo_study;

import java.util.Scanner;
import java.util.Stack;
import java.util.List;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;

public class Problem_BOJ_1260 {

	static int N;
	static int M;
	static List<Integer>[] graph;
	

	
	public static void BFS(int start) {
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
		List<Integer> path = new ArrayList<>();
		
		queue.add(start);
		
		while(!queue.isEmpty()) {
			Integer top = queue.poll();
			
			if(!visited[top]) {
				// 미방문
				visited[top] = true;
				path.add(top);
				
				List<Integer> childs = graph[top];
				
				for(int i = 0; i < childs.size(); i++) {
					getSortBFS(childs);
					Integer child = childs.get(i);
					if(!visited[child]) {
						queue.add(child);
					}
				}
			}
		}
		print(path);
	}
	
	public static void DFS(int start) {
		Stack<Integer> stack = new Stack<>();
		boolean[] visited = new boolean[N+1];
		List<Integer> path = new ArrayList<>();
		
		
		
		stack.push(start);
		
		while(!stack.isEmpty()) {
			Integer top = stack.pop();
			
			if(visited[top]) {
				continue;
			}else {
				visited[top] = true;
				path.add(top);
				
				List<Integer> childs = graph[top];
				
				for(int i = 0; i <= childs.size() - 1; i++) {
					getSortDFS(childs);
					Integer child = childs.get(i);
					if(!visited[child]) {
						stack.push(child);
					}
				}
			}
		}
		
		print(path);
	}
	
	public static void print(List<Integer> path) {
		for(int i = 0; i < path.size(); i++) {
			System.out.printf("%d ",path.get(i));
		}
	}
	
	public static void getSortDFS(List<Integer> list) {
		list.sort(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o2.compareTo(o1);
			}
		});		
	}
	
	public static void getSortBFS(List<Integer> list) {
		list.sort(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o1.compareTo(o2);
			}
		});		
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		N = scan.nextInt();
		M = scan.nextInt();
		graph = new List[N+1];
		for(int i = 1; i < graph.length; i++) {
			graph[i] = new ArrayList<>();
		}
		int start = scan.nextInt();
		
		for(int i = 0; i < M; i++) {
			int a = scan.nextInt();
			int b = scan.nextInt();
			
			graph[a].add(b);
			graph[b].add(a);
			
		}
		// 입력완료
		DFS(start);
		System.out.println();
		BFS(start);
	}
	

}
