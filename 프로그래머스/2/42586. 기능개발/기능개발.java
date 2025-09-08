import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> res = new ArrayList<>();
        int n = progresses.length;
        
        int day = (int)Math.ceil((100 - progresses[0]) / (double)speeds[0]);
        int count = 1;
        
        for (int i = 1; i < n; i++) {
            int need = (int)Math.ceil((100 - progresses[i]) / (double)speeds[i]);
            if (need <= day) {
                count++;
            } else {
                res.add(count);
                count = 1;
                day = need;
            }
        }
        res.add(count);
        
        return res.stream().mapToInt(i -> i).toArray();
    }
}
