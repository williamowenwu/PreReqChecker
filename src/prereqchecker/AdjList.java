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
        
        int totalNumberOfCourses = Integer.parseInt(StdIn.readLine());
        CourseNode[] allCourses = new CourseNode[totalNumberOfCourses];
        for(int i = 0; i < totalNumberOfCourses; i++){
            allCourses[i] = new CourseNode(StdIn.readLine());
        }

        Curriculum curriculum = new Curriculum(allCourses, totalNumberOfCourses);
        int numOfConnections = Integer.parseInt(StdIn.readLine());

        //fills the queue
        Queue<String> temporary = new LinkedList<String>();
        
        for(int i = 0; i < numOfConnections; i++){
            temporary.offer(StdIn.readLine());
        }

        //* Creates the curriculum
        while(!temporary.isEmpty()){
            String poppedString = temporary.poll();
            String[] splitString = poppedString.split(" ");
            CourseNode[] connections = new CourseNode[splitString.length];
            for(int i = 0; i < splitString.length; i++){
                connections[i] = new CourseNode(splitString[i]);
            }
            curriculum.createImmediatePrereq(connections);
        }
        printList(curriculum);

        /* Plan:
        1. for each class, create a course node with linked list attribuutes Check
        2. put everything into the curriculum (just an array of courseNodes)    Check
        3. When dealing with connections, I need to find the relationship of first index with second -> put as next of the linked list
        4. Create the graph with the hashmap/set

        */

        // if ( args.length < 2 ) {
        //     StdOut.println("Execute: java -cp bin prereqchecker.AdjList <adjacency list INput file> <adjacency list OUTput file>");
        //     return;
        // }
    }

    public static void printList(Curriculum curriculum){
        for(CourseNode n: curriculum.getCourseNodes()){
            while(n != null){
                if(n.getNext() == null){
                    StdOut.print(n.getName());
                    n = n.getNext();
                }
                else{
                    StdOut.print(n.getName() + " ");
                    n = n.getNext();
                }
            }
            StdOut.println();
        }
    }

     
    
}
