import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, ans;
    static int[][] eggs;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        eggs = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++) eggs[i][j] = Integer.parseInt(st.nextToken());
        }

        solve();

        System.out.println(ans);
    }

    static void solve() {
        if (N == 1) return;

        // 1. 가장 왼쪽의 계란을 든다.
        dfs(0, 0);
    }

    // depth : 가장 왼쪽의 계란
    // cnt : 깨진 계란 개수
    static void dfs(int depth, int cnt) {
        // 3. 가장 최근에 든 계란이 오른쪽에 위치한 계란일 경우
        if (depth == N) {
            if (ans < cnt) ans = cnt;
            return;
        }

        // 2. 손에 든 계란이 깨졌거나 깨지지 않은 계란이 없으면, 3번 진행
        if (eggs[depth][0] <= 0 || cnt == N - 1) {
            dfs(depth + 1, cnt);
            return;
        }

        int prevCnt = cnt;

        // 2. 깨지지 않은 계란 중 하나 고름
        for (int i = 0; i < N; i++) {
            if (depth == i || eggs[i][0] <= 0) continue;

            eggs[depth][0] -= eggs[i][1];
            eggs[i][0] -= eggs[depth][1];

            if (eggs[depth][0] <= 0) cnt++;
            if (eggs[i][0] <= 0) cnt++;

            dfs(depth + 1, cnt);

            eggs[depth][0] += eggs[i][1];
            eggs[i][0] += eggs[depth][1];

            cnt = prevCnt;
        }
    }
}