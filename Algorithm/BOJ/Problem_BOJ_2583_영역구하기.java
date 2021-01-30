import java.util.*;
import java.io.*;
 
public class Problem_BOJ_2583_영역구하기 {
    static int stoi(String s) { return Integer.parseInt(s);}
 
    static int m;
    static int n;
    static int k;
    static int[][] arr;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int area;
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
 
        st = new StringTokenizer(br.readLine());
        m = stoi(st.nextToken());
        n = stoi(st.nextToken());
        k = stoi(st.nextToken());
        arr = new int[m][n];
        visited = new boolean[m][n];
 
        for(int i=0; i<k; i++) {
            st = new StringTokenizer(br.readLine());
            setSqure(stoi(st.nextToken()), stoi(st.nextToken()), stoi(st.nextToken()), stoi(st.nextToken()));
        }
 
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        int count = 0;
 
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                area = 0;
                if(arr[i][j] == 0) {
                    count++;
                    dfs(i, j);
                    pq.offer(area);
                }
            }
        }
 
        bw.write(count + "\n");
        while(!pq.isEmpty())
            bw.write(pq.poll() + " ");
        bw.flush();
    }
 
    static void setSqure(int lx, int ly, int rx, int ry) {
        for(int i=ly; i<ry; i++){
            for(int j=lx; j<rx; j++) {
                arr[i][j] = 1;
            }
        }
    }
 
    static void dfs(int x, int y) {
        arr[x][y] = 1;
        area++;
 
        for(int i=0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
 
            if(0 <= nx && nx < m && 0 <= ny && ny < n) {
                if(arr[nx][ny] == 0)
                    dfs(nx, ny);
            }
        }
    }
}