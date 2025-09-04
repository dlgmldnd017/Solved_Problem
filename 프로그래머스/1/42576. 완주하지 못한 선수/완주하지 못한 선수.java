import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";

        Map<String, Integer> map = new HashMap<>();
        
        for (int i = 0; i < participant.length; i++) {
            map.put(participant[i], map.getOrDefault(participant[i], 0) + 1);
        }
        
        for (int i = 0; i < completion.length; i++) {
            int cnt = map.get(completion[i]) - 1;
            if (cnt == 0) map.remove(completion[i]);
            else map.put(completion[i], cnt);
        }

        return map.keySet().iterator().next();
    }
}