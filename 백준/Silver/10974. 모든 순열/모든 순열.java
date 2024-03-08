import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static List<Integer> list;
    static boolean isSelected[];

    static BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(sc.readLine());

        list = new ArrayList<>();
        isSelected = new boolean[N];

        solve(0);
    }

    public static void solve(int depth) {
        if (depth == N) {
            for(int j=0; j<N; j++){
                System.out.print(list.get(j)+" ");
            }
            System.out.println();
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!isSelected[i]) {
                isSelected[i] = true;
                list.add(i+1);
                solve(depth + 1);
                list.remove(list.size()-1);
                isSelected[i] = false;
            }
        }
    }
}