import java.util.StringTokenizer;
import java.util.Set;
import java.util.HashMap;
import java.util.TreeMap;

// 프로그래머스 2022 KAKAO BLIND RECRUITMENT - 주차 요금 계산
public class P92341 {
	public static void main(String[] args) throws Exception {
		// 입력
		int[] fees = {180,5000, 10, 600};
		String[] records = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};

		int[] result = solution(fees, records);
		for (int i : result) {
			System.out.println(i);
		}
	}
	
	public static int[] solution(int[] fees, String[] records) {
		TreeMap<String, Integer> timeMap = new TreeMap<>(); // 차량번호, 누적시간
		HashMap<String, String> recordMap = new HashMap<>(); // 차량번호, 입차시간
		
		// 모든 데이터 처리
		StringTokenizer st;
		for (String str : records) {
			st = new StringTokenizer(str);
			String time = st.nextToken();
			String no = st.nextToken();
			String type = st.nextToken();
			
			if(type.equals("IN")) {
				// 들어오면 맵에넣고
				recordMap.put(no, time);
				if (!timeMap.containsKey(no))
					timeMap.put(no, 0);
			} else {
				// 나갈 때 시간 계산해서 recordMap에서 빼고, timeMap에 시간 갱신
				String[] inTime = recordMap.get(no).split(":");
				String[] outTime = time.split(":");
				int diffHour = Integer.parseInt(outTime[0]) - Integer.parseInt(inTime[0]);
				int diffMinute = Integer.parseInt(outTime[1]) - Integer.parseInt(inTime[1]);
				int diff = diffHour*60 + diffMinute;
				timeMap.put(no, timeMap.get(no) + diff);
				recordMap.remove(no);
			}
		}
		// 11:59분에 남은 데이터 처리
		Set<String> keyset = recordMap.keySet();
		for (String key : keyset) {
			String[] inTime = recordMap.get(key).split(":");
			int diffHour = 23 - Integer.parseInt(inTime[0]);
			int diffMinute = 59 - Integer.parseInt(inTime[1]);
			int diff = diffHour*60 + diffMinute;
			timeMap.put(key, timeMap.get(key) + diff);
		}
		
		int[] answer;
		// 배열에 담자.
		keyset = timeMap.keySet();
		answer = new int[keyset.size()];
		int idx = 0;
		for (String key : keyset) {
			int diffTime = timeMap.get(key);
			int money = fees[1];
			if (fees[0] < diffTime) {
				diffTime -= fees[0];
				diffTime = (int) Math.ceil((double)diffTime / fees[2]);
				money += diffTime * fees[3];
			}
			answer[idx++] = money;
		}

        return answer;
    }
}
