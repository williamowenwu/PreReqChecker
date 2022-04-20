package prereqchecker;

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

    public boolean isValidPrereq(String fileName){
        StdIn.setFile(fileName);
        String course = StdIn.readLine();
        String potentialPrereq = StdIn.readLine();
        DFS search = new DFS();
        CourseNode prereq = new CourseNode();

        for(CourseNode node: nodes){
            if(node.getName().equals(course)){
                int start = node.getArrayIndex();
                search = new DFS(curr, start, node); 
                break;
            }
            if(node.getName().equals(potentialPrereq)){
                prereq = node;
            }
        }
        boolean contain; 
        return contain = (search.getVisited().contains(prereq)) ? true : false;
    }

    



   
}
