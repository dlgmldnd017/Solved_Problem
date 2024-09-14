import java.util.*;
import java.io.*;

class Node implements Comparable<Node>{
    int y, x, key, cnt;

    public Node(int y, int x, int key, int cnt){
        this.y = y;
        this.x = x;
        this.key = key;
        this.cnt = cnt;
    }

    public int compareTo(Node n){
        return this.cnt - n.cnt;
    }
}

public class Main{
    static int N, M, startY, startX, ans;
    static char map[][];
    static boolean visited[][][];

    static int dy[] = {0, 0, -1, 1};
    static int dx[] = {-1, 1, 0, 0};

    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];

        visited = new boolean[64][N][M];

        for(int i=0; i<N; i++){
            String str = br.readLine();

            for(int j=0; j<M; j++){
                map[i][j] = str.charAt(j);

                if(map[i][j] == '0'){
                    startY = i;
                    startX = j;
                }
            }
        }

        ans = Integer.MAX_VALUE;

        solve();

        if(ans == Integer.MAX_VALUE) ans = -1;

        System.out.println(ans);
    }

    static void solve(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(startY, startX, 0, 0));
        visited[0][startY][startX] = true;

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            for(int k=0; k<4; k++){
                int ny = dy[k] + cur.y;
                int nx = dx[k] + cur.x;

                // 범위를 벗어나거나, 해당 키를 들고 방문했거나, 벽인 경우
                if(!inRange(ny, nx) || visited[cur.key][ny][nx] || map[ny][nx] == '#') continue;

                // (1) 출구 일 경우
                if(map[ny][nx] == '1'){
                    ans = cur.cnt+1;
                    return;
                }

                // (2) 열쇠일 경우
                if('a' <= map[ny][nx] && map[ny][nx] <= 'f'){
                    int key = cur.key | (1 << (map[ny][nx] - 'a'));
                    
                    visited[key][ny][nx] = true;
                    pq.add(new Node(ny, nx, key, cur.cnt+1));
                }

                // (3) 문 일 경우
                else if('A' <= map[ny][nx] && map[ny][nx] <= 'F'){
                    // 지나갈 수 있다면
                    if((cur.key & (1 << (map[ny][nx] - 'A'))) > 0){
                        pq.add(new Node(ny, nx, cur.key, cur.cnt+1));
                    }
                }

                // (4) 빈칸일 경우
                else{
                    pq.add(new Node(ny, nx, cur.key, cur.cnt+1));
                }

                visited[cur.key][ny][nx] = true;
            }
        }
    }

    static boolean inRange(int y, int x){
        return (y>=0&&y<N) && (x>=0&&x<M);
    }
}