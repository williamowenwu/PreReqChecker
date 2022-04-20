package prereqchecker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Curriculum {
    private CourseNode[] courseNodes; 
    private int currentSize; 
    private int sizeOfArray;
    private int arrayIndex;
    private CourseNode[] adj;
    private HashSet<CourseNode> courses;
    private HashMap<CourseNode, ArrayList<CourseNode>> list;
    private ArrayList<CourseNode> immediatePrereq;

    public Curriculum(CourseNode[] allCourses, int sizeOfCourses){
        this.sizeOfArray = sizeOfCourses;
        this.courseNodes = new CourseNode[sizeOfArray];
        this.currentSize = 0;
        for(int i = 0; i < sizeOfArray; i++){
            courseNodes[i] = allCourses[i];
            courseNodes[i].setArrayIndex(i);
            //this.courses.add(allCourses[i]);
            //this.list.put(allCourses[i], null);
        }
        
    }

    public Curriculum(CourseNode[] allCourseNodes){
        this.courseNodes = allCourseNodes;
        for(int i = 0; i <allCourseNodes.length; i++){
            this.list.put(allCourseNodes[i], new ArrayList<CourseNode>());
        }   
    }
    
    public void addCourse(CourseNode n){
        courseNodes[currentSize] = n;
        currentSize++;
    }


    public void createImmediatePrereq(CourseNode[] prereqs){
        CourseNode course = prereqs[0];
        CourseNode prereq = prereqs[1];

        for(int i = 0; i < list.size();i++){
            if(course.getName().equals(courseNodes[i].getName())){
                if(courseNodes[i].getNext() != null){
                    //list.get(course).add(prereq);
                    courseNodes[i].getLastCourseNode().setNext(prereq);
                    courseNodes[i].updateAdjEdges(prereq);
                    break;
                }
                else{
                    courseNodes[i].setNext(prereq);
                    courseNodes[i].updateAdjEdges(prereq);
                    break;
                }
            }
        }

    }


    public CourseNode[] getCourseNodes() {return courseNodes;}
    public int getCurrentSize() {return currentSize;}
    public int getSizeOfArray() {return sizeOfArray;}

   

    // public int getConnectedVertices(CourseNode start){
    //     CourseNode tracker = start.getNext();
    //     if(tracker == null) {
    //         int index = start.getArrayIndex();
    //         if(courseNodes[index].getName().equals(start.getName())) {return 0;}
    //         return 1 + getConnectedVertices(courseNodes[index]);
            
    //     }
    //     getConnectedVertices(start.getNext());
    //     ArrayList<Boolean> hwat = new ArrayList<Boolean>();
    //     hwat.add(false);
    //     boolean huh = hwat.get(0);
    //     hwat.set(0, true);
    //     return 1;
        

    //}

}
