package uni.gaborgalazzo.algo2;

import it.uniupo.graphLib.DirectedGraph;
import it.uniupo.graphLib.Edge;
import it.uniupo.graphLib.GraphInterface;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Flights {

    private DirectedGraph links;
    private Dijkstra dijkstra;
    private BFS bfs;

    public Flights(DirectedGraph links) {
        this.links = links;
        dijkstra = new Dijkstra(links);
        bfs = new BFS(links);
    }

    public int time(int departure, int arrival){

        boolean[] found = new boolean[links.getOrder()];
        ArrayList<Edge> queue = new ArrayList<>();
        for(Edge e: links.getOutEdges(departure))
            queue.add(e);
        int[] times = new int[links.getOrder()];
        Arrays.fill(times, -1);
        found[departure] = true;
        times[departure] = 0;
        while (!queue.isEmpty()){
            Edge e = queue.remove(0);
            if(!found[e.getHead()]){
                found[e.getHead()] = true;
                times[e.getHead()]=times[e.getTail()]+e.getWeight();
                for(Edge n: links.getOutEdges(e.getHead()))
                    queue.add(n);
            }
        }
        return times[arrival];
    }

    public int numAirports(int departure, int arrival){
        return bfs.getDistance(departure)[arrival];
    }

    public int minimumTime(int departure, int arrival){
        GraphInterface links = dijkstra.getMinimumPathsTree(departure);
        boolean[] found = new boolean[links.getOrder()];
        ArrayList<Edge> queue = new ArrayList<>();
        for(Edge e: links.getOutEdges(departure))
            queue.add(e);
        int[] times = new int[links.getOrder()];
        Arrays.fill(times, -1);
        found[departure] = true;
        times[departure] = 0;
        while (!queue.isEmpty()){
            Edge e = queue.remove(0);
            if(!found[e.getHead()]){
                found[e.getHead()] = true;
                times[e.getHead()]=times[e.getTail()]+e.getWeight();
                for(Edge n: links.getOutEdges(e.getHead()))
                    queue.add(n);
            }
        }
        return times[arrival];
    }

    public ArrayList<Edge> fastestPath(int departure, int arrival){
        ArrayList<Edge> airports=  dijkstra.getMinimumPath(departure, arrival);
        if(airports==null || airports.isEmpty())
            return null;
        return airports;
    }


    public ArrayList<Integer> airports(int departure, int arrival){
        ArrayList<Edge> minimumPath=  dijkstra.getMinimumPath(departure, arrival);
        if(minimumPath==null || minimumPath.isEmpty())
            return null;
        ArrayList<Integer> airports = new ArrayList<>();
        airports.add(departure);
        for(Edge e: minimumPath){
            airports.add(e.getHead());
        }
        return airports;
    }
}
