import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, K, arr[][], ans;
    static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) arr[i][j] = Integer.parseInt(st.nextToken());
        }

        ans = Integer.MIN_VALUE;

        if (K == 1) ans = 0;
        else solve(0);

        System.out.println(ans);
    }

    static void solve(int depth) {
        if (depth == N) {
            if (list.size() != K) return;

            int sum = 0;

            for (int i = 0; i < K; i++) {
                int a = list.get(i);

                for (int j = i + 1; j < K; j++) {
                    int b = list.get(j);
                    sum += arr[a][b];
                }
            }

            if (ans < sum) ans = sum;

            return;
        }

        list.add(depth);
        solve(depth + 1);

        list.remove(list.size() - 1);
        solve(depth + 1);
    }
}