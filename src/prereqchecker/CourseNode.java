package prereqchecker;

import java.util.HashSet;

public class CourseNode {
    private String name;
    private int arrayIndex;
    private HashSet<CourseNode> PrereqNodes; //CourseNode Version of the CourseID
    private HashSet<String> Prereq; // Just the name of the CourseID

    public CourseNode(String name){
        this.name = name;
        this.Prereq = new HashSet<>();
        this.PrereqNodes = new HashSet<>();;
    }

    public void setPrereq(CourseNode n){
        this.Prereq.add(n.getName());
    }

    public void setArrayIndex(int index) {
        this.arrayIndex = index;
    }
    public void setPrereqNodes(CourseNode course){
        this.PrereqNodes.add(course);
    }

    public int getArrayIndex(){return arrayIndex;}
    public HashSet<CourseNode> getPrereqNodes() {return PrereqNodes;}
    public String getName() {return name;}
    public HashSet<String> getPrereqs() {return Prereq;}

}
