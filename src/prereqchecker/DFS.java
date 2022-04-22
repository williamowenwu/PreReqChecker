package prereqchecker;

import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

public class DFS {
    // private boolean[] marked;
    private ArrayList<CourseNode> visitedNodes;
    private HashMap<String, Boolean> coursesVisited;
    private HashSet<String> completedCourses;
    //private ArrayList<Integer> wut[];
    
    public DFS(){

    }

    public DFS(Curriculum curr,CourseNode courseName) {
        Map<String, ArrayList<CourseNode>> map = curr.getMap();
        CourseNode[] allCourses = curr.getCourseNodes();
        this.coursesVisited = new HashMap<String, Boolean>();
        for(CourseNode classes: allCourses){
            coursesVisited.put(classes.getName(), false);
        }
        this.completedCourses = new HashSet<String>();
        //int numOfVert = curr.getSizeOfArray();
        this.visitedNodes = new ArrayList<CourseNode>();
        //marked = new boolean[map.get(courseName).size()];
        //this.originalCourseNode = courseName;
        dfs(map, courseName, curr);
    }
    
    private void dfs(Map<String, ArrayList<CourseNode>> map, CourseNode courseName, Curriculum curr){
            coursesVisited.replace(courseName.getName(), true);
            completedCourses.add(courseName.getName());
            for(CourseNode neighbors: curr.getPrereq(courseName.getName())){
                    if(!coursesVisited.get(neighbors.getName())){
                        dfs(map, neighbors, curr);
                        //completedCourses.add(courseName);
                        visitedNodes.add(neighbors);
                    }
            }
    }

    //public boolean[] getMarked() {return marked;}
    public boolean getVisited(String courseName) {
        return coursesVisited.get(courseName);
    }

    public HashSet<String> getCompletedCourses() {return completedCourses;}
}
