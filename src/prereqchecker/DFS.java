package prereqchecker;

import java.util.ArrayList;

public class DFS {
    private boolean[] marked;
    private ArrayList<CourseNode> visitedNodes;
    private ArrayList<Integer> wut[];
    
    public DFS(){

    }

    public DFS(Curriculum curr, int start, CourseNode startNode) {
        CourseNode[] classes = curr.getCourseNodes();
        int numOfVert = curr.getSizeOfArray();
        this.visitedNodes = new ArrayList<CourseNode>();
        marked = new boolean[numOfVert];
        dfs(classes, start, startNode);
    }
    
    private void dfs(CourseNode[] classes, int start, CourseNode startNode){
        marked[start] = true;
        for(CourseNode prereqs: startNode.getAdjEdges()){
            int index = prereqs.getArrayIndex();
            if(!marked[index]){
                visitedNodes.add(prereqs);
                dfs(classes, index, startNode.getNext());
            }
        }
    }

    public boolean[] getMarked() {return marked;}
    public ArrayList<CourseNode> getVisited() {return visitedNodes;}
}
