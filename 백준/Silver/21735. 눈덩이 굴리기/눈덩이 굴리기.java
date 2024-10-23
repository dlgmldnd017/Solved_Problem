import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, arr[], ans;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        ans = Integer.MIN_VALUE;

        solve(0, -1, 1);

        System.out.println(ans);
    }

    static void solve(int depth, int idx, int sum) {
        if (depth == M || idx == N - 1) {
            if (ans < sum) ans = sum;
            return;
        }

        if (idx + 1 < N) solve(depth + 1, idx + 1, sum + arr[idx + 1]);
        if (idx + 2 < N) solve(depth + 1, idx + 2, (sum / 2) + arr[idx + 2]);
    }
}