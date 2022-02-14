import java.util.*;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		
		Scanner scan=new Scanner(System.in);
        
		for(int test_case = 1; test_case <= 10; test_case++)
		{
			int arr[] = new int[8];
			Queue<Integer> q = new LinkedList<>();
			int num, temp;
            num = scan.nextInt();
            for (int i=0; i<8; i++)
            {
             	temp = scan.nextInt();
                q.add(temp);
            }
            int step = 0;
            while (true)
            {
             	step++;
                if (step > 5)
                    step = 1;
                temp = q.peek() - step;
                if (temp <= 0)
                {
                 	temp = 0;
                    q.remove();
                    q.add(temp);
                    break;
                }
                q.remove();
                q.add(temp);
            }
            System.out.print("#" + test_case + " " );
            while(!q.isEmpty())
            {
                System.out.print(q.peek() + " ");
             	q.remove();   
            }
            System.out.println();
		}
	}
}
