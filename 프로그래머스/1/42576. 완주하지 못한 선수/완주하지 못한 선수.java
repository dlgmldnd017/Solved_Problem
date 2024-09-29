import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        
        Map<String, Integer> map = new HashMap<>();
        for(int i=0; i<completion.length; i++){
            map.put(completion[i], map.getOrDefault(completion[i], 0)+1);
        }
        
        for(int i=0; i<participant.length; i++){
            if(map.containsKey(participant[i])) {
                int cnt = map.get(participant[i])-1;
                
                if(cnt == 0) {
                    map.remove(participant[i]);
                }
                else {
                    map.put(participant[i], cnt);
                }
                
            }
            else {
                answer = participant[i];
                break;
            }
        }
        
        return answer;
    }
}