/** File: Main.java
 *  Professor: Professor Kapleau
 *  Name: Rudra Mehta (rm279@njit.edu)
 *  Course: CS280
 *  Section: 011
 *  
 *  The code below will have a starting position, default or user determined, and then perform a knight's tour to reach every square on the board.
 *  Detailed comments are below
 */

//kFLFBx9T7XJmrr3p2F/fmAj+8DS8GqnKeq/TOIAPcuI=

public class Main{
    //declare class variables to be used throughout
    private static int moves = 0, posX = 0, posY = 7, modif[][] = {{1, -2}, {2, -1}, {2, 1}, {1, 2}, {-1, 2}, {-2, 1}, {-2, -1}, {-1, -2}}, board[][] = new int[8][8];
    private static String path = "";

    public static void main(String[] args){
        //optional feature to use command-line arguments to provide a custom starting location
        if(args.length == 2){
            if(check(Integer.parseInt(args[0]), Integer.parseInt(args[1]), -1)){
                posX = Integer.parseInt(args[0]);
                posY = Integer.parseInt(args[1]);
            }

            //invalid arguments
            else
                System.out.println("At least one starting position index was not between 0-7, program will use default (0, 7) instead.");
        }
        
        //mark starting position and start solving
        board[posY][posX] = ++moves;
        boardSolve();
    }

    //check() - a method that will see if the given movement will yield a legal or illegal position to prevent illegals moves
    private static boolean check(int x, int y, int opt){
        if(opt != -1){
            x += modif[opt][0];
            y += modif[opt][1];
        }
        return -1 < x && x < 8 && -1 < y && y < 8 && board[y][x] == 0;
    }

    //check() - a method that will move to the desired position and place a move marker on the board
    private static void move(int opt){
        path += opt;
        posX += modif[opt][0];
        posY += modif[opt][1];
    }

    private static boolean moveable(){
        return (check(posX, posY, 0) || check(posX, posY, 1) || check(posX, posY, 2) || check(posX, posY, 3) || check(posX, posY, 4) || check(posX, posY, 5) || check(posX, posY, 6) || check(posX, posY, 7));
    }

    //boardSolve() - a method that will recursively find positions to move and progress through, solving the knight's tour from the specified starting position
    private static void boardSolve(){
        //base case
        if(moves == 64){
            System.out.println("Solved!");
            boardView();
        }

        //move case
        else if(moveable()){
            for(int i = 0; i < 8; i++){
                if(check(posX, posY, i)){
                    move(i);
                    board[posY][posX] = ++moves;
                    break;
                }
            }
            
            boardSolve();
        }

        //backtrack case
        else{
            try{
                System.out.println("backtrack needed.");
                tryAnother();
                boardSolve();
            }

            catch(StringIndexOutOfBoundsException e){
                System.out.println("No solution found.");
                boardView();
                System.exit(0);
            }
        }
    }

    private static void tryAnother(){
        if(!moveable()){
            //reset current position
            board[posY][posX] = 0;
            moves--;
            
            //go to previous position
            int prevMove = Integer.parseInt(path.substring(path.length() - 1));
            path = path.substring(0, path.length() - 1);
            if(prevMove < 4)
                move(prevMove + 4);
            else
                move(prevMove - 4);

            //make a next move
            if(prevMove == 7)
                tryAnother();
            
            else{
                move(prevMove + 1);
                board[posY][posX] = ++moves;
            }

                        
        }
    }

    //boardView() - a method that will print the board with move markers to display the traveled path of the knight's tour 
    private static void boardView(){
        for(int[] row : board){
            for(int loc : row){
                System.out.printf("%2d ", loc);
            }
            System.out.println();
        }
    }
}