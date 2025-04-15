import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

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
        if (depth == N) {
            if (list.size() > 1 && L <= sum && sum <= R) {
                int min = list.get(0), max = list.get(list.size() - 1);

                if (max - min >= X) ans++;
            }
            return;
        }

        list.add(arr[depth]);
        dfs(depth + 1, sum + arr[depth]);

        list.remove(list.size() - 1);
        dfs(depth + 1, sum);
    }
}