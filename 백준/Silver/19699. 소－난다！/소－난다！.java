import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    static int N, M, arr[];
    static List<Integer> list = new ArrayList<>();
    static TreeSet<Integer> set = new TreeSet<>();
    static boolean visited[];

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

        solve(0, 0);

        if (set.size() == 0) System.out.println(-1);
        else for (int i : set) System.out.print(i + " ");
    }

    static void solve(int depth, int idx) {
        if (depth == M) {
            int sum = 0;
            for (int i : list) sum += i;

            if (sum == 2) {
                if (set.contains(sum)) return;
                set.add(sum);
            } else if (sum < 2) return;
            else if (check(sum)) {
                if (set.contains(sum)) return;
                set.add(sum);
            }
            return;
        }

        for (int i = idx; i < N; i++) {
            if (visited[i]) continue;

            list.add(arr[i]);
            visited[i] = true;
            solve(depth + 1, i);
            visited[i] = false;
            list.remove(list.size() - 1);
        }
    }

    static boolean check(int sum) {
        for (int i = 2; i < sum; i++) {
            if (sum % i == 0) return false;
        }
        return true;
    }
}