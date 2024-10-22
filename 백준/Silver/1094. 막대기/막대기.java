import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static int X;
    static List<Integer> list = new ArrayList<>();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        X = Integer.parseInt(br.readLine());

        solve(0, 0);
    }

    static void solve(int depth, int cnt) {
        if (depth == 7) {
            int sum = getSum();
            if(sum==X) {
                System.out.println(cnt);
                System.exit(0);
            }
            return;
        }

        list.add(0);
        solve(depth + 1, cnt);
        list.remove(list.size() - 1);

        list.add(1);
        solve(depth + 1, cnt + 1);
        list.remove(list.size() - 1);
    }

    static int getSum() {
        int sum = 0;
        for (int i = 6; i >= 0; i--) if (list.get(i) == 1) sum += 1 << i;

        return sum;
    }
}