import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, ans;
    static boolean[] visited;
    static boolean[][] arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new boolean[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) arr[i][j] = st.nextToken().charAt(0) == '0';
        }

        solve();

        System.out.println(ans + "\n" + sb);
    }

    static void solve() {
        visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            if (visited[i]) continue;
            visited[i] = true;

            List<Integer> list = new ArrayList<>();
            list.add(i);

            for (int j = 1; j <= N; j++) {
                if (i == j || !arr[i][j]) continue;

                if (visited[j]) {
                    System.out.println(0);
                    System.exit(0);
                }

                list.add(j);
                visited[j] = true;
            }

            if (list.size() == 1) {
                System.out.println(0);
                System.exit(0);
            }

            for (int a : list) {
                for (int b : list) {
                    if (!arr[a][b]) {
                        System.out.println(0);
                        System.exit(0);
                    }
                }
            }

            ans++;

            for (int a : list) sb.append(a).append(' ');
            sb.append("\n");
        }
    }
}