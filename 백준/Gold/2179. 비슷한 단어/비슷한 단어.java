import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static List<String> words = new ArrayList<>();

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) words.add(br.readLine());

        solve();

        System.out.println(sb);
    }

    static void solve() {
        int idx1 = -1, idx2 = -1, max = -1;

        for (int i = 0; i < N - 1; i++) {
            String s1 = words.get(i);

            for (int j = i + 1; j < N; j++) {
                String s2 = words.get(j);

                int cnt = check(s1, s2);

                if (cnt > max) {
                    idx1 = i;
                    idx2 = j;
                    max = cnt;
                }
            }
        }

        sb.append(words.get(idx1)).append("\n").append(words.get(idx2));
    }

    static int check(String s1, String s2) {
        int cnt = 0, size = Math.min(s1.length(), s2.length());

        for (int i = 0; i < size; i++) {
            if (s1.charAt(i) != s2.charAt(i)) break;
            cnt++;
        }

        return cnt;
    }
}