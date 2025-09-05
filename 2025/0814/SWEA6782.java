import java.util.*;
import java.io.*;

public class SWEA6782 {


	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		double sqrtN;
		for(int tc=1; tc<=T; tc++) {
			long N = sc.nextLong();
			long count = 0;
			while(N!=2) {
				sqrtN = Math.sqrt(N);
				if(sqrtN==(long)sqrtN) {
					N = (long)sqrtN;
					count++;
				}
				else {					
					// sqrt가능까지
					count += ((long)sqrtN+1)*((long)sqrtN+1)-N;
					
					//sqrt
					count++;
					N = (long)sqrtN+1;
					
				}
			}
			
			System.out.println("#"+tc+" "+count);
		}
		
		
		
	}
}
