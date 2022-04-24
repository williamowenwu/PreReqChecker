package prereqchecker;

import java.util.*;

public class SchedulePlan {
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
                outFile = "scheduleplan.out";
                specialFile = "scheduleplan.in";
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
                outFile = "scheduleplan.out";
                StdOut.println("Special File: ");
                specialFile = StdIn.readString();
                break;
        }

        StdOut.setFile(outFile);
        Curriculum curr = createCurr(inFile);
        DegreeNavigator nav = new DegreeNavigator(curr);
        HashMap<Integer, HashSet<CourseNode>> n = nav.planClasses(specialFile);
        printValues(n);

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

    public static void printMap(HashMap<Integer,HashSet<CourseNode>> map){
        StdOut.println(map.values().size());
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

    public static void printValues(HashMap<Integer,HashSet<CourseNode>> map){
        StdOut.println(map.values().size());
        for(HashSet<CourseNode> n: map.values()){
            for(CourseNode node : n){
                StdOut.print(node.getName() + " ");
            }
            StdOut.println();
        }
    }
}

