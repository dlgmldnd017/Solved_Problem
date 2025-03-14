import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, K, ans;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N];

        int right = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            right += arr[i];
        }

        solve(0, right);

        System.out.println(ans);
    }

    static void solve(int left, int right) {
        while (left <= right) {
            int mid = (left + right) / 2;

            if (canDivide(mid)) {
                ans = mid;
                left = mid + 1;
            } else right = mid - 1;
        }
    }

    static boolean canDivide(int minScore) {
        int count = 0, sum = 0;

        for (int score : arr) {
            sum += score;

            if (sum >= minScore) {
                count++;
                sum = 0;
            }
        }

        return count >= K;
    }
}