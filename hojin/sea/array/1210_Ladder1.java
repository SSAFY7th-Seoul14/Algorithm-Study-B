import java.io.BufferedReader;
import java.io.InputStreamReader;
 
public class Solution {
    static char[][] ladderGrid = new char[100][100];
    static int currentY, currentX;
 
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
 
        for (int tc = 1; tc <= 10; tc++) {
            in.readLine();
            for (int i = 0; i < 100; i++) {
                ladderGrid[i] = in.readLine().replaceAll(" ", "").toCharArray();
            }
            for (int i = 0; i < 100; i++) {
                if (ladderGrid[99][i] == '2')
                    currentX = i;
            }
            currentY = 99;
            while (currentY > 0) {
                if (currentX == 99) {
                    if (ladderGrid[currentY][currentX - 1] == '1')
                        xMinus();
                } else if (currentX == 0) {
                    if (ladderGrid[currentY][currentX + 1] == '1')
                        xPlus();
                } else if (currentX > 0 && currentX < 99) {
                    if (ladderGrid[currentY][currentX - 1] == '1')
                        xMinus();
                    else if (ladderGrid[currentY][currentX + 1] == '1')
                        xPlus();
                }
                currentY--;
            }
 
            sb.append("#").append(tc).append(" ").append(currentX).append("\n");
        }
        in.close();
        System.out.println(sb);
    }
 
    private static void xPlus() {
        currentX++;
        while (ladderGrid[currentY - 1][currentX] != '1') {
            currentX++;
        }
    }
 
    private static void xMinus() {
        currentX--;
        while (ladderGrid[currentY - 1][currentX] != '1') {
            currentX--;
        }
    }
}
