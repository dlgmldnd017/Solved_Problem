import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int T, a, b;
    static boolean prime[] = new boolean[10000];

    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        getPrime();

        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            if (a == b) sb.append("0");
            else solve();

            sb.append("\n");
        }

        System.out.println(sb);
    }

    static void solve() {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(a);

        boolean visited[] = new boolean[10000];
        visited[a] = true;

        int cnt[] = new int[10000];

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j <= 9; j++) {
                    if (i == 0 && j == 0) continue;

                    StringBuilder tmp = new StringBuilder(cur + "");
                    tmp.setCharAt(i, (char) (j + '0'));
                    int k = Integer.parseInt(tmp.toString());

                    if (prime[k] || visited[k]) continue;

                    if (k == b) {
                        sb.append(cnt[cur] + 1);
                        return;
                    }

                    q.add(k);
                    visited[k] = true;
                    cnt[k] = cnt[cur] + 1;
                }
            }
        }

        sb.append("Impossible");
    }

    static void getPrime() {
        prime[0] = prime[1] = true;

        for (int i = 2; i < 10000; i++) {
            if (prime[i]) continue;

            for (int j = i + i; j < 10000; j += i) prime[j] = true;
        }
    }
}