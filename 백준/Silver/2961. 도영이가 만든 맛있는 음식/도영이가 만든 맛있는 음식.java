import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, arr[][];
    static long ans;
    static List<Integer> list = new ArrayList<>();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++) arr[i][j] = Integer.parseInt(st.nextToken());
        }

        ans = Long.MAX_VALUE;


        solve(0);

        System.out.println(ans);
    }

    static void solve(int depth) {
        if (depth == N) {
            long diff = Integer.MAX_VALUE;

            if (list.size() != 0) diff = getDiff();
            if (ans > diff) ans = diff;
            return;
        }

        list.add(depth);
        solve(depth + 1);

        list.remove(list.size() - 1);
        solve(depth + 1);
    }

    static long getDiff() {
        long sum1 = 1, sum2 = 0;

        for (int i : list) {
            sum1 *= arr[i][0];
            sum2 += arr[i][1];
        }

        return Math.abs(sum1 - sum2);
    }
}