import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    static int N;
    static int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
    static long ans;
    static List<Long> list = new ArrayList<>();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        solve();

        System.out.println(ans);
    }

    static void solve() {
        dfs(0, 0);

        Collections.sort(list);

        if (N > list.size()) ans = -1;
        else ans = list.get(N - 1);
    }

    private static void dfs(long num, int i) {
        if (!list.contains(num)) list.add(num);

        if (i >= 10) return;

        dfs((num * 10) + arr[i], i + 1);

        dfs(num, i + 1);
    }
}