import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, K, arr[], ans;
    static List<Integer> list = new ArrayList<>();
    static boolean visited[];

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        visited = new boolean[N];

        solve(0, 500);

        System.out.println(ans);
    }

    static void solve(int depth, int weight) {
        if (weight < 500) return;

        if (depth == N) {
            ans++;
            return;
        }

        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;

            visited[i] = true;
            solve(depth + 1, weight + arr[i] - K);
            visited[i] = false;
        }
    }
}