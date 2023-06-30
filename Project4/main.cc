/** File: main.cc
 *  Professor: Professor Kapleau
 *  Course: CS280
 *  Section: 011
 *
 *  This program will create a test a Complex numbers class which performs standard arithmetic, 
 *  other extra functions, and returns useful information about the complex number. 
 *  Explanatory comments are provided below.
 */

//kFLFBx9T7XJmrr3p2F/fmAj+8DS8GqnKeq/TOIAPcuI=

#include <iostream>
#include <cmath>
#include <cstdio>
using namespace std;

class Complex{
    private:
        //instance variables
        double real;
        double img;
    
    public:
        //constructor
        Complex(double real = 0.0, double img = 0.0) : real(real), img(img) {}
    
        //returns the real component
        double re() const{
            return real;
        }

        //returns the imaginary component
        double im() const{
            return img;
        }
        
        //returns the phase angle of the complex number
        double phase() const{
            return atan(img * 1.0 / real);
        }

        //returns the modulus or magnitude of the complex number
        double magnitude() const{
            return hypot(real, img);
        }
      
        //returns the conjugate of the complex number
        Complex conjugate(){
            return Complex(real, -img);
        }

        //returns the complex number that represents the (c + c) addition
        Complex operator+(const Complex &c) const{
            return Complex(real + c.real, img + c.img);
        }

        //returns the complex number that represents the (c + d) addition
        Complex operator+(const double &d) const{
            return Complex(real + d, img);
        }

        //returns the complex number that represents the (d + c) addition
        friend Complex operator+(double d, const Complex &c){
            return c + d;
        }

        //returns the complex number that represents the (c - c) subtraction
        Complex operator-(const Complex &c) const{
            return Complex(real - c.real, img - c.img);
        }

        //returns the complex number that represents the (c - d) subtraction
        Complex operator-(const double &d) const{
            return Complex(real - d, img);
        }

        //returns the complex number that represents the (d - c) subtraction
        friend Complex operator-(double d, const Complex &c){
            return -1 * c + d;
        }

        //returns the complex number that represents the (c * c) multiplication
        Complex operator*(const Complex &c) const{
            return Complex(real*c.real - img*c.img, real*c.img + img*c.real);
        }

        //returns the complex number that represents the (c * d) multiplication
        Complex operator*(double d) const{
            return Complex(d*real, d*img);
        }

        //returns the complex number that represents the (d * c) multiplication
        friend Complex operator*(double d, const Complex &c){
            return c * d;
        }

        //returns the complex number that represents the (c / c) division
        Complex operator/(const Complex &c) const{
            double mag = magnitude() / c.magnitude();
            double theta = phase() - c.phase();
            return Complex(mag*cos(theta), mag*sin(theta));
        }

        //returns the complex number that represents the (c / d) division
        Complex operator/(double d) const{
            return Complex(real/d, img/d);
        }

        //returns the complex number that represents the (d / c) division
        friend Complex operator/(double d, const Complex &c){ 
            return d * Complex(cos(-1*c.phase())/c.magnitude(), sin(-1*c.phase())/c.magnitude());
        }

        //returns true or false depending on whether they represent the same complex number
        bool operator == (const Complex &c){
            return real == c.real && img == c.img;
        }

        //overloads the stream insertion operator to print complex numbers in algebraic form
        friend ostream &operator<<(ostream &out, const Complex &c){
            out << '(' << c.real << (c.img >= 0 ? " + " : " - ") << abs(c.img) << "i)";
            return out;
        }
};

int main(void){

    Complex c1(1, 1);
    Complex c2(2, 2);
    double d = 2.0;

    //Retrieves properties of the complex numbers
    cout << "Z1 = " << c1 << endl;
    cout << "Re(Z1): " << c1.re() << endl;
    cout << "Im(Z1): " << c1.im() << endl;
    cout << "Phase angle of Z1: " << c1.phase() << endl;
    cout << "Modulus or |Z1|: " << c1.magnitude() << endl << endl;
    cout << "Z2 = " << c2 << endl;
    cout << "Re(Z2): " << c2.re() << endl;
    cout << "Im(Z2): " << c2.im() << endl;
    cout << "Phase angle of Z2: " << c2.phase() << endl;
    cout << "Modulus or |Z2|: " << c2.magnitude() << endl << endl;

    //Performs standard operations on the complex numbers
    cout << c1 << " + " << c2 << " = " << c1 + c2 << endl; //addition
    cout << c1 << " + " << d << " = " << c1 + d << endl; //real number addition 1
    cout << d << " + " << c1 << " = " << d + c1 << endl; //real number addition 2
    cout << c1 << " - " << c2 << " = " << c1 - c2 << endl; //subtraction
    cout << c1 << " - " << d << " = " << c1 - d << endl; //real number subtraction 1
    cout << d << " - " << c1 << " = " << d - c1 << endl; //real number subtraction 2
    cout << c1 << " * " << c2 << " = " << c1 * c2 << endl; //multiplication
    cout << c1 << " * " << d << " = " << c1 * d << endl; //real number multiplication 1
    cout << d << " * " << c1 << " = " << d * c1 << endl; //real number multiplication 2
    cout << c1 << " / " << c2 << " = " << c1 / c2 << endl; //division
    cout << c1 << " / " << d << " = " << c1 / d << endl; //real number division 1
    cout << d << " / " << c1 << " = " << d / c1 << endl << endl; //real number division 2

    //Extra features
    cout << "Conjugate of Z1 is: " << c1.conjugate() << endl;
    cout << "Reciprocal of Z2 is: " << 1 / c2 << endl;
    cout << "Z1 == Z2: " << ((c1 == c2) == 1 ? "true" : "false") << endl;
    cout << d << " * Z1" << " = " << (d * c1) << endl;
    cout << "(Z1 * " << d << ") == Z2: " << (((d * c1) == c2) == 1 ? "true" : "false") << endl;

    return 0;
}
