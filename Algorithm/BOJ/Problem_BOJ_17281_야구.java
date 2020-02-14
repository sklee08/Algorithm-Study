package algo_study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Problem_BOJ_17281_야구 {

	static int N;
	static int [][]score;
	static int max = Integer.MIN_VALUE;
	static int ans;

	static final int MAX_N = 100;

	static int front;
	static int rear;
	static boolean queue[] = new boolean[MAX_N];

	static void queueInit()
	{
		front = 0;
		rear = 0;
	}

	static boolean queueIsEmpty()
	{
		return (front == rear);
	}

	static boolean queueIsFull()
	{
		if ((rear + 1) % MAX_N == front)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	static boolean queueEnqueue(boolean value)
	{
		if (queueIsFull())
		{
			//System.out.print("queue is full!");
			return false;
		}
		queue[rear] = value;
		rear++;
		if (rear == MAX_N)
		{
			rear = 0;
		}

		return true;
	}

	static Boolean queueDequeue()
	{
		if (queueIsEmpty()) 
		{
			//System.out.print("queue is empty!");
			return null;
		}

		Boolean value = new Boolean(queue[front]);

		front++;
		if (front == MAX_N)
		{
			front = 0;
		}
		return value;
	}
	
	static int queueSize() {
		return rear-front;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		score = new int[N][9];

		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 9; j++) {
				score[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int [] tmp = new int[9];
		boolean[] visit = new boolean[9];
		visit[0] = true;
		makePer(tmp, 0,visit);
		// 입력 완료
		System.out.println(max);
	}

	public static void getScore(int[] tmp) {

		int out = 0;
		int index = 0;

		for(int i = 0; i < N; i++) {
			queueInit();
			while(out < 3) {

				int t = score[i][tmp[index%9]];
				switch(t) {
				case 0:
					out++;
					break;
				case 1:
					queueEnqueue(true);
					break;
				case 2:
					queueEnqueue(true);
					queueEnqueue(false);
					break;
				case 3:
					queueEnqueue(true);
					queueEnqueue(false);
					queueEnqueue(false);
					break;
				case 4:
					while(!queueIsEmpty()) {
						if(queueDequeue()) ans++;
					}					
					ans++;
					break;
				}
				while(queueSize() > 3) {
					if(queueDequeue()) ans++;
				}
				index++;
			}
			out = 0;
		}
	}

	public static void makePer(int []tmp, int curr, boolean[] visit) {
		if(curr == 3) {
			tmp[3] = 0;
			makePer(tmp,curr+1,visit);
		}else if(curr == 9) {
			//종료
			// 해당 순열에 해당하는 
			//System.out.println(Arrays.toString(tmp));
			getScore(tmp);
			if(max < ans) {
				max = ans;
			}
			ans = 0;

		}else {
			for(int i = 0; i < 9; i++) {
				if(!visit[i]) {
					visit[i] = true;
					tmp[curr] = i;
					makePer(tmp,curr+1,visit);
					visit[i] = false;
				}
			}
		}
	}

}
