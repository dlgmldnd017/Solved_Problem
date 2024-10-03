import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, ans;
    static boolean visited[][];

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        visited = new boolean[100][100];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            solve(y, x);
        }

        System.out.println(ans);
    }

    static void solve(int y, int x) {
        for (int i = y; i < y + 10; i++) {
            for (int j = x; j < x + 10; j++) {
                if(visited[i][j]) continue;
                visited[i][j] = true;
                ans++;
            }
        }
    }
}