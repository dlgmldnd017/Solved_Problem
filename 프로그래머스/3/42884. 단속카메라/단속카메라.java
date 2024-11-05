import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        // 진출 지점을 기준으로 정렬하여 겹치는 구간을 쉽게 찾음
        Arrays.sort(routes, (a, b) -> Integer.compare(a[1], b[1]));
        
        int answer = 0;
        int cameraPosition = Integer.MIN_VALUE;
        
        for (int[] route : routes) {
            int entry = route[0];
            int exit = route[1];
            
            // 현재 차량의 진입 지점이 이전 카메라 위치를 넘어서면 카메라 추가 설치
            if (cameraPosition < entry) {
                cameraPosition = exit; // 새로운 카메라를 해당 차량의 진출 지점에 설치
                answer++;
            }
        }
        
        return answer;
    }
}
