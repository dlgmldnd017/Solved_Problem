import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int G, ans;
    static List<Long> list = new ArrayList<>();

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        G = Integer.parseInt(br.readLine());

        solve();

        if (list.isEmpty()) sb.append("-1");
        else print();

        System.out.println(sb);
    }

    static void solve() {
        long i = 2, j = 1;

        while (i < 100_000) {
            long ii = i * i, jj = j * j;

            if (ii - jj == G) list.add(i);

            if (ii - jj > G) j++;
            else i++;
        }
    }

    static void print() {
        Collections.sort(list);

        for (long i : list) sb.append(i + "\n");
    }
}