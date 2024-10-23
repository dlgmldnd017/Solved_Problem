import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M, arr[];
    static List<Integer> list = new ArrayList<>();
    static boolean visited[];

    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        visited = new boolean[N];

        Arrays.sort(arr);

        solve(0);

        System.out.println(sb);
    }

    static void solve(int depth) {
        if (depth == M) {
            for (int i : list) sb.append(i + " ");
            sb.append("\n");
            return;
        }

        int prev = -1;
        for (int i = 0; i < N; i++) {
            if (visited[i] || prev == arr[i]) continue;

            list.add(arr[i]);
            visited[i] = true;
            solve(depth + 1);
            prev = arr[i];
            visited[i] = false;
            list.remove(list.size() - 1);
        }
    }
}