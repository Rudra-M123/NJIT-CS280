/** File: Main.java
 *  Professor: Professor Kapleau
 *  Course: CS280
 *  Section: 011
 *  
 *  The code below will have a starting position, default or user determined, and then perform a knight's tour to reach every square on the board.
 *  More comments are below.
 */

//kFLFBx9T7XJmrr3p2F/fmAj+8DS8GqnKeq/TOIAPcuI=

public class Main{
    //static variables to be used throughout
    private static int[] xMod = {1, 2, 2, 1, -1, -2, -2, -1}, yMod = {-2, -1, 1, 2, 2, 1, -1, -2}, board[] = new int[8][8];
    public static void main(String[] args) {
        int posX = 0, posY = 0;
        //optional feature to use command-line arguments to provide a custom starting location
        if(args.length == 2){
            if(-1 < Integer.parseInt(args[0]) && Integer.parseInt(args[0]) < 8 && -1 < Integer.parseInt(args[1]) && Integer.parseInt(args[1]) < 8 && board[Integer.parseInt(args[1])][Integer.parseInt(args[0])] == 0){
                posX = Integer.parseInt(args[0]);
                posY = Integer.parseInt(args[1]);
            }
            //invalid arguments
            else
                System.out.println("At least one starting position index was not between 0-7, program will use default (0, 0) instead.");
        }

        //Begin the knight's tour solution process
        if(boardSolve(posX, posY, 1))
            boardView();
        
        else{
            System.out.println("No solution can be found.");
            System.exit(0);
        }
    }

    //boardSolve() - recursively finds positions to move and progress through, solving the knight's tour from the specified starting position
    private static boolean boardSolve(int x, int y, int moves){
        if(-1 < x && x < 8 && -1 < y && y < 8 && board[y][x] == 0){
            board[y][x] = moves;
            //base case
            if(moves == 64)
                return true;
            
            else{
                //move case
                for(int i=0; i < 8; i++){
                    if(boardSolve(x + xMod[i], y + yMod[i], moves + 1)){
                        return true;
                    }
                }
                //backtrack case
                board[y][x] = 0;   
            }
        }
        return false; //illegal position therefore no solution
    }

    //boardView() - prints the completed knight's tour to the screen for the user to view.
    private static void boardView(){
        for(int[] row : board){
            for(int col : row)
                System.out.printf("%2d ", col);
            System.out.println();
        }
    }
}
