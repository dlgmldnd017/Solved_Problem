import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static List<Long> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        if (N <= 10) System.out.println(N);
        else if(N > 1022) System.out.println(-1);
        else {
            for (int i = 0; i <= 9; i++) solve(i);

            Collections.sort(list);

            System.out.println(list.get(N));
        }
    }

    static void solve(long num) {
        list.add(num);

        for (int i = 0; i < num % 10; i++) solve((num * 10) + i);
    }
}