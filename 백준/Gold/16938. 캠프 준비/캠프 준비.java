import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, L, R, X, arr[], ans;
    static List<Integer> list = new ArrayList<>();
    static boolean visited[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr);

        visited = new boolean[N];

        solve(0);

        System.out.println(ans);
    }

    static void solve(int depth) {
        if (depth == N) {
            if (list.size() <= 1) return;

            int sum = 0;
            for (int i : list) sum += i;

            if (L > sum || sum > R) return;

            int diff = list.get(list.size() - 1) - list.get(0);

            if (diff >= X) ans++;
            return;
        }

        list.add(arr[depth]);
        solve(depth + 1);

        list.remove(list.size() - 1);
        solve(depth + 1);
    }
} 