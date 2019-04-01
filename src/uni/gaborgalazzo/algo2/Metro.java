package uni.gaborgalazzo.algo2;

import it.uniupo.graphLib.UndirectedGraph;

import java.util.ArrayList;
import java.util.Arrays;

public class Metro {

    private UndirectedGraph map;

    public Metro(UndirectedGraph map) {
        this.map = map;
    }

    public ArrayList<Integer> getStops(int startStop, int destStop){

        boolean[] found = new boolean[map.getOrder()];
        int[] parent = new int[map.getOrder()];
        Arrays.fill(parent, -1);
        ArrayList<Integer> queue = new ArrayList<>();
        queue.add(startStop);
        while(!queue.isEmpty()){
            Integer node = queue.remove(0);
            if(!found[node])
            {
                found[node] = true;
                for(Integer n : map.getNeighbors(node)){
                    queue.add(n);
                    if(!found[n])
                        parent[n] = node;
                }
            }
        }

        if(parent[destStop] == -1)
            return null;
        ArrayList<Integer> stops = new ArrayList<>();
        int p = destStop;
        while (p!=startStop){
            stops.add(0, p);
            p=parent[p];
        }
        return  stops;
    }
}