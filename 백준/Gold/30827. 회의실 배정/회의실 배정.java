import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력 처리
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 회의 수
        int k = Integer.parseInt(st.nextToken()); // 회의실 수

        // 회의 목록 입력 및 종료 시간 기준 내림차순 정렬
        List<int[]> meetings = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            meetings.add(new int[]{start, end});
        }
        meetings.sort((a, b) -> b[1] - a[1]); // 종료 시간 기준 내림차순 정렬

        // 회의실 상태 및 결과값 초기화
        List<Integer> rooms = new ArrayList<>();
        int ans = 0;

        // 모든 회의 처리
        while (!meetings.isEmpty()) {
            int[] meeting = meetings.remove(meetings.size() - 1); // 종료 시간이 가장 빠른 회의
            int start = meeting[0];
            int end = meeting[1];

            rooms.sort(Comparator.reverseOrder());

            boolean assigned = false;
            for (int i = 0; i < rooms.size(); i++) {
                if (rooms.get(i) < start) { // 현재 회의실 사용 가능
                    rooms.set(i, end); // 종료 시간 갱신
                    ans++;
                    assigned = true;
                    break;
                }
            }

            // 새로운 회의실 추가
            if (!assigned && rooms.size() < k) {
                rooms.add(end);
                ans++;
            }
        }

        // 결과 출력
        System.out.println(ans);
    }
}