import java.util.*;

//only implemented for int weights
public class Graph<E>{
    private boolean bidirectional;
    private boolean weighted;
    private HashMap<E, HashMap<E, Integer>> graph = new HashMap<>();

    public Graph(){
        this(true, false);
    }

    public Graph(boolean bi, boolean w){
        this.bidirectional = bi;
        this.weighted = w;
    }

    public void addVertex(E v){
        if(!graph.containsKey(v))
            graph.put(v, new HashMap<>() );
    }

    public void addEdge(E from, E to){
        addEdge(from, to, 0);
    }

    public void addEdge(E from, E to, int w){
        if(!graph.containsKey(from)){
            addVertex(from);
        }
        if(!graph.containsKey(to)){
            addVertex(to);
        }
        graph.get(from).put(to, w);
        if(bidirectional)
            graph.get(to).put(from, w);
    }

    public void removeEdge(E from, E to){
        if(graph.containsKey(from)){
            graph.get(from).remove(to);
        }
        if(bidirectional){
            if(graph.containsKey(to)){
                graph.get(to).remove(from);
            }
        }
    }

    public void removeVertex(E v){
        if(graph.containsKey(v)){
            graph.remove(v);
            for(E e: graph.keySet()){
                graph.get(e).remove(v);
            }
        }
    }

    public List<E> getVertexes(){//TODO: add directed
        return new ArrayList<>(graph.keySet());
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for(E e: graph.keySet()){
            str.append(e.toString() + ": ");
            for(E k: graph.get(e).keySet()){
                if(weighted)
                    str.append(k.toString() + "->" + graph.get(e).get(k) + " ");
                else
                    str.append(k.toString() +" ");
            }
            str.append("\n");
        }
        return str.toString();
    }

    public int edgeCount(){
        int count = 0;
        for(E e: graph.keySet()){
            count += graph.get(e).size();
        }
        if(bidirectional) return count / 2;
        return count;
    }

    public int vertexCount() {
        return graph.size();
    }

    public Graph copy(){
        Graph copy = new Graph(isBidirectional(), isWeighted());
        for(E from: graph.keySet()){
			for(E to: graph.get(from).keySet()){
        		copy.addEdge(from, to, graph.get(from).get(to));
        	}
        }
        return copy;
    }

    public HashMap<E, HashMap<E, Integer>> getGraph(){
        return this.graph;
    }

    public boolean isBidirectional() {
        return bidirectional;
    }

    public void setDirection(boolean bidirectional) {
        this.bidirectional = bidirectional;//TODO: add checks and conversion dir->bi
    }

    public boolean isWeighted() {
        return weighted;
    }

    public void setWeighted(boolean weighted) {
        this.weighted = weighted;
    }

    public void setWeight(E from, E to, int weight){
    	if(graph.containsKey(from)){
    		if(graph.get(from).containsKey(to)){
    			graph.get(from).put(to, weight);
    		}
    	}
    	if(isBidirectional()){
    		if(graph.containsKey(to)){
    			if(graph.get(to).containsKey(from)){
    				graph.get(to).put(from,weight);
    			}
    		}
    	}
    }

    public void setBidirectional(){
    	this.bidirectional = true;
    }

    public Graph getBidirectional(){//TODO: finish, need this to count components in graph
    	Graph res = this.copy();
		if(res.isBidirectional())
			return res;
		res.setBidirectional();
		
		HashMap<E,HashMap<E,Integer>> map = res.getGraph();
		for(E from: map.keySet()){
			for(E to: map.get(from).keySet()){
				res.addEdge(to, from, map.get(from).get(to));
			}
		}
    	return res;
    }

    public int countComponents(){
    	int count = 0;
    	Graph gr = this.getBidirectional();
    	BFS bfs = new BFS(this);
		Set<E> visited = new HashSet<>();
		for(E el: (Set<E>)gr.getGraph().keySet()){
			if(!visited.contains(el)){
				List<List<E>> lists = bfs.getBfs(el);
				for(List<E> list: lists){
					for(E vertex: list){
						visited.add(vertex);
					}
				}
				count++;
			}
		}
    	return count;
    }
}
