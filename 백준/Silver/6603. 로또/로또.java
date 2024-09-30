import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int K, A[];
    static List<Integer> list;
    static boolean visited[];

    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        do {
            st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());

            A = new int[K];

            for (int i = 0; i < K; i++) {
                A[i] = Integer.parseInt(st.nextToken());
            }

            list = new ArrayList<>();

            visited = new boolean[K];

            solve(0, 0);

            sb.append("\n");
        } while (K != 0);

        solve(0, 0);

        System.out.println(sb);
    }

    static void solve(int depth, int idx) {
        if (depth == 6) {
            for (int i : list) {
                sb.append(i + " ");
            }
            sb.append("\n");
            return;
        }

        for (int i = idx; i < K; i++) {
            if(visited[i]) continue;

            list.add(A[i]);
            visited[i] = true;

            solve(depth+1, i);

            visited[i] = false;
            list.remove(list.size()-1);
        }
    }
}

