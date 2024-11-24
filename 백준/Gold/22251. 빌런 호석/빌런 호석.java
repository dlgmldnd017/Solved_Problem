import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, K, P, X, ans;

    static int displayInfo[][] = {
            {1, 1, 1, 0, 1, 1, 1}, //0
            {0, 0, 1, 0, 0, 0, 1}, //1
            {0, 1, 1, 1, 1, 1, 0}, //2
            {0, 1, 1, 1, 0, 1, 1}, //3
            {1, 0, 1, 1, 0, 0, 1}, //4
            {1, 1, 0, 1, 0, 1, 1}, //5
            {1, 1, 0, 1, 1, 1, 1}, //6
            {0, 1, 1, 0, 0, 0, 1}, //7
            {1, 1, 1, 1, 1, 1, 1}, //8
            {1, 1, 1, 1, 0, 1, 1}, //9
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        solve();

        System.out.println(ans);
    }

    static void solve() {
        int X_digit[] = numToDigit(X);

        for (int i = 1; i <= N; i++) {
            if (i == X) continue;

            if (canReverse(i, X_digit)) ans++;
        }
    }

    static int[] numToDigit(int x) {
        int result[] = new int[K];

        for (int i = K - 1; i >= 0; i--) {
            result[i] = x % 10;
            x /= 10;
        }

        return result;
    }

    static boolean canReverse(int target, int[] X_digit) {
        int[] target_digit = numToDigit(target);

        int diff_count = 0;
        for (int i = 0; i < K; i++) {
            for (int j = 0; j < 7; j++) {
                if (displayInfo[X_digit[i]][j] != displayInfo[target_digit[i]][j]) {
                    diff_count++;
                    if (diff_count > P) return false;
                }
            }
        }
        return true;

    }
} 