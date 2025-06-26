import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int T;
    static int[] dx = {-1, 1, 0};
    static int[][] arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int t = 0; t < 10; t++) {
            T = Integer.parseInt(br.readLine());

            arr = new int[100][100];

            for (int i = 0; i < 100; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 100; j++) arr[i][j] = Integer.parseInt(st.nextToken());
            }

            int x = -1;

            for (int i = 0; i < 100; i++) {
                if (arr[99][i] == 2) {
                    x = i;
                    break;
                }
            }

            solve(98, x);
        }

        System.out.println(sb);
    }

    static void solve(int y, int x) {
        while (true) {
            // 양방향 확인
            for (int k = 0; k < 2; k++) {
                int nx = dx[k] + x;

                if (!inRange(nx) || arr[y][nx] != 1) continue;

                do {
                    nx += dx[k];
                } while (inRange(nx) && arr[y][nx] != 0);

                x = nx - dx[k];
                break;
            }

            y -= 1;

            if (y == 0) {
                sb.append("#").append(T).append(" ").append(x).append("\n");
                return;
            }
        }
    }

    static boolean inRange(int x) {
        return (0 <= x && x < 100);
    }
}