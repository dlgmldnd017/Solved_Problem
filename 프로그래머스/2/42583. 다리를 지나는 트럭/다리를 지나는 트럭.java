import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Truck> bridge = new LinkedList<>();
        int idx = 0, totalWeight = 0, time = 0;

        while (idx < truck_weights.length || !bridge.isEmpty()) {
            time++;

            if (!bridge.isEmpty() && bridge.peek().exitTime == time) {
                totalWeight -= bridge.poll().weight;
            }

            if (idx < truck_weights.length && totalWeight + truck_weights[idx] <= weight) {
                totalWeight += truck_weights[idx];
                bridge.add(new Truck(truck_weights[idx], time + bridge_length));
                idx++;
            }
        }

        return time;
    }

    static class Truck {
        int weight;
        int exitTime;

        Truck(int weight, int exitTime) {
            this.weight = weight;
            this.exitTime = exitTime;
        }
    }
}