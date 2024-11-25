import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, ans;
    static List<Integer> crane = new ArrayList<>();
    static List<Integer> box = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) crane.add(Integer.parseInt(st.nextToken()));

        Collections.sort(crane);
        Collections.reverse(crane);

        M = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) box.add(Integer.parseInt(st.nextToken()));

        Collections.sort(box);
        Collections.reverse(box);

        if (crane.get(0) < box.get(0)) ans = -1;
        else solve();

        System.out.println(ans);
    }

    static void solve() {
        while (!box.isEmpty()) {
            int i = 0, j = 0;

            while (i < N) {
                if (j == box.size()) break;
                else if (crane.get(i) >= box.get(j)) {
                    box.remove(j);
                    i++;
                } else j++;
            }

            ans++;
        }
    }
}


