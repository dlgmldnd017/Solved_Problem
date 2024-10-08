import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, ans;
    static String balls;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        balls = br.readLine();

        ans = Integer.MAX_VALUE;

        solve();

        System.out.println(ans);
    }

    static void solve() {
        int cnt = 0;
        boolean check = false;

        for (int i = 0; i < N; i++) {
            if (check && balls.charAt(i) == 'R') {
                cnt++;
                continue;
            }

            if (balls.charAt(i) == 'B') check = true;
        }

        ans = Math.min(ans, cnt);

        cnt = 0;
        check = false;

        for (int i = N-1; i >= 0; i--) {
            if (check && balls.charAt(i) == 'R') {
                cnt++;
                continue;
            }

            if (balls.charAt(i) == 'B') check = true;
        }

        ans = Math.min(ans, cnt);

        cnt = 0;
        check = false;

        for (int i = 0; i < N; i++) {
            if (check && balls.charAt(i) == 'B') {
                cnt++;
                continue;
            }

            if (balls.charAt(i) == 'R') check = true;
        }

        ans = Math.min(ans, cnt);

        cnt = 0;
        check = false;

        for (int i = N-1; i >= 0; i--) {
            if (check && balls.charAt(i) == 'B') {
                cnt++;
                continue;
            }

            if (balls.charAt(i) == 'R') check = true;
        }

        ans = Math.min(ans, cnt);
    }
}