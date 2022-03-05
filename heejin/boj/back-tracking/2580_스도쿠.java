import java.io.*;
import java.util.*;

// BOJ / 스도쿠 / G4 / 50분
// https://www.acmicpc.net/problem/2580
// 아이디어 참고함
public class Main_2580  {
	static int map[][];
	static List<int[]> list;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		map = new int[9][9];
		list = new ArrayList<int[]>(); //빈 칸 x,y좌표 저장

		
		for(int i=0;i<9;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<9;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		//빈칸 리스트에 저장
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				if(map[i][j]==0)
					list.add(new int[] {i,j});
			}
		}

		//스도쿠 시작
		dfs(0);
	}
	private static void dfs(int cnt) {
		if(cnt==list.size()) { //모든 빈칸 다 채웠으면 끝
			for(int i=0;i<9;i++) {
				for(int j=0;j<9;j++)
					System.out.print(map[i][j]+" ");
				System.out.println();
			}
			System.exit(0); //중복 출력 방지
		}
		int[] cur = list.get(cnt);
		int x = cur[0], y = cur[1];
		
		for(int i=1;i<=9;i++) { //1~9까지 숫자가 빈칸에 유망한지 확인
			if(isPromising(x, y, i)) {
				map[x][y]=i;
				dfs(cnt+1); //유망하면 다음 칸 ㄱㄱ
				map[x][y]=0;
			}
		}
		
		
	}
	private static boolean isPromising(int x, int y, int val) {
		//1. 같은 행 검사 -> 열 값 체크
		for(int i=0;i<9;i++) {
			if(i!=y && map[x][i]==val)
				return false;
		}
		//2. 같은 열 검사 -> 행 값 체크
		for(int i=0;i<9;i++) {
			if(i!=x && map[i][y]==val)
				return false;
		}
		//3. 3x3 검사
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(map[x/3*3+i][y/3*3+j]==val)
					return false;
			}
		}
		return true;
	}
}
