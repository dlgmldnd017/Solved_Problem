import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Node{
    int idx, cnt;

    public Node(int idx, int cnt) {
        this.idx = idx;
        this.cnt = cnt;
    }
}

public class Main {
    static int N, ans, max;
    static List<Node> list[];
    static boolean visited[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        list = new List[N+1];

        for(int i=0; i<N+1; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i=0; i<N-1; i++){
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            list[p].add(new Node(c, w));
            list[c].add(new Node(p, w));
        }

        solve();

        System.out.println(ans);
    }

    static void solve(){
        visited = new boolean[N+1];
        visited[1] = true;
        dfs(1, 0);

        visited = new boolean[N+1];
        visited[max] = true;
        dfs(max, 0);
    }

    static void dfs(int idx, int cnt){
        if(ans < cnt){
            ans = cnt;
            max = idx;
        }

        for(Node a : list[idx]){
            if(!visited[a.idx]){
                visited[a.idx] = true;
                dfs(a.idx, cnt + a.cnt);
            }
        }
    }
}