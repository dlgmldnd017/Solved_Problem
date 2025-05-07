import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Pool implements Comparable<Pool> {
    int start, end;

    Pool(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int compareTo(Pool p) {
        return this.start - p.start;
    }
}

public class Main {
    static int N, L, ans;
    static List<Pool> list = new ArrayList<>();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            list.add(new Pool(start, end));
        }

        Collections.sort(list);

        solve();

        System.out.println(ans);
    }

    static void solve() {
        int prev = -1;

        for (Pool p : list) {
            int start = p.start;
            int end = p.end;

            if (start < prev) {
                if (prev >= end) continue;
                start = prev;
            }

            int diff = end - start;

            if (diff % L == 0) {
                ans += diff / L;
                prev = end;
            } else {
                ans += diff / L + 1;
                prev = L * (diff / L) + L + start;
            }
        }
    }
}