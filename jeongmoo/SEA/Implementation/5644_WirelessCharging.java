import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// SW Expert Academy 5644번. 무선 충전
class BatteryCharger implements Comparable<BatteryCharger> {
	int no;
	int x, y, c, p;

	public BatteryCharger(int no, int x, int y, int c, int p) {
		this.no = no;
		this.x = x;
		this.y = y;
		this.c = c;
		this.p = p;
	}

	@Override
	// 성능 높은 순으로 정렬
	public int compareTo(BatteryCharger o) {
		return o.p - this.p;
	}
}

public class SWEA5644_WirelessCharging {	
	// 입력 데이터
	static int[] moveA; 
	static int[] moveB; 
	static BatteryCharger[] bc;
	static int M;
	static int A;
	
	// 계산 데이터
	static int sum = 0;
	static Point personA;
	static Point personB;
	
	// 1,2,3,4. 상우하좌
	static int dx[] = {0, -1, 0, 1, 0};
	static int dy[] = {0, 0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int i = 1; i <= T; i++) {
			// 초기화
			sum = 0;
			personA = new Point(1,1);
			personB = new Point(10,10);
			
			// 입력
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken()); // 시간
			A = Integer.parseInt(st.nextToken()); // BC의 개수
			
			moveA = new int[M];
			moveB = new int[M];
			bc = new BatteryCharger[A];
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				moveA[j] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				moveB[j] = Integer.parseInt(st.nextToken());
			}
			
			for (int j = 0; j < A; j++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				bc[j] = new BatteryCharger(j+1, x, y, c, p);
			}
			
			// 처리
			move();
			
			// 출력
			System.out.printf("#%d %d\n", i, sum);
		}
	}
	
	public static void move() {
		PriorityQueue<BatteryCharger> pqA = new PriorityQueue<>();
		PriorityQueue<BatteryCharger> pqB = new PriorityQueue<>();
		for(int time=0; time<=M; time++) {
			// 1. 현재 위치 값 더하기
			pqA.clear();
			pqB.clear();
			
			// 1-1. BC의 개수(A)만큼 순회하며 찾는다. 
			for (int j = 0; j < A; j++) {
				BatteryCharger b = bc[j];
				if(isAvailable(b, personA))
					pqA.offer(b);
				if(isAvailable(b, personB))
					pqB.offer(b);
			}
			
			// 1-2. 케이스 따라서 값을 더해준다.			
			// 1-2-1. 둘중 하나에만 있으면 그 값을 더해준다.
			if (pqA.size() == 0 || pqB.size() == 0) {
				if (!pqA.isEmpty())
					sum += pqA.poll().p;
				if (!pqB.isEmpty())
					sum += pqB.poll().p;
			} else { // 둘 다 있는 경우
				BatteryCharger b = pqA.poll();
				BatteryCharger b2 = pqB.poll();
				
				// 1-2-2. 2개를 뽑아 2개가 다르면 둘다 더한다.
				if (b.no != b2.no) {
					sum += b.p;
					sum += b2.p;
				} else {
					// 같은 경우에는 큰 값을 더하고 다음을 본다. 
					sum += b.p;
					
					// 1-2-3. 뽑고나서 1개가 비어있다면 남은 쪽을 더한다.
					if (pqA.size() == 0 || pqB.size() == 0) {
						if (!pqA.isEmpty())
							sum += pqA.poll().p;
						if (!pqB.isEmpty())
							sum += pqB.poll().p;
					}
					// 1-2-4. 양쪽이 다 남아있다면 그 다음 중 큰 값을 더한다.
					else {						
						sum += pqA.peek().p>pqB.peek().p?pqA.peek().p:pqB.peek().p;
					}
				}
				
			}
			
			// 2. 시간 도착하면 끝
			if(time==M)
				return;
			
			// 3. 이동
			personA.x += dy[moveA[time]];
			personA.y += dx[moveA[time]];
			personB.x += dy[moveB[time]];
			personB.y += dx[moveB[time]];
		}
	}
	
	public static boolean isAvailable(BatteryCharger b, Point person) {
		if (getDistance(b, person) <= b.c)
			return true;
		return false;
	}
	
	public static int getDistance(BatteryCharger b, Point person) {
		return Math.abs(b.x - person.x) + Math.abs(b.y - person.y);
	}
}
