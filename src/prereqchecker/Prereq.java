package prereqchecker;

import java.util.HashMap;
import java.util.ArrayList;

public class Prereq {
    private Curriculum curriculum;

    public Prereq(Curriculum curriculum){
        this.curriculum = curriculum;

    }

    public void createImmediatePrereq(CourseNode[] prereqs){
        CourseNode course = prereqs[0];
        CourseNode prereq = prereqs[1];
        CourseNode[] allCourses = curriculum.getCourseNodes();

        for(int i = 0; i < curriculum.getSizeOfArray();i++){
            if(course.getName().equals(allCourses[i].getName())){
                if(allCourses[i].getNext() != null){
                    allCourses[i].getLastCourseNode().setNext(prereq);
                    break;
                }
                else{
                    allCourses[i].setNext(prereq);
                    break;
                }
            }
        }

    }
    
}

