import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, arr[], min, max;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr);

        solve();

        System.out.println(arr[min] + " " + arr[max]);
    }

    static void solve() {
        int low = 0, high = N - 1;

        long ans = Long.MAX_VALUE;

        while (low < high) {
            long sum = Math.abs(arr[low] + arr[high]);

            if (ans > sum) {
                ans = sum;
                min = low;
                max = high;

                if (sum == 0) return;
            }

            if (arr[low] + arr[high] < 0) low++;
            else high--;
        }
    }
}