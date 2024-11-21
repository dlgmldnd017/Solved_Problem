import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, arr[][], ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());

            arr[i][0] = S;
            arr[i][1] = W;
        }

        solve(0, 0);

        System.out.println(ans);
    }

    static void solve(int idx, int cnt) {
        if (idx == N) {
            if (ans < cnt) ans = cnt;
            return;
        }

        if (arr[idx][0] <= 0 || cnt == N - 1) {
            solve(idx + 1, cnt);
            return;
        }

        int prevCnt = cnt;

        for (int i = 0; i < N; i++) {
            if (idx == i || arr[i][0] <= 0) continue;

            arr[idx][0] -= arr[i][1];
            arr[i][0] -= arr[idx][1];

            if (arr[idx][0] <= 0) cnt++;
            if (arr[i][0] <= 0) cnt++;

            solve(idx + 1, cnt);

            arr[idx][0] += arr[i][1];
            arr[i][0] += arr[idx][1];

            cnt = prevCnt;
        }
    }
}