import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, K, arr[];
    static long ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr);

        solve();

        System.out.println(ans);
    }

    static void solve() {
        long diff[] = new long[N - 1];

        for (int i = 0; i < N - 1; i++) diff[i] = arr[i + 1] - arr[i];

        Arrays.sort(diff);

        for (int i = 0; i < N - K; i++) ans += diff[i];
    }
} 