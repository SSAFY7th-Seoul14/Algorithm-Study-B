import java.util.*;

class Solution {

	public int[] solution(int[] fees, String[] records) {
		HashMap<Integer, Integer> mapTime = new HashMap<>();
		HashMap<Integer, Stack> map = new HashMap<>();
		Set<Integer> set = new HashSet<>();
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> a[0] - b[0]);
		StringTokenizer st;
		for (String reco : records) {
			st = new StringTokenizer(reco);
			String timeReco = st.nextToken();
			String[] tmp = timeReco.split(":");
			int times = Integer.parseInt(tmp[0]) * 60 + Integer.parseInt(tmp[1]);
			int carNo = Integer.parseInt(st.nextToken());
			String inOut = st.nextToken();
			if (!map.containsKey(carNo)) {
				Stack<Integer> stack = new Stack<>();
				stack.add(times);
				map.put(carNo, stack);
				mapTime.put(carNo, 0);
				set.add(carNo);
			} else {
				if (inOut.equals("OUT")) {
					int timeIn = (int) map.get(carNo).pop();
					int curDiff = times - timeIn;
					mapTime.replace(carNo, mapTime.get(carNo) + curDiff);
				} else {
					map.get(carNo).add(times);
				}
			}
		}
		for (int key : set) {
			if (!map.get(key).isEmpty()) {
				mapTime.replace(key, mapTime.get(key) + 1439 - (int) map.get(key).pop());
			}
		}
		for (int key : set) {
			int totalTime = mapTime.get(key);
			pq.offer(new int[] { key, calcFee(fees, totalTime) });
		}
		int size = pq.size();
		int[] answer = new int[size];
		for (int i = 0; i < size; ++i) {
			answer[i] = pq.poll()[1];
		}

		return answer;
	}

	private int calcFee(int[] fees, int times) {
		int defaultT = fees[0];
		int defaultFee = fees[1];
		int unitT = fees[2];
		int unitFee = fees[3];
		int fee = defaultFee;
		if (times > defaultT) {
			fee += unitFee * Math.ceil((double)(times - defaultT) / (double)unitT);
		}
		return fee;
	}
}