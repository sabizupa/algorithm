
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA2805{
	static int[][] map;
	static int N;
	static int T;
	
	public static int harvest() {
		if (N==1) { return map[0][0]; }
		
		int sum = 0;
		for(int i=0; i<N; i++) {
			int jStart;
			int jEnd;
			int jIncrease;
			
			if(i<=N/2) {
				jStart = N/2-i;
				jEnd = N/2+i;
			}
			else { // i=가운데 아래
				jStart = i-(N/2);
				jEnd = (N/2)+N-i-1;

			}
			for(int j=jStart; j<=jEnd; j++) {
				//System.out.println("i, j: "+i+" "+j);
				sum = sum+map[i][j];
			}
		}
		return sum;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		for(int tc=1; tc<T+1; tc++) {
			st = new StringTokenizer(br.readLine());
			N =Integer.parseInt(st.nextToken());
			map = new int[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				String s = st.nextToken();
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(String.valueOf(s.charAt(j)));
				}
			}
			System.out.println("#"+tc+" "+harvest());
		}
		
		
	}
}