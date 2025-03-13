import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, ans;
    static List<Node> list;

    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            list = new ArrayList<>();
            
            N = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());
            int startY = Integer.parseInt(st.nextToken());
            int startX = Integer.parseInt(st.nextToken());

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int y = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());

                list.add(new Node(y, x, 0));
            }

            st = new StringTokenizer(br.readLine());
            int endY = Integer.parseInt(st.nextToken());
            int endX = Integer.parseInt(st.nextToken());

            list.add(new Node(endY, endX, 0));

            solve(startY, startX, endY, endX);

            if (ans == 0) sb.append("sad").append("\n");
            else sb.append("happy").append("\n");

            ans = 0;
        }

        System.out.println(sb);
    }

    static void solve(int startY, int startX, int endY, int endX) {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(startY, startX, 20));

        boolean[] visited = new boolean[N + 1];

        while (!q.isEmpty()) {
            Node cur = q.poll();

            for (int i = 0; i < N + 1; i++) {
                if (visited[i]) continue;

                Node next = list.get(i);

                if (Math.abs(cur.y - next.y) + Math.abs(cur.x - next.x) > cur.bCnt * 50) continue;

                if (next.y == endY && next.x == endX) {
                    ans = 1;
                    return;
                }

                visited[i] = true;
                q.add(new Node(next.y, next.x, 20));
            }
        }
    }
}

class Node {
    int y, x, bCnt;

    Node(int y, int x, int bCnt) {
        this.y = y;
        this.x = x;
        this.bCnt = bCnt;
    }
}