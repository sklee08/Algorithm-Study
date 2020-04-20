import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Problem_BOJ_16235_나무제태크 {
	
	static int N, M, K;
	static int[][] yangbun;		// 겨울에 추가되는 양분
	static int[][] leftY;		// 남아있는 양분
	static List<Tree> trees;
	static List<Tree> newTrees;
	static Queue<Tree> dieTrees;
	static int[] dr = {0,0,1,-1,1,-1,1,-1};
	static int[] dc = {1,-1,0,0,1,-1,-1,1};
	
	static class Tree{
		private int x;
		private int y;
		private int age;
		private boolean life;
	
		public Tree(int x, int y, int age, boolean life) {
			super();
			this.x = x;
			this.y = y;
			this.age = age;
			this.life = life;
		}
		
		public boolean getLife() {
			return this.life;
		}
		
		public void setLife(boolean life) {
			this.life = life;
		}
		
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}

	}
	
	public static void print() {
		System.out.println();
		for(int i = 0; i < trees.size(); i++) {
			System.out.println(trees.get(i));
		}
		System.out.println();
	}
	
	public static void spring() {
		// 봄
		// 나무들의 양분 섭취~~
		
		for(Tree tree : trees) {
			int age = tree.getAge();
			if(leftY[tree.getX()][tree.getY()] >= age) {
				leftY[tree.getX()][tree.getY()] -= age;
				tree.setAge(age+1);
			}else {
				tree.setLife(false);
			}		
		}		
	}
	
	public static void summer() {
		// 여름
		// 죽은 나무가 양분 
		for(Iterator<Tree> itt = trees.iterator(); itt.hasNext();) {
			Tree tmp = itt.next();
			if(!tmp.life) {
				leftY[tmp.getX()][tmp.getY()] += tmp.getAge() / 2;
				itt.remove();
			}
		}
	}
	
	public static boolean isIn(int r, int c) {
		return (0 <= r && r < N && 0 <= c && c < N);
	}
	
	public static void fall() {
		for(Tree tmp : trees) {
			if(tmp.getAge() % 5 == 0) {
				for(int j = 0; j < 8; j++) {
					int nr = tmp.getX() + dr[j];
					int nc = tmp.getY() + dc[j];
					if(isIn(nr,nc)) {
						// 안에 있을 경우
						newTrees.add(new Tree(nr,nc,1,true));
					}
				}
			}
		}
		
		trees.addAll(0, newTrees);
	}
	
	public static void winter() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				leftY[i][j] += yangbun[i][j];
			}
		}
	}
	
	public static void afterTime() {
		Comparator<Tree> treeComparator = new Comparator<Tree>() {

			@Override
			public int compare(Tree o1, Tree o2) {
				return o1.getAge() - o2.getAge();
			}
		};
		Collections.sort(trees, treeComparator);
		for(int i = 0; i < K; i++) {
			// K년 동안 반복
			newTrees = new LinkedList<>();
			spring();
			summer();
			fall();
			winter();			
		}
		
		System.out.println(trees.size());
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		yangbun = new int[N][N];
		leftY = new int[N][N];
		dieTrees = new LinkedList<>();
		M = Integer.parseInt(st.nextToken());
		trees = new LinkedList<>();
		K = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				yangbun[i][j] = Integer.parseInt(st.nextToken());
				leftY[i][j] = 5;
			}
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			trees.add(new Tree(x-1, y-1, age, true));
		}
		// 입력완료
		
		
		afterTime();
	}
	
}
