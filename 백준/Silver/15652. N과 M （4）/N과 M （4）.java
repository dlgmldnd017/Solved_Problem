import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static List<Integer> list = new ArrayList<>();

    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        solve(0, 1);

        System.out.println(sb);
    }

    static void solve(int depth, int idx) {
        if (depth == M) {
            for (int i : list) sb.append(i + " ");
            sb.append("\n");
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (list.size() > 0 && list.get(list.size() - 1) > i) continue;

            list.add(i);

            solve(depth + 1, i);

            list.remove(list.size() - 1);
        }
    }
}