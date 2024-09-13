import java.util.*;
import java.io.*;

class Node implements Comparable<Node>{
    int d, p;

    public Node(int d, int p){
        this.d = d;
        this.p = p;
    }

    public int compareTo(Node n){
        return n.p - this.p;
    }
}

public class Main{
    static int N, maxDay, ans;
    static List<Node> list = new ArrayList<>();
    static boolean[] usedDays;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            list.add(new Node(d, p));
            maxDay = Math.max(maxDay, d);
        }

        Collections.sort(list);

        usedDays = new boolean[maxDay + 1];

        solve();

        System.out.println(ans);
    }

    static void solve(){
        for(Node n : list){
            for(int i=n.d; i >= 1; i--){
                if(!usedDays[i]){
                    ans += n.p;
                    usedDays[i] = true;
                    break;
                }
            }
        }
    }
}