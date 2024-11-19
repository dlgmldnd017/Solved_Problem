import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    int x, cnt;

    Node(int x, int cnt) {
        this.x = x;
        this.cnt = cnt;
    }
}

public class Main {
    static int N, K, visited[], route[], ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        ans = Integer.MAX_VALUE;

        solve();
    }

    static void solve() {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(N, 0));

        visited = new int[100_001];
        Arrays.fill(visited, Integer.MAX_VALUE);
        visited[N] = 0;

        route = new int[100_001];

        while (!q.isEmpty()) {
            Node cur = q.poll();

            if (cur.x == K) {
                ans = cur.cnt;
                break;
            }

            int nextPos[] = {cur.x - 1, cur.x + 1, cur.x * 2};

            for (int next : nextPos) {
                if (next < 0 || next > 100_000 || visited[next] < cur.cnt + 1) continue;

                route[next] = cur.x;
                visited[next] = cur.cnt + 1;
                q.add(new Node(next, cur.cnt + 1));
            }
        }

        Stack<Integer> st = new Stack<>();
        int idx = K;

        while(idx != N) {
            st.add(idx);
            idx = route[idx];
        }

        st.add(N);

        StringBuilder sb = new StringBuilder();
        sb.append(ans + "\n");

        while (st.size() != 0) sb.append(st.pop() + " ");

        System.out.println(sb);
    }
}