package com.ssafy.exam;

import java.io.BufferedReader;
import java.io.InputStreamReader;


// BOJ / 설탕 배달 / B1
//https://www.acmicpc.net/problem/2839
public class Main_2839 {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N= Integer.parseInt(br.readLine());
		int cnt=0; //설탕봉지 수 
		boolean flag=true; // n킬로그램 만들기 가능 여부
		
		while(N>0) {
			if(N%5==0) { //5의 배수일 경우 5 빼줌
				N-=5;
				cnt++;
			}
			else if(N%3==0) { // 3의 배수일 경우 3 빼줌
				N-=3;
				cnt++;
			}
			else { //5와 3의 배수가 아니면 5를 빼줌
				N-=5;
				if(N<0) { // n<0이 되면 n킬로그램을 못 만들므로 flag=false 후 반복문 탈출
					flag=false;
					break;
				}
					
				cnt++;
			}
		}
		if(flag)
			System.out.println(cnt);
		else
			System.out.println(-1);
	}

}
