import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Node {
    int y, x;

    Node(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

public class Main {
    static int N, M, arr[][], ans;
    static List<Node> chickens = new ArrayList<>();
    static List<Node> homes = new ArrayList<>();
    static List<Integer> list = new ArrayList<>();
    static boolean visited[];

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                arr[i][j] = tmp;

                if (tmp == 2) chickens.add(new Node(i, j));
                else if (tmp == 1) homes.add(new Node(i, j));
            }
        }

        visited = new boolean[chickens.size()];

        ans = Integer.MAX_VALUE;

        solve(0, 0);

        System.out.println(ans);
    }

    static void solve(int depth, int idx) {
        if (depth == M) {
            int sum = getSum();
            if (ans > sum) ans = sum;
            return;
        }

        for (int i = idx; i < chickens.size(); i++) {
            if (visited[i]) continue;

            list.add(i);
            visited[i] = true;
            solve(depth + 1, i);
            visited[i] = false;
            list.remove(list.size() - 1);
        }
    }

    static int getSum() {
        int len = 0;

        for (Node home : homes) {
            int sum = Integer.MAX_VALUE;

            for (int i = 0; i < chickens.size(); i++) {
                if (!visited[i]) continue;

                Node chicken = chickens.get(i);
                int dis = Math.abs(home.y - chicken.y) + Math.abs(home.x - chicken.x);

                if (sum > dis) sum = dis;
            }

            len += sum;
        }

        return len;
    }
}