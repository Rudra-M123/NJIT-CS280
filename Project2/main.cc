/** File: main.cc
 *  Professor: Professor Kapleau
 *  Course: CS280
 *  Section: 011
 *
 * A -> I = E 
 * E -> P O P | P
 * O -> + | - | * | / | **
 * P -> I | L | UI | UL | (E)
 * U -> + | - | !
 * I -> C | CI
 * C -> a | b | ... | y | z
 * L -> D | DL
 * D -> 0 | 1 | ... | 8 | 9
 * 
 * This program will read from a file named "input.txt" and determine whether the input is a valid statement in this abstract language.
 * 
 * Detailed comments are below. Please note that whitespace should be avoided in the input to ensure proper functionality  
 *  
 */

//kFLFBx9T7XJmrr3p2F/fmAj+8DS8GqnKeq/TOIAPcuI=

#include <iostream>
#include <fstream>
#include <string>
using namespace std;

//declare variables and functions to be useed throughout
static string line;
static int i;
static bool A(void);
static bool E(void);
static bool O(void);
static bool P(void);
static bool U(void);
static bool I(void);
static bool C(void);
static bool L(void);
static bool D(void);

int main(void){
    ifstream fin("input.txt");
    //check if input file is present, if so run the program
    while(fin >> line){
        //perform operations here
        i = 0;
        if(A() && i == line.length())
            cout << "The string \"" << line << "\" is in the language." << endl;
        else
            cout << "The string \"" << line << "\" is not in the language." << endl;
    }

    fin.close();
}

//A() - a method to check for a valid statement
static bool A() {
    if(I()){
        if(i < line.length() && line[i] == '='){
            i++;
            if(E())
                return true;
        }
    }
    return false;
}

//E() - a method to check for a valid expression
static bool E(){
    if(P()){
        //algebraic/ two operand case
        if(O()){
            if(P()){
                return true;
            }
            return false;
        }
        return true;
    }
    return false;
}

//O() - a method to check for a valid operator
static bool O(){
    if(i < line.length() && (line[i] == '+' || line[i] == '-' || line[i] == '*' || line[i] == '/')){
        i++;
        if(i<line.length() && line[i-1] == '*' && line[i] == '*')
            i++;
        return true;
    }
    return false;
}

//P() - a method to check for a valid letter, number (or their unary-modified variants), or another expression enclosed in parentheses
static bool P(){
    //letter/ sequence option
    if(I()){
        return true;
    }

    //digit/ sequence option
    else if(L()){
        return true;
    }

    //unary letter/ digit option
    else if (U()){
        if(I())
            return true;

        else if(L())
            return true;
    }

    //enclosed expression option 
    else if(i < line.length() && line[i] == '('){
        i++;
        if(i < line.length() && E()){
            if(i < line.length() && line[i] == ')'){
                i++;
                return true;
            }
        }
    }
    return false;
}

//U() - a method to check for a valid unary operator
static bool U(){
    if(i < line.length() && (line[i] == '+' || line[i] == '-' || line[i] == '!')){
        i++;
        return true;
    }

    return false;
}

//I() - a method to check for a valid char/ sequence
static bool I(){
    if(C()){
        if(I()){}
        return true;
    }
    return false;
}

//C() - a method to check for a valid letter
static bool C(){
    if (i < line.length() && 'a' <= line[i] && line[i] <= 'z') {
        i++;
        return true;
    }
    return false;
}

//L() - a method to check for a valid digit/ sequence
static bool L(){
    if(D()){
        if(L()){}
        return true;
    }
    return false;
}

//D() -  a method to check for a valid digit
static bool D(){
    if (i < line.length() && '0' <= line[i] && line[i] <= '9') {
        i++;
        return true;
    }
    return false;
}
