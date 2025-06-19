import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, L, R, X, ans;
    static int[] arr;
    static List<Integer> list = new ArrayList<>();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        solve();

        System.out.println(ans);
    }

    static void solve() {
        Arrays.sort(arr);

        dfs(0, 0);
    }

    static void dfs(int depth, int sum) {
        if (sum > R) return;

        if (depth == N) {
            if (L <= sum && list.get(list.size() - 1) - list.get(0) >= X) ans++;
            return;
        }

        list.add(arr[depth]);
        dfs(depth + 1, arr[depth] + sum);
        list.remove(list.size() - 1);

        dfs(depth + 1, sum);
    }
}