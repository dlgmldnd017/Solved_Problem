import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, min, max;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        solve();

        System.out.println(min + " " + max);
    }

    static void solve() {
        min = Integer.MAX_VALUE;

        max = Integer.MIN_VALUE;

        dfs(N, getOdd(N));
    }

    static void dfs(int num, int cnt) {
        if (num < 10) {
            if (min > cnt) min = cnt;
            if (max < cnt) max = cnt;

        } else if (num < 100) {
            int sum = (num / 10) + (num % 10);

            dfs(sum, cnt + getOdd(sum));

        } else {
            String str = Integer.toString(num);

            for (int i = 0; i <= str.length() - 3; i++) {
                for (int j = i + 1; j <= str.length() - 2; j++) {
                    String s1 = str.substring(0, i + 1);
                    String s2 = str.substring(i + 1, j + 1);
                    String s3 = str.substring(j + 1);

                    int sum = Integer.parseInt(s1) + Integer.parseInt(s2) + Integer.parseInt(s3);

                    dfs(sum, cnt + getOdd(sum));

                }
            }
        }
    }

    static int getOdd(int num) {
        String str = Integer.toString(num);
        int cnt = 0;

        for (int i = 0; i < str.length(); i++) {
            int tmp = str.charAt(i) - '0';

            if (tmp % 2 != 0) cnt++;
        }

        return cnt;
    }
}