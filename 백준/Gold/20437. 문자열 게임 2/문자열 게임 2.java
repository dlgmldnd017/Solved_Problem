import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static String W;
    static int T, K;
    static List<Integer> list[] = new ArrayList[26];

    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            W = br.readLine();
            K = Integer.parseInt(br.readLine());

            if (K == 1) {
                sb.append("1 1\n");
                continue;
            }

            solve();
        }

        System.out.println(sb);
    }

    static void solve() {
        for (int i = 0; i < 26; i++) list[i] = new ArrayList<>();
        for (int i = 0; i < W.length(); i++) list[W.charAt(i) - 'a'].add(i);

        int min = W.length() + 1;
        int max = -1;

        for (int i = 0; i < 26; i++) {
            List<Integer> now = list[i];
            if (now.size() < K) continue;

            int end = K - 1;

            for (int j = end; j < now.size(); j++) {
                int tmp = now.get(j) - now.get(j - K + 1) + 1;
                if (min > tmp) min = tmp;
                if (max < tmp) max = tmp;
            }
        }

        if (min == W.length() + 1 && max == -1) sb.append("-1\n");
        else sb.append(min + " " + max + "\n");
    }
}