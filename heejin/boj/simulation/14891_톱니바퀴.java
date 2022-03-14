import java.io.*;
import java.util.*;

// BOJ / 톱니바퀴 / G5 / 55분
//https://www.acmicpc.net/problem/14891
public class Main_14891 {
	static int tires[][]; //톱니바퀴 N극, S극 저장
	static int num; //현재 회전시키고자 하는 톱니바퀴
	static int[] dirs; //각 톱니바퀴 별로 회전 여부 -1: 반시계, 0: 회전X, 시계:1
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		tires = new int[4][8];
		
		for(int i=0;i<4;i++) {
			String input = br.readLine();
			for(int j=0;j<8;j++)
				tires[i][j] = input.charAt(j)-'0';
		}
		int K = Integer.parseInt(br.readLine());
		for(int i=0;i<K;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			num = Integer.parseInt(st.nextToken()); //현재 톱니바퀴
			int dir = Integer.parseInt(st.nextToken()); //회전방향
			num--; //톱니바퀴 index는 0부터 시작, 톱니바퀴는 1번부터 시작이므로 -1
			dirs = new int[4]; //톱니바퀴 4개의 회전 방향 저장
			getDir(num,dir); //회전방향 구하기
			spin();//회전방향 구했으면 회전시키기
			
		}
		
		//점수 계산
		int res=0;
		for(int k=0;k<4;k++) {
			if(tires[k][0]==1)
				res+=Math.pow(2,k);
		}
		System.out.println(res);
		
		
		
	}
	private static void spin() {
		for(int i=0;i<4;i++) {
			if(dirs[i]==0) 
				continue;
			else if(dirs[i]==-1) { //반시계방향
				int tmp[] = tires[i].clone();
				for(int j=0;j<8;j++)
					tires[i][j]= tmp[(j+1)%8];
			}
			else if(dirs[i]==1) { //시계 방향
				int tmp[] = tires[i].clone();
				for(int j=0;j<8;j++) {
					tires[i][j] = tmp[(j+7)%8];
				}
			}	
		}
		
	}
	private static void getDir(int n, int dir) {
		if(n<0 || n>3) {
			return;
		}
		if(n<num) { //왼쪽 톱니바퀴는 자신의 2번과 오른쪽의 6번과 비교
			int cur_dir=0;
			if(tires[n][2]!=tires[n+1][6] && dirs[n+1]!=0) { //극이 다를 경우 -> 반대방향 회전
				cur_dir = (dir==-1)?1:-1;
				dirs[n]= cur_dir;
			}
			else { //극이 같을 경우 -> 회전 X
				dirs[n] = 0;
			}
			getDir(n-1,cur_dir);
		}
		else if(n>num) { //오른쪽 톱니바퀴는 자신의 6번과 왼쪽의 2번과 비교
			int cur_dir=0;
			if(tires[n][6]!=tires[n-1][2] && dirs[n-1]!=0) { //극이 다를 경우 -> 반대방향 회전
				cur_dir = (dir==-1)?1:-1;
				dirs[n]= cur_dir;
			}
			else { //극이 같을 경우 -> 회전 X
				dirs[n] = 0;
			}
			getDir(n+1,cur_dir);
		}
		else {
			dirs[n]=dir;
			getDir(n-1,dir);
			getDir(n+1,dir);
		}

	}

}
