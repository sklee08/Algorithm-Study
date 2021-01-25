import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class Problem_BOJ_11559_PUYOPUYO {
 
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int[] dirX = new int[] { 0, 0, -1, 1 };
    public static int[] dirY = new int[] { 1, -1, 0, 0 };
    public static char[][] map;
    public static boolean[][] visited;
    public static List<Node> list;
    public static int N = 12, M = 6, ans = 0;
 
    public static void main(String[] args) throws Exception {
 
        map = new char[N][M];
        visited = new boolean[N][M];
        list = new ArrayList<Node>();
 
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
            }
        }
        solve();
        System.out.println(ans);
 
    }
 
    //모든 뿌요들을 땅으로 내려보내는 메소드
    public static void toGround() {
 
        for (int i = 0; i < M; i++) {
            for (int j = N - 1; j > 0; j--) {
 
                if (map[j][i] == '.') {
 
                    for (int k = j - 1; k >= 0; k--) {
                        if (map[k][i] != '.') {
                            map[j][i] = map[k][i];
                            map[k][i] = '.';
                            break;
                        }
                    }
                }
            }
        }
 
    }
 
    public static void solve() {
 
        while (true) {
            
            //while문을 탈출하기 위한 flag와 visited 배열 초기화
            boolean flag = true;
            for (int i = 0; i < N; i++)
                Arrays.fill(visited[i], false);
 
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (!visited[i][j] && map[i][j] != '.')
                        bfs(i, j);
 
                    //BFS 탐색을 한번 하고 난 뒤 list의 크기가 4이상일 경우 해당 뿌요들을 터뜨린다.
                    if (list.size() >= 4) {
                        flag = false;
                        for (Node node : list) {
                            map[node.row][node.col] = '.';
                        }
                    }
                    //4개 이상 인접한 한가지 색깔의 뿌요를 터뜨렸으면 List를 초기화해야 한다.
                    list.clear();
                }
            }
            toGround();
            if (flag)
                break;
            else
                ans += 1;
            
        }
    }
    
    //해당 지점부터 BFS탐색을 시작하는 메소드
    public static void bfs(int startRow, int startCol) {
 
        Queue<Node> q = new LinkedList<Node>();
        
        visited[startRow][startCol] = true;
        char val = map[startRow][startCol];
        q.offer(new Node(startRow, startCol));
        
        //같은 뿌요가 4개가 인접한 경우 연쇄반응이 일어나야 하므로 list에 저장시킨다.
        //list의 크기가 4이상이면 for문을 통해 터뜨린다.
        list.add(new Node(startRow, startCol));
 
        while (!q.isEmpty()) {
 
            Node node = q.poll();
            int row = node.row;
            int col = node.col;
 
            for (int i = 0; i < 4; i++) {
                int nr = row + dirX[i];
                int nc = col + dirY[i];
 
                if (isBoundary(nr, nc) && !visited[nr][nc] && map[nr][nc] == val) {
                    list.add(new Node(nr, nc));
                    q.offer(new Node(nr, nc));
                    visited[nr][nc] = true;
                }
            }
        }
        
    }
 
    public static boolean isBoundary(int row, int col) {
        return (row >= 0 && row < N) && (col >= 0 && col < M);
    }
 
}
 
class Node {
 
    int row;
    int col;
 
    public Node(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
