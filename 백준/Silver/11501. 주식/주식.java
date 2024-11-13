import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int T, N, arr[];
    static long ans;

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());

            arr = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

            ans = 0;

            solve();

            sb.append(ans + "\n");
        }

        System.out.println(sb);
    }

    static void solve() {
        int max = arr[N - 1];

        for (int j = N - 2; j >= 0; j--) {
            if (arr[j] <= max) ans += max - arr[j];
            else max = arr[j];
        }
    }
}
