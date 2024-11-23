import java.util.*;

class Solution {
    static int N, T, arr[], ans;
    static List<Character> list = new ArrayList<>();
    static char op[] = {'+', '-'};
    
    public int solution(int[] numbers, int target) {
        N = numbers.length;
        T = target;
        arr = numbers;
        
        dfs(0);
        
        return ans;
    }
    
    static void dfs(int depth) {
        if(depth == N) {
            int target = getTarget();
            
            if (target == T) ans++;
            return;
        }
        
        list.add(op[0]);
        dfs(depth + 1);
        list.remove(list.size() - 1);
        
        list.add(op[1]);
        dfs(depth + 1);
        list.remove(list.size() - 1);
    }
    
    static int getTarget() {
        int target = 0, idx = 0;
        
        for (char c : list) {
            if (c == '+') target += arr[idx++];
            else target -= arr[idx++];
        }
        
        return target;
    }
}