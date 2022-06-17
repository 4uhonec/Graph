//TODO: implement cycle search

import java.util.*;

public class DFS<E,K extends Number> {
    private final HashMap<E, HashMap<E, K>> graphMap;
    private E startElement;
    private Set<E> visited = new HashSet<>();
    private List<List<E>> layers = new ArrayList<>();

    public DFS(Graph<E,K> graph){
        this.graphMap = graph.getGraph();
    }

    //idea is to build hashmap from start, them in getDfs() we'll translate map to List<List> with BFS (-speed)
    private void buildDfs(){

    }

    public List<List<E>> getDfs(E start){
        startElement = start;

        return layers;
    }
}
