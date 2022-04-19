package prereqchecker;

public class Curriculum {
    private CourseNode[] curriculum; 
    private int currentSize;

    public Curriculum(int sizeOfArray){
        this.curriculum = new CourseNode[sizeOfArray];
        currentSize = 0;
    }
    
    public void addCourse(CourseNode n){
        curriculum[currentSize] = n;
        currentSize++;
    }
}
