import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, arr[];
    static List<Integer> list = new ArrayList<>();
    static boolean visited[];

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        visited = new boolean[N];

        solve(0);
    }

    static void solve(int depth) {
        if (depth == N) {
            if (!check()) return;

            for (int i : list) sb.append(i + 1 + " ");
            System.out.println(sb);
            System.exit(0);
        }

        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;

            list.add(i);
            visited[i] = true;

            solve(depth + 1);

            visited[i] = false;
            list.remove(list.size() - 1);
        }
    }

    static boolean check() {
        for (int i = 0; i < N; i++) {
            int me = list.get(i);

            if (arr[me] > i) return false;

            int cnt = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (list.get(j) > me) cnt++;
            }

            if (cnt != arr[me]) return false;
        }

        return true;
    }
}
