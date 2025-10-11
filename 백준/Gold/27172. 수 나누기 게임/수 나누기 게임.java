import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, max;
    static int[] cnt, card;

    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        card = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            card[i] = Integer.parseInt(st.nextToken());

            if (max < card[i]) max = card[i];
        }

        cnt = new int[1_000_001];

        for (int i : card) cnt[i]++;

        solve();

        System.out.println(sb);
    }

    static void solve() {
        int[] score = new int[1_000_001];

        for (int i = 1; i <= max; i++) {
            if (cnt[i] == 0) continue;

            for (int j = i * 2; j <= max; j += i) {
                if (cnt[j] == 0) continue;

                score[i]++;
                score[j]--;
            }
        }

        for (int i : card) {
            sb.append(score[i]).append(" ");
        }
    }
}