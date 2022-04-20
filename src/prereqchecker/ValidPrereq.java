package prereqchecker;

import java.util.Currency;
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
 * ValidPreReqInputFile name is passed through the command line as args[1]
 * Read from ValidPreReqInputFile with the format:
 * 1. 1 line containing the proposed advanced course
 * 2. 1 line containing the proposed prereq to the advanced course
 * 
 * Step 3:
 * ValidPreReqOutputFile name is passed through the command line as args[2]
 * Output to ValidPreReqOutputFile with the format:
 * 1. 1 line, containing either the word "YES" or "NO"
 */
public class ValidPrereq {
    public static void main(String[] args) {
        String inFile = "adjlist.in";
        String outFile = "validprereq.out";
        String prereqFile = "validprereq.in";

        Curriculum curr = createCurr(inFile);
        // cs417
        // cs111
        DegreeNavigator nav = new DegreeNavigator(curr);
        StdOut.setFile(outFile);
        StdOut.print(nav.isValidPrereq(prereqFile));
        // StdOut.print(curr.getConnectedVertices(nodeThing));
        


        // if ( args.length < 3 ) {
        //     StdOut.println("Execute: java -cp bin prereqchecker.ValidPrereq <adjacency list INput file> <valid prereq INput file> <valid prereq OUTput file>");
        //     return;
        // }

        //DegreeNavigator nav = new DegreeNavigator(curriculum);
        
    }

    public static Curriculum createCurr(String inFile){
        StdIn.setFile(inFile);

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
        return curriculum;
    }

    public static void isValidPrereq(String fileName){
        StdIn.setFile(fileName);
        String course = StdIn.readLine();
        String potentialPrereq = StdIn.readLine();


    }
}
