package project4part2;
import java.awt.Color;

/*************************************************************************
 *  
 *  A data type for points in the plane at real coordinates
 *  for the Graph Coloring Project.
 *
 *
 *  FOR THE GRAPH COLORING PROJECT YOU SHOULD NOT NEED TO CHANGE THIS
 *
 *************************************************************************/

public class Point implements Comparable<Point>{

    private static double SPOTSIZE = 15;
    private static double TOLERANCE = 1E-200; // for equality comparisons
                                              // in some contexts


    private double x;     // x coordinate
    private double y;     // y coordinate
    
    public Point(){}

    // constructor
    public Point(double x, double y) { 
       this.x = x;
       this.y = y;
    }

    // convert to string representation
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

   // getters and setters for the coordinates
   public double getX() { return x;}
   public double getY() { return y;}

   public void setX(double x) { this.x = x;}
   public void setY(double y) { this.y = y;}
    
   // slope other makes with this
   // returns Double.MAX_VALUE if the x coordinates are the same
   public double slopeWith(Point other){

      if (other.x == this.x)
         return Double.MAX_VALUE;
      else 
         return ((double)( y - other.y))/ (x - other.x);
   }

   // For polar coordinate representation

   // distance of the point from the origin
   public double r() { return Math.sqrt(x*x+y*y);}

   // Angle from the origin and x-axis
   public double theta() 
   { return Math.atan2(y,x);}

   // dist(p) and distSquared(p) methods: return the distance from this
   // to p, and the distance squared.

   public double dist( Point p) {
      return Math.sqrt( distSquared(p));
   }
   public double distSquared( Point p) {
    return (x-p.x)*(x-p.x) + (y-p.y)*(y-p.y);
   }

   // getter and setter for TOLERANCE
   public static double getTOLERANCE() {
      return TOLERANCE;
   }

   public static void setTOLERANCE( double newTolerance) {
     TOLERANCE = newTolerance;
   }

   // getter and setter for SPOTSIZE
   public static double getSPOTSIZE() {
      return SPOTSIZE;
   }

   public static void setSPOTSIZE( double newSPOTSIZE) {
     SPOTSIZE = newSPOTSIZE;
   }

   // exchange the values of this and p
   public void swap(Point p){
      double temp = x;
      x = p.x;
      p.x = temp;
      temp = y;
      y = p.y;
      p.y = temp;
   }

   public int compareTo(Point other){
   // compares based on lex ordering of the two coordinates
      double 
         oX = other.x,
         oY = other.y;

      if (x < oX)
         return -1;
      else if (x > oX)
         return 1;
      else  // x coords are =
         if (y < oY)
            return -1;
         else if (y > oY)
            return 1;
         else // both coords are =
            return 0;
   }

   // returns true if both coordinates are the same;
   public boolean equals(Point other){
      // in some applications, this should be
      // replaced by
      // return dist(other) < TOLERANCE;
      return x == other.x && y == other.y;
   }


   // draw in the Turtle using current SPOTSIZE
   public void draw(){
      Turtle.fly(x,y);
      Turtle.spot(SPOTSIZE);
   }

}
