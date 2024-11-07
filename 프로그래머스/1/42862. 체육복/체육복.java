import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        
        TreeSet<Integer> set = new TreeSet<>();
        
        for (int i : reserve) set.add(i);
        
        List<Integer> list = new ArrayList<>();
        
        for (int i : lost) {
            if (set.contains(i)) set.remove(i);
            else list.add(i);
        }
        
        Collections.sort(list);
        
        int cnt = 0;
        
        for (int i : list) {
            if (set.contains(i - 1)) {
                set.remove(i - 1);
                cnt++;
            } else if (set.contains(i + 1)) {
                set.remove(i + 1);
                cnt++;
            }
            
            if (set.size() == 0) break;
        }
        
        answer = n - list.size() + cnt;
        
        return answer;
    }
}