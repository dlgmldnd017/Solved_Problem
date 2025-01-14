import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, arr[], ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) arr[i] = Integer.parseInt(st.nextToken());

        solve();

        System.out.println(ans);
    }

    static void solve() {
        int low = getLowHoney();
        int mid = getMidHoney();
        int high = getHighHoney();

        ans = Math.max(low, Math.max(mid, high));
    }

    static int getLowHoney() {
        int prefixSum[] = new int[N + 1];

        for (int i = N - 1; i >= 1; i--) prefixSum[i] = prefixSum[i + 1] + arr[i + 1];

        int max = Integer.MIN_VALUE;

        for (int i = 2; i <= N; i++) max = Math.max(max, prefixSum[1] - arr[i] + prefixSum[i]);

        return max;
    }

    static int getMidHoney() {
        int prefixSum[] = new int[N + 1];

        for (int i = 2; i <= N; i++) prefixSum[i] = prefixSum[i - 1] + arr[i - 1];

        int max = Integer.MIN_VALUE;

        for (int i = 1; i < N; i++) max = Math.max(max, prefixSum[N] - arr[i] + prefixSum[i]);

        return max;
    }

    static int getHighHoney() {
        int leftSum[] = new int[N + 1];
        int rightSum[] = new int[N + 1];

        for (int i = 2; i <= N; i++) leftSum[i] = leftSum[i - 1] + arr[i];

        for (int i = N - 1; i >= 1; i--) rightSum[i] = rightSum[i + 1] + arr[i];

        int max = Integer.MIN_VALUE;

        for (int i = 1; i <= N; i++) max = Math.max(max, leftSum[i] + rightSum[i]);

        return max;
    }
} 