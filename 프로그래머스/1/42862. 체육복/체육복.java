import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int[] stu = new int[n + 1];
        Arrays.fill(stu, 1);
        
        for (int i : lost) stu[i]--;
        for (int i : reserve) stu[i]++;
        
        int answer = 0;
        
        for (int i = 1; i <= n; i++) {
            if (stu[i] == 0) {
                if (i != 1 && stu[i - 1] == 2) {
                    stu[i]++;
                    stu[i - 1]--;
                } else if (i != n && stu[i + 1] == 2) {
                    stu[i]++;
                    stu[i + 1]--;
                }
            }
            
            if (stu[i] >= 1) answer++;
        }
        
        return answer;
    }
}