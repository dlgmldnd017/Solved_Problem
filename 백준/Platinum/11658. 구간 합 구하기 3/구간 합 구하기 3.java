import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] arr, tree;

    static void update(int x, int y, int val) {
        while (x <= N) {
            for (int i = y; i <= N; i += i & -i) tree[x][i] += val;
            x += x & -x;
        }
    }

    static int sum(int x, int y) {
        int result = 0;
        while (x > 0) {
            for (int i = y; i > 0; i -= i & -i) result += tree[x][i];
            x -= x & -x;
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        arr = new int[N + 1][N + 1];
        tree = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                update(i, j, arr[i][j]);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            if (op == 1) {
                int x2 = Integer.parseInt(st.nextToken());
                int y2 = Integer.parseInt(st.nextToken());
                sb.append((sum(x2, y2) - sum(x2, y1 - 1) - sum(x1 - 1, y2) + sum(x1 - 1, y1 - 1)) + "\n");
            } else {
                int c = Integer.parseInt(st.nextToken());
                update(x1, y1, c - arr[x1][y1]);
                arr[x1][y1] = c;
            }
        }
        System.out.println(sb.toString());
    }
}