package project4part2;
import java.util.*;

/***

A class to hold a connected component(CC) for the SLI algorithm.

Let w be the node that is provoking us to use a color M for the
first time, with M > 0.  The Smallest Last with Interchange 
algorithm considers (i,j) pairs with 0 <= i < j < M to see if by
exchanging the values of i and j in a few places, it can avoid 
using M.  Essentially, i, j, M, and w are the determining parameters
for this particular piece of the color assignment to w, along with
the current state of coloring of the other nodes.

For a particular pair of values (i,j), an instance of this class
will hold a connected component of the graph induced by 
{ v  | v is in the graph and v is colored i or j } with at least
one node colored i that has an edge to w.  The calculation of the 
node members of the component proceeds sequentially as given in the
algorithm, which is described more fully in the Graph.java class.
Briefly, it works as follows.

The first node added is a node colored i that has an edge with
w.  There is an additional list of nodes, auxList to hold nodes
that are reachable from the first node via a path of nodes all
of which are colored i or j, and so in the induced graph.  Every
time a node is encountered for the first time along such a path,
it is added to auxList.  Nodes are taken off auxList to consider
their adjacent nodes for inclusion.

If at any time a node is encountered that is colored j and has
an edge with w, then the calculation stops with failure, since
such a node means the swap of the colors i and j will not
fix the problem.

Otherwise, the calculation continues until all nodes of the
connected component have been found.


The methods here support that step by providing

1. constructor to create the CC with the first node, which
   should be a node adjacent to the problem node w and
   colored i, the lesser of the (i,j) pair of colors that is
   being considered for interchange
2. a method to add a node
3. a method to clear the addedToAuxList data member of the
   node in the CC(Node needs to have the clearAddedToAuxList 
   method, however)
4. a getter for the set of Nodes in the CC (you won't need this
   if you use the clearAddedToAuxList method)
5. a method to iterate over the CC and swap the i and j
   colors of each node in it
6. a compareTo method so the class has an ordering relation
   and you can use TreeSet of ConnectedComponent to hold all
   of them

YOU SHOULD NOT NEED TO CHANGE THIS.

***/

public class ConnectedComponent implements Comparable<ConnectedComponent>{


// create an empty set of nodes; this is the only data member
private TreeSet<Node> nodesOfCC = new TreeSet<Node>();

// the only needed data member is already initialized
public ConnectedComponent(){
}


// constructor for the creating a component with the first node;
public ConnectedComponent(Node n){
   nodesOfCC.add(n);
}


// getter for the set of Nodes
public TreeSet<Node> getNodesOfCC(){
   return nodesOfCC;
}

//  iterates over the nodes of the data member and sets the
//  addToAuxList data member each to false
public void clearAddedToAuxList(){

   for (Node v : nodesOfCC)
      v.clearAddedToAuxList();
}



// if the (i,j) pair does work, then this method
// is called to swap the i and j colors of the nodes
// in the component; requires getter and setter for color
public void performInterchange(int i, int j){

   Node v;

   Iterator<Node> iter = nodesOfCC.iterator();

   while (iter.hasNext()){
      v = iter.next();
      if (v.getColor() == i)
         v.setColor(j);
      else
         v.setColor(i);
}
}

// needed so that we can have a TreeSet of ConnectedComponent
public int compareTo(ConnectedComponent other){
/*

   if both are empty, returns 0

   else if 1 is empty makes the empty one the earlier one

   else, neither empty, they should have no over lap if they
   are not the same connected component, so compare this's 
   first node with other's first node and return the result.

   Essentially

   this < other

   iff 
      this is empty and other is not

      or

      both are non-empty and the first node of this < the first node of other
 

*/
if (nodesOfCC.size() == 0)
   if (other.nodesOfCC.size() == 0)
      return 0;
   else
      return -1;
else if (other.nodesOfCC.size() == 0)
   // other empty and this is not empty
   return 1;
else{ // neither empty

   Node
      thisN = nodesOfCC.first(),
      otherN = other.nodesOfCC.first();

   return thisN.compareTo(otherN);
}
}

// adds p to the set data member;
// note, since this is a set,
// if the node is already IN the set, it will not be added a
// second time(the equals method of Node is used to test for membership)
public void addNode(Node p){
   nodesOfCC.add(p);
}


}



