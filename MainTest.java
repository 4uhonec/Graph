import java.util.*;
//TODO: make normal tests


public class MainTest {
    public static void main(String[] args) {
        Graph<Integer, Integer> integerGraph = new Graph<>(false, true);//add check for weighted
        Graph<Integer, Integer> testGraph = new Graph<>(false, false);//add check for weighted

        integerGraph.addEdge(1,2,15);
        integerGraph.addEdge(3,2,2);
        integerGraph.addEdge(4,9,30);
        integerGraph.addEdge(1,7,12);
        integerGraph.addEdge(6,2,5);
        integerGraph.addEdge(6,7,25);
        integerGraph.addEdge(10,9,6);
        integerGraph.addEdge(4,7,7);
        integerGraph.addEdge(3,9,7);
        integerGraph.addEdge(11,12,1);
        integerGraph.addEdge(11,13,2);

        testGraph.addEdge(1,3);
        testGraph.addEdge(1,2);
        testGraph.addEdge(2,5);
        testGraph.addEdge(5,1);
        testGraph.addEdge(3,6);
        testGraph.addEdge(3,4);
        testGraph.addEdge(4,5);
        testGraph.addEdge(7,3);
        testGraph.addEdge(4,8);
        testGraph.addEdge(8,7);
        testGraph.addEdge(7,11);
        testGraph.addEdge(11,12);
        testGraph.addEdge(8,12);
        testGraph.addEdge(8,9);
        testGraph.addEdge(9,10);
        testGraph.addEdge(10,5);
        //System.out.println(integerGraph);
        //System.out.println(integerGraph.vertexCount());
        //System.out.println(integerGraph.edgeCount());

        /*Graph copy = integerGraph.copy();
        System.out.println(copy);

        System.out.println(integerGraph1);*/
		//TODO: add to Graph funcs BFS,Dijkstra, ..
        //TODO: divide test file to cases
        //BFS<Integer, Integer> bfs = new BFS<>(integerGraph);
        BFS<Integer, Integer> bfs1 = new BFS<>(testGraph);
        Dijkstra<Integer> di = new Dijkstra<>(integerGraph);
        
        int start = 1;
        int end = 10;

        //bfs from start to end
		System.out.println("bfs test from "+start+" to "+end);
        System.out.println(bfs1.findPath(start, end).toString()+"\n\n");

        //Dijkstra from start
		System.out.println("dijkstra from "+start);
		Map<Integer, Integer> map = new HashMap<>(di.getDist(start));
		for(Integer el: map.keySet()){
			System.out.println(el+": "+map.get(el));
		}

        /*
        //count graph components test
		System.out.println("\n\nNumber of components: "+integerGraph.countComponents());

        //get graph components, print them
        List<Graph<Integer,Integer>> components = integerGraph.getComponents();
        for(Graph<Integer, Integer> graph: components){
            System.out.println(graph);
        }
        */
    }
}
