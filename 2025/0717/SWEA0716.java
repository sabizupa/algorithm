import java.io.*;
import java.util.*;
public class SWEA0716 {
	static int T;
	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		for(int tc=1; tc<T+1; tc++) {
			st = new StringTokenizer(br.readLine());
			int sum = 0;
			for(int i=0; i<10; i++) {
				int posNum = Integer.parseInt(st.nextToken());
				if(posNum%2 != 0 ) { sum += posNum; }
			}
			System.out.println("#"+tc+" "+sum);		
		}
		
	}

}
