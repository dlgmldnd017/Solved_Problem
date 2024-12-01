import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int K, arr[][], ans;
    static List<Integer> list[] = new ArrayList[5];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 1; i <= 4; i++) list[i] = new ArrayList<>();

        for (int i = 1; i <= 4; i++) {
            String input = br.readLine();

            for (int j = 0; j < 8; j++) list[i].add(input.charAt(j) - '0');
        }

        K = Integer.parseInt(br.readLine());

        arr = new int[K][2];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int rotation = Integer.parseInt(st.nextToken());

            arr[i][0] = idx;
            arr[i][1] = rotation;
        }

        solve();

        System.out.println(ans);
    }

    static void solve() {
        for (int i = 0; i < K; i++) {
            int idx = arr[i][0], rotation = arr[i][1];

            List<Node> roList = new ArrayList<>();
            roList.add(new Node(idx, rotation));

            switch (idx) {
                case 1:
                    for (int j = 1; j < 4; j++) {
                        rotation = rotation == 1 ? -1 : 1;
                        if (list[j].get(2) != list[j + 1].get(6)) roList.add(new Node(j + 1, rotation));
                        else break;
                    }
                    break;

                case 2, 3:
                    for (int j = idx; j < 4; j++) {
                        rotation = rotation == 1 ? -1 : 1;
                        if (list[j].get(2) != list[j + 1].get(6)) roList.add(new Node(j + 1, rotation));
                        else break;
                    }

                    rotation = arr[i][1] == 1 ? -1 : 1;
                    for (int j = idx; j > 1; j--) {
                        if (list[j].get(6) != list[j - 1].get(2)) roList.add(new Node(j - 1, rotation));
                        else break;
                        rotation = rotation == 1 ? -1 : 1;
                    }
                    break;

                case 4:
                    for (int j = idx; j > 1; j--) {
                        rotation = rotation == 1 ? -1 : 1;
                        if (list[j].get(6) != list[j - 1].get(2)) roList.add(new Node(j - 1, rotation));
                        else break;
                    }
                    break;
            }

            for (Node n : roList) rotate(n.idx, n.k);
        }

        for (int i = 1; i <= 4; i++) if (list[i].get(0) == 1) ans += 1 << (i - 1);

    }

    static void rotate(int idx, int rotation) {
        if (rotation == 1) {
            int tmp = list[idx].get(7);
            list[idx].remove(list[idx].size() - 1);
            list[idx].add(0, tmp);

        } else {
            int tmp = list[idx].get(0);
            list[idx].remove(0);
            list[idx].add(tmp);
        }
    }
}

class Node {
    int idx, k;

    Node(int idx, int k) {
        this.idx = idx;
        this.k = k;
    }
}