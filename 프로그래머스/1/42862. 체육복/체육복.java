import java.util.*;

class Solution {
    static Set<Integer> set = new TreeSet<>();
    static Set<Integer> black = new TreeSet<>();
    
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        
        Arrays.sort(lost);
        
        for(int i : lost){
            for(int j : reserve) {
                if(i==j) {
                    black.add(i);
                    break;
                }
            }
        }
        
        for(int i : reserve) {
            if(black.contains(i)) continue;
            set.add(i);
        }
        
        for(int i : lost) {
            if(black.contains(i)) continue;
            i--;
            
            for(int j=0; j<2; j++) {
                if(set.contains(i)){
                    answer++;
                    set.remove(i);
                    break;
                }
                i+=2;
            }
        }
        
        answer = n - lost.length + answer + black.size();
        
        return answer;
    }
}