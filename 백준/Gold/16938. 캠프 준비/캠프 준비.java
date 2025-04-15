import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, L, R, X, ans;
    static int[] arr;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        solve();

        System.out.println(ans);
    }

    static void solve() {
        Arrays.sort(arr);

        dfs(0, 0, Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
    }

    static void dfs(int depth, int cnt, int min, int max, int sum) {
        if (depth == N) {
            if (cnt >= 2 && L <= sum && sum <= R && (max - min) >= X) ans++;
            return;
        }

        dfs(depth + 1, cnt + 1, Math.min(min, arr[depth]), Math.max(max, arr[depth]), sum + arr[depth]);

        dfs(depth + 1, cnt, min, max, sum);
    }
}