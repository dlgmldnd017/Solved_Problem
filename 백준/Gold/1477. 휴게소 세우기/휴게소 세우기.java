import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M, L, arr[], ans;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        arr = new int[N + 2];
        arr[0] = 0;
        arr[N + 1] = L;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr);

        solve();

        System.out.println(ans);
    }

    static void solve() {
        int left = 1, right = L;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (canInstall(mid)) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
    }

    static boolean canInstall(int dist) {
        int cnt = 0;

        for (int i = 1; i < arr.length; i++) {
            int gap = arr[i] - arr[i - 1];
            cnt += (gap / dist);

            if (gap % dist == 0) cnt--;
        }

        return cnt <= M;
    }
}