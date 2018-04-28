package project4part2;
import java.util.*;
import java.awt.Color;

/***

A template class for representing nodes in the graph for the Graph Coloring project.

You will need to add some data members and methods.  See below.


***/

public class Node implements Comparable<Node>{

public static final int UNCOLORED = -1;

private int
   id, // key for the class
   color = UNCOLORED, // initially not colored
   degree;  // number of nodes adjacent to this one

private Point location;
private TreeSet<Integer> adjNodeIds = new TreeSet<Integer>();
private Set<Node> adjNodes = new TreeSet<Node>();

/********************************************************************

YOU WILL NEED TO ADD DATA MEMBERS TO SUPPORT THE ALGORITHMS YOU 
IMPLEMENT, AND METHODS TO MANIPULATE THEM THAT THOSE ALGORITHMS 
NEED.


// for the sequential assignment algorithm used by LF and SL

colorsOfAdjNodes : TreeSet of Integer holding the colors of all this
                   node's adjacent nodes; initially empty, and each time
                   one of its adjacent nodes is colored some nonnegative i,
                   i is added to the set; the algorithm colors the node
                   THE SMALLEST NONNEGATIVE INTEGER THAT IS NOT IN THIS
                   SET

// for the initial sorting used by SL
unsortedAdjCount: integer // initially this should be the same
                          // as the size of the adjNodeIds list when the Node is
                          // constructed, but as nodes are placed at the end of
                          // array during the smallest last ordering, it is reduced;
                          // for example, suppose during a pass over the unsorted nodes,
                          // it is determined that node w is the largest and should
                          // be last among the unsorted nodes; then the algorithm 
                          // should iterate over w's adjacent nodes and decrease the
                          // value in their unsorteAdjCount data members by 1 to 
                          // reflect that w has been sorted

// for SLI
addedToAuxList: boolean   // should be initially false, but may be set and cleared
                          // multiple times as the algorithm proceeds to find an
                          // (i,j) color pair to swap
                          // YOU MUST CODE THE clearAddedToAuxList METHOD

// for RLF
inT: boolean;  // initially true at the start of each stage
countOfUncoloredAdjacentNodes: int; // at each stage this records the count, for this
                                    // node, of its adjacent nodes that are in T or U
countOfAdjacentNodesInU: int; // initially 0; this always has to be <= the previous 
                              // data member, since every node in U is uncolored;
                              // the nodes in U are adjacent to some node that has
                              // been colored during the stage;



******************************************************************************/


public Node(int key, Point loc, java.util.Set<Integer> aNIds){
// you should initialize the other data members you add here;

   id = key;
   location = loc;
   degree = aNIds.size();

   for (Integer nodeIndex : aNIds)
      adjNodeIds.add(nodeIndex);

}

// getters

public int getColor(){
   return color;
}


public int getId(){
   return id;
}

public Set<Integer> getAdjNodeIds(){
   return adjNodeIds;
}

public int getDegree(){
   return degree;
}

public Set<Node> getAdjNodes(){
   return adjNodes;
}

public Point getLocation(){
   return location;
}

// setters

public void clearColor(){
   color = UNCOLORED;
}

public void setColor(int newColor){
   color = newColor;
}


public int compareTo(Node other){
/*

   Compares first by degree, putting larger degree'd nodes as LESS,
   and uses the id in event of a tie; the smaller id will be earlier
   in the ordering

   Recall, compareTo should return

   a negative value if this < other
   0 if they are equal
   a positive value if this > other

*/
   int degreeDiff = other.degree - degree;

   if (degreeDiff == 0)
      return id - other.id;
   else 
      return degreeDiff;
}


public boolean equals(Node other){
   return other.id == id;
}



// YOU MUST CODE THIS TO SET addedToAuxList TO FALSE
public void clearAddedToAuxList(){
}


// YOU MUST CODE THIS TO CREATE THE adjNodes SET FROM THE
// adjNodeIds set AND THE ARRAY OF NODES na
// It is called in the Graph constructor
public void setAdjNodes(Node[] na){

   // iterate over adjNodeIds and for each nodeIndex in it, add
   // na[nodeIndex] TO adjNodes


}

}