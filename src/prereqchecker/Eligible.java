package prereqchecker;

import java.util.*;

/**
 * 
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
 * EligibleInputFile name is passed through the command line as args[1]
 * Read from EligibleInputFile with the format:
 * 1. c (int): Number of courses
 * 2. c lines, each with 1 course ID
 * 
 * Step 3:
 * EligibleOutputFile name is passed through the command line as args[2]
 * Output to EligibleOutputFile with the format:
 * 1. Some number of lines, each with one course ID
 */
public class Eligible {
    public static void main(String[] args) {
        String inFile = "adjlist.in";
        String outFile = "validprereq.out";
        String eligibleFile = "eligible.in";

        Curriculum curr = createCurr(inFile);

        // if ( args.length < 3 ) {
        //     StdOut.println("Execute: java -cp bin prereqchecker.Eligible <adjacency list INput file> <eligible INput file> <eligible OUTput file>");
        //     return;
        // }

	
    }
    public static Curriculum createCurr(String inFile){
        StdIn.setFile(inFile);

        int totalNumberOfCourses = Integer.parseInt(StdIn.readLine());
        CourseNode[] allCourses = new CourseNode[totalNumberOfCourses];
        for(int i = 0; i < totalNumberOfCourses; i++){
            allCourses[i] = new CourseNode(StdIn.readLine());
        }

        Curriculum curriculum = new Curriculum(allCourses);
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

    public static void printList(Curriculum curriculum){
        Map<String, ArrayList<CourseNode>> map = curriculum.getMap();
        Iterator it = map.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry pair = (Map.Entry)it.next();
            StdOut.print(pair.getKey() + " ");
            for(CourseNode prereq: map.get(pair.getKey())){
                StdOut.print(prereq.getName() + " ");
            }
            StdOut.println();
        }
    }
}
