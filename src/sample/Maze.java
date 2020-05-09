package sample;

import java.util.Objects;
import java.util.Scanner;

class Pos{
    private int x,y;

    public Pos(int x0,int y0){
        x=x0;
        y=y0;
    }

    public void setX(int x0){
        x=x0;
    }

    public void setY(int y0){
        y=y0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pos pos = (Pos) o;
        return x == pos.x &&
                y == pos.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

public class Maze {

    static int nrows,ncols;


    public static void setNcols(int ncols) {
        Maze.ncols = ncols;
    }

    public static void setNrows(int nrows) {
        Maze.nrows = nrows;
    }

    public static int getNcols() {
        return ncols;
    }

    public static int getNrows() {
        return nrows;
    }


    public static Pos findElem(String[][]arr, String str){

        for(int i=0;i<nrows;i++)
            for(int j=0;j<ncols;j++) {
                if (arr[i][j].equals(str))
                    return new Pos(j, i);
            }

        return null;
    }

    public static Pos getPreviousPos(int[][] solution,String[][] maze,Pos pos){
        int i=pos.getY(),j=pos.getX();

        int k = solution[i][j];

        if(j-1>=0 && solution[i][j-1]==k-1)
            return new Pos(j-1,i);

        else if(i-1>=0 && solution[i-1][j]==k-1)
            return new Pos(j,i-1);

        else if(j+1<=ncols-1 && solution[i][j+1]==k-1)
            return new Pos(j+1,i);

        else if(i+1<=nrows-1 && solution[i+1][j]==k-1)
            return new Pos(j,i+1);

        else
            try {
                throw new Exception("Can't find previous cell...");
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }


        return null;
    }

    public static Stack<Pos> getPath(int[][]solution,String[][] maze){ // reverse - from the end to the beginning
        Stack<Pos> result = new Stack<Pos>();

        Pos currPos = findElem(maze,"F");//the end pos

        Pos startPos = findElem(maze,"S");

        while(!currPos.equals(startPos)){
            result.push(currPos);
            currPos = getPreviousPos(solution,maze,currPos);
        }

        result.push(currPos);

        return result;
    }


    public static void main(String[] args) {
        /*
        String[][] maze = {
                {"S", "*", "*", "0", "F"},
                {"0", "0", "*", "0", "*"},
                {"0", "0", "0", "0", "0"},
                {"*", "*", "*", "0", "*"},
                {"0", "0", "0", "0", "0"}};*/

        Scanner in = new Scanner(System.in);
        System.out.println("nrows :   ");
        nrows = in.nextInt();
        System.out.println("ncols :   ");
        ncols = in.nextInt();


        String[][] maze = new String[nrows][ncols];


        System.out.println("maze :   ");

        for(int i=0;i<nrows;i++)
            for(int j=0;j<ncols;j++)
                maze [i][j] = in.next("[S*0F]");


        int[][] solution = new int[nrows][ncols];

        for(int i=0;i<nrows;i++)
            for(int j=0;j<ncols;j++)
                solution[i][j]=Integer.MAX_VALUE;// distance to the cell

        Pos start = findElem(maze,"S");



        int startX=start.getX(),startY=start.getY();
        solution[startY][startX]=0;


        markNeighbors(solution,maze,startY,startX,0);

        solutionOutput(solution,maze);

        Stack<Pos> res = getPath(solution,maze);


        while(!res.isEmpty()){

            Pos currPos = res.pop();
            System.out.println("(" + currPos.getY() +" , " + currPos.getX() + ")");
        }

    }

    public static void markNeighbors(int[][] solution,String[][] maze, int y0,int x0, int k){


        if(x0-1>=0&&( k+1<solution[y0][x0-1]) && !maze[y0][x0-1].equals("*"))
        {
            solution[y0][x0-1]=k+1;
            markNeighbors(solution,maze,y0,x0-1,k+1);
        }


        if(y0-1>=0&&( k+1<solution[y0-1][x0]) && !maze[y0-1][x0].equals("*"))
        {
            solution[y0-1][x0]=k+1;
            markNeighbors(solution,maze,y0-1,x0,k+1);
        }


        if((x0+1<=ncols-1)&&( k+1<solution[y0][x0+1]) && !maze[y0][x0+1].equals("*"))
        {
            solution[y0][x0+1]=k+1;
            markNeighbors(solution,maze,y0,x0+1,k+1);
        }

        if((y0+1<=nrows-1)&&( k+1<solution[y0+1][x0]) && !maze[y0+1][x0].equals("*"))
        {
            solution[y0+1][x0]=k+1;
            markNeighbors(solution,maze,y0+1,x0,k+1);
        }

    }

    public static void solutionOutput(int [][] solution, String[][] maze){
        for(int i=0;i<nrows;i++)
        {
            for(int j=0;j<ncols;j++) {
                if(maze[i][j].equals("*"))
                    System.out.print("*");
                else
                    System.out.print(solution[i][j]);


                System.out.print("   ");
            }


            System.out.println();


        }
    }

}
