public class MainTest {
    public static void main(String[] args) {
        Graph<Integer> integerGraph = new Graph<>(true, true);//add check for weighted
        Graph<Integer> integerGraph1 = new Graph<>(false, false);//add check for weighted

        integerGraph.addEdge(1,2,15);
        integerGraph.addEdge(3,2,2);
        integerGraph.addEdge(4,9,30);
        integerGraph.addEdge(1,7,12);
        integerGraph.addEdge(6,2,5);
        integerGraph.addEdge(6,7,25);
        integerGraph.addEdge(10,9,6);
        integerGraph.addEdge(4,7,7);
        integerGraph.addEdge(3,9,7);

        integerGraph1.addEdge(1,2,15);
        integerGraph1.addEdge(3,2,2);
        integerGraph1.addEdge(4,9,30);
        integerGraph1.addEdge(1,7,12);
        integerGraph1.addEdge(6,2,5);
        integerGraph1.addEdge(6,7,25);
        integerGraph1.addEdge(10,9,6);
        integerGraph1.addEdge(4,7,7);
        integerGraph1.addEdge(3,9,7);
        //integerGraph1.removeEdge(4,10);

        //System.out.println(integerGraph);
        //System.out.println(integerGraph.vertexCount());
        //System.out.println(integerGraph.edgeCount());

        /*Graph<Integer> copy = new Graph<>();
        copy.copy(integerGraph);
        System.out.println(copy);

        System.out.println(integerGraph1);*/

        BFS bfs = new BFS(integerGraph, 1);
        System.out.println(bfs);
    }
}
