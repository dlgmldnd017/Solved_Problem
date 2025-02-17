import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static long X, ans;
    static long[] size, patty;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        X = Long.parseLong(st.nextToken());

        size = new long[N + 1];
        patty = new long[N + 1];

        size[0] = 1;
        patty[0] = 1;

        for (int i = 1; i <= N; i++) {
            size[i] = 2 * size[i - 1] + 3;
            patty[i] = 2 * patty[i - 1] + 1;
        }

        solve();

        System.out.println(ans);
    }

    static void solve() {
        ans = countPatty(N, X);
    }

    static long countPatty(int level, long x) {
        if (level == 0) return 1;

        if (x == 1) return 0;
        else if (x <= 1 + size[level - 1]) return countPatty(level - 1, x - 1);
        else if (x == 2 + size[level - 1]) return patty[level - 1] + 1;
        else if (x <= 2 + 2 * size[level - 1]) return patty[level - 1] + 1 + countPatty(level - 1, x - 2 - size[level - 1]);
        else return patty[level];
    }
}