import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, x, y, K, arr[][], w[], h[];
    static List<Integer> list = new ArrayList<>();

    static StringBuilder sb = new StringBuilder();

    static int dy[] = {0, 0, 0, -1, 1};
    static int dx[] = {0, 1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) arr[i][j] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) list.add(Integer.parseInt(st.nextToken()));

        solve();

        System.out.println(sb);
    }

    static void solve() {
        w = new int[3];
        h = new int[4];

        for (int i : list) {
            x += dy[i];
            y += dx[i];

            if (!inRange(x, y)) {
                x -= dy[i];
                y -= dx[i];
                continue;
            }

            rotateDice(i);

            if (arr[x][y] != 0) {
                w[1] = h[1] = arr[x][y];
                arr[x][y] = 0;
            } else {
                arr[x][y] = w[1];
            }

            sb.append(h[3] + "\n");
        }
    }

    static void rotateDice(int dir) {
        int tmpH[] = h.clone();
        int tmpW[] = w.clone();

        switch (dir) {
            case 1:
                tmpH[1] = w[2];
                tmpH[3] = w[0];
                tmpW[0] = w[1];
                tmpW[1] = w[2];
                tmpW[2] = h[3];
                break;

            case 2:
                tmpH[1] = w[0];
                tmpH[3] = w[2];
                tmpW[0] = h[3];
                tmpW[1] = w[0];
                tmpW[2] = w[1];
                break;

            case 3:
                tmpW[1] = h[0];
                tmpH[0] = h[3];
                tmpH[1] = h[0];
                tmpH[2] = h[1];
                tmpH[3] = h[2];
                break;

            case 4:
                tmpW[1] = h[2];
                tmpH[0] = h[1];
                tmpH[1] = h[2];
                tmpH[2] = h[3];
                tmpH[3] = h[0];
                break;
        }

        h = tmpH.clone();
        w = tmpW.clone();
    }

    static boolean inRange(int x, int y) {
        return (x >= 0 && x < N) && (y >= 0 && y < M);
    }
}
