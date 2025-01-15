import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, ans;
    static char original[], target[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        original = br.readLine().toCharArray();
        target = br.readLine().toCharArray();

        int result1 = solve(false);
        int result2 = solve(true);

        ans = Math.min(result1, result2);

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    static int solve(boolean pressFirst) {
        char ch[] = original.clone();
        int cnt = 0;

        if (pressFirst) {
            press(ch, 0);
            cnt++;
        }

        for (int i = 1; i < N; i++) {
            if (ch[i - 1] != target[i - 1]) {
                press(ch, i);
                cnt++;
            }
        }

        if (ch[N - 1] != target[N - 1]) return Integer.MAX_VALUE;

        return cnt;
    }

    static void press(char[] arr, int idx) {
        arr[idx] = toggle(arr[idx]);
        if (idx > 0) arr[idx - 1] = toggle(arr[idx - 1]);
        if (idx < N - 1) arr[idx + 1] = toggle(arr[idx + 1]);
    }

    static char toggle(char c) {
        return c == '0' ? '1' : '0';
    }
}