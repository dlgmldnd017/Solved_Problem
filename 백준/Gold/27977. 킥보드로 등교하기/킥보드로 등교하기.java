import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int L, N, K, station[], ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        station = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) station[i] = Integer.parseInt(st.nextToken());

        if (K == 0) ans = L;
        else solve();

        System.out.println(ans);
    }

    static void solve() {
        int left = 0, right = L;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (canGo(mid)) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
    }

    static boolean canGo(int mid) {
        int cnt = 0, lastPos = 0;

        for (int i = 0; i < N; i++) {
            if (station[i] - lastPos > mid) {
                if (i == 0) return false;

                cnt++;
                lastPos = station[i - 1];
                if (station[i] - lastPos > mid) return false;
            }
        }

        if (L - lastPos > mid) {
            cnt++;
            if (L - station[N - 1] > mid) return false;
        }

        return cnt <= K;
    }
}