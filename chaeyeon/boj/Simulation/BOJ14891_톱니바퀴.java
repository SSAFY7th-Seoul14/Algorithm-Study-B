import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ14891_Åé´Ï¹ÙÄû {
	static ArrayList<LinkedList<Integer>> gears;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		gears = new ArrayList<>();
		gears.add(new LinkedList<>());
		for(int i=1; i <= 4; i++) {//Åé´Ï¹ÙÄû Á¤º¸ÀÔ·Â
			String info = br.readLine();
			LinkedList<Integer> gear = new LinkedList<>();
			for(int j=0; j < 8; j++) {
				gear.add(info.charAt(j)-'0');
			}
			gears.add(gear);		
		}
		
		int K = Integer.parseInt(br.readLine());
		
		for(int i=0; i < K; i++) {//È¸Àü
			st = new StringTokenizer(br.readLine());
			int gearNum = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			
			dfsLeft(gearNum-1, dir*-1);//¿ÞÂÊdfs
			dfsRight(gearNum+1, dir*-1);//¿À¸¥ÂÊdfs
			rotate(gearNum,dir);//ÀÚ½Å È¸Àü
		}
		
		int result = 0;
		for(int i=1; i <= 4; i++) {
			result += gears.get(i).get(0)<<(i-1);
		}

		System.out.println(result);

	}
	
	public static void dfsLeft(int gearNum, int dir) {
		if(gearNum == 0) return;
		if(gears.get(gearNum).get(2) == gears.get(gearNum+1).get(6)) return;
		
		dfsLeft(gearNum-1, dir*-1);
		rotate(gearNum, dir);
	}
	
	public static void dfsRight(int gearNum, int dir) {
		if(gearNum == 5) return;
		if(gears.get(gearNum).get(6) == gears.get(gearNum-1).get(2)) return;
		
		dfsRight(gearNum+1, dir*-1);
		rotate(gearNum, dir);
	}

	private static void rotate(int gearNum, int dir) {
		
		if(dir == -1) {
			gears.get(gearNum).add(gears.get(gearNum).poll());
		}
		else {
			gears.get(gearNum).offerFirst(gears.get(gearNum).pollLast());
		}

	}

}
