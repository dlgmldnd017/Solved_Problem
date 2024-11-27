import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static int N, M, h[], hs[];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        h = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) h[i] = Integer.parseInt(st.nextToken());

        hs = new int[N + 2];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            hs[a] = hs[a] + k;
            hs[b + 1] = hs[b + 1] - k;
        }

        solve();

        for (int i = 1; i <= N; i++) sb.append(h[i] + " ");

        System.out.println(sb);
    }

    static void solve() {
        for (int i = 1; i <= N; i++) hs[i] = hs[i - 1] + hs[i];

        for (int i = 1; i <= N; i++) h[i] += hs[i];
    }
}
