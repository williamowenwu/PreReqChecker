package prereqchecker;

import java.util.HashMap;
import java.util.HashSet;

public class DegreeNavigator {
    private CourseNode[] nodes;
    private Curriculum curr;
    private HashMap<String, HashSet<CourseNode>> map;

    //* Constructors

    public DegreeNavigator(){

    }

    public DegreeNavigator(Curriculum curr){
        this.curr = curr;
        this.nodes = curr.getCourseNodes();
        this.map = curr.getMap();
    }
    //* Constructors

    /**
     *  Given a Course name, it will get the correct respective CourseNode
     * @param name String Course name E.g. "cs336"
     * @return CourseNode of the given string
     */
    private CourseNode findRespectiveCourseNode(String name){
        int CourseIndex = -1;
        for(CourseNode n: curr.getCourseNodes()){
            CourseIndex = (n.getName().equals(name)) ? n.getArrayIndex() : -1;
            if(CourseIndex != -1){break;}
        }
        CourseNode Course = curr.getCourseNodes()[CourseIndex];
        return Course;
    }
    /**
     * Given a file name with course and potential prereq respectively, this method will dictate if the prereq will pose as a problem being a prereq. 
     * E.g. cs211 cannot be a prereq for cs111
     * @param fileName File containing n courses and m connections(relationship between one prereq and a course)
     * @return String containing either "YES" or "NO"
     */
    public String isValidPrereq(String fileName){
        StdIn.setFile(fileName);
        String course = StdIn.readLine();
        String potentialPrereq = StdIn.readLine();
        DFS search = new DFS();

        for(CourseNode node: nodes){
            if(node.getName().equals(course)){
                search = new DFS(curr,node); 
                break;
            }
        }
        String answer = (search.getCompletedCourses().contains(potentialPrereq)) ? "YES" : "NO";
        return answer; 
    }

    /**
     * The method returns all course you are eligible for with the amount of classes you already took
     * @param fileName File with c courses already taken(does not include prerequisites of those classes)
     * @return HashSet of CourseNodes that contains the classes the student is eligble for
     */
    public HashSet<CourseNode> eligibleFor(String fileName){
        StdIn.setFile(fileName);
        HashSet<CourseNode> takenCourses = new HashSet<>();
        HashSet<String> completedCourses = new HashSet<>();
        HashSet<CourseNode> eligibleCourses = new HashSet<>();

        int numOfTakenCourses = Integer.parseInt(StdIn.readLine());
        for(int i = 0; i < numOfTakenCourses; i++){
            CourseNode n = new CourseNode(StdIn.readLine());
            takenCourses.add(n);
        }
        
        for(CourseNode n: takenCourses){
            DFS traverse = new DFS(curr, n);
            for(String node : traverse.getCompletedCourses()){
                completedCourses.add(node);
            }
        }
        //* if you didn't take the course and satisfies the prereq of the course you want to take, add it to eligble.
        for(CourseNode courseName: curr.getCourseNodes()){ 
            if(!completedCourses.contains(courseName.getName())){ 
                if(completedCourses.containsAll(courseName.getPrereqs())){
                    eligibleCourses.add(courseName);
                }
            }
        }
        return eligibleCourses;
    }
    /**
     * 
     * @param inFile Infile with target course, d courses already completed (not including any prerequisites)
     * @return
     */
    public HashSet<CourseNode> classesNeedToTake(String inFile){
        StdIn.setFile(inFile);
        String target = StdIn.readLine();

        HashSet<CourseNode> classesNeeded = new HashSet<>();
        HashSet<String> takenCourses = new HashSet<>();

        // Gets the courseNode of the respective target String
        CourseNode targetCourse = findRespectiveCourseNode(target);

        int numOfTakenCourses = Integer.parseInt(StdIn.readLine());
        for(int i = 0; i < numOfTakenCourses; i++){
            CourseNode n = findRespectiveCourseNode(StdIn.readLine());
            DFS trav = new DFS(curr, n);
            takenCourses.addAll(trav.getCompletedCourses());
        }
        needToTake(targetCourse, takenCourses, classesNeeded);
        return classesNeeded;
    } 

    /**
     * Given a target course, this method will recursively call itself and get all the neccessary courses
     * @param target Target class student wants to take
     * @param takenCourses  A Hashset of all taken courses with no indirect/direct preqs
     * @param classesNeeded Hashset of the classes the student needs to reach the target course
     */
    private void needToTake(CourseNode target, HashSet<String> takenCourses, HashSet<CourseNode> classesNeeded){
        HashSet<String> targetPrereqs = target.getPrereqs();
            for(String c : targetPrereqs){
                if(!takenCourses.containsAll(targetPrereqs)){ 
                    if(!takenCourses.contains(c)){
                        target = findRespectiveCourseNode(c);
                        needToTake(target, takenCourses, classesNeeded);
                        classesNeeded.add(target);
                    }
                }   
            }
    }
    /**
     * 
     * @param inFile Infile with target course, d courses already completed (not including any prerequisites)
     * @return A HashMap with key Integer(For the semester) and value: HashSet of coursenodes that are the classes you will take that semester.
     */
    public HashMap<Integer, HashSet<CourseNode>> planClasses(String inFile){
        StdIn.setFile(inFile);
        HashSet<CourseNode> takenCourses = new HashSet<>();
        
        CourseNode courseStudentWants = findRespectiveCourseNode(StdIn.readLine());
        int numOfTakenCourses = Integer.parseInt(StdIn.readLine());
        for(int i = 0; i < numOfTakenCourses; i++){
            CourseNode node = findRespectiveCourseNode(StdIn.readLine());
            takenCourses.add(node);
        }

        //* takenCourses will now include all of the prereqs. E.g. takenCourse of 112 will now also have 111
        HashSet<CourseNode> copyOf = new HashSet<>();
        for(CourseNode n : takenCourses){
            DFS trav = new DFS(curr, n);
            HashSet<String> completedCourses = trav.getCompletedCourses();
            for(String s: completedCourses){
                copyOf.add(findRespectiveCourseNode(s));
            }
        }
        takenCourses.addAll(copyOf);
        copyOf.clear();

        HashMap<Integer, HashSet<CourseNode>> plan = new HashMap<>();
        HashSet<CourseNode> currentlyTaking = new HashSet<>();
        HashSet<CourseNode> allCoursesNeeded = new HashSet<>();
        allCoursesNeeded = classesNeedToTake(inFile);

        int semester = 1;
        //* Goes semester by semester and if you have the prereq for the neccessary class take it in the corresponding semester
        while(!allCoursesNeeded.isEmpty()){
            for(CourseNode node: allCoursesNeeded){
                if(node.getPrereqNodes().isEmpty() || takenCourses.containsAll(node.getPrereqNodes())){
                    currentlyTaking.add(node);
                }
            }
            if(!currentlyTaking.isEmpty()){
                HashSet<CourseNode> copy = new HashSet<>();
                copy.addAll(currentlyTaking); // copy the classes Im taking
                plan.put(semester, copy);
                allCoursesNeeded.removeAll(copy);
                takenCourses.addAll(copy);
                currentlyTaking.clear();
                semester++;
            }
        }
        return plan;
    }

}
