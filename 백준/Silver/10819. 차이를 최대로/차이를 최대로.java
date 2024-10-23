import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, arr[], ans;
    static List<Integer> list = new ArrayList<>();
    static boolean visited[];

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr);

        visited = new boolean[N];

        ans = Integer.MIN_VALUE;

        solve(0);

        System.out.println(ans);
    }

    static void solve(int depth) {
        if (depth == N) {
            int max = 0;
            for (int i = 0; i < N - 1; i++) max += Math.abs(list.get(i) - list.get(i + 1));
            if (ans < max) ans = max;
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