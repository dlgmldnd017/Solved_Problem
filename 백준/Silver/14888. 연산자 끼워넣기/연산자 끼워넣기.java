import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, arr[], op[], max, min;
    static List<String> list = new ArrayList<>();
    static List<Integer> list2 = new ArrayList<>();
    static boolean visited[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        op = new int[4];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) op[i] = Integer.parseInt(st.nextToken());

        for (int i = 0; i < 4; i++) {
            int cnt = op[i];

            while (cnt != 0) {
                switch (i) {
                    case 0:
                        list.add("+");
                        break;
                    case 1:
                        list.add("-");
                        break;
                    case 2:
                        list.add("*");
                        break;
                    case 3:
                        list.add("/");
                        break;
                }

                cnt--;
            }
        }

        visited = new boolean[list.size()];

        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;

        solve(0);

        System.out.println(max + "\n" + min);
    }

    static void solve(int depth) {
        if (depth == list.size()) {
            int sum = getSum();

            if(max < sum) max = sum;
            if(min > sum) min = sum;

            return;
        }

        for (int i = 0; i < list.size(); i++) {
            if (visited[i]) continue;

            list2.add(i);
            visited[i] = true;
            solve(depth + 1);
            visited[i] = false;
            list2.remove(list2.size() - 1);
        }
    }

    static int getSum() {
        int sum = arr[0];

        for (int i = 0; i < list2.size(); i++) {
            String operation = list.get(list2.get(i));

            switch (operation) {
                case "+":
                    sum += arr[i + 1];
                    break;
                case "-":
                    sum -= arr[i + 1];
                    break;
                case "*":
                    sum *= arr[i + 1];
                    break;
                case "/":
                    sum /= arr[i + 1];
                    break;
            }
        }

        return sum;
    }
}