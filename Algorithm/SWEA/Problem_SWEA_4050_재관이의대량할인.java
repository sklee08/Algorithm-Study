import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;
 
public class Problem_SWEA_4050_재관이의대량할인 {
    
    static int T,N;
    static Integer[] arr;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        T = Integer.parseInt(br.readLine());
        
        int t = 1;
        while(T-- > 0 ) {
            N = Integer.parseInt(br.readLine());
            
            arr = new Integer[N];
            
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            int total = 0;
            
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                total += arr[i];
            }
            
            Arrays.sort(arr, Collections.reverseOrder());
            
            int sale = 0;
            for (int i = 2; i < N; i+=3) {
                sale += arr[i];
            }
            System.out.printf("#%d %d\n",t++,total-sale);
        }
    }
}