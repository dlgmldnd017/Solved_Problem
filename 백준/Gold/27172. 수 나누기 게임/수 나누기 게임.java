import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, cards[], max, cnt[];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        cards = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
            if (max < cards[i]) max = cards[i];
        }

        cnt = new int[max + 1];
        for (int card : cards) cnt[card]++;

        solve();

        System.out.println(sb);
    }

    static void solve() {
        int score[] = new int[max + 1];

        for (int i = 1; i <= max; i++) {
            if (cnt[i] <= 0) continue;

            for (int j = i * 2; j <= max; j += i) {
                if (cnt[j] <= 0) continue;

                score[i] += cnt[j];
                score[j] -= cnt[i];
            }
        }

        for (int card : cards) sb.append(score[card] + " ");
    }
}