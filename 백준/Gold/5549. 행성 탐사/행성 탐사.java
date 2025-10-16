import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static int[][] jungle, ocean, ice;

    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        K = Integer.parseInt(br.readLine());

        jungle = new int[N + 1][M + 1];
        ocean = new int[N + 1][M + 1];
        ice = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            String input = br.readLine();

            for (int j = 1; j <= M; j++) {
                char c = input.charAt(j - 1);

                switch (c) {
                    case 'J':
                        jungle[i][j] = jungle[i - 1][j] + jungle[i][j - 1] - jungle[i - 1][j - 1] + 1;
                        ocean[i][j] = ocean[i - 1][j] + ocean[i][j - 1] - ocean[i - 1][j - 1];
                        ice[i][j] = ice[i - 1][j] + ice[i][j - 1] - ice[i - 1][j - 1];
                        break;

                    case 'O':
                        ocean[i][j] = ocean[i - 1][j] + ocean[i][j - 1] - ocean[i - 1][j - 1] + 1;
                        jungle[i][j] = jungle[i - 1][j] + jungle[i][j - 1] - jungle[i - 1][j - 1];
                        ice[i][j] = ice[i - 1][j] + ice[i][j - 1] - ice[i - 1][j - 1];
                        break;

                    default:
                        ice[i][j] = ice[i - 1][j] + ice[i][j - 1] - ice[i - 1][j - 1] + 1;
                        jungle[i][j] = jungle[i - 1][j] + jungle[i][j - 1] - jungle[i - 1][j - 1];
                        ocean[i][j] = ocean[i - 1][j] + ocean[i][j - 1] - ocean[i - 1][j - 1];
                }
            }
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            int J = jungle[c][d] - jungle[a - 1][d] - jungle[c][b - 1] + jungle[a - 1][b - 1];
            int O = ocean[c][d] - ocean[a - 1][d] - ocean[c][b - 1] + ocean[a - 1][b - 1];
            int I = ice[c][d] - ice[a - 1][d] - ice[c][b - 1] + ice[a - 1][b - 1];

            sb.append(J).append(" ").append(O).append(" ").append(I).append("\n");
        }

        System.out.println(sb);
    }
}