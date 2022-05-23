// https://programmers.co.kr/learn/courses/30/lessons/72412?language=java
import java.util.*;

class Solution {

	public int[] solution(String[] info, String[] query) {
		HashMap<String, Integer> LANG = new HashMap<String, Integer>();
		LANG.put("-", 0);
		LANG.put("cpp", 1);
		LANG.put("java", 2);
		LANG.put("python", 3);
		HashMap<String, Integer> FIELD = new HashMap<String, Integer>();
		FIELD.put("-", 0);
		FIELD.put("backend", 1);
		FIELD.put("frontend", 2);
		HashMap<String, Integer> CAREER = new HashMap<String, Integer>();
		CAREER.put("-", 0);
		CAREER.put("junior", 1);
		CAREER.put("senior", 2);
		HashMap<String, Integer> FOOD = new HashMap<String, Integer>();
		FOOD.put("-", 0);
		FOOD.put("chicken", 1);
		FOOD.put("pizza", 2);
		int pplNo = info.length;
		int qLen = query.length;
		int[] answer = new int[qLen];
		ArrayList<Integer>[][][][] ppl = new ArrayList[4][3][3][3];
		for (int i = 0; i < 4; ++i) {
			for (int j = 0; j < 3; ++j) {
				for (int k = 0; k < 3; ++k) {
					for (int l = 0; l < 3; ++l) {
						ppl[i][j][k][l] = new ArrayList<Integer>();
					}
				}
			}
		}
		StringTokenizer st;
		for (int i = 0; i < pplNo; ++i) {
			st = new StringTokenizer(info[i], " ");
			int lang = LANG.get(st.nextToken());
			int field = FIELD.get(st.nextToken());
			int career = CAREER.get(st.nextToken());
			int food = FOOD.get(st.nextToken());
			int score = Integer.parseInt(st.nextToken());
			for (int j = 0; j < 2; ++j) {
				for (int k = 0; k < 2; k++) {
					for (int l = 0; l < 2; l++) {
						for (int m = 0; m < 2; ++m) {
							ppl[lang * j][field * k][career * l][food * m].add(score);
						}
					}
				}
			}
		}
		for (int i = 0; i < 4; ++i) {
			for (int j = 0; j < 3; ++j) {
				for (int k = 0; k < 3; ++k) {
					for (int l = 0; l < 3; ++l) {
						Collections.sort(ppl[i][j][k][l]);
					}
				}
			}
		}
		for (int i = 0; i < qLen; ++i) {
			st = new StringTokenizer(query[i], " ");
			int lang = LANG.get(st.nextToken());
			st.nextToken();
			int field = FIELD.get(st.nextToken());
			st.nextToken();
			int career = CAREER.get(st.nextToken());
			st.nextToken();
			int food = FOOD.get(st.nextToken());
			int score = Integer.parseInt(st.nextToken());
			// 여기서 이분탐색
			int left = 0;
			int size = ppl[lang][field][career][food].size();
			int right = size - 1;
			while (left <= right) {
				int mid = (left + right) / 2;
				if (ppl[lang][field][career][food].get(mid) < score) {
					left = mid + 1;
				} else {
					right = mid - 1;
				}
			}
			answer[i] = size - (right + 1);
		}
		return answer;
	}

}