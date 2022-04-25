// 프로그래머스 2022 KAKAO BLIND RECRUITMENT - 양궁대회
public class P92342 {
	public static void main(String[] args) throws Exception {
		// 입력
		int n = 9;
		int[] info = {0,0,1,2,0,1,1,1,1,1,1};

		int[] result = solution(n, info);
		for (int i : result) {
			System.out.println(i);
		}
	}
	
	static int maxDiff = 0;
	static int[] answer = new int[11];
	public static int[] solution(int n, int[] info) { // 화살 개수, 점수배열		
		// 부분집합으로 만들면 2^10. 0~10점을 선택하고 안하고로
		subset(0, n, info, new int[11]);
		
		if (maxDiff != 0) // 이기는 경우가 존재하면
			return answer;
		
        return new int[] {-1};
    }
	
	public static void subset(int idx, int remain, int[] apeach, int[] lion) {
		if (remain == 0 || idx == 11) {
			int scoreDiff = getDiff(apeach, lion);
			if (scoreDiff > 0) {
				int[] temp = lion.clone();
				if (remain > 0) // 화살이 남아있다면 가장 낮은 점수를 더 많이 맞춘다.
					temp[10] += remain;
				
				if (maxDiff < scoreDiff) {
					maxDiff = scoreDiff;
					answer = temp.clone();
				} else if (maxDiff == scoreDiff) {
					// 가장 낮은 점수를 맞힌 경우가 더 많으면 답 변경
					boolean change = false;
					for (int i = 10; i >= 0; i--) {
						if (answer[i] == temp[i])
							continue;
						if (answer[i] < temp[i])
							change = true;
						else
							change = false;
						break;
					}
					
					if (change)
						answer = temp.clone();
				}
			}
			return;
		}
		
		// 선택
		if (remain > apeach[idx]) {
			lion[idx] += apeach[idx]+1;
			subset(idx+1, remain-(apeach[idx]+1), apeach, lion);
			lion[idx] -= apeach[idx]+1;
		}
		
		// 비선택
		subset(idx+1, remain, apeach, lion);
	}
	
	public static int getDiff(int[] apeach, int[] lion) {
		int apeachScore = 0;
		int lionScore = 0;
		for (int i = 0; i < lion.length; i++) {
			if (apeach[i] < lion[i]) {
				lionScore += 10-i;
			} else if (apeach[i] != 0) { // 어피치가 더 크거나 같은데. 0발은 제외
				apeachScore += 10-i;
			}
		}
		
		return lionScore - apeachScore;
	}
}
