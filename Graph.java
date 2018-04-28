package project4part2;
import java.awt.*;
import java.util.*;

/***

A class for representing the graph in the Graph Coloring problem.


The constructor has been coded except you will need to code the
setAdjNodes method of Node to have it work.

The rest of the methods are just given with stubs and a spec.

The data members you will need have been declared.  You will need
to define methods to implement the algorithms for coloring the
graph and the draw method.
	
Note, before embarking on one of the algorithms, the color data member of
every node should be -1, and the colorAssignments map object should be
empty.  For every coloring algorithm except SLI, once a node is 
assigned a color, it is not changed.  For those algorithms, you can
update the colorAssignments map object as you assign the node a color.
For SLI, since the colors may be changed multiple times, it is better
to wait until all the nodes have been assigned a color and then construct
the map object from the allNodes array by iterating over the array.

The following Map methods would be useful.

void clear() // empties the map table

V get(K k) // returns null if there is no entry with key value k
           // else the data value associated with k in the table

void put(K k, V v) // installs v as the entry for k in the table,
                   // overwriting any prior entry for k

Note that the data values stored in the table are TreeSet's of Integer,
and so are a reference type.  To add another node v to the set colored i,
the following code is adequate

TreeSet<Integer> alreadyColored = colorAssignments.get(i);

if (alreadyColored == null){ // first time any node is assigned i
   alreadyColored = new TreeSet<Integer>();
   alreadyColored.add(v.getId());
   colorAssignments.put(i,alreadyColored);
}
else // not the first time any node has been assigned i
   alreadyColored.add(v.getId());

In all but SLI, if you keep track of the current maximum used color 
value, you can simplify the code by testing

if (i > currentMaxColor){ this is the first time for i
   create a new TreeSet for { v.getId() }
   colorAssignments.put(i, the new treeset);
}
else // i has been used already, and so there will be an entry 
     // for i in the map object
   colorAssignments.get(i).add(v.getId());


ADDITIONAL NEEDED METHODS
=========================

These are each discussed in more depth in the ImplementationNotes.txt
file.

void sequentiallyAssignColors()  
// not called from the driver, so technically not required, but
// highly recommended since both LF and SL use it; 
// iterates down the allNodes array and assigns the colors;
// it assumes that all the colors are currently -1, so if that is not
// the case, they all need to be set back to -1;
// discussed in more depth in the implementation notes

void sequentiallyAssignColorsWithInterchange()
// again, not called by the driver, but highly recommended;
// modifies the basic sequential assignment algorithm to try to
// avoid using the next higher color by looking for a chance to interchange
// a pair of node colors

void colorLF() 
// required
// colors the nodes in the allNodes array using the LF algorithm;

void colorSL()
// required
// colors the nodes in the allNodes array using the SL algorithm;


void colorSLI()
// either this one or RLF is required in the basic assignment

void colorRLF()
// either this one or SLI is required in the basic assignment

void displayColoringResults()
// this is coded below; you should not change it;



*************************************************************************************/

public class Graph{

   // code to establish a palette of colors with some contrast

   // only display the nodes in the Turtle
   // if there are fewer than 41 of them
   private static final int MAX_VERTICES_FOR_TURTLE = 40;

   // a static array to convert an int color to a real Color to paint with
   private static Color[] palette = new Color[MAX_VERTICES_FOR_TURTLE];
   static{ // static initialization block

      // use primary colors FIRST
      palette[0] = new Color(204,0,204); //  purple
      palette[1] = Color.RED;
      palette[2] = Color.YELLOW;
      palette[3] = Color.BLUE;
      palette[4] = Color.LIGHT_GRAY;
      palette[5] = Color.GREEN;
      palette[6] = new Color(0    ,100  ,0  );
      palette[7] = new Color(255    ,20   ,147); 
      palette[8] = Color.PINK;
      palette[9] = Color.BLACK;
      
      palette[10] = new Color( 60,179,113); 
      palette[11] = Color.CYAN;
      palette[12] = Color.DARK_GRAY;
      palette[13] = new Color(102,0,0);
      palette[14] = new Color(220    ,20   ,60 );
      palette[15] = new Color(160    ,82   ,45 ); 
      palette[16] = new Color(120    ,120  ,120); 
      palette[17] = new Color(255    ,127  ,36 ); 
      palette[18] = new Color(0      ,205  ,0  ); 
      palette[19] = new Color(222    ,184  ,135); 

      palette[20] = new Color(255    ,215  ,0  ); 
      palette[21] = new Color(178    ,34      ,34   );
      palette[22] = new Color(  0    ,128  ,128); 
      palette[23] = new Color(255    ,236  ,139);
      palette[24] = new Color(0    ,0    ,102);
      palette[25] = new Color(139   ,69   ,19 ); 
      palette[26] = new Color(135       ,38      ,87   );
      palette[27] = new Color(188   ,210  ,238); 
      palette[28] = new Color(75    ,0    ,130); 
      palette[29] = new Color(104   ,34   ,139); 

      palette[30] = new Color(148    ,0   , 211); 
      palette[31] = new Color(205    ,85      ,85   );
      palette[32] = new Color(139    ,117     ,0    );
      palette[33] = new Color(128    ,0    ,0  );
      palette[34] = new Color(153,255,153);// a lighter green
      palette[35] = new Color(255    ,62   ,150); 
      palette[36] = Color.MAGENTA;
      palette[37] = new Color(255    ,160  ,122); 
      palette[38] = new Color(65     ,105  ,225); 
      palette[39] = new Color(140    ,149  ,237); 


   }

   // instance specific data members

   private int numberOfNodes;  // the number of nodes in this graph
   private Node[] allNodes;    // an array of the nodes

   // records for each color used, the node ids of the nodes 
   // that were colored that color
   private TreeMap<Integer,TreeSet<Integer>> colorAssignments
        = new TreeMap<Integer,TreeSet<Integer>>();


   /*

      To display the assignment of colors to the nodes at the screen.
      Assumes the data member has been loaded.   

      Iterates over the colorAssignments map object for each color from 0
      to the max color used, and lists to System.out  the color as an int(plainly labeled) and
      the list of node ids that were colored that color, formatted for easy reading;

       The following scheme is used.

       Color c: n1 n2 n3 ...
 
       with <= 20 node ids per line. If there are more than 20, indent the subsequent
       lines 5 spaces, and put twenty more on the next line, for example,
   
       Color 0: 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 56 82 90 91
            100 101 102 103 104 105 106 107 108 109 110 111 112 113 114 115 116 117 118 119
            123 124 125
       Color 1: 16 17 18 19 20 21 22 23 24 25 26 
    
       etc. 
   */
public void displayColoringResults(){

   int color, count;
   Iterator<Integer>
      iter = colorAssignments.keySet().iterator(),
      iter2;
   StringBuilder sb;

   System.out.println("\nThe algorithm used " + colorAssignments.size() + " colors to color "
   + allNodes.length + " nodes.");
   System.out.println("\nThe nodes were colored as follows.");
   while (iter.hasNext()){
      sb = new StringBuilder();
      color = iter.next();
      iter2 = colorAssignments.get(color).iterator();
      count = 0;
      sb.append("Color " + color + ": ");
      while (iter2.hasNext()){
         sb.append(iter2.next());
         count++;
         if (count == 20){
            count = 0;
            sb.append("\n     ");
         }
         else
            sb.append(' ');
      }
      if (count > 0)
         sb.append('\n');
      System.out.print(sb.toString());
   }
}
      

// constructor for the graph from the file, assumed formatted
// as in the assignment discussion
public Graph(Scanner src) throws Exception{
   int i, j, len;
   TreeSet<Integer> aPnts = new TreeSet<Integer>();
   double x,y;

   if (src == null || !src.hasNextInt())
      throw new Exception("Source file does not begin with an integer.");
   else{
      len = src.nextInt();
      if (len < 0)
         throw new Exception("Number of points < 0.");

      allNodes = new Node[len];

      // get each node and add it to the array
      for (i = 0; i < len; i++){
         x = src.nextDouble();
         y = src.nextDouble();
         aPnts.clear();
         src.nextLine();
         String s = src.nextLine();
         Scanner lineSc = new Scanner(s);
         while (lineSc.hasNextInt())
            aPnts.add(lineSc.nextInt());
         allNodes[i] = new Node(i, new Point(x,y), aPnts);
      }
      // YOU MUST CODE setAdjNodes
      for (i = 0; i < len; i++)
         allNodes[i].setAdjNodes(allNodes);
   }
}


// The basic assignment is for you to do both LF and SL and
// either one of SLI and RLF.  The other can be done for extra
// credit.

// your implementation of the LF algorithm
public void colorLF(){
}

// your implementation of the SL algorithm
public void colorSL(){
}

// your implementation of the SLI algorithm
/*

I recommend that you code a method

private TreeSet<ConnectedComponent> calculateCCs(int k, Node curr, int i, int j)

to obtain a set of ConnectedComponents for swapping colors i and j, or null
if i and j is no help.

The input parameters are

   k : the largest color that has been used so far; the we are trying to avoid
       using k+1

   curr: the node we are coloring that appears to need to use color k+1 for the first
         time

   i,j :  0 <= i < j <= k; these are two colors we are considering for exchange
         to avoid using k+1

   it returns null if swapping i and j does not fix the problem, else it returns
   a set of ConnectedComponents  of the graph induced by the nodes colored i or
   j, exactly the connected components that connect a node colored i with an edge
   to curr;

   In either case, it restores the addedToAuxList data member of all of
   the nodes it see to false before returning.

*********************************************************************************/
public void colorSLI(){
}

// your implementation of the RLF algorithms
public void colorRLF(){
}



/*

Draw the nodes in the Turtle in the color they were colored(use
Color.BLACK if they were not colored) and draw the edges between
nodes in Color.BLACK.

*************************************************************/
public void draw(){
}



      
}
