import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, X;
    static long arr[];

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line;

        while ((line = br.readLine()) != null) {
            X = Integer.parseInt(line);
            X *= 10_000_000;

            N = Integer.parseInt(br.readLine());

            arr = new long[N];

            for (int i = 0; i < N; i++) arr[i] = Long.parseLong(br.readLine());

            Arrays.sort(arr);

            solve();
        }

        System.out.println(sb);
    }

    static void solve() {
        int low = 0, high = N - 1, minIdx = -1, maxIdx = -1;
        long max = Long.MIN_VALUE;

        while (low < high) {
            long sum = arr[low] + arr[high];

            if (X == sum) {
                long diff = Math.abs(arr[low] - arr[high]);

                if (max < diff) {
                    max = diff;
                    minIdx = low;
                    maxIdx = high;
                }
            }

            if (X > sum) low++;
            else high--;
        }

        if (max == Long.MIN_VALUE) sb.append("danger\n");
        else sb.append("yes " + arr[minIdx] + " " + arr[maxIdx] + "\n");
    }
}