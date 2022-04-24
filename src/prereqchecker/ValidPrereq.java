package prereqchecker;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map;

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
        String inFile = " ";
        String outFile = " ";
        String specialFile = " ";
        int input = -1;
        StdOut.println("Hardcode or CustomInput?");
        StdOut.println("1. Hardcode \n2. Everything \n3. Special File Only");
        StdOut.print("=> ");
        while(true){
            try {
                input = StdIn.readInt();
                break;
            } catch(InputMismatchException misMatch) {
                StdOut.println("Input must be a number");
                StdOut.print("=> ");
            }
        }

        switch(input){
            case 1:
                inFile = "adjlist.in";
                outFile = "validprereq.out";
                specialFile = "validprereq.in";
                break;
            
            case 2: 
                StdOut.println("Input File: ");
                inFile = StdIn.readString();
                StdOut.println("Output File: ");
                outFile = StdIn.readString();
                StdOut.println("Special File: ");
                specialFile = StdIn.readString();
                break;
            
            case 3:
                inFile = "adjlist.in";
                outFile = "validprereq.out";
                StdOut.println("Special File: ");
                specialFile = StdIn.readString();
                break;
        }

        StdOut.setFile(outFile);
        Curriculum curr = createCurr(inFile);
        DegreeNavigator nav = new DegreeNavigator(curr);
        StdOut.print(nav.isValidPrereq(specialFile));
    }

    public static Curriculum createCurr(String inFile){
        StdIn.setFile(inFile);
        int totalNumberOfCourses = Integer.parseInt(StdIn.readLine());
        CourseNode[] allCourses = new CourseNode[totalNumberOfCourses];
        for(int i = 0; i < totalNumberOfCourses; i++){
            allCourses[i] = new CourseNode(StdIn.readLine());
        }

        Curriculum curriculum = new Curriculum(allCourses);
        curriculum.createImmediatePrereq(inFile);
        return curriculum;
    }

    public static void printList(Curriculum curriculum){
        Map<String, HashSet<CourseNode>> map = curriculum.getMap();
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
