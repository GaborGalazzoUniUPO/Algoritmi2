package uni.gaborgalazzo.algo2;

import it.uniupo.graphLib.DirectedGraph;
import it.uniupo.graphLib.Edge;
import it.uniupo.graphLib.GraphUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Kosaraju {

    private DirectedGraph graph;

    public Kosaraju(DirectedGraph graph) {
        this.graph = graph;
    }

    public int[] getScc(){
        //ORDINE TOPOLOGICO
        DFS dfs = new DFS(graph);
        ArrayList<Integer> tord = dfs.postVisitList();
        Collections.reverse(tord);

        //GRAFO TRASPOSTO
        DirectedGraph gT =  GraphUtils.reverseGraph(graph);

        //COSTRUISCO SCC
        int[] scc = new int[gT.getOrder()];
        Arrays.fill(scc, -1);
        boolean[] found = new boolean[gT.getOrder()];
        for(int i= 0; i<gT.getOrder(); i++){

            ArrayList<Integer> queue = new ArrayList<>();
            queue.add(tord.get(i));
            while (!queue.isEmpty()){
                Integer node = queue.remove(0);
                if(!found[node])
                {
                    found[node] = true;
                    scc[node] = i;
                    for(Integer n: gT.getNeighbors(node))
                        queue.add(n);
                }
            }
        }


        return scc;
    }

    public int getMaxSCC(){
        int[] scc = getScc();
        Integer[] max = new Integer[graph.getOrder()];
        Arrays.fill(max,0);
        for(int i = 0; i<scc.length; i++)
            max[scc[i]]++;
        return Collections.max(Arrays.asList(max));
    }
}
