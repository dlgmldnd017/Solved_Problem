import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, arr[], cnt[];
    static String ops[] = {"+", "-", "*", "/"};
    static long max, min;
    static List<Integer> list = new ArrayList<>();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        cnt = new int[4];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) cnt[i] = Integer.parseInt(st.nextToken());

        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;

        solve(0);

        System.out.println(max + "\n" + min);
    }

    static void solve(int depth) {
        if (depth == N-1) {
            long sum = getSum();

            if (max < sum) max = sum;
            if (min > sum) min = sum;
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (cnt[i] == 0) continue;

            cnt[i]--;
            list.add(i);

            solve(depth + 1);

            cnt[i]++;
            list.remove(list.size() - 1);
        }
    }

    static long getSum() {
        long sum = arr[0];

        for (int i = 1; i < N; i++) {
            switch (ops[list.get(i - 1)]) {
                case "+":
                    sum += arr[i];
                    break;
                case "-":
                    sum -= arr[i];
                    break;
                case "*":
                    sum *= arr[i];
                    break;
                case "/":
                    if (arr[i] < 0) {
                        sum /= -(arr[i]);
                        sum = -sum;
                    } else {
                        sum /= arr[i];
                    }
                    break;
            }
        }
        return sum;
    }
}