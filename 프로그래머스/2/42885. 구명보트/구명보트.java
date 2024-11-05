import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        Arrays.sort(people);  // 몸무게 오름차순 정렬

        int i = 0;             // 가장 가벼운 사람의 인덱스
        int j = people.length - 1;  // 가장 무거운 사람의 인덱스

        while (i <= j) {       // 두 인덱스가 만나거나 교차할 때까지 반복
            if (people[i] + people[j] <= limit) {  // 가장 가벼운 사람과 가장 무거운 사람의 합이 limit 이하면
                i++;           // 두 사람을 같은 보트에 태움
            }
            j--;               // 무거운 사람은 항상 보트에 태움
            answer++;          // 보트 하나 추가
        }

        return answer;
    }
}
