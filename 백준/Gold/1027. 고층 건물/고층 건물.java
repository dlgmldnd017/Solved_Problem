import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, h[], ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        h = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) h[i] = Integer.parseInt(st.nextToken());

        solve();

        System.out.println(ans);
    }

    static void solve() {
        for (int i = 0; i < N; i++) {
            int cnt = 0;

            for (int j = i - 1; j >= 0; j--) {
                if (isVisible(j, i)) cnt++;
            }

            for (int j = i + 1; j < N; j++) {
                if (isVisible(i, j)) cnt++;
            }

            if (ans < cnt) ans = cnt;
        }
    }

    private static boolean isVisible(int i, int j) {
        double slope = (double) (h[j] - h[i]) / (j - i);

        for (int k = i + 1; k < j; k++) {
            double expectedHeight = h[i] + slope * (k - i);
            if (h[k] >= expectedHeight) {
                return false;
            }
        }
        return true;
    }
}