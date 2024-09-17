import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N=5, ans;
    static int comb[], combX[], combY[];

    static char map[][];

    static int dy[] = {-1, 1, 0, 0};
    static int dx[] = {0, 0, -1, 1};

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        comb = new int[7];
        combY = new int[N*N];
        combX = new int[N*N];

        for(int i=0; i<N*N; i++) {
            combY[i] = i/N;
            combX[i] = i%N;
        }

        map = new char[N][N];

        for(int i=0; i<N; i++) {
            String str = sc.readLine();

            for(int j=0; j<N; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        solve();
        System.out.println(ans);
    }

    static void solve() {
        // 조합을 만든다.
        makeComb(0, 0, 0);
    }

    static void makeComb(int depth, int i, int j) {
        if(j==7) {
            isConnect();
            return;
        }

        if(depth==25) return;

        comb[i] = depth;
        makeComb(depth+1, i+1, j+1);
        makeComb(depth+1, i, j);
    }

    static void isConnect() {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(comb[0]);

        boolean visited[] = new boolean[7];
        visited[0] = true;

        int cnt=1, cntS=0, cntY=0;
        while(!q.isEmpty()) {
            int start = q.poll();
            int y = combY[start];
            int x = combX[start];

            if(map[y][x]=='S') cntS++;
            else cntY++;

            if(cntY>=4) return;

            for(int k=0; k<4; k++) {
                int ny = y+dy[k];
                int nx = x+dx[k];

                if(!inRange(ny, nx)) continue;

                for(int next=1; next<7; next++) {
                    if(visited[next]) continue;

                    int cy = combY[comb[next]];
                    int cx = combX[comb[next]];

                    if(ny!=cy || nx!=cx) continue;

                    cnt++;
                    q.add(comb[next]);
                    visited[next]=true;
                }
            }
        }
        if(cnt==7&&cntS>=4) ans++;
    }

    static boolean inRange(int y, int x) {
        return (y>=0&&y<5) && (x>=0&&x<5);
    }
}

class Node{
    int y, x;

    public Node(int y, int x) {
        this.y = y;
        this.x = x;
    }
}