import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static String W;
    static int T, K;
    static boolean check;

    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            W = br.readLine();
            K = Integer.parseInt(br.readLine());

            check = false;

            solve3();
            if (check) continue;
            solve4();
        }

        System.out.println(sb);
    }

    static void solve3() {
        int len = Integer.MAX_VALUE;

        for (int i = 0; i < W.length(); i++) {
            char c = W.charAt(i);

            int idx = i;
            for (int j = 0; j < K; j++) {
                idx = W.indexOf(c, idx);
                if (idx == -1) break;
                idx++;
            }

            if (idx == -1) continue;

            int diff = (idx - i);
            if (len > diff) len = diff;
        }

        if (len == Integer.MAX_VALUE) {
            sb.append(-1 + "\n");
            check = true;
        } else sb.append(len + " ");
    }

    static void solve4() {
        int len = Integer.MIN_VALUE;

        for (int i = 0; i < W.length(); i++) {
            char c = W.charAt(i);

            int idx = i;
            for (int j = 0; j < K; j++) {
                idx = W.indexOf(c, idx);
                if (idx == -1) break;
                idx++;
            }

            if (idx == -1) continue;

            int diff = (idx - i);
            if (len < diff) len = diff;
        }

        sb.append(len + "\n");
    }
}