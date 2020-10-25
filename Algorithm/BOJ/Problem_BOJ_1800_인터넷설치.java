package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem_BOJ_1800_인터넷설치 {

	static int N, P, K;
	static int cost[][];
	static int[][] copyCost;
	static int ans;

	static class Node {
		int idx;
		ArrayList<Integer> list = new ArrayList<>();

		public Node(int i) {
			this.idx = i;
		}
	}

	static class Link {
		int start;
		int end;
		int cost;

		public Link(int start, int end, int cost) {
			super();
			this.start = start;
			this.end = end;
			this.cost = cost;
		}

		@Override
		public String toString() {
			return "Link [start=" + start + ", end=" + end + ", cost=" + cost + "]";
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		cost = new int[N + 1][N + 1];
		copyCost = new int[N + 1][N + 1];
		ans = Integer.MAX_VALUE;

		for (int i = 0; i < P; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int c1 = Integer.parseInt(st.nextToken());
			int c2 = Integer.parseInt(st.nextToken());
			int cst = Integer.parseInt(st.nextToken());
			cost[c1][c2] = cst;
			cost[c2][c1] = cst;
			copyCost[c1][c2] = cst;
			copyCost[c2][c1] = cst;
		}
		// 입력완료
		Node node = new Node(1);
		node.list.add(1);
		dfs(node);

		if (ans == Integer.MAX_VALUE)
			ans = -1;
		System.out.println(ans);
	}

	public static void dfs(Node n) {
		if (n.idx == N) {
			ArrayList<Integer> retList = n.list;
			System.out.println("return : " + retList);
			int size = retList.size();
			PriorityQueue<Link> linkList = new PriorityQueue<Link>(new Comparator<Link>() {

				@Override
				public int compare(Link o1, Link o2) {
					Integer c1 = o1.cost;
					Integer c2 = o2.cost;
					return c2.compareTo(c1);
				}
			});

			for (int i = 0; i < size - 1; i++) {
				int start = retList.get(i);
				int end = retList.get(i + 1);
				int cst = copyCost[start][end];
				linkList.add(new Link(start, end, cst));
			}

			for (int i = 0; i < K; i++) {
				linkList.poll();
			}
			int tmpAns = 0;
			if (linkList.size() == 0) {
				tmpAns = 0;
			} else {
				tmpAns = linkList.poll().cost;
			}
			ans = Math.min(ans, tmpAns);

			return;
		}

		for (int i = 1; i <= N; i++) {
			int c = cost[n.idx][i];
			if (c != 0 && cost[i][n.idx] != 0) {
				Node next = new Node(i);
				int size = n.list.size();
				for (int j = 0; j < size; j++) {
					next.list.add(n.list.get(j));
				}
				next.list.add(i);

				cost[n.idx][i] = 0;
				dfs(next);
				cost[n.idx][i] = c;
			}
		}
	}

}
