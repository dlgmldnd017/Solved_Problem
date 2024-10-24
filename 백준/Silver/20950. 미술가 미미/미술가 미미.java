import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, arr[][], gom[], ans;
    static List<Integer> list = new ArrayList<>();
    static boolean visited[];

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N][3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) arr[i][j] = Integer.parseInt(st.nextToken());
        }

        gom = new int[3];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 3; i++) gom[i] = Integer.parseInt(st.nextToken());

        int k = N >= 7 ? 7 : N;

        ans = Integer.MAX_VALUE;

        for (int i = 2; i <= k; i++) {
            visited = new boolean[N];
            solve(0, i, 0);
        }

        System.out.println(ans);
    }

    static void solve(int depth, int K, int idx) {
        if (depth == K) {
            int rgb[] = getRGB();

            int diffSum = 0;
            for (int j = 0; j < 3; j++) diffSum += Math.abs(rgb[j] - gom[j]);

            if (ans > diffSum) ans = diffSum;
            return;
        }

        for (int i = idx; i < N; i++) {
            if (visited[i]) continue;

            list.add(i);
            visited[i] = true;
            solve(depth + 1, K, i);
            visited[i] = false;
            list.remove(list.size() - 1);
        }
    }

    static int[] getRGB() {
        int rgb[] = new int[3];
        int r = 0, g = 0, b = 0;

        for (int i : list) {
            r += arr[i][0];
            g += arr[i][1];
            b += arr[i][2];
        }

        rgb[0] = r / list.size();
        rgb[1] = g / list.size();
        rgb[2] = b / list.size();

        return rgb;
    }
}