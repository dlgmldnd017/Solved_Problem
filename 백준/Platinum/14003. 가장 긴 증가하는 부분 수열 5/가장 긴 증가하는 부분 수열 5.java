import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, arr[];
    static List<Integer> lis = new ArrayList<>();

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        solve();

        System.out.println(sb);
    }

    static void solve() {
        int[] pos = new int[N];

        for (int i = 0; i < N; i++) {
            int num = arr[i];

            int posIdx = Collections.binarySearch(lis, num);
            if (posIdx < 0) posIdx = -(posIdx + 1);

            if (posIdx < lis.size()) lis.set(posIdx, num);
            else lis.add(num);

            pos[i] = posIdx;
        }

        sb.append(lis.size() + "\n");

        int currentPos = lis.size() - 1;

        lis = new ArrayList<>();
        for (int i = N - 1; i >= 0; i--) {
            if (pos[i] == currentPos) {
                lis.add(arr[i]);
                currentPos--;
            }
        }

        Collections.reverse(lis);

        for (int i : lis) sb.append(i + " ");
    }
}
