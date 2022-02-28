package com.ssafy.study;
import java.util.*;


//PGS / 섬 연결하기 / lv3 / 18분
//https://programmers.co.kr/learn/courses/30/lessons/42861
class Solution {
	 
	 public static int[] parents;
	 
	 public static int find(int x){
	     if(x==parents[x]) return x;
	     return parents[x] = find(parents[x]);
	 }
	 
	 public static void union(int a, int b){
	     int aRoot = find(a);
	     int bRoot = find(b);
	     if(aRoot==bRoot) return;
	     if(aRoot<bRoot) parents[bRoot]=aRoot;
	     else parents[aRoot]=bRoot;
	     
	 }
	 public void makeSet(int n){
	     for(int i=0;i<n;i++)
	         parents[i]=i;
	     
	 }
	 public int solution(int n, int[][] costs) {
	     int answer = 0; //최소 비용
	     parents = new int[n];
	     makeSet(n);
	     //가중치 정렬
	     Arrays.sort(costs,new Comparator<int[]>(){
	         @Override
	         public int compare(int[] o1, int[] o2){
	             return o1[2]-o2[2]; //가중치 순으로 오름차순 정렬
	         }
	     });
	     
	     int cnt=0; //최소 신장트리의 간선 개수
	     //최소 신장 트리 만들기
	     for(int i=0;i<costs.length;i++){
	         if(cnt==n-1)
	             break;
	         if(find(costs[i][0])!=find(costs[i][1])){
	             union(costs[i][0],costs[i][1]);
	              answer +=costs[i][2];
	             cnt++;
	         }
	            
	     }
	     
	     return answer;
	 }
}