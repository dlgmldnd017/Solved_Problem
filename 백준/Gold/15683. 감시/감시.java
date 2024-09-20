import java.util.*;
import java.io.*;

class CCTV{
    int y, x, num;

    public CCTV(int y, int x, int num){
        this.y = y;
        this.x = x;
        this.num = num;
    }
}

public class Main{
    static int N, M, map[][], cnt, ans;
    static List<CCTV> list = new ArrayList<>();

    static int dir[][][] = {
            { },
            { {0}, {1}, {2}, {3} },
            { {0,2}, {1, 3} },
            { {1,2}, {2,3}, {3,0}, {0,1} },
            { {1,2,3}, {2,3,0}, {3,0,1}, {0,1,2} },
            { {0, 1, 2, 3} }
    };

    static int dy[] = {0, 1, 0, -1};
    static int dx[] = {1, 0, -1, 0};

    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());

            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());

                if(1<=map[i][j] && map[i][j]<=5) list.add(new CCTV(i, j, map[i][j]));
                else if(map[i][j]==0) cnt++;
            }
        }

        ans = Integer.MAX_VALUE;

        solve(0, 0);

        System.out.println(ans);
    }

    static void solve(int depth, int watchedCnt) {
        if(depth==list.size()) {
            ans = Math.min(ans, cnt-watchedCnt);
            return;
        }

        int y = list.get(depth).y;
        int x = list.get(depth).x;
        int num = list.get(depth).num;

        for(int i=0; i<dir[num].length; i++) {
            int tmp = fillFlag(dir[num][i], -1, y, x);
            solve(depth+1, watchedCnt+tmp);
            fillFlag(dir[num][i], 1, y, x);
        }
    }

    static int fillFlag(int dirs[], int flag, int y, int x) {
        int cntSum=0;

        for(int i=0; i<dirs.length; i++) {
            for(int s=1;; s++) {
                int ny = y+dy[dirs[i]]*s;
                int nx = x+dx[dirs[i]]*s;

                if(!inRange(ny, nx) || map[ny][nx]==6) break;

                if(map[ny][nx]>0) continue;
                else if(map[ny][nx]==0) cntSum++;

                map[ny][nx] += flag;
            }
        }

        return cntSum;
    }

    static boolean inRange(int y, int x) {
        return (y>=0&&y<N) && (x>=0&&x<M);
    }
}