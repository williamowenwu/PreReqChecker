package prereqchecker;

import java.util.ArrayList;
import java.util.HashSet;

public class CourseNode {
    private String name;
    private boolean marked;
    private CourseNode next;
    private CourseNode lastNode;
    private int arrayIndex;
    private ArrayList<CourseNode> AdjEdges;
    private HashSet<String> Prereq;

    public CourseNode(String name){
        this.name = name;
        this.marked = false;
        this.next = null;
        this.Prereq = new HashSet<String>();
        // for(CourseNode n: prereqs){
        //     this.Prereq.add(n);
        // }
        this.AdjEdges = new ArrayList<CourseNode>();
    }
    public CourseNode(){
        
    }

    public void setNext(CourseNode n) {
        this.next = n;
        lastNode = n;
    }
    public void setPrereq(CourseNode n){
        this.Prereq.add(n.getName());
    }


    public void setArrayIndex(int index) {
        this.arrayIndex = index;
    }
    public void updateAdjEdges(CourseNode course){
        this.AdjEdges.add(course);
    }


    public CourseNode getNext() {return next;}
    public CourseNode getLastCourseNode() {return lastNode;}
    public int getArrayIndex(){return arrayIndex;}
    public ArrayList<CourseNode> getAdjEdges() {return AdjEdges;}
    public String getName() {return name;}
    public boolean getMarked() {return marked;}
    public HashSet<String> getImmediatePrereqs() {return Prereq;}

}
