import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, K, notebook[][], sticker[][][], stickerCnt[], ans;
    static boolean visited[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        notebook = new int[N][M];

        sticker = new int[K][][];

        stickerCnt = new int[K];

        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            sticker[k] = new int[n][m];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());

                for (int j = 0; j < m; j++) {
                    sticker[k][i][j] = Integer.parseInt(st.nextToken());

                    if (sticker[k][i][j] == 1) stickerCnt[k]++;
                }
            }
        }

        visited = new boolean[K];

        solve();

        System.out.println(ans);
    }

    static void solve() {
        for (int k = 0; k < K; k++) {
            if (visited[k]) continue;

            visited[k] = true;

            for (int rot = 0; rot < 4; rot++) {
                if (tryPlaceSticker(sticker[k])) break;
                sticker[k] = rotate90(sticker[k]);
            }
        }
    }

    static boolean tryPlaceSticker(int[][] stk) {
        int rows = stk.length;
        int cols = stk[0].length;

        for (int i = 0; i <= N - rows; i++) {
            for (int j = 0; j <= M - cols; j++) {
                if (canPlace(stk, i, j)) {
                    placeSticker(stk, i, j);
                    return true;
                }
            }
        }

        return false;
    }

    static boolean canPlace(int[][] stk, int y, int x) {
        for (int i = 0; i < stk.length; i++) {
            for (int j = 0; j < stk[0].length; j++) {
                if (stk[i][j] == 1 && notebook[y + i][x + j] == 1) {
                    return false;
                }
            }
        }

        return true;
    }

    static void placeSticker(int[][] stk, int y, int x) {
        for (int i = 0; i < stk.length; i++) {
            for (int j = 0; j < stk[0].length; j++) {
                if (stk[i][j] == 1) {
                    notebook[y + i][x + j] = 1;
                    ans++;
                }
            }
        }
    }

    static int[][] rotate90(int[][] original) {
        int rows = original.length;
        int cols = original[0].length;
        int[][] rotated = new int[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                rotated[j][rows - 1 - i] = original[i][j];
            }
        }
        return rotated;
    }
}