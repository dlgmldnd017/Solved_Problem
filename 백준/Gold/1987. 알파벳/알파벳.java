import java.util.*;
import java.io.*;

class Node{
    int y, x, cnt, key;

    public Node(int y, int x, int cnt, int key){
        this.y = y;
        this.x = x;
        this.cnt = cnt;
        this.key = key;
    }
}

public class Main{
    static int R,C, ans;
    static char map[][];

    static int dy[] = {0, 0, -1, 1};
    static int dx[] = {-1, 1, 0, 0};

    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];

        for(int i=0; i<R; i++){
            String str = br.readLine();

            for(int j=0; j<C; j++){
                map[i][j] = str.charAt(j);
            }
        }

        solve();

        System.out.println(ans);
    }

    static void solve(){
        int key = 1 << (map[0][0]-'A');

        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(0, 0, 1, key));

        Set<String> visited = new HashSet<>();
        visited.add(0+","+0+","+key);

        while(!q.isEmpty()){
            Node cur = q.poll();

            ans = Math.max(ans, cur.cnt);

            for(int k=0; k<4; k++){
                int ny = dy[k] + cur.y;
                int nx = dx[k] + cur.x;

                if(!inRange(ny, nx) || (cur.key & 1 << (map[ny][nx]-'A')) > 0) continue;

                int newKey = cur.key | (1<<(map[ny][nx]-'A'));

                String state = ny+","+nx+","+newKey;
                if(visited.contains(state)) continue;

                visited.add(state);
                q.add(new Node(ny, nx, cur.cnt+1, newKey));
            }
        }
    }

    static boolean inRange(int y, int x){
        return (y>=0&&y<R) && (x>=0&&x<C);
    }
}