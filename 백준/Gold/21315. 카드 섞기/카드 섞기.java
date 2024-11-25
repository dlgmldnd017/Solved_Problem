import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, arr[], goal[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        goal = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) goal[i] = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) arr[i - 1] = i;

        solve();
    }

    static void solve() {
        for (int k1 = 1; (1 << k1) < N; k1++) {
            for (int k2 = 1; (1 << k2) < N; k2++) {
                int tmp[] = arr.clone();

                shuffle(tmp, k1);
                shuffle(tmp, k2);

                if (check(tmp)) {
                    System.out.println(k1 + " " + k2);
                    return;
                }
            }
        }
    }

    static void shuffle(int tmp[], int k) {
        int pos = N;

        for (int i = 1; i <= k + 1; i++) {
            int upCnt = (1 << (k - i + 1)), idx = pos - upCnt, next[] = new int[N], nextIdx = 0;

            // 더미 카드 위로 올림
            for (int j = idx; j < pos; j++) next[nextIdx++] = tmp[j];

            for (int j = 0; j < idx; j++) next[nextIdx++] = tmp[j];

            // 나머지 카드 올림
            for (int j = pos; j < N; j++) next[nextIdx++] = tmp[j];

            for (int j = 0; j < N; j++) tmp[j] = next[j];

            pos = upCnt;
        }
    }

    static boolean check(int tmp[]) {
        for (int i = 0; i < N; i++) {
            if (goal[i] != tmp[i]) return false;
        }

        return true;
    }
}