package com.ssafy.io;

import java.io.*;
import java.util.*;

public class SWEA1289 {
	
	static int T;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		for(int tc=1; tc<T+1; tc++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			char posChar = '0';
			int sum = 0;
			for(int i=0; i<s.length(); i++) {
				if(s.charAt(i) != posChar) {
					posChar = s.charAt(i);
					sum++;
				}
			}
			System.out.println("#"+tc+" "+sum);
		}
		
		
		
		
	}
	
	
}
