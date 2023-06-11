/** File: main.cc
 *  Professor: Professor Kapleau
 *  Name: Rudra Mehta (rm279@njit.edu)
 *  Course: CS280
 *  Section: 011
 *  
 *  The code below will have a starting position, default or user determined, and then perform a knight's tour to reach every square on the board.
 *  More comments are below.
 */

//kFLFBx9T7XJmrr3p2F/fmAj+8DS8GqnKeq/TOIAPcuI=

#include <iostream>
#include <cstdlib>
using namespace std;

//declare static variables and functions to be used throughout
static int xMod[] = {1, 2, 2, 1, -1, -2, -2, -1}, yMod[] = {-2, -1, 1, 2, 2, 1, -1, -2}, board[8][8];
static bool boardSolve(int x, int y, int moves);
static void boardView();

int main(int argc, char* argv[]){
    int posX = 0, posY = 0;
    //optional feature to use command-line arguments to provide a custom starting location
    if(argc == 3){
        if( -1 < atoi(argv[1]) && atoi(argv[1]) < 8 && -1 < atoi(argv[2]) && atoi(argv[2]) < 8 && board[atoi(argv[2])][atoi(argv[1])]){
            posX = atoi(argv[1]);
            posY = atoi(argv[2]);
        }

        //invalid arguments
        else
            cout << "At least one starting position index was not between 0-7, program will use default (0, 0) instead." << endl;
    }
    
    //Begin the knight's tour solution process
    if(boardSolve(posX, posY, 1))
        boardView();

    else
        cout << "No solution can be found." << endl;
}

//boardSolve() - recursively finds positions to move and progress through, solving the knight's tour from the specified starting position
static bool boardSolve(int x, int y, int moves){
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
void boardView(){
    for(int i = 0; i < 8; i++){
        for(int j = 0; j < 8; j++)
            printf("%2d ", board[i][j]);
        cout << endl;
    }
}
