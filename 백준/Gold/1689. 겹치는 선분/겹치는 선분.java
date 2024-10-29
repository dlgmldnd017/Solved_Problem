import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Point implements Comparable<Point> {
    int x, v;

    Point(int x, int v) {
        this.x = x;
        this.v = v;
    }

    public int compareTo(Point n) {
        if (this.x == n.x) return this.v - n.v;
        return this.x - n.x;
    }
}

public class Main {
    static int N, ans;
    static List<Point> list = new ArrayList<>();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            list.add(new Point(x1, +1));
            list.add(new Point(x2, -1));
        }

        Collections.sort(list);

        ans = Integer.MIN_VALUE;

        solve();

        System.out.println(ans);
    }

    static void solve() {
        int cnt = 0;

        for (Point p : list) {
            if (p.v == 1) {
                cnt++;
                if (ans < cnt) ans = cnt;
            } else cnt--;
        }
    }
}