import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, D, K, C, arr[], valueCnt[], ans;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new int[N + K - 1];

        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(br.readLine());
        for (int i = N; i < N + K - 1; i++) arr[i] = arr[i - N];

        valueCnt = new int[D + 1];

        ans = Integer.MIN_VALUE;

        solve();

        System.out.println(ans);
    }

    static void solve() {
        int i = 0, j = 0, cnt = 0;

        while (i < N) {
            while (j < K + i) {
                valueCnt[arr[j]]++;
                if (valueCnt[arr[j]] == 1) cnt++;
                j++;
            }

            if (valueCnt[C] == 0) ans = Math.max(ans, cnt + 1);
            else ans = Math.max(ans, cnt);

            valueCnt[arr[i]]--;
            if (valueCnt[arr[i]] == 0) cnt--;
            i++;
        }
    }
}