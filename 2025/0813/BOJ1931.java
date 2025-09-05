import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1931 {
	// 참고함: https://wikidocs.net/206266
	static int N;
	static int[][] time; // [n][0]: 시작. [n][1]: 끝.
	static int result;
	
	public static void calc() {
		int end = time[0][1];
		result++;
		for(int i=1; i<time.length;i++) {
			if(end > time[i][0]) { continue; }
			end = time[i][1];
			result++;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		time = new int[N][2];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			time[i][0] = Integer.parseInt(st.nextToken());
			time[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(time, (t1, t2)->{
			if(t1[1]<t2[1]) {
				return -1;
			}
			else if(t1[1]>t2[1]) {
				return 1;
			}
			else {
				if(t1[0]<t2[0]) {
					return -1; 
				}
				else if(t1[0]==t2[0]) {
					return 0;
				}
				else {
					return 1;
				}
			}
		});
		//System.out.println(Arrays.deepToString(time));
		calc();
		System.out.println(result);
	}
	
}
