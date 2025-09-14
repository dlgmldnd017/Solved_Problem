import java.util.*;

class Solution {
    public int solution(String name) {
        int len = name.length();
        
        int vertical = 0;
        
        for (int i = 0; i < len; i++) {
            char c = name.charAt(i);
            vertical += Math.min(c - 'A', 'Z' - c + 1);
        }
        
        int horizontal = len - 1;
        
        int i = 0;
        while (i < len) {
            int next = i + 1;
            while (next < len && name.charAt(next) == 'A') next++;
            
            int tmp = (len - next);
            horizontal = Math.min(horizontal, (i << 1) + tmp);
            horizontal = Math.min(horizontal, (tmp << 1) + i);
            
            i = next;
        }

        return vertical + horizontal;
    }
}
