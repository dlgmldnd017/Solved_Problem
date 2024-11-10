import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int T, K, alpha[];
    static String W;

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            W = br.readLine();
            K = Integer.parseInt(br.readLine());

            alpha = new int[26];

            for (int i = 0; i < W.length(); i++) alpha[W.charAt(i) - 'a']++;

            solve();
        }

        System.out.println(sb);
    }

    static void solve() {
        int min = 10001, max = 1;

        for (int i = 0; i < W.length(); i++) {
            if (alpha[W.charAt(i) - 'a'] < K) continue;

            int start = i + 1, cnt = 1;

            while (cnt != K) {
                start = W.indexOf(W.charAt(i), start);

                if (start == -1) break;
                start++;
                cnt++;
            }

            if (start == -1) continue;
            int len = start - i;

            if (min > len) min = len;
            if (max < len) max = len;
        }

        if (min == 10001) {
            sb.append("-1\n");
            return;
        }

        sb.append(min + " " + max + "\n");
    }
}