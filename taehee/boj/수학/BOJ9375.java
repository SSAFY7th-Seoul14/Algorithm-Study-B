package com;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Solution {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t=0; t<T; t++) {
			HashMap<String,Integer> map = new HashMap<>();
			int n = Integer.parseInt(br.readLine());
			
			for(int i=0; i<n; i++) {
				String[] clothes = br.readLine().split(" ");
				String name = clothes[1];
				if(map.containsKey(name)) {
					map.put(name, map.get(name)+1);
				}else {
					map.put(name,1);
				}
			}
			int ans = 1;
			for(int x : map.values()) {
				ans *= x+1;
			}
			System.out.println(ans - 1);
		}
	}
	
}
