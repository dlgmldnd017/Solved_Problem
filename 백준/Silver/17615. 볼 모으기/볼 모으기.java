import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int N, ans;
    static String str;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        str = br.readLine();

        ans = Integer.MAX_VALUE;

        solve();

        System.out.println(ans);
    }

    static void solve() {
        boolean check = false;
        int cnt = 0, tmp = 0;

        // R을 오른쪽으로
        for (int i = 0; i < N; i++) {
            if (str.charAt(i) == 'B') {
                if (check) {
                    cnt += tmp;
                    tmp = 0;
                    check = false;
                }
            } else {
                check = true;
                tmp++;
            }
        }
        if (ans > cnt) ans = cnt;
        check = false;
        cnt = tmp = 0;

        // R을 왼쪽으로
        for (int i = N - 1; i >= 0; i--) {
            if (str.charAt(i) == 'B') {
                if (check) {
                    cnt += tmp;
                    tmp = 0;
                    check = false;
                }
            } else {
                check = true;
                tmp++;
            }
        }
        if (ans > cnt) ans = cnt;
        check = false;
        cnt = tmp = 0;

        // B를 오른쪽으로
        for (int i = 0; i < N; i++) {
            if (str.charAt(i) == 'R') {
                if (check) {
                    cnt += tmp;
                    tmp = 0;
                    check = false;
                }
            } else {
                check = true;
                tmp++;
            }
        }
        if (ans > cnt) ans = cnt;
        check = false;
        cnt = tmp = 0;

        // B를 왼쪽으로
        for (int i = N - 1; i >= 0; i--) {
            if (str.charAt(i) == 'R') {
                if (check) {
                    cnt += tmp;
                    tmp = 0;
                    check = false;
                }
            } else {
                check = true;
                tmp++;
            }
        }
        if (ans > cnt) ans = cnt;
    }
}