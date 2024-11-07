import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, B, map[][], min, max, time, height;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        min = 256;
        max = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (max < map[i][j]) max = map[i][j];
                if (min > map[i][j]) min = map[i][j];
            }
        }

        time = Integer.MAX_VALUE;

        solve();

        System.out.println(time + " " + height);
    }

    static void solve() {
        for (int k = min; k <= max; k++) {
            int cnt = 0, block = B;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (k < map[i][j]) {
                        cnt += (map[i][j] - k) * 2;
                        block += map[i][j] - k;
                    } else {
                        cnt += k - map[i][j];
                        block -= k - map[i][j];
                    }
                }
            }

            if (block < 0) return;

            if (time >= cnt) {
                time = cnt;
                height = k;
            }
        }
    }
}