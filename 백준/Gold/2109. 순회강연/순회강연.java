import java.util.*;
import java.io.*;

class Node implements Comparable<Node>{
    int d, p;

    public Node(int d, int p){
        this.d = d;
        this.p = p;
    }

    @Override
    public int compareTo(Node n){
        if(this.d == n.d) return n.p - this.p;
        return this.d - n.d;
    }
}

public class Main{
    static int N, ans;
    static List<Node> list = new ArrayList<>();
    static boolean[] usedDays; // 날짜 사용 여부 확인

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        int maxDay = 0;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            list.add(new Node(d, p));
            maxDay = Math.max(maxDay, d);
        }

        Collections.sort(list, (a, b) -> b.p - a.p);

        usedDays = new boolean[maxDay + 1];

        solve();

        System.out.println(ans);
    }

    static void solve(){
        for(Node n : list){
            for(int day = n.d; day >= 1; day--){
                if(!usedDays[day]){
                    ans += n.p;
                    usedDays[day] = true;
                    break;
                }
            }
        }
    }
}
