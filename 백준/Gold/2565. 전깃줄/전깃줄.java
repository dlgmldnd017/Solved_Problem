import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, arr[][], dp[], ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        solve();

        System.out.println(ans);
    }

    static void solve() {
        Arrays.sort(arr, Comparator.comparing(a -> a[0]));

        List<Integer> lis = new ArrayList<>();
        int[] pos = new int[N];

        for (int i = 0; i < N; i++) {
            int num = arr[i][1];
            int posIdx = Collections.binarySearch(lis, num);
            if (posIdx < 0) posIdx = -(posIdx + 1);

            if (posIdx < lis.size()) lis.set(posIdx, num);
            else lis.add(num);


            pos[i] = posIdx;
        }

        ans = N - lis.size();
    }
}