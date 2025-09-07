import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        Map<String, Integer> map = new HashMap<>();
        
        for (String[] item : clothes) {
            String type = item[1];
            map.put(type, map.getOrDefault(type, 0) + 1);
        }
        
        int ways = 1;
        for (int cnt : map.values()) {
            ways *= (cnt + 1);
        }

        return ways - 1;
    }
}
