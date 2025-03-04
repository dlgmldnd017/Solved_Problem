import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[][] prefixJ, prefixO, prefixI;
    static char[][] map;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        K = Integer.parseInt(br.readLine());

        prefixJ = new int[N + 1][M + 1];
        prefixO = new int[N + 1][M + 1];
        prefixI = new int[N + 1][M + 1];

        map = new char[N + 1][M + 1];

        for (int i = 1; i < N + 1; i++) {
            String input = br.readLine();

            for (int j = 1; j < M + 1; j++) {
                map[i][j] = input.charAt(j - 1);

                prefixJ[i][j] = prefixJ[i - 1][j] + prefixJ[i][j - 1] - prefixJ[i - 1][j - 1];
                prefixO[i][j] = prefixO[i - 1][j] + prefixO[i][j - 1] - prefixO[i - 1][j - 1];
                prefixI[i][j] = prefixI[i - 1][j] + prefixI[i][j - 1] - prefixI[i - 1][j - 1];

                if (map[i][j] == 'J') prefixJ[i][j]++;
                else if (map[i][j] == 'O') prefixO[i][j]++;
                else prefixI[i][j]++;
            }
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int y1 = Integer.parseInt(st.nextToken());
            int x1 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());

            int cntJ = getSum(prefixJ, y1, x1, y2, x2);
            int cntO = getSum(prefixO, y1, x1, y2, x2);
            int cntI = getSum(prefixI, y1, x1, y2, x2);

            sb.append(cntJ + " " + cntO + " " + cntI + "\n");
        }

        System.out.println(sb);
    }

    static int getSum(int[][] prefixSum, int y1, int x1, int y2, int x2) {
        return prefixSum[y2][x2] - prefixSum[y2][x1 - 1] - prefixSum[y1 - 1][x2] + prefixSum[y1 - 1][x1 - 1];
    }
}