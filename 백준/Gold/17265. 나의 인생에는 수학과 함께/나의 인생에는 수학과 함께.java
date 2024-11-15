import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, min, max;
    static char arr[][];

    static int dy[] = {0, 1};
    static int dx[] = {1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new char[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) arr[i][j] = st.nextToken().charAt(0);
        }

        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;

        solve(0, 0, arr[0][0] + "");

        System.out.println(max + " " + min);
    }

    static void solve(int y, int x, String str) {
        if (y == N - 1 && x == N - 1) {
            int sum = getSum(str);

            if (min > sum) min = sum;
            if (max < sum) max = sum;
            return;
        }

        if (inRange(y + 1, x)) solve(y + 1, x, str + arr[y + 1][x]);
        if (inRange(y, x + 1)) solve(y, x + 1, str + arr[y][x + 1]);
    }

    static int getSum(String str) {
        int sum = str.charAt(0) - '0';

        for (int i = 1; i < str.length() - 1; i += 2) {
            char op = str.charAt(i);

            switch (op) {
                case '+':
                    sum += (str.charAt(i + 1) - '0');
                    break;

                case '-':
                    sum -= (str.charAt(i + 1) - '0');
                    break;

                case '*':
                    sum *= (str.charAt(i + 1) - '0');
                    break;
            }
        }
        return sum;
    }

    static boolean inRange(int y, int x) {
        return (y >= 0 && y < N) && (x >= 0 && x < N);
    }
}