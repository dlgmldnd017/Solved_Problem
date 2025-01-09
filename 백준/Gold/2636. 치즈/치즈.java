import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, cheese[][], cnt;
    static boolean visited[][];

    static Stack<Integer> stack = new Stack<>();

    static int dy[] = {0, 0, -1, 1};
    static int dx[] = {-1, 1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        cheese = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                cheese[i][j] = Integer.parseInt(st.nextToken());

                if (cheese[i][j] == 1) cnt++;
            }
        }

        solve();

        System.out.println(stack.size() + "\n" + stack.pop());
    }

    static void solve() {
        int t = 1;
        while (true) {
            int removedCnt = getRemovedCnt();

            if (removedCnt == 0) return;

            stack.add(removedCnt);
        }
    }

    static int getRemovedCnt() {
        int removedCnt = 0;

        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(0, 0));

        visited = new boolean[N][M];
        visited[0][0] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            for (int k = 0; k < 4; k++) {
                int ny = cur.y + dy[k];
                int nx = cur.x + dx[k];

                if (!inRnage(ny, nx) || visited[ny][nx]) continue;

                if (cheese[ny][nx] == 0) q.add(new Node(ny, nx));
                else {
                    cheese[ny][nx] = 0;
                    removedCnt++;
                }

                visited[ny][nx] = true;
            }
        }
        return removedCnt;
    }

    static boolean inRnage(int y, int x) {
        return (y >= 0 && y < N) && (x >= 0 && x < M);
    }
}

class Node {
    int y, x;

    Node(int y, int x) {
        this.y = y;
        this.x = x;
    }
}