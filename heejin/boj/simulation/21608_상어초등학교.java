// BOJ / 상어 초등학교 / S1 / 45분
// https://www.acmicpc.net/problem/21608

import java.io.*;
import java.util.*;


class Student implements Comparable<Student>{
	int num;
	int x;
	int y;
	int fav;
	int space;
	public Student(int num, int x, int y, int fav, int space) {
		super();
		this.num = num;
		this.x = x;
		this.y = y;
		this.fav = fav;
		this.space = space;
	}
	@Override
	public int compareTo(Student o) { //좋아하는 사람 내림차순 -> 빈칸 내림차순 -> 행 오름차순 -> 열 오름차순
		if(this.fav==o.fav) {
			if(this.space==o.space) {
				if(this.x==o.x) {
					return this.y-o.y; //열 오름차순
				}else
					return this.x-o.x; //행 오름차순
			}else
				return o.space-this.space; //빈칸 내림차순
		}
		else
			return o.fav-this.fav; //좋아하는 사람 내림차순
	}
}
public class Main_21608 {
	static int N;
	static int map[][]; //자리 배치 상태
	static List<ArrayList<Integer>> list; //각 학생별로 좋아하는 학생 리스트
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		list = new ArrayList<>();
		for(int i=0;i<N*N+1;i++) {
			list.add(new ArrayList<Integer>());
		}
		//입력받기
		StringTokenizer st= null;
		for(int n=1;n<N*N+1;n++) {
			st = new StringTokenizer(br.readLine());
			int studentNo = Integer.parseInt(st.nextToken()); //학생 번호
			for(int j=0;j<4;j++) { //좋아하는 4명 학생 추가
				int fav = Integer.parseInt(st.nextToken());
				list.get(studentNo).add(fav);
			}
			//현재 입력받은 학생 자리 배치 시작
			PriorityQueue<Student> pq = new PriorityQueue<>();
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(map[i][j]!=0) continue; //이미 다른 사람 있으면 패스
					int fav=0; //좋아하는 사람 수
					int space =0; //빈칸 수
					for(int d=0;d<4;d++) { //각 좌표별로 4방 탐색
						int nx = i + dx[d];
						int ny = j + dy[d];
						if(nx>=0 && nx<N && ny>=0 && ny<N) {
							if(map[nx][ny]==0) //빈칸이면
								space++;
							if(list.get(studentNo).contains(map[nx][ny])) //좋아하는 사람 있으면
								fav++;	
						}
					}
					pq.add(new Student(studentNo, i, j, fav, space)); //4방 탐색 후 pq에 삽입
				} 
			}
			Student cur = pq.poll(); //조건 만족하는 가장 첫번째 위치
			int x = cur.x, y = cur.y;
			map[x][y]=cur.num; //자리 배치 완료
		}
		
		
		//만족도 조사
		int res=0; //만족도(정답)
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				int fav=0; //좋아하는 사람 수
				int cur = map[i][j]; //현재 탐색하는 자리에 앉은 학생
				for(int d=0;d<4;d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];
					if(nx>=0 && nx< N && ny>=0 && ny<N) {
						if(list.get(cur).contains(map[nx][ny]))
							fav++;
					}
					
				}
				if(fav==1) //만족도 계산
					res+=1;
				else if(fav==2)
					res +=10;
				else if(fav==3)
					res+=100;
				else if(fav==4)
					res += 1000;
			}
		}
		System.out.println(res);		
	}

}
