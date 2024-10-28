import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M, arr[];
    static long prefixSum[], ans;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        prefixSum = new long[N];
        prefixSum[0] = arr[0];

        for (int i = 1; i < N; i++) prefixSum[i] += prefixSum[i - 1] + arr[i];

        ans = Long.MIN_VALUE;

        if (M == 0) ans = 0;
        else solve();

        System.out.println(ans);
    }

    static void solve() {
        for (int i = N - 1; i > M - 1; i--) {
            long tmp = prefixSum[i] - prefixSum[i - M];
            if (ans < tmp) ans = tmp;
        }

        if (ans < prefixSum[M - 1]) ans = prefixSum[M - 1];
    }
}