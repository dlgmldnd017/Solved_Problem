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
    static List<Integer> list = new ArrayList<>();
    static boolean visited[];

    static List<Node> homes = new ArrayList<>();
    static List<Node> chickens = new ArrayList<>();

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

                if (tmp == 1) homes.add(new Node(i, j));
                else if (tmp == 2) chickens.add(new Node(i, j));
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
        int sumAll = 0;

        for (Node h : homes) {
            int min = Integer.MAX_VALUE;

            for (int i = 0; i < list.size(); i++) {
                int dis = Math.abs(h.y - chickens.get(list.get(i)).y) + Math.abs(h.x - chickens.get(list.get(i)).x);

                if (min > dis) min = dis;
            }
            sumAll += min;
        }

        return sumAll;
    }
}