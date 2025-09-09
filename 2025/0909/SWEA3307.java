import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA3307 {
	static int T;
	static int N;
	static int[] arr;
	static int[] dp;

	static int binarySearch(int start, int end, int key) {
		int mid;
		while(start < end) {
			mid = (start + end) / 2;
			if(dp[mid] < key) {
				start = mid + 1;
			}else {
				end = mid;
			}
		}
		return end;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			arr = new int[N];
			dp = new int[N];			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
	
			
			int length = 0;
			for(int i=0; i<N; i++) {
				if(length == 0 || arr[i] > dp[length-1]) {
					dp[length] = arr[i];
					length++;
				}else {
					int index = binarySearch(0, length-1, arr[i]);
					dp[index] = arr[i];
				}
			}
			System.out.println("#"+tc+" "+length);
			
		}

	}
}
