package prereqchecker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Steps to implement this class main method:
 * 
 * Step 1:
 * AdjListInputFile name is passed through the command line as args[0]
 * Read from AdjListInputFile with the format:
 * 1. a (int): number of courses in the graph
 * 2. a lines, each with 1 course ID
 * 3. b (int): number of edges in the graph
 * 4. b lines, each with a source ID
 * 
 * Step 2:
 * AdjListOutputFile name is passed through the command line as args[1]
 * Output to AdjListOutputFile with the format:
 * 1. c lines, each starting with a different course ID, then 
 *    listing all of that course's prerequisites (space separated)
 */
public class AdjList {
    public static void main(String[] args) {

        StdIn.setFile("adjlist.in");
        StdOut.setFile("adjlist.out");
        test randomName = new test();
        randomName.printSomething();

        int totalNumberOfCourses = StdIn.readInt();
        Curriculum whatever = new Curriculum(totalNumberOfCourses);
        //CourseNode[] temp = new CourseNode[totalNumberOfCourses];
        HashMap<String, ArrayList> courses = new HashMap<String, ArrayList>();

        for(int i = 0; i < totalNumberOfCourses; i++){
            CourseNode bruh = new CourseNode(StdIn.readString());
            whatever.addCourse(bruh);
            
            //Todo: Keep the array, and have a marked attribute(boolean) in the class I want to make 
        }

        int numOfConnections = StdIn.readInt();
        Queue<String> temporary = new LinkedList<String>();
        for(int i = 0; i < numOfConnections; i++){
            temporary.offer(StdIn.readLine());
        }

        //* This might not be necessary 
        while(!temporary.isEmpty()){
            String poppedString = temporary.poll();
            String[] wut = poppedString.split(" ");
            //String huh = wut[0];
        }
        
        // if ( args.length < 2 ) {
        //     StdOut.println("Execute: java -cp bin prereqchecker.AdjList <adjacency list INput file> <adjacency list OUTput file>");
        //     return;
        // }

	    System.out.println("test");
    }
}
