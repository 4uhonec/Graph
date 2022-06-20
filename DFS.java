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
    //maybe build new graph?
    private void buildDfs(){
        Map<E,E> parent = new HashMap<>();//<parent, child>
        Stack<E> stack = new Stack<>();
        E current = startElement;
        stack.push(current);
        while(!stack.isEmpty()){
            current = stack.pop();
            if(!visited.contains(current)){
                visited.add(current);
                for(E el: graphMap.keySet()){
                    stack.push(el);
                    parent.put(current, el);//check how this works
                }
            }
        }
    }

    public List<List<E>> getDfs(E start){
        startElement = start;
        buildDfs();

        return layers;
    }
}
