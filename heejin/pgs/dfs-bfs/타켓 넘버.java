package com.ssafy.study;

// PGS / 타겟 넘버 / LV2 / 10분
class Solution { 
    
    static boolean[] isSelected;
    static int N;
    static int cnt; // target 만들 수 있는 경우의 수
    
    public int solution(int[] numbers, int target) {
        int answer = 0;
        N = numbers.length;
        
        isSelected = new boolean[N];
        
        subset(0, numbers, target);
        
        answer = cnt;
        
        return answer;
    }
    
    public static void subset(int idx, int[] numbers, int target){
        if(idx==N){
            int sum=0;
            for(int i=0;i<N;i++){
                if(isSelected[i]) // 덧셈 연산
                    sum +=numbers[i];
                else                // 뺄셈 연산
                    sum -= numbers[i];
                
            }
            if(sum==target)
                cnt++;
            return;
        }
        
        isSelected[idx]=true;
        subset(idx+1, numbers, target);
        isSelected[idx]=false;
        subset(idx+1, numbers, target); 
    }
}