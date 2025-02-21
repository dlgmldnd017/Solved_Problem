import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int X;
    static int[] dp, path;

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        X = Integer.parseInt(br.readLine());

        dp = new int[X + 1];

        path = new int[X + 1];

        solve();

        System.out.println(sb);
    }

    static void solve() {
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[1] = 0;

        for (int i = 2; i <= X; i++) {
            // (1) 1을 빼는 경우
            dp[i] = dp[i - 1] + 1;
            path[i] = i - 1;

            // (2) 2로 나누어 떨어지는 경우
            if (i % 2 == 0 && dp[i] > dp[i / 2] + 1) {
                dp[i] = dp[i / 2] + 1;
                path[i] = i / 2;
            }

            // (3) 3으로 나누어 떨어지는 경우
            if (i % 3 == 0 && dp[i] > dp[i / 3] + 1) {
                dp[i] = dp[i / 3] + 1;
                path[i] = i / 3;
            }
        }

        sb.append(dp[X] + "\n");

        int current = X;

        while (current > 0) {
            sb.append(current).append(" ");
            current = path[current]; // 이전 숫자로 이동
        }
    }
}
