package project4part2;
import java.io.*;
import java.util.*;

/***

  Gets the data file and, if present, the milliseconds to pause, from the command
  line.  Creates the graph and colors it with the several algorithms, drawing for each,
  and displaying the color assignments for each.

  If the numnber of milliseconds to pause is given, then the program will hold
  the Turtle image for that number of ms before continuing.  Otherwise, it will hold
  the image until the user hits enter.
  
  Usage:  java GCPPart2Driver  inputFileName [millisecondsToPause]


   This driver is designed to give the grader some control over the time the Turtle
   windows are displayed.  You should not modify it.

**********************************************************************************/


public class GCPPart2Driver
{
   private static Graph g;
   private static Scanner src;
   private static int millisForPause;
   private static final int TURTLE_SIZE = 700;
   private static Scanner stdIn = new Scanner(System.in);
   private static boolean useEnterToContinue;

   public static void main(String[] args)
   {
      if(args.length == 0) {
          System.out.println("Error : Input file not specified - Terminating.");
          System.out.println("Usage : java GCPPart2Driver Input-File [ PauseTimeInMillis ]");
          return;
      }
      try {
         if(args.length == 1) {
             useEnterToContinue = true;
         }
         else if(args.length >= 2) {
             millisForPause = Integer.parseInt(args[1]);
         }
         
         Turtle.create(TURTLE_SIZE, TURTLE_SIZE);
         src = new Scanner(new File(args[0]));
         
         g = new Graph(src);
         g.colorLF();
         g.displayColoringResults();
         g.draw();
         Turtle.render();
         if(useEnterToContinue)
            stdIn.nextLine();         
         else
            Turtle.pause(millisForPause);         
         
         Turtle.clear();
         g.colorSL();
         g.displayColoringResults();
         g.draw();
         Turtle.render();
         if(useEnterToContinue)
            stdIn.nextLine();         
         else 
            Turtle.pause(millisForPause);   

      
         /***
         Uncomment whichever of these two that you do.
         ***/

         /***

         Turtle.clear();
         g.colorSLI();
         g.displayColoringResults();
         g.draw();
         Turtle.render();
         if(useEnterToContinue)
            stdIn.nextLine();
         else
            Turtle.pause(millisForPause);

         
         ***/
         
         /***

         Turtle.clear();
         g.colorRLF();
         g.displayColoringResults();
         g.draw();
         Turtle.render();
         if(useEnterToContinue)
            stdIn.nextLine();         
         else
            Turtle.pause(millisForPause);
         
         ***/
         
         Turtle.destroy();
      }
      catch(Exception e) {
          System.out.println("\nGCPPart2Driver::main - An exception was thrown !");
          e.printStackTrace();
      }
   }
}

