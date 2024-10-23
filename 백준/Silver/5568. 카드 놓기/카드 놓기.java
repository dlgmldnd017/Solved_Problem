import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    static int N, K, arr[], ans;
    static Set<String> set = new HashSet<>();
    static List<Integer> list = new ArrayList<>();
    static boolean visited[];

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        arr = new int[N];
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(br.readLine());

        visited = new boolean[N];

        solve(0);

        System.out.println(ans);
    }

    static void solve(int depth) {
        if (depth == K) {
            String str = "";
            for (int i : list) str += i + "";
            if (set.contains(str)) return;

            set.add(str);
            ans++;
            return;
        }

        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;

            list.add(arr[i]);
            visited[i] = true;
            solve(depth + 1);
            visited[i] = false;
            list.remove(list.size() - 1);
        }
    }
}