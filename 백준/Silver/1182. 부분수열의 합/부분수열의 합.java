import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, S, arr[], ans;
    static List<Integer> list = new ArrayList<>();
    static boolean visited[];

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        visited = new boolean[N];

        solve(0, 0, 0);

        System.out.println(ans);
    }

    static void solve(int depth, int idx, int sum) {
        if (depth == N) {
            if (list.size() != N && sum == S) {
                ans++;
                return;
            }
        }

        if (depth > 0 && sum == S) ans++;

        for (int i = idx; i < N; i++) {
            if (visited[i]) continue;

            list.add(i);
            visited[i] = true;

            solve(depth + 1, i, sum + arr[i]);

            visited[i] = false;
            list.remove(list.size() - 1);
        }
    }
}