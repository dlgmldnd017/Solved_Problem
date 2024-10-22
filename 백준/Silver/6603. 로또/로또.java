import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int K, arr[];
    static boolean visited[];
    static List<Integer> list;

    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());

            if (K == 0) break;

            arr = new int[K];

            for (int i = 0; i < K; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            visited = new boolean[K];

            list = new ArrayList<>();

            solve(0, 0);

            sb.append("\n");
        }

        System.out.println(sb);
    }

    static void solve(int depth, int idx) {
        if (depth == 6) {
            for (int i = 0; i < 6; i++) sb.append(list.get(i) + " ");
            sb.append("\n");
            return;
        }

        for (int i = idx; i < K; i++) {
            if (visited[i]) continue;

            list.add(arr[i]);
            visited[i] = true;

            solve(depth + 1, i);

            visited[i] = false;
            list.remove(list.size() - 1);
        }
    }
}