package prereqchecker;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Stack;

public class DFS {
    private HashMap<String, Boolean> coursesVisited;
    private HashSet<String> completedCourses;
    private Map<String, HashSet<CourseNode>> map;
    private Stack<CourseNode> classesInOrder;
    
    // Default constructor that does nothing
    public DFS(){

    }

    public DFS(Curriculum curr,CourseNode courseName) {
        this.map = curr.getMap();
        this.coursesVisited = new HashMap<String, Boolean>();
        this.classesInOrder = new Stack<CourseNode>();
        this.completedCourses = new HashSet<String>();
        
        CourseNode[] allCourses = curr.getCourseNodes();
        for(CourseNode classes: allCourses){
            coursesVisited.put(classes.getName(), false);
        }
        dfs(map, courseName, curr);
    }
    
    private void dfs(Map<String, HashSet<CourseNode>> map, CourseNode courseName, Curriculum curr){
            coursesVisited.replace(courseName.getName(), true);
            completedCourses.add(courseName.getName());
            for(CourseNode neighbors: curr.getPrereq(courseName.getName())){
                    if(!coursesVisited.get(neighbors.getName())){
                        dfs(map, neighbors, curr);
                        classesInOrder.push(neighbors);
                    }
            }
    }

    public boolean getVisited(String courseName) {return coursesVisited.get(courseName);}
    public HashSet<String> getCompletedCourses() {return completedCourses;}
    public Stack<CourseNode> getClassesInOrder() {return classesInOrder;}
}
