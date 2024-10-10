import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, H, down[], up[], min, cnt;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        down = new int[N / 2];
        up = new int[N / 2];

        for (int i = 0; i < N; i++) {
            if (i % 2 == 0) down[i / 2] = Integer.parseInt(br.readLine());
            else up[i / 2] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(down);
        Arrays.sort(up);

        min = N;

        solve();

        System.out.println(min + " " + cnt);
    }

    static void solve() {
        for (int i = 1; i < H + 1; i++) {
            int start = getObstaclesCnt(down, i);
            int end = getObstaclesCnt(up, H - i + 1);

            if (min > start + end) {
                min = start + end;
                cnt = 1;
            } else if (min == start + end) {
                cnt++;
            }
        }
    }

    static int getObstaclesCnt(int arr[], int h) {
        int low = 0, high = N / 2;

        while (low < high) {
            int mid = (low + high) / 2;

            if (arr[mid] >= h) high = mid;
            else low = mid + 1;
        }

        return arr.length - high;
    }
}