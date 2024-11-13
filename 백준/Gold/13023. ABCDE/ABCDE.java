import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static List<Integer> list[];
    static boolean visited[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new ArrayList[N];

        for (int i = 0; i < N; i++) list[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
        }

        for (int i = 0; i < N; i++) {
            visited = new boolean[N];

            visited[i] = true;
            solve(i, 1);
        }

        System.out.println(0);
    }

    static void solve(int start, int cnt) {
        if (cnt == 5) {
            System.out.println(1);
            System.exit(0);
        }

        for (int next : list[start]) {
            if (visited[next]) continue;
            visited[next] = true;
            solve(next, cnt + 1);
            visited[next] = false;
        }
    }
}