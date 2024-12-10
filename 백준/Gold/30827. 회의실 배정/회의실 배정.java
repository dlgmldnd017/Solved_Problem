import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, K, arr[], ans;
    static List<Node> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            list.add(new Node(s, e));
        }

        arr = new int[K];

        Collections.sort(list);

        solve();

        System.out.println(ans);
    }

    static void solve() {
        List<Integer> rooms = new ArrayList<>();

        for (Node n : list) {
            boolean check = false;

            rooms.sort(Comparator.reverseOrder());

            for (int i = 0; i < rooms.size(); i++) {
                if (rooms.get(i) < n.s) {
                    rooms.set(i, n.e);
                    ans++;
                    check = true;
                    break;
                }
            }

            if (!check && rooms.size() < K) {
                rooms.add(n.e);
                ans++;
            }
        }
    }
}

class Node implements Comparable<Node> {
    int s, e;

    Node(int s, int e) {
        this.s = s;
        this.e = e;
    }

    public int compareTo(Node n) {
        return this.e - n.e;
    }
}