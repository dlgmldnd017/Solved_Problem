import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, arr[][], ans;
    static List<Integer> list1 = new ArrayList<>();
    static List<Integer> list2 = new ArrayList<>();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ans = Integer.MAX_VALUE;

        solve(0);

        System.out.println(ans);
    }

    static void solve(int depth) {
        if (depth == N) {
            if (list1.size() != list2.size()) return;

            int sum1 = getSum(list1);
            int sum2 = getSum(list2);
            int diff = Math.abs(sum1 - sum2);

            if (ans > diff) ans = diff;

            return;
        }

        list1.add(depth);
        solve(depth + 1);
        list1.remove(list1.size() - 1);

        list2.add(depth);
        solve(depth + 1);
        list2.remove(list2.size() - 1);
    }

    static int getSum(List<Integer> list) {
        int sum = 0;

        for (int i = 0; i < list.size(); i++) {
            int a = list.get(i);

            for (int j = i + 1; j < list.size(); j++) {
                int b = list.get(j);

                sum += arr[a][b] + arr[b][a];
            }
        }

        return sum;
    }
}