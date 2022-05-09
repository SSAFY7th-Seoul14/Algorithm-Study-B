import java.util.*;
import java.io.*;

public class BOJ1487_물건팔기 {
	static class Person implements Comparable<Person> {
		int pay, fee;

		public Person(int pay, int fee) {
			super();
			this.pay = pay;
			this.fee = fee;
		}

		@Override
		public int compareTo(Person o) {
			return o.pay - this.pay;
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// n <= 50
		int n = stoi(br.readLine());
		Person[] people = new Person[n];
		int minPay = 1000001;
		int maxPay = 0;
		for (int i = 0; i < n; ++i) {
			st = new StringTokenizer(br.readLine());
			// 지불할 최대 금액과 배송비
			// 0<= <= 1,000,000
			int pay = stoi(st.nextToken());
			minPay = Math.min(pay, minPay);
			maxPay = Math.max(pay, maxPay);
			people[i] = new Person(pay, stoi(st.nextToken()));
		}
		Arrays.sort(people);
		int maxProfit = 0;
		int answer = 0;
		// 주어진 최대 지불 금액과 최소 지불 금액 사이에서 다 탐색하면서 max 갱신하고 그 때 가격 기록
		for (int i = minPay; i <= maxPay; ++i) {
			int profitSum = 0;
			for (int j = 0; j < n; ++j) {
				if (people[j].pay < i) {
					break;
				}
				int profit = i - people[j].fee;
				profitSum += profit > 0 ? profit : 0;
			}
			if (maxProfit < profitSum) {
				maxProfit = profitSum;
				answer = i;
			}
		}
		System.out.println(answer);
		br.close();
	}

	private static int stoi(String readLine) {
		return Integer.parseInt(readLine);
	}

}
