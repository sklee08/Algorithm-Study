import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Algo2_서울_14반_이원오 {

	static int N;					// test case 개수
	static int V, E;				// V = 정점의 개수, E = 간선의 개수
	static int nodes[];				// 노드의 정보 배열
	static boolean ans;				// 각 테스트 케이스의 정답
	static boolean[] answers;		// 모든 테스트 케이스 정답 배열
	static List<Integer> list[];	// 인접 리스트 배열
	
	
	
	public static void dfs(int color, int start) {
		nodes[start] = color;
		
		for(int i = 0; i < list[start].size(); i++) {
			int tmp = list[start].get(i);
			if(nodes[tmp] == 0) {
				dfs(color * -1, tmp);
			}else if(nodes[tmp] == nodes[start] && nodes[start] != 0) {
				ans = false;
				return;
			}
		}
	}
	
	public static void bfs(int start, int node) {
		// start지점부터 bfs로 모두 방문하는 함수
		
		Queue<Integer> q = new LinkedList<>();		
		q.add(start);
		nodes[start] = node;
		// 처음 지점 방문처리
		
		while(!q.isEmpty() && ans) {
			int tmp = q.poll();
			
			for(int i = 0; i< list[tmp].size(); i++) {
				int temp = list[tmp].get(i);
				
				if(nodes[temp] == 0) {
					// 방문하지 않은 지점인 경우					
					q.add(temp);
					nodes[temp] = nodes[tmp] * (-1);
					// 노드의 정보를 다른 값으로 삽입 (1 -> -1, -1 -> 1)
				}
				else if(nodes[temp] == nodes[tmp] && nodes[tmp] != 0) {
					// 방문한 지점인 경우
					// 값이 같고, 둘 다 0이 아닌 경우	
					
					ans = false;
					return;
				}
			}
		}
	}	
	
	public static void isBiparite() {
		// 모든 노드들을 돌면서 bfs 돌리는 메소드
		
		for(int i = 1; i < V+1; i++) {
			if(!ans) {
				break;	
			}			
			if(nodes[i] == 0) {
				// 방문 안한 지점부터 bfs 시작
				dfs(1, i);
			}
		}
	}

	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		// test case
		answers = new boolean[N];
		// 정답 배열
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			nodes = new int[V+1];
			// 노드의 정보, 0이면 방문 X 
			//  1, -1이면 방문 O
			// 처음은 모두 0으로 초기화
			
			list = new ArrayList[V+1];
			ans = true;
			// ans를 true로 초기화
			
			for(int j = 0; j < V+1; j++) {
				// 리스트 배열 초기화
				list[j] = new ArrayList<Integer>();
			}
			
			for(int j = 0; j < E; j++) {
				st = new StringTokenizer(br.readLine(), " ");
				int node1 = Integer.parseInt(st.nextToken());
				int node2 = Integer.parseInt(st.nextToken());
				list[node1].add(node2);
				list[node2].add(node1);
				// list에 그래프 정보 삽입
			}
			
			// 입력 완료
			
			isBiparite();
			// 메소드 실행
			
			answers[i] = ans;
			// 정답입력
		}
		
		for(int i = 0; i < N; i++) {
			// 정답출력
			if(answers[i]) {
				System.out.println("YES");
			}else {
				System.out.println("NO");
			}
		}
	}

}
