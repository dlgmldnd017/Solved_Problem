import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, arr[][], stuOrder[], ans;
    static List<Integer> list[];
    static Map<Integer, Node> map = new HashMap<>();

    static int dy[] = {0, 0, -1, 1};
    static int dx[] = {-1, 1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        list = new List[N * N];

        for (int i = 0; i < N * N; i++) list[i] = new ArrayList<>();

        stuOrder = new int[N * N];

        for (int i = 0; i < N * N; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken()) - 1;
            stuOrder[i] = num;

            for (int j = 0; j < 4; j++) list[num].add(Integer.parseInt(st.nextToken()) - 1);
        }

        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) arr[i][j] = -1;
        }

        solve();

        System.out.println(ans);
    }

    static void solve() {
        for (int i = 0; i < N * N; i++) {
            int stuNum = stuOrder[i];

            check(stuNum);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int cnt = 0;

                for (int k = 0; k < 4; k++) {
                    int ny = dy[k] + i;
                    int nx = dx[k] + j;

                    if (!inRange(ny, nx)) continue;

                    for (int next : list[arr[i][j]]) {
                        if (next != arr[ny][nx]) continue;

                        cnt++;
                        break;
                    }
                }

                switch (cnt) {
                    case 2:
                        ans += 10;
                        break;
                    case 3:
                        ans += 100;
                        break;
                    case 4:
                        ans += 1000;
                        break;
                    default:
                        ans += cnt;
                }
            }
        }
    }

    static void check(int stuNum) {
        int cnt[][] = new int[N][N];

        for (int likeNum : list[stuNum]) {
            if (!map.containsKey(likeNum)) continue;

            Node n = map.get(likeNum);

            for (int k = 0; k < 4; k++) {
                int ny = dy[k] + n.y;
                int nx = dx[k] + n.x;

                if (!inRange(ny, nx) || arr[ny][nx] != -1) continue;

                cnt[ny][nx]++;
            }
        }

        List<Node> students = new ArrayList<>();
        int max = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (cnt[i][j] == 0) continue;

                if (max <= cnt[i][j]) {
                    if (max < cnt[i][j]) {
                        max = cnt[i][j];
                        students.clear();
                    }
                    students.add(new Node(i, j, cnt[i][j]));
                }
            }
        }

        if (students.size() == 1) {
            Node n = students.get(0);
            arr[n.y][n.x] = stuNum;
            map.put(stuNum, new Node(n.y, n.x, 0));

        } else if (students.size() > 1) {
            List<Node> blank = new ArrayList<>();
            max = 0;

            for (Node s : students) {
                int blankCnt = 0;

                for (int k = 0; k < 4; k++) {
                    int ny = dy[k] + s.y;
                    int nx = dx[k] + s.x;

                    if (!inRange(ny, nx) || arr[ny][nx] != -1) continue;

                    blankCnt++;
                }

                if (max <= blankCnt) {
                    if (max < blankCnt) {
                        blank.clear();
                        max = blankCnt;
                    }
                    blank.add(new Node(s.y, s.x, blankCnt));
                }
            }

            Collections.sort(blank);

            Node n = blank.get(0);
            arr[n.y][n.x] = stuNum;
            map.put(stuNum, new Node(n.y, n.x, 0));

        } else {
            List<Node> blank = new ArrayList<>();
            max = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (arr[i][j] != -1) continue;

                    int blankCnt = 0;

                    for (int k = 0; k < 4; k++) {
                        int ny = dy[k] + i;
                        int nx = dx[k] + j;

                        if (!inRange(ny, nx) || arr[ny][nx] != -1) continue;

                        blankCnt++;
                    }

                    if (max <= blankCnt) {
                        if (max < blankCnt) {
                            blank.clear();
                            max = blankCnt;
                        }
                        blank.add(new Node(i, j, blankCnt));
                    }
                }
            }

            Collections.sort(blank);

            Node n = blank.get(0);
            arr[n.y][n.x] = stuNum;
            map.put(stuNum, new Node(n.y, n.x, 0));
        }
    }

    static boolean inRange(int y, int x) {
        return (y >= 0 && y < N) && (x >= 0 && x < N);
    }
}

class Node implements Comparable<Node> {
    int y, x, cnt;

    Node(int y, int x, int cnt) {
        this.y = y;
        this.x = x;
        this.cnt = cnt;
    }

    public int compareTo(Node n) {
        if (this.y == n.y) return this.x - n.x;
        return this.y - n.y;
    }
}