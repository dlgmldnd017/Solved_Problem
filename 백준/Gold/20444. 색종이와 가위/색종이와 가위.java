import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static long N, K;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Long.parseLong(st.nextToken());
        K = Long.parseLong(st.nextToken());

        solve();

        System.out.println("NO");
    }

    static void solve() {
        long low = 0, high = N / 2;

        while (low <= high) {
            long row = (low + high) / 2;
            long col = N - row;

            long papers = (row + 1) * (col + 1);

            if (papers == K) {
                System.out.println("YES");
                System.exit(0);
            } else if (papers < K) low = row + 1;
            else high = row - 1;
        }
    }
} 