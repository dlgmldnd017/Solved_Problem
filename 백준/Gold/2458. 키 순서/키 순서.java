import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, ans;
    static List<Integer>[] fwList, bwList;
    static boolean[] visited;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        fwList = new ArrayList[N + 1];
        bwList = new ArrayList[N + 1];

        visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            fwList[i] = new ArrayList<>();
            bwList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            fwList[a].add(b);
            bwList[b].add(a);
        }

        for (int i = 1; i <= N; i++) {
            int biggerCnt = solve(fwList, i);
            int smallerCnt = solve(bwList, i);

            if(biggerCnt + smallerCnt == N - 1) ans++;
        }

        System.out.println(ans);
    }

    static int solve(List<Integer>[] list, int start) {
        int cnt = 0;

        Arrays.fill(visited, false);
        visited[start] = true;

        Queue<Integer> q = new ArrayDeque<>();
        q.add(start);

        while(!q.isEmpty()) {
            int cur = q.poll();

            for (int next : list[cur]) {
                if (visited[next]) continue;

                cnt++;
                q.add(next);
                visited[next] = true;
            }
        }

        return cnt;
    }
}