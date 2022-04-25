class Solution {

	static int[] selected;
	static int max = 0;
	static int appeachScore;
	static boolean flag;
	static int[] answer;

	public int[] solution(int n, int[] info) {
		selected = new int[11];
		answer = new int[11];
		for (int i = 0; i < 11; ++i) {
			if (info[i] > 0)
				appeachScore += 10 - i;
		}
		flag = false;
		subSet(n, 10, 0, info);
		return flag ? answer : new int[] { -1 };
	}

	private void subSet(int n, int curScore, int cur, int[] info) {
		int index = 10 - curScore;
		if (curScore == 0 && n >= 0) {
			int diff = cur - appeachScore;
			selected[index] = n;
			if (diff > max) {
				flag = true;
				max = diff;
				answer = selected.clone();
			} else if (diff == max && isBetter()) {
				answer = selected.clone();
			}
			return;
		}
		// i점 획득
		else if (n > info[index]) {
			selected[index] = info[index] + 1;
			if (info[index] > 0)
				appeachScore -= curScore;
			subSet(n - selected[index], curScore - 1, cur + curScore, info);
			if (info[index] > 0)
				appeachScore += curScore;
			selected[index] = 0;
		}
		// i점 못 획득
		subSet(n, curScore - 1, cur, info);
	}

	private boolean isBetter() {
		for (int i = 10; i >= 0; --i) {
			if (selected[i] > answer[i]) return true;
			else if (selected[i] < answer[i]) return false;
		}
		return true;
	}
}