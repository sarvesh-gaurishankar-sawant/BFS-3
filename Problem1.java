/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

/*
133. Clone Graph
Ran on leetcode: Yes
TC: O(V + E) 
SC: O(V + E)
In BFS manner, remove the node from the queue and connect it to all of its neighbors and if neighbors are seen for the first time add them to the queue to be processed like the node.If node is seen for the first time create the node and add it to the map and if the node is seen earlier then return the cloned node for it from the map.
*/

class Solution {
    
    Map<Node, Node> map;

    public Node cloneGraph(Node node) {
        if (node == null) return null;
        this.map = new HashMap<>();
        bfs(node);
        return map.get(node);
    }

    private void bfs(Node node){
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);

        while(!queue.isEmpty()){
            // Remove the node from the queue and clone it
            Node pollNode = queue.poll();
            Node cloneNode = clone(pollNode);
            
            // Connect to all of its neighbors
            for(Node neigh: pollNode.neighbors){ 
                if(!map.containsKey(neigh)){
                    queue.offer(neigh);
                }
                cloneNode.neighbors.add(clone(neigh));
            }
        }
    }

    private Node clone(Node node){
        if(!map.containsKey(node)){ // if node is seen for the first time then create clone and add it to the map
            Node cloneNode = new Node(node.val);
            map.put(node, cloneNode);
        }
        return map.get(node); // Return the clone
    }
}