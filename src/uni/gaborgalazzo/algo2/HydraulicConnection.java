package uni.gaborgalazzo.algo2;

import it.uniupo.algoTools.MinHeap;
import it.uniupo.graphLib.Edge;
import it.uniupo.graphLib.UndirectedGraph;

import java.util.Arrays;

public class HydraulicConnection {

    private final UndirectedGraph propProject;
    private final UndirectedGraph muniProject;

    private  int[] pipeCostProp;
    private int[] pipeCostMuni;
    private int digCostProp = 0;
    private int digCostMuni = 0;


    public HydraulicConnection(UndirectedGraph map, int[][] digCosts, int pipeCost, int connectionPoint) {


        muniProject = (UndirectedGraph) map.create();
        boolean[] found = new boolean[map.getOrder()];
        found[connectionPoint] = true;

        pipeCostProp = new int[map.getOrder()];
        Arrays.fill(pipeCostProp, -1);
        pipeCostMuni = new int[map.getOrder()];
        Arrays.fill(pipeCostMuni, -1);

        pipeCostProp[connectionPoint] = 0;
        pipeCostMuni[connectionPoint] = 0;

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
                pipeCostProp[e.getHead()] = pipeCostProp[e.getTail()] + e.getWeight()*pipeCost;

                for(Edge n: map.getOutEdges(e.getHead()))
                    if(!found[n.getHead()])
                        heap.add(n,digCosts[n.getHead()][n.getTail()]);
            }
        }


        propProject = new UndirectedGraph(map.getOrder());
        found = new boolean[map.getOrder()];


        found[connectionPoint] = true;
        for(Edge e: map.getOutEdges(connectionPoint)){
            heap.add(e, pipeCostProp[connectionPoint] + e.getWeight());
        }
        while (!heap.isEmpty()){
            Edge e = heap.extractMin();
            if(!found[e.getHead()]){
                found[e.getHead()] = true;
                propProject.addEdge(new Edge(e.getTail(), e.getHead(), e.getWeight()*pipeCost));

                digCostProp += digCosts[e.getHead()][e.getTail()];
                pipeCostMuni[e.getHead()] = pipeCostMuni[e.getTail()] + e.getWeight()*pipeCost;

                for(Edge n: map.getOutEdges(e.getHead()))
                    if(!found[n.getHead()])
                        heap.add(n, pipeCostProp[n.getTail()] + n.getWeight()*pipeCost);
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
        int propPrjCost = pipeCostProp[place];
        int muniPrjCost = pipeCostMuni[place];
        return propPrjCost-muniPrjCost;
    }
}
