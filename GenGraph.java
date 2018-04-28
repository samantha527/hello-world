import java.util.*;
/*

Usage is

java GenGraph numOfNodes aveDegree

where numOfNodes is an integer and will be the number of nodes
in the generated graph, and aveDegree will be the average number
of edges out of a node.

The program generates the output file to standard out, in the format
described in the Implmentation Notes file.

numOfNodes should be >= 0 and we should have
0 <= aveDegree < numOfNodes - 1

Note, if a value larger than 2000 is entered, there will be an extra
two lines of output at the beginning of the file.

*/


public class GenGraph{

   private static final int  
      TURTLE_BOUND = 600, //  should be positive and even
      NODE_BOUND = 2000,
      HALF_TURTLE = TURTLE_BOUND/2,
      RADIUS = HALF_TURTLE - 10;

   private static Object[] an;

   private static int numOfN,totalEdges, neededEdges;
   private static double aveLen;

   private static Random rnd = new Random(System.currentTimeMillis());

   public static void main (String[] args){
      double gaus, radianIncrem = 0, currAngle = 0.0, x, y;
      int v,degree;
      Set<Integer> adjNodes = new HashSet<Integer>(); 

      if (args.length < 2){
         System.out.println("Expecting num of nodes and average degree of the nodes.");
         return;
      }
      else{
         try{
            numOfN = Integer.parseInt(args[0]);
            aveLen = Double.parseDouble(args[1]);
    
         }
         catch (Exception e){
            System.out.println("Command line arguments failed to parse. "
            + "Need an int and double.");
            return;
         }
         if (numOfN < 0){
            System.out.println("Num of nodes should be nonnegative.");
            return;
         }
         if (numOfN > NODE_BOUND){
            System.out.println("Input for number of nodes was too large.\n" +
            "Using " + NODE_BOUND + " instead.");
            numOfN = NODE_BOUND;
         }
         if (aveLen > numOfN - 1){
            System.out.println("Average too large. Must be <= " + (numOfN - 1) + '.');
            return;
         }
         System.out.println("" + numOfN);
         if (numOfN > 0)
            radianIncrem = 2 * Math.PI / numOfN;

         // because each edge will occur in two adjacency lists,
         // the average degree * number of nodes / 2 is the number of needed
         // edges to obtain the average; we calculate it here
         neededEdges = (int)(aveLen * numOfN / 2);

         /*
            an[i] are the indices of the nodes that are
            adjacent to node i

         */
         // have to do it this way because of Java's faulty 
         // implementation of generics
         an = new Object[numOfN];
         int i;
         // for each node, we create a set of the nodes it's
         // adjacent to;
         for (i = 0; i < numOfN; i++)
            an[i] = new TreeSet<Integer>();

         int n1,n2;

         while (neededEdges > totalEdges){
            // generate a random pair of distinct nodes
            do{
               n1 = rnd.nextInt(numOfN);
               n2 = rnd.nextInt(numOfN);
            }while(n1 == n2);
            // check if the edge is already there
            if (!((TreeSet) (an[n1])).contains(n2)){
               // add an edge for the two nodes
               ((TreeSet)(an[n1])).add(n2);
               ((TreeSet)(an[n2])).add(n1);
               totalEdges++;
            }
         }
         
         /***

             write the file

             format is
    
             N(the total number of nodes)
             data for node 0
             data for node 1
             ...
             data for node N-1


             and each data for a node is given in two lines

             xcoord ycoord
             <indices of adjacent nodes in a list>

             where the coordinates will be doubles in the range of the Turtle size
             and the list will be the indices (0 to N-1) of the adjacent nodes; if
             the node is isolated, there will still be a blank line after the coordinates.

         ***/
         Iterator<Integer> iter;
         for (i = 0;  i < numOfN; i++){
            x = Math.cos(currAngle) * RADIUS + HALF_TURTLE;
            y = Math.sin(currAngle) * RADIUS + HALF_TURTLE;
            currAngle += radianIncrem;
            
            System.out.println("" + x + ' ' + y);

            iter = ((TreeSet)(an[i])).iterator();
            while (iter.hasNext())
               System.out.print("" + iter.next() + ' ');

            System.out.println();
            
         }                
      }
   }
}      
         


   

