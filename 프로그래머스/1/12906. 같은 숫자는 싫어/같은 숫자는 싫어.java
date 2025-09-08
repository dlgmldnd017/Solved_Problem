import java.util.*;

public class Solution {
    public int[] solution(int[] arr) {
        Queue<Integer> q = new ArrayDeque<>();
        
        for (int i = 0; i < arr.length; i++) {
            q.add(arr[i]);
        }
        
        int prev = -1;
        List<Integer> list = new ArrayList<>();
        
        while (!q.isEmpty()) {
            int cur = q.poll();
            
            if (prev == cur) continue;
            
            prev = cur;
            list.add(cur);
        }
        
        int[] ans = new int[list.size()];
        
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }

        return ans;
    }
}