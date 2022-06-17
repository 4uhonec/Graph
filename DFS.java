//TODO: implement cycle search

import java.util.*;

public class DFS<E> {
    private final HashMap<E, HashMap<E, Integer>> graphMap;
    private E startElement;
    private Set<E> visited = new HashSet<>();

    public DFS(Graph<E> graph){
        this.graphMap = graph.getGraph();
    }

    public void buildDfs(E start){
        startElement = start;

    }
}
