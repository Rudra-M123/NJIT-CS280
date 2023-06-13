/** File: Main.java
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

//import required packages
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Main{
    //declare variables to be useed throughout
    private static int i;
    private static String line;

    public static void main(String[] args){
        //check if input file is present, if so run the program
        try (Scanner sc = new Scanner(new File("input.txt"))) {
            while(sc.hasNextLine()){
                i=0;
                line = sc.nextLine();
                System.out.println("The string \"" + line + "\" is" + ((A() && i == line.length())? "" : " not") + " in the language.");
            }

        } catch (IOException e) {
            System.out.println("The file \"input.txt\" could not be found in this directory.");
            System.exit(0);
        }
    }

    //A() - a method to check for a valid statement
    private static boolean A() {
        if(I()){
            if(i < line.length() && line.charAt(i) == '='){
                i++;
                if(E())
                    return true;
            }
        }
        return false;
    }

    //E() - a method to check for a valid expression
    private static boolean E(){
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
    private static boolean O(){
        if(i < line.length() && (line.charAt(i) == '+' || line.charAt(i) == '-' || line.charAt(i) == '*' || line.charAt(i) == '/')){
            i++;
            if(i<line.length() && line.charAt(i-1) == '*' && line.charAt(i) == '*')
                i++;
            return true;
        }
        return false;
    }

    //P() - a method to check for a valid letter, number (or their unary-modified variants), or another expression enclosed in parentheses
    private static boolean P(){
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
        else if(i < line.length() && line.charAt(i) == '('){
            i++;
            if(i < line.length() && E()){
                if(i < line.length() && line.charAt(i) == ')'){
                    i++;
                    return true;
                }
            }
        }
        return false;
    }

    //U() - a method to check for a valid unary operator
    private static boolean U(){
        if(i < line.length() && (line.charAt(i) == '+' || line.charAt(i) == '-' || line.charAt(i) == '!')){
            i++;
            return true;
        }

        return false;
    }

    //I() - a method to check for a valid char/ sequence
    private static boolean I(){
        if(C()){
            if(I()){}
            return true;
        }
        return false;
    }

    //C() - a method to check for a valid letter
    private static boolean C(){
        if (i < line.length() && 'a' <= line.charAt(i) && line.charAt(i) <= 'z') {
            i++;
            return true;
        }
        return false;
    }

    //L() - a method to check for a valid digit/ sequence
    private static boolean L(){
        if(D()){
            if(L()){}
            return true;
        }
        return false;
    }

    //D() -  a method to check for a valid digit
    private static boolean D(){
        if (i < line.length() && '0' <= line.charAt(i) && line.charAt(i) <= '9') {
            i++;
            return true;
        }
        return false;
    }
}
