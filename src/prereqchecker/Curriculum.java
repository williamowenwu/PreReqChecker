package prereqchecker;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Curriculum{
    private CourseNode[] courseNodes; 
    private int currentSize; 
    private int sizeOfArray;
    private HashMap<String, HashSet<CourseNode>> list;

    public Curriculum(CourseNode[] allCourseNodes){
        this.list = new HashMap<String, HashSet<CourseNode>>();
        this.courseNodes = new CourseNode[allCourseNodes.length];
        for(int i = 0; i <allCourseNodes.length; i++){
            HashSet<CourseNode> hs = new HashSet<>();
            this.courseNodes[i] = allCourseNodes[i];
            this.courseNodes[i].setArrayIndex(i);
            this.list.put(allCourseNodes[i].getName(), hs);
        }
    }

    /**
     * Given an inputFile, for the respective course, add its corresponding prerequisite
     * @param inFile
     */
    public void createImmediatePrereq(String inFile){
        StdIn.setFile(inFile);
        // all this for loop does is gets rid of unncesary lines
        int whatever = Integer.parseInt(StdIn.readLine());
        for(int i = 0; i < whatever; i++){
            StdIn.readLine();
        }
        
        Queue<String> temporary = new LinkedList<String>();
        int numberOfPrereq = Integer.parseInt(StdIn.readLine());

        for(int i = 0; i < numberOfPrereq; i++){
            temporary.offer(StdIn.readLine());
        }
        while(!temporary.isEmpty()){
            String poppedString = temporary.poll();
            String[] splitString = poppedString.split(" ");
            final int UNFOUND = -1;
            String courseName = splitString[0];
            String prereqName = splitString[1];
            int courseIndex = UNFOUND;
            int prereqIndex = UNFOUND;

            for(CourseNode n: courseNodes){
                if(courseIndex == UNFOUND || prereqIndex == UNFOUND){
                    if(n.getName().equals(courseName)){
                        courseIndex = n.getArrayIndex();
                    }
                    else if(n.getName().equals(prereqName)){
                        prereqIndex = n.getArrayIndex();
                    }
                }
            }
            CourseNode course = courseNodes[courseIndex];
            CourseNode prereq = courseNodes[prereqIndex];

            this.list.get(course.getName()).add(prereq);
            course.setPrereq(prereq);
            course.setPrereqNodes(prereq);
        }
    }


    //* Getter Methods
    public CourseNode[] getCourseNodes() {return courseNodes;}
    public int getCurrentSize() {return currentSize;}
    public int getSizeOfArray() {return sizeOfArray;}
    public HashMap<String, HashSet<CourseNode>> getMap() {return list;}
    public HashSet<CourseNode> getPrereq(String key) {return list.get(key);}

}
