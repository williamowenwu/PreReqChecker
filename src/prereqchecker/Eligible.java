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
                outFile = "eligible.out";
                specialFile = "eligible.in";
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
                outFile = "eligible.out";
                StdOut.println("Special File: ");
                specialFile = StdIn.readString();
                break;
        }

        StdOut.setFile(outFile);
        Curriculum curr = createCurr(inFile);
        DegreeNavigator nav = new DegreeNavigator(curr);
        HashSet<CourseNode> eligibleCourses = nav.eligibleFor(specialFile);

        for(CourseNode n: eligibleCourses){
            StdOut.println(n.getName());
        }

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
