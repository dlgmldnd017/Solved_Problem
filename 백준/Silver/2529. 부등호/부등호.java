import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int K;
    static String arr[], map[], max, min;
    static List<String> list = new ArrayList<>();
    static boolean vistied[] = new boolean[10];

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        K = Integer.parseInt(br.readLine());

        map = new String[K];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) map[i] = st.nextToken();

        arr = new String[10];

        for (int i = 0; i < 10; i++) arr[i] = i + "";

        max = "-9999999999";
        min = "9999999999";

        solve(0);

        System.out.println(max + "\n" + min);
    }

    static void solve(int depth) {
        if (depth == K + 1) {
            if (!check()) return;

            String value = "";
            for (String str : list) value += str;

            long num = Long.parseLong(value);
            long MAX = Long.parseLong(max);
            long MIN = Long.parseLong(min);

            if (MAX < num) max = value;
            if (MIN > num) min = value;

            return;
        }

        for (int i = 0; i < 10; i++) {
            if (vistied[i]) continue;

            list.add(i + "");
            vistied[i] = true;
            solve(depth + 1);
            vistied[i] = false;
            list.remove(list.size() - 1);
        }
    }

    static boolean check() {
        for (int i = 0; i < map.length; i++) {
            int x1 = Integer.parseInt(list.get(i));
            int x2 = Integer.parseInt(list.get(i + 1));

            if (map[i].equals("<")) {
                if (x1 > x2) return false;
            } else {
                if (x1 < x2) return false;
            }
        }

        return true;
    }
}