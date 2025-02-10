import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, arr[], ans;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];

        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);
        }

        solve(min, max);

        System.out.println(ans);
    }

    static void solve(int min, int max) {
        int left = 0, right = max - min;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (canDiv(mid)) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
    }

    static boolean canDiv(int diff) {
        int min = arr[0], max = arr[0], cnt = 1;

        for (int i = 1; i < N; i++) {
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);

            if (max - min > diff) {
                cnt++;
                min = arr[i];
                max = arr[i];
            }
        }

        return cnt <= M;
    }
}