import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, L, S[], Q[], ans;

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        S = new int[M + 1];

        for (int i = 0; i < M; i++) S[i] = Integer.parseInt(br.readLine());

        S[M] = L;

        Q = new int[N];

        for (int i = 0; i < N; i++) Q[i] = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) solve(Q[i]);

        System.out.println(sb);
    }

    static void solve(int q) {
        int low = 0, high = L, ans = 0;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (check(mid, q)) {
                low = mid + 1;
                ans = Math.max(ans, mid);
            } else high = mid - 1;
        }

        sb.append(ans + "\n");
    }

    static boolean check(int mid, int q) {
        int prev = 0;

        for (int i = 0; i <= M; i++) {
            if (S[i] - prev >= mid) {
                q--;
                prev = S[i];
            }
        }

        return q < 0 ? true : false;
    }
}