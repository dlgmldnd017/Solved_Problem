import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, gom[], arr[][], ans;
    static List<Integer> list = new ArrayList<>();
    static boolean visited[];

    public static void main(String[] args) throws Exception {
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

        ans = Integer.MAX_VALUE;

        int K = N <= 7 ? N : 7;

        for (int i = 2; i <= K; i++) {
            visited = new boolean[N];
            solve(0, i, 0);
        }

        System.out.println(ans);
    }

    static void solve(int depth, int K, int idx) {
        if (depth == K) {
            int sum[] = getSum();

            int min = 0;
            for (int i = 0; i < 3; i++) {
                sum[i] /= list.size();
                sum[i] = Math.abs(sum[i] - gom[i]);
                min += sum[i];
            }

            if (ans > min) ans = min;

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

    static int[] getSum() {
        int sum[] = new int[3];

        for (int i : list) {
            for (int j = 0; j < 3; j++) {
                sum[j] += arr[i][j];
            }
        }

        return sum;
    }
}