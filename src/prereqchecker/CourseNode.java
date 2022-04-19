package prereqchecker;

public class CourseNode {
    private String name;
    private boolean marked;
    private String[] connections;
    private CourseNode next;

    public CourseNode(String name){
        this.name = name;
        this.marked = false;
        //this.connections = connections;
        this.next = null;
    }
    public void setNext(CourseNode n) {this.next = n;}
    public CourseNode getNext(CourseNode n) {return n.next;}


    public void createGraph(String[] connections){
        
    }

}
