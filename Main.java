/** File: Main.java
 *  Professor: Professor Kapleau
 *  Name: Rudra Mehta (rm279@njit.edu)
 *  Course: CS280
 *  Section: 011
 *
 *  This program will create a test a Complex numbers class which performs standard arithmetic, 
 *  other extra functions, and returns useful information about the complex number. 
 *  Explanatory comments are provided below.
 * 
 *  Note: the output is rounded to stop floating point arithmetic issues.
 */

//kFLFBx9T7XJmrr3p2F/fmAj+8DS8GqnKeq/TOIAPcuI=

public class Main {
    public static void main(String[] args) {

        Complex c1 = new Complex(1, 1);
        Complex c2 = new Complex(2, 2);
        double d = 2.0;

        //Retrieves properties of the complex numbers
        System.out.println("Z1 = " + c1);
        System.out.println("Re(Z1): " + c1.re());
        System.out.println("Im(Z1): " + c1.im());
        System.out.println("Phase angle of Z1: " + c1.phase());
        System.out.println("Modulus or |Z1|: " + c1.modulus() + 
         "\n");
        System.out.println("Z2 = " + c2);
        System.out.println("Re(Z2): " + c2.re());
        System.out.println("Im(Z2): " + c2.im());
        System.out.println("Phase angle of Z2: " + c2.phase());
        System.out.println("Modulus or |Z2|: " + c2.modulus() + "\n");

        //Performs standard operations on the complex numbers
        System.out.println(c1 + " + " + c2 + " = " + c1.add(c2)); //addition
        System.out.println(c1 + " - " + c2 + " = " + c1.sub(c2)); //subtraction
        System.out.println(c1 + " * " + c2 + " = " + c1.mul(c2)); //multiplication
        System.out.println(c1 + " / " + c2 + " = " + c1.div(c2)); //division
        System.out.println(c1 + " + " + d + " = " + c1.add(d)); //real number addition
        System.out.println(c1 + " - " + d + " = " + c1.sub(d)); //real number subtraction
        System.out.println();

        //Extra features
        System.out.println("Conjugate of Z1 is: " + c1.conjugate());
        System.out.println("Reciprocal of Z2 is: " + c2.reciprocal());
        System.out.println("Z1 == Z2: " + c1.equals(c2));
        System.out.println("Z1 * " + d + " is: " + c1.scale(d));
        System.out.println("(" + d + " * Z1) == Z2: " + c1.scale(d).equals(c2));

    }
}

class Complex{
    //instance variables
        private double real;
        private double img;

    //constructors
        public Complex() {
            this(0, 0);
        }

        public Complex(double real, double img) {
            this.real = real;
            this.img = img;
        }
    
    //methods of the Complex class
        //re() - returns the real component
        public double re(){
            return round(this.real);
        }

        //im() - returns the imaginary component
        public double im(){
            return round(this.img);
        }
        
        //phase() - returns the phase angle of the complex number
        public double phase(){
            return round(Math.atan2(this.img, this.real));
        }

        //modulus() - returns the modulus or magnitude of the complex number
        public double modulus(){
            return round(Math.hypot(this.real, this.img));
        }
      
        //conjugate() - returns the conjugate of the complex number
        public Complex conjugate(){
            return new Complex(round(this.real), round(-1*this.img));
        }
        
        //add()
            //returns the complex number that represents the addition of the method caller by another complex number
            public Complex add(Complex c1){
                return new Complex(round(this.real + c1.real), round(this.img + c1.img));
            } 

            //returns the complex number that represents the addition of the complex number by a double real number
            public Complex add(double d){
                return new Complex(round(this.real + d), round(this.img));
            }

        //sub()
            //returns the complex number that represents the subtraction of the method caller by another complex number
            public Complex sub(Complex c1){
                return new Complex(round(this.real - c1.real), round(this.img - c1.img));
            }

            //returns the complex number that represents the subtraction of the complex number by a double real number
            public Complex sub(double d){
                return new Complex(round(this.real - d), round(this.img));
            }

        //mul() - returns the complex number that represents the multiplication of the method caller by another complex number
        public Complex mul(Complex c1){
            return new Complex(round(this.real*c1.real - this.img*c1.img), round(this.real*c1.img + this.img*c1.real));
        }

        //scale() - returns the complex number that represents the given one when scaled up or down by some factor
        public Complex scale(double d){
            return new Complex(round(d*real), round(d*img));
        }

        //div() - returns the complex number that represents the division of the method caller by another complex number 
        public Complex div(Complex c1){
            double mag = this.modulus() / c1.modulus();
            double theta = this.phase() - c1.phase();
            return new Complex(round(mag*Math.cos(theta)), round(mag*Math.sin(theta)));
        }

        //reciprocal() - returns the reciprocal of the given complex number
        public Complex reciprocal(){
            return new Complex(round(Math.cos(-1*this.phase())/this.modulus()), round(Math.sin(-1*this.phase())/this.modulus()));
        } 

        //round() - fixes floating-point arithmetic at the cost of trimming to 3 decimal points
        private double round(double input){
            return Math.round(input*1000)/1000.0;
        }
    
    //methods inherited from Object
        //equals() - returns true or false depending on whether they represent the same complex number
        public boolean equals(Object obj){
            Complex c1 = (Complex) obj;
            return this.real == c1.real && this.img == c1.img;
        }

        //toString() - returns a string representation of complex numbers in algebraic form
        public String toString() {
            return "(" + round(real) + (img >= 0 ? " + " : " - ") + Math.abs(round(img)) + "i)";
        }
}