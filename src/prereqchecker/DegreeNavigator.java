package prereqchecker;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class DegreeNavigator {
    private String inputFile;
    private String outputFile;
    private CourseNode[] nodes;
    private Curriculum curr; 

    //* Constructors
    public DegreeNavigator(){

    }

    public DegreeNavigator(Curriculum curr){
        this.curr = curr;
        this.nodes = curr.getCourseNodes();
    }
    //* Constructors



    public void displaySmth(){
        StdOut.print(StdIn.readAll());
    }

    public String isValidPrereq(String fileName){
        StdIn.setFile(fileName);
        String course = StdIn.readLine();
        String potentialPrereq = StdIn.readLine();
        DFS search = new DFS();
        CourseNode prereq = new CourseNode();

        for(CourseNode node: nodes){
            if(node.getName().equals(course)){
                search = new DFS(curr,node); 
                break;
            }
            if(node.getName().equals(potentialPrereq)){
                prereq = node;
            }
        }
        String answer = (search.getVisited(potentialPrereq)) ? "YES" : "NO";
        return answer; 
    }

    public HashSet<CourseNode> eligibleFor(String fileName){
        StdIn.setFile(fileName);
        int numOfTakenCourses = Integer.parseInt(StdIn.readLine());
        HashSet<CourseNode> takenCourses = new HashSet<>();
        HashSet<String> completedCourses = new HashSet<>();
        HashSet<CourseNode> eligibleCourses = new HashSet<>();
        for(int i = 0; i < numOfTakenCourses; i++){
            CourseNode n = new CourseNode(StdIn.readLine());
            takenCourses.add(n);
        }
        
        for(CourseNode n: takenCourses){
            DFS traverse = new DFS(curr, n);
            for(String node : traverse.getCompletedCourses()){
                completedCourses.add(node);
            }
        }
        
        for(CourseNode courseName: curr.getCourseNodes()){
            if(!completedCourses.contains(courseName.getName())){
                if(completedCourses.containsAll(courseName.getImmediatePrereqs())){
                    eligibleCourses.add(courseName);
                }
            }
        }
        return eligibleCourses;
        // if you did not take the course
            // if it satisfies the required course
                // add it as a eligible course
    }

    



   
}
