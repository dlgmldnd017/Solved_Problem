import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, K, words[], selected, ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if (K < 5) {
            System.out.println(0);
            return;
        }

        words = new int[N];

        for (int i = 0; i < N; i++) {
            for (char c : br.readLine().toCharArray()) words[i] |= (1 << (c - 'a'));
        }

        selected |= (1 << ('a' - 'a'));
        selected |= (1 << ('n' - 'a'));
        selected |= (1 << ('t' - 'a'));
        selected |= (1 << ('i' - 'a'));
        selected |= (1 << ('c' - 'a'));

        solve(0, 0);

        System.out.println(ans);
    }

    static void solve(int depth, int idx) {
        if (depth == K - 5) {
            int cnt = getReadableWords();
            if (ans < cnt) ans = cnt;
            return;
        }

        for (int i = idx; i < 26; i++) {
            if ((selected & (1 << i)) != 0) continue;

            selected |= (1 << i);
            solve(depth + 1, i + 1);
            selected &= ~(1 << i);
        }
    }

    static int getReadableWords() {
        int cnt = 0;

        for (int i : words) {
            if ((i & selected) == i) cnt++;
        }

        return cnt;
    }
}