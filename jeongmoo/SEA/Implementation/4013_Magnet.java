import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// SW Expert Academy 4013번. 특이한 자석
public class SWEA4013_Magnet {
	static int[][] magnet = new int[4][8];
	static final int right = 2;
	static final int left = 6;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= t; tc++) {
        	// 입력
        	int k = Integer.parseInt(br.readLine()); // 회전 수
        	for (int i = 0; i < 4; i++) {
        		st = new StringTokenizer(br.readLine());
        		for (int j = 0; j < 8; j++) {
    				magnet[i][j] = Integer.parseInt(st.nextToken());
    			}
			}
        	
        	for (int i = 0; i < k; i++) {
        		st = new StringTokenizer(br.readLine());
        		int magnetNo = Integer.parseInt(st.nextToken());
        		int dir = Integer.parseInt(st.nextToken());
        		
        		rotateAll(magnetNo-1, dir, 0);        		
			}
        	
        	int score = getScore();
    		sb.append("#").append(tc).append(" ").append(score).append("\n");
        }
        System.out.println(sb);
    }
    
    public static void rotateAll(int no, int dir, int check) {
    	if ((check & 1<<no) != 0)
    		return;

    	if (no>0 && magnet[no][left] != magnet[no-1][right])
    		rotateAll(no-1, dir*-1, check |= 1<<no);
    	if (no<3 && magnet[no][right] != magnet[no+1][left])
    		rotateAll(no+1, dir*-1, check |= 1<<no);
    	
    	rotate(no, dir);
    }
    
    public static void rotate(int no, int dir) {
    	if (dir == 1) { // 시계
    		int temp = magnet[no][7];
    		for (int i = 7; i > 0; i--) {
				magnet[no][i] = magnet[no][i-1];
			}
    		magnet[no][0] = temp;
    	} else { // 반시계
    		int temp = magnet[no][0];
    		for (int i = 1; i < 8; i++) {
				magnet[no][i-1] = magnet[no][i];
			}
    		magnet[no][7] = temp;
    	}
    }
    
    public static int getScore() {
    	int score = 0;
    	for (int i = 0; i < 4; i++) {
    		if(magnet[i][0] == 1)
    			score += 1<<i;
		}
    	return score;
    }
}