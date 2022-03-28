import java.io.*;
import java.util.*;

// BOJ / 비슷한 단어 / S2 / 30분
// https://www.acmicpc.net/problem/1411
public class Main_1411 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] words = new String[N]; //N개의 단어
		
		for(int i=0;i<N;i++)
			words[i] = br.readLine();
		
		int cnt = 0; //비슷한 단어 쌍 (정답)
		
		for(int i=0;i<N;i++) {		//단어 A
			for(int j=i+1;j<N;j++) {		//단어 B
				boolean flag = true;
				HashMap<Character,Character> map = new HashMap<>(); //숌스럽게 바꾼 문자 저장
				for(int k=0;k<words[j].length();k++) {	//단어 A와 단어 B의 문자열 탐색
					char origin = words[i].charAt(k); //단어 A의 원본 문자열
					char compare = words[j].charAt(k); //숌스럽게 바꾼 단어 B의 문자열
					if(map.containsKey(origin)) { //원본 문자열이 이미 있을 경우(숌하게 바꾼 적 O)
						if(map.get(origin)!=compare) { //숌하지 않을 경우
							flag=false;
							break;
						}
					}
					else { //단어 A의 원본 문자열의 숌스럽게 바꾼 것이 없는 경우
						Iterator<Character> keys = map.keySet().iterator();
						while(keys.hasNext()) {
							char key = keys.next();
							if(map.get(key)==compare) { //이미 다른 알파벳의 대체 알파벳일 경우
								flag=false;
								break;
							}
						}
						if(flag)
							map.put(origin, compare);
					}
				}
				if(flag)
					cnt++;
			}
		}
		System.out.println(cnt);
		
	}
}
