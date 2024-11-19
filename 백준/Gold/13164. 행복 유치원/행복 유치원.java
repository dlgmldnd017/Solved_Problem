import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, K, arr[], ans;
    static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        if (K == 1) ans = arr[N - 1] - arr[0];
        else solve();

        System.out.println(ans);
    }

    static void solve() {
        for (int i = 1; i < N; i++) list.add(arr[i] - arr[i - 1]);

        Collections.sort(list);

        for (int i = 0; i < N - K; i++) ans += list.get(i);
    }
} 