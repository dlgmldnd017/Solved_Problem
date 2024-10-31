import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int T, K, min, max;
    static String str;

    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            str = br.readLine();
            K = Integer.parseInt(br.readLine());

            if (K == 1) sb.append("1 1");
            else solve();

            sb.append("\n");
        }

        System.out.println(sb);
    }

    static void solve() {
        int i = 0;

        min = Integer.MAX_VALUE;
        while (i < str.length() - K) {
            int cnt = 1, idx = -1, j = i + 1;

            while (cnt != K) {
                idx = str.indexOf(str.charAt(i), j);
                if (idx == -1) break;
                j = idx + 1;
                cnt++;
            }

            if (cnt == K) if (min > (idx - i + 1)) min = idx - i + 1;
            i++;
        }

        if (min == Integer.MAX_VALUE) {
            sb.append("-1");
            return;
        }

        sb.append(min + " ");

        i = 0;

        max = Integer.MIN_VALUE;
        while (i < str.length() - K) {
            int cnt = 1, idx = -1, j = i + 1;

            while (cnt != K) {
                idx = str.indexOf(str.charAt(i), j);
                if (idx == -1) break;
                j = idx + 1;
                cnt++;
            }

            if (cnt == K) if (max < (idx - i + 1)) max = idx - i + 1;
            i++;
        }
        sb.append(max);
    }
}