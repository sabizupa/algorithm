import java.io.*;
import java.util.*;

public class SWEA14510 {
	static int T;
	static int N;
	static int tree[];

	// 참고함: https://ghs4593.tistory.com/12
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			tree = new int[N];
			st = new StringTokenizer(br.readLine());
			int maxHeight = 0;
			for (int i = 0; i < N; i++) {
				tree[i] = Integer.parseInt(st.nextToken());
				maxHeight = Math.max(maxHeight, tree[i]);
			}
			
			int even = 0;
			int odd = 0;
			for(int i=0; i<N; i++) {
				int d = maxHeight - tree[i];
				even += d/2;
				odd += d%2;
			}
		
			while(even - odd > 1) {
				even--;
				odd += 2;
			}
			
			int result = 0;
			if(even > odd) {
				result = even * 2;
			}else if(odd > even) {
				result = odd * 2 - 1;
			}else {
				result = odd + even;
			}
			
			System.out.println("#" + tc + " " + result);

		}

	}
}
