class Solution {
    static int answer, targetNum, numLen, arr[];
    public int solution(int[] numbers, int target) {
        arr = numbers;
        numLen = numbers.length;        
        targetNum = target;
        answer = 0;
        dfs(0, 0);
        return answer;
    }
    
    public void dfs(int cnt, int sum) {
        if (cnt == numLen) {
            if (sum == targetNum) answer++;
            return;
        }
        dfs(cnt+1, sum + arr[cnt]);
        dfs(cnt+1, sum - arr[cnt]);
    }
      
}