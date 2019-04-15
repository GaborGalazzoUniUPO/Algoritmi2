package uni.gaborgalazzo.algo2;

import it.uniupo.algoTools.MST;
import it.uniupo.algoTools.MinHeap;
import it.uniupo.graphLib.Edge;
import it.uniupo.graphLib.GraphInterface;
import it.uniupo.graphLib.UndirectedGraph;

import java.util.Arrays;

public class HydraulicConnection {

    private final UndirectedGraph propProject;
    private final UndirectedGraph muniProject;

    private final int connectionPoint;
    private final int[] propSingleCosts;
    private int[] muniSingleCosts;
    private int digCostProp = 0;
    private int digCostMuni = 0;


    public HydraulicConnection(UndirectedGraph map, int[][] digCosts, int pipeCost, int connectionPoint) {

        this.connectionPoint = connectionPoint;

        muniProject = (UndirectedGraph) map.create();
        boolean[] found = new boolean[map.getOrder()];
        found[connectionPoint] = true;

        propSingleCosts = new int[map.getOrder()];
        Arrays.fill(propSingleCosts, -1);
        muniSingleCosts = new int[map.getOrder()];
        Arrays.fill(muniSingleCosts, -1);

        propSingleCosts[connectionPoint] = 0;
        muniSingleCosts[connectionPoint] = 0;

        MinHeap<Edge, Integer> heap = new MinHeap<>();
        for(Edge e: map.getOutEdges(connectionPoint)){
            heap.add(e,
                    digCosts[e.getHead()][e.getTail()]);
        }
        while (!heap.isEmpty()){
            Edge e = heap.extractMin();
            if(!found[e.getHead()]){
                found[e.getHead()] = true;
                muniProject.addEdge(new Edge(e.getTail(), e.getHead(), digCosts[e.getHead()][e.getTail()]));
                digCostMuni+=digCosts[e.getHead()][e.getTail()];


                propSingleCosts[e.getHead()] = propSingleCosts[e.getTail()] + e.getWeight()*pipeCost;


                for(Edge n: map.getOutEdges(e.getHead()))
                    if(!found[n.getHead()])
                        heap.add(n,digCosts[n.getHead()][n.getTail()]);
            }
        }


        propProject = new UndirectedGraph(map.getOrder());
        found = new boolean[map.getOrder()];


        found[connectionPoint] = true;
        for(Edge e: map.getOutEdges(connectionPoint)){
            heap.add(e, propSingleCosts[connectionPoint] + e.getWeight());
        }
        while (!heap.isEmpty()){
            Edge e = heap.extractMin();
            if(!found[e.getHead()]){
                found[e.getHead()] = true;

                muniSingleCosts[e.getHead()] = muniSingleCosts[e.getTail()] + e.getWeight()*pipeCost;

                propProject.addEdge(new Edge(e.getTail(), e.getHead(), e.getWeight()*pipeCost));
                digCostProp += digCosts[e.getHead()][e.getTail()];
                for(Edge n: map.getOutEdges(e.getHead()))
                    if(!found[n.getHead()])
                        heap.add(n, propSingleCosts[n.getTail()] + n.getWeight()*pipeCost);
            }
        }

    }

    public UndirectedGraph propProject(){
      return propProject;
    }

    public UndirectedGraph getMuniProject(){
        return muniProject;
    }

    public int extraMuni(){
        return digCostProp-digCostMuni;
    }

    public int extraProp(int place){
        int propPrjCost = propSingleCosts[place];
        int muniPrjCost = muniSingleCosts[place];
        return propPrjCost-muniPrjCost;
    }
}
