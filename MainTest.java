import java.util.*;

public class MainTest {
    public static void main(String[] args) {
        Graph<Integer> integerGraph = new Graph<>(true, true);//add check for weighted
        Graph<Integer> testGraph = new Graph<>(false, false);//add check for weighted

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

        /*Graph<Integer> copy = new Graph<>();
        copy.copy(integerGraph);
        System.out.println(copy);

        System.out.println(integerGraph1);*/
		//TODO: add to Graph funcs BFS,Dijkstra, ..
        BFS bfs = new BFS(integerGraph);
        BFS bfs1 = new BFS(testGraph);
        Dijkstra di = new Dijkstra(integerGraph);
        
        int start = 2;
        int end = 10;

		System.out.println("bfs test from "+start+" to "+end);
        System.out.println(bfs1.findPath(start, end).toString());

		System.out.println("dijkstra from "+start);

		Map<Integer, Integer> map = new HashMap<>(di.getDist(start));

		for(Integer el: map.keySet()){
			System.out.println(el+": "+map.get(el));
		}

		System.out.println("Number of components: "+integerGraph.countComponents());
    }
}
