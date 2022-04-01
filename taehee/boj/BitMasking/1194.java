package com;

import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = (1<<('a'-'a')) | 0; //1
		int b = (1<<('b'-'a')) | 0; //2 
		int f = (1<<('f'-'a')) | 0; //32
		
		System.out.println(a);
		System.out.println(b);
		System.out.println(f);
		
		int A = (1<<('A'-'A')) & 37;//ex) 100101 일때  a가지고있는지
		int B = (1<<('B'-'A')) & 37; //없으면 0 출력
		System.out.println(A);
		System.out.println(B);
	}
}