import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node{
	int y, x, t, key;

	public Node(int y, int x, int t, int key) {
		this.y = y;
		this.x = x;
		this.t = t;
		this.key = key;
	}
}

public class Main {
	static int N, M, ans;
	static char map[][];
	
	static int dy[] = {-1, 0, 0, 1};
	static int dx[] = {0, -1, 1, 0};

	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(sc.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int y=0, x=0;
		
		map = new char[N][M];
		for(int i=0; i<N; i++) {
			String str = sc.readLine();
			
			for(int j=0; j<M; j++) {
				map[i][j] = str.charAt(j);
				
				if(map[i][j]=='0') {
					y=i; x=j;
				}
			}
		}

		ans = solve(y, x);
		System.out.println(ans);
	}

	static int solve(int y, int x) {
		Queue<Node> q = new LinkedList<Node>();
		q.add(new Node(y, x, 0, 0));
		
		boolean visited[][][] = new boolean[64][N][M];
		visited[0][y][x]=true;
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			
			for(int k=0; k<4; k++) {
				int ny = cur.y+dy[k];
				int nx = cur.x+dx[k];
				
				if(!inRange(ny, nx) || map[ny][nx]=='#' || visited[cur.key][ny][nx]) continue;
				
				if(map[ny][nx]=='1') return cur.t+1;
				
				else if(map[ny][nx]=='.' || map[ny][nx]=='0') {
					visited[cur.key][ny][nx]=true;
					q.add(new Node(ny, nx, cur.t+1, cur.key));
				}
				
				else if(map[ny][nx]>='a' && map[ny][nx]<='f') {
					int key = 1 << (map[ny][nx]-'a');
					key |= cur.key;
					
					visited[cur.key][ny][nx]=true;
					visited[key][ny][nx]=true;
					q.add(new Node(ny, nx, cur.t+1, key));
				}
				
				else if(map[ny][nx]>='A' && map[ny][nx]<='F') {
					int door = 1 << (map[ny][nx]-'A');
					
					if((door&cur.key)>0) {
						visited[cur.key][ny][nx]=true;
						q.add(new Node(ny, nx, cur.t+1, cur.key));
					}
				}
			}
		}
		
		return -1;
	}
	
	static boolean inRange(int y, int x) {
		return (y>=0&&y<N) && (x>=0&&x<M);
	}
}