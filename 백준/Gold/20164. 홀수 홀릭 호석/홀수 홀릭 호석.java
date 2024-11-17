import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, min, max;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;

        solve(N, getOdd(N));

        System.out.println(min + " " + max);
    }

    static void solve(int num, int cnt) {
        if (num < 10) {

            if (min > cnt) min = cnt;
            if (max < cnt) max = cnt;

        } else if (num < 100) {

            int sum = (num / 10) + (num % 10);
            solve(sum, cnt + getOdd(sum));

        } else {

            String s = Integer.toString(num);
            int len = s.length();

            for (int i = 0; i <= len - 3; i++) {
                for (int j = i + 1; j <= len - 2; j++) {
                    String s1 = s.substring(0, i + 1);
                    String s2 = s.substring(i + 1, j + 1);
                    String s3 = s.substring(j + 1, len);

                    int sum = Integer.parseInt(s1) + Integer.parseInt(s2) + Integer.parseInt(s3);
                    solve(sum, cnt + getOdd(sum));
                }
            }

        }
    }

    static int getOdd(int num) {
        String s = Integer.toString(num);
        int cnt = 0;

        for (int i = 0; i < s.length(); i++) if ((s.charAt(i) - '0') % 2 != 0) cnt++;

        return cnt;
    }
}