import java.util.*;

class Solution {
    static int ans;
    static boolean visited[];
    
    public int solution(String begin, String target, String[] words) {
        ans = Integer.MAX_VALUE;
        
        visited = new boolean[words.length];
        
        dfs(begin, target, 0, words);
    
        if (ans == Integer.MAX_VALUE) ans = 0;
        return ans;
    }
    
    static void dfs(String s, String e, int cnt, String[] words) {
        if (s.equals(e)) {
            if (ans > cnt) ans = cnt;
            return;
        }
        
        for (int i = 0; i < words.length; i++) {
            if (visited[i] || !check(s, words[i])) continue;
            
            visited[i] = true;
            dfs(words[i], e, cnt + 1, words);
            visited[i] = false;
        }
    }
    
    static boolean check(String s, String t) {
        int cnt = 0;
        
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != t.charAt(i)) cnt++;
        }
        
        return cnt == 1;
    }
}