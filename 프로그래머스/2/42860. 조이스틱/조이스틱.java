class Solution {
    public int solution(String name) {
        int answer = 0;
        int length = name.length();
        
        // 각 자리에서 알파벳 변경 비용 계산
        for (int i = 0; i < length; i++) {
            char target = name.charAt(i);
            answer += Math.min(target - 'A', 'Z' - target + 1);  // ▲ 또는 ▼ 조작 최소값
        }

        // 커서 이동 최소화
        int minMove = length - 1;  // 최대한 오른쪽으로 가는 경우
        for (int i = 0; i < length; i++) {
            int next = i + 1;
            // 연속된 A 처리
            while (next < length && name.charAt(next) == 'A') {
                next++;
            }
            // 오른쪽으로 갔다가 왼쪽으로 돌아오는 경우와 왼쪽으로 갔다가 오른쪽으로 돌아오는 경우 비교
            minMove = Math.min(minMove, i + length - next + Math.min(i, length - next));
        }
        
        answer += minMove;
        return answer;
    }
}
