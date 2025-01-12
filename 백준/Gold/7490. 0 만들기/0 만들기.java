import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int T, N;
    static char op[];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());

            op = new char[N - 1];

            solve(0);

            sb.append("\n");
        }

        System.out.println(sb);
    }

    static void solve(int depth) {
        if (depth == N - 1) {
            if (isZero()) {
                for (int i = 1; i < N; i++) sb.append(i + "" + op[i - 1]);
                sb.append(N + "\n");
            }
            return;
        }

        op[depth] = ' ';
        solve(depth + 1);

        op[depth] = '+';
        solve(depth + 1);

        op[depth] = '-';
        solve(depth + 1);
    }

    static boolean isZero() {
        List<Integer> list = new ArrayList<>();
        int i = 0;

        while (i <= N - 1) {
            int num = i + 1;

            while (i < N - 1 && op[i] == ' ') {
                num *= 10;
                num += ++i + 1;
            }

            list.add(num);
            i++;
        }

        if (list.size() == 1) return false;

        int sum = list.get(0), j = 1;

        for (i = 0; i < N - 1; i++) {
            if (op[i] == ' ') continue;
            else if (op[i] == '+') sum += list.get(j++);
            else sum -= list.get(j++);
        }

        return sum == 0;
    }
}