package uni.gaborgalazzo.algo2;

import it.uniupo.graphLib.Edge;
import it.uniupo.graphLib.GraphInterface;
import it.uniupo.graphLib.UndirectedGraph;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

/*********
 * le seguenti tre righe servono per eseguire i test in ordine
 * Non e' corretto perche' i test devono avere successo in qualunque
 * ordine siano eseguiti, ma serve per il lavoro in lab:
 * lavorate in modo che i test abbiano successo dal primo in avanti
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class TestBFS {
    GraphInterface testGraph;
    BFS visit;
    
    //********************************
    //testCreate(): non ci sono errori nel costruttore
    
    @Test
    public void test00Create() {
        testGraph = new UndirectedGraph(1);
        visit = new BFS(testGraph);
        assertNotNull(visit);
    }
    
    //********coda********************
    //avete scritto correttamente la condizione di terminazione del ciclo?
    
    @Test
    public void test05Coda() {
    	 testGraph = new UndirectedGraph("2; 0 1");
         visit = new BFS(testGraph);
         try {
        	 visit.getNodesInOrderOfVisit(0);
         } catch (IndexOutOfBoundsException e){
        	 fail("Avete messo come condizione di ciclo che la coda non sia vuota? Attenzione: coda non vuota (!coda.isEmpty()) e' diverso da coda non null (coda!= null)");
         } catch (Exception e) {
        	 fail("Qualcosa è andato storto, ma non so cosa.");
         }
    }
     
    //********test base***************
    
    //deve funzionare correttamente in un grafo banale con un solo nodo e senza archi
    @Test
    public void test10OneNodeVisited() { //BFS su grafo con un solo nodo
        testGraph = new UndirectedGraph(1);
        visit = new BFS(testGraph);
        ArrayList<Integer> visited = visit.getNodesInOrderOfVisit(0);
        assertEquals("La visita in ampiezza deve trovare un nodo",1, visited.size());
        int node = visited.get(0);
        assertEquals("Il nodo trovato deve essere 0", 0, node);
    }

    //deve funzionare correttamente in un grafo banale con due nodi e un arco
    @Test
    public void test11TwoNodesVisited() { //BFS su grafo con due nodi
        testGraph = new UndirectedGraph(2);
        testGraph.addEdge(1, 0);
        visit = new BFS(testGraph);
        ArrayList<Integer> visited = visit.getNodesInOrderOfVisit(1);
        assertEquals("La visita in ampiezza deve trovare due nodi",2, visited.size());
        int node = visited.get(0);
        assertEquals("Il primo nodo incontrato e' 1",1, node);
        node = visited.get(1);
        assertEquals("Il secondo nodo incontrato e' 0", 0, node);

    }
    
    //************verifica che la visita sia realmente una BFS***********
   @Test
   public void test15BFSOrder() {
	   testGraph = new UndirectedGraph("4;0 2;0 1;2 3;1 3");
       visit = new BFS(testGraph);
       assertTrue("La visita in ampiezza con sorgente 2 deve trovare i nodi nell'ordine 2,3,0,1 (0 e' il terzo visitato) oppure 2,0,3,1 (3 e' il terzo visitato)",visit.getNodesInOrderOfVisit(2).get(2) == 0 ||
    		   visit.getNodesInOrderOfVisit(2).get(2) == 3);
       int node = visit.getNodesInOrderOfVisit(2).get(2);
       assertNotEquals("La visita in ampiezza non deve trovare 1 come terzo nodo", 1, node);
       
   }
   
   
   //*************test inizializzazione******************
   
   //numero di nodi scoperti
   @Test
   public void test20InitNumeroNodi() {
     GraphInterface grafo = 
   		new UndirectedGraph ("4;0 2;0 1;2 3;1 3");
     BFS visit = new BFS(grafo); //<<- creato una volta sola
     assertEquals(4, visit.getNodesInOrderOfVisit(0).size()); //<<-due chiamate del metodo con argomenti diversi
     assertEquals(4, visit.getNodesInOrderOfVisit(2).size());
   }
   
  
   //ordine dei nodi scoperti
   @Test
   public void test25InitOrdine() {
     GraphInterface grafo = 
   		new UndirectedGraph ("4;0 2;0 1;2 3;1 3");
     BFS visit = new BFS(grafo); //<<- creato una volta sola
     int dist = visit.getNodesInOrderOfVisit(2).get(2);
     assertNotEquals("Nella visita da sorgente 2: 1 non e' il terzo visitato", 1, dist); //<<-tre chiamate del metodo
     dist = visit.getNodesInOrderOfVisit(1).get(2);
     assertNotEquals("Nella visita da sorgente 1: 0 non e' il terzo visitato", 0, dist); 
     dist = visit.getNodesInOrderOfVisit(0).get(2);
     assertNotEquals("Nella visita da sorgente 0: 3 non e' il terzo visitato", 3, dist); 
   }
   
   //***********test distanza***********
   
   //valore corretto da una sorgente
  @Test
  public void test30Distanza() {
	  GraphInterface grafo = 
		   		new UndirectedGraph ("4;0 2;0 1;2 3;1 3");
	  BFS visit = new BFS(grafo);
	  int[] distanza = visit.getDistance(2); //ottieni tutte le distanze dalla sorgente 2
	  assertEquals("distanza(2,2) = 0", 0, distanza[2]);  
	  assertEquals("distanza(2,0) = 1", 1, distanza[0]);
	  assertEquals("distanza(2,3) = 1", 1, distanza[3]);
	  assertEquals("distanza(2,1) = 2", 2, distanza[1]);
  }
  
  //inizializzazione: metodo richiamato con due sorgenti diverse
  @Test
  public void test35InitDistanza() {
    GraphInterface grafo = 
  		new UndirectedGraph ("4;0 2;0 1;2 3;1 3");
    BFS visit = new BFS(grafo); //<<- creato una volta sola
      System.out.println(Arrays.toString(visit.getDistance(2)));
	  assertEquals("distanza(2,2) = 0", 0, visit.getDistance(2)[2]); //<--distanze da 2
	  assertEquals("distanza(2,0) = 1", 1, visit.getDistance(2)[0]);
	  assertEquals("distanza(3,0) = 2", 2, visit.getDistance(3)[0]); //<--distanze da 3
	  assertEquals("distanza(3,1) = 1", 1, visit.getDistance(3)[1]);

  }

  @Test
  public void testDimBFSTree(){
      GraphInterface g =
              new UndirectedGraph ("4;0 2;0 1;2 3;1 3");
      BFS visit = new BFS(g); //<<- creato una volta sola
      for(int i = 0; i<g.getOrder(); i++){
          assertEquals(g.getOrder()-1, visit.bfsTree(i).getEdgeNum());
      }


  }

    @Test
    public void getShortestPath() {

        GraphInterface g = new UndirectedGraph("6;4 0;4 1;4 2;2 3;3 4");

        BFS bfs = new BFS(g);
        ArrayList<Edge> sp = bfs.getShortestPath(0, 2);
        assertEquals(2, sp.size());
        assertTrue(sp.containsAll(Arrays.asList(new Edge(0, 4), new Edge(4, 2))));
        assertNull(bfs.getShortestPath(0, 5));
    }
}
