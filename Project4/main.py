"""
File: main.py
Professor: Professor Kapleau
Course: CS280
Section: 011

This program will create a test a Complex numbers class which performs standard arithmetic, 
other extra functions, and returns useful information about the complex number. 
Explanatory comments are provided below.

Note: the output is rounded to stop floating point arithmetic issues.
"""

#kFLFBx9T7XJmrr3p2F/fmAj+8DS8GqnKeq/TOIAPcuI=

import math
class Complex:
    # constructor
    def __init__(self, real = 0.0, img = 0.0):
        self.real = real
        self.img = img

    # returns the real component
    def re(self):
        return self.real
    
    # returns the imaginary component
    def im(self):
        return self.img
    
    # returns the phase angle of the complex number
    def phase(self):
        return math.atan2(self.img, self.real)
    
    # returns the modulus or magnitude of the complex number
    def magnitude(self):
        return math.hypot(self.real, self.img)
    
    # returns the conjugate of the complex number
    def conjugate(self):
        return Complex(self.real, -1.0*self.img)
    
    # returns the complex number that represents the (c + c) or (c + d) addition 
    def __add__(self, t):
        if isinstance(t, Complex):
            return Complex(self.real + t.real, self.img + t.img)
        elif isinstance(t, (float, int)):
                return Complex(self.real + t, self.img)
        else:
            raise TypeError
    
    # returns the complex number that represents the (d + c) addition
    def __radd__(self, d):
        if isinstance(d, (float, int)):
            return self + d
        else:
            raise TypeError

    # returns the complex number that represents the (c - c) or (c - d) subtraction
    def __sub__(self, c):
        if isinstance(c, Complex):
            return Complex(self.real - c.real, self.img - c.img)
        elif isinstance(c, (float, int)):
            return Complex(self.real - c, self.img)
        else:
            raise TypeError
    
    # returns the complex number that represents the (d - c) subtraction
    def __rsub__(self, d):
        if isinstance(d, (float, int)):
            return -1.0 * self + d
        else:
            raise TypeError

    # returns the complex number that represents the (c * c) or (c * d) multiplication
    def __mul__(self, t):
        if isinstance(t, Complex):
            return Complex(self.real*t.real - self.img*t.img, self.real*t.img + self.img*t.real)
        elif isinstance (t, (float, int)):
            return Complex(t*self.real, t*self.img)
        else:
            raise TypeError
    
    # returns the complex number that represents the (d * c) multiplication
    def __rmul__(self, d):
        if isinstance(d, (float, int)):
            return self * d
        else:
            raise TypeError
    
    # returns the complex number that represents the (c / c) or (c / d) division
    def __truediv__(self, t):
        if isinstance(t, Complex):
            mag = self.magnitude() / t.magnitude()
            theta = self.phase() - t.phase()
            return Complex(mag*math.cos(theta), mag*math.sin(theta))    
        elif isinstance (t, (float, int)):
            return Complex(self.real/ t, self.img/ t)
        else:
            raise TypeError
        
    # returns the complex number that represents the (d / c) division
    def __rtruediv__(self, d):
        if isinstance(d, (float, int)):
            return d * Complex(round(math.cos(-1*self.phase())/self.magnitude(), 3), round(math.sin(-1*self.phase())/self.magnitude(), 3))
        else:
            raise TypeError
    

    # returns true or false depending on whether they represent the same complex number
    def __eq__(self, c):
        if isinstance(c, Complex):
            return self.real == c.real and self.img == c.img
        else:
            raise TypeError

    # returns a string representation of complex numbers in algebraic form
    def __str__(self):
        return "(" + str(float(self.real)) + (" + " if self.img >= 0 else " - ") + str(float(abs(self.img))) + "i)"

if __name__ == "__main__":
    c1 = Complex(1,1)
    c2 = Complex(2,2)
    d = 2.0

    #Retrieves properties of the complex numbers
    print("Z1 = %s" % c1)
    print("Re(Z1): %f" % c1.re())
    print("Im(Z1): %f" % c1.im())
    print("Phase angle of Z1: %f" % c1.phase())
    print("Modulus or |Z1|: %f\n" % c1.magnitude())
    print("Z2 = %s" % c2)
    print("Re(Z2): %f" % c2.re())
    print("Im(Z2): %f" % c2.im())
    print("Phase angle of Z2: %f" % c2.phase())
    print("Modulus or |Z2|: %f\n" % c2.magnitude())

    #Performs standard operations on the complex numbers
    print('%s + %s = %s' % (c1, c2, c1 + c2)) #addition
    print('%s + %f = %s' % (c1, d, c1 + d)) #real number addition 1
    print('%f + %s = %s' % (d, c1, d + c1)) #real number addition 2
    print('%s - %s = %s' % (c1, c2, c1 - c2)) #subtraction
    print('%s - %f = %s' % (c1, d, c1 - d)) #real number subtraction 1
    print('%f - %s = %s' % (d, c1, d - c1)) #real number subtraction 2
    print('%s * %s = %s' % (c1, c2, c1 * c2)) #multiplication
    print('%s * %f = %s' % (c1, d, c1 * d)) #real number multiplication 1
    print('%f * %s = %s' % (d, c1, d * c1)) #real number multiplication 2
    print('%s / %s = %s' % (c1, c2, c1 / c2)) #division
    print('%s / %f = %s' % (c1, d, c1 / d)) #real number division 1
    print('%f / %s = %s\n' % (d, c1, d / c1)) #real number division 2

    #Extra features
    print("Conjugate of Z1 is: %s" % c1.conjugate())
    print("Reciprocal of Z2 is: %s" % (1 / c2))
    print("Z1 == Z2: %s" % (c1 == c2))
    print("Z1 * %f = %s" % (d, (d * c1)))
    print("(%s * Z1) == Z2: %s" % (d, ((d * c1) == c2)))
