/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rectangle;

import java.io.*;       // For basic input needs
import java.util.*;     // For basic input needs

/**
 * Programmer: Priyanshu Bhatt
 * Date:  Monday, January 18th, 2021
 * Program Name: Rectangle Class
 * Program Description: This program has 2 constructors methods: 
 * one constructor that has no parameters and sets each field to 
 * a default value of zero. One constructor that has four parameters 
 * representing the four fields of the class. The constructor should 
 * replace any negative width and/or height parameters with zero. 
 * A toString method that returns coordinates and the size of the 
 * rectangle. It also includes an area of a rectangle method and an 
 * perimeter of a rectangle method. An instance method, contains, 
 * that has one parameter of type Rectangle. A class method (static)
 * , intersection, that has two Rectangle parameters. The method returns
 * the rectangle formed by the area common to the two rectangles. A class
 * method (static), totalPerimeter, that has two Rectangle parameters. 
 * The method returns the total perimeter of the figure formed by the two rectangles. 
 */

public class Rectangle {

    private int left;       // Initializes the x coordinate
    private int bottom;     // Initializes the y coordinate
    private int width;      // Initializes the width of the rectangle
    private int height;     // Initializes the height of the rectangle
    
    // This is a default constructor, returning a default value of zeros to each field
    public Rectangle(){
        
    }
    
    // This sorts the 4 fields of the rectangle
    public Rectangle(int l, int b, int w, int h){
        
        if(w<0){        // If the width is negative, it changes it to zero
            w = 0;
        }
        
        if(h<0){        // If the height is negative, it changes it to zero
            h = 0;
        }
        
        left = l;       // Initializes the x coordinate
        bottom = b;     // Initializes the y coordinate
        width = w;      // Initializes the width of the rectangle
        height = h;     // Initializes the height of the rectangle  
    }
    
    public String toString(){
        return "base: ("+left+","+bottom+") w:"+width+" h:"+height;     // This returns the values given for the rectangles as a string
    }
    
    public int area() {
        return width*height;        // Calculates the area of the rectangle
    }

    public int perimeter() {
        return 2*width + 2*height;      // Calculates the perimeter of the rectangle
    }
    
    // This checks if the 2 rectangles intersect. If they do, it displays the 
    // measurements of the space overlapped. If they dont, it displays zeros
    public static Rectangle intersection(Rectangle r1, Rectangle r2){
        
        Rectangle rLeft;        // Initializes what rectangle is on what side
        Rectangle rRight;       // Initializes what rectangle is on what side
        
        if(r1.left < r2.left){      // If r2's x coordinate is greater than r1, r2 is on the right 
            rLeft = r1;
            rRight = r2;
        }
        else {                      // If r1's x coordinate is greater than r2, r1 is on the right 
            rLeft = r2;
            rRight = r1;
        }
        
        if(rLeft.left + rLeft.width < rRight.left){     // This checks if the rectangles intersect horizontally,
            return new Rectangle (0,0,0,0);             // If they dont, it returns a rectangle with zeros
        }
        
        Rectangle rBottom;      // Initializes what rectangle is on what height
        Rectangle rTop;         // Initializes what rectangle is on what height
        
        if(r1.bottom < r2.bottom){      // If r2's y coordinate is greater than r1, r2 is higher 
            rBottom = r1;
            rTop = r2;
        }
        else{                           // If r1's y coordinate is greater than r2, r1 is higher
            rBottom = r2;
            rTop = r1;
        }
        
        if(rBottom.bottom + rBottom.height < rTop.bottom){      // This checks if the rectangles intersect vertically,
            return new Rectangle(0,0,0,0);                      // If they dont, it returns a rectangle with zeros
        }
        
        Rectangle rInt = null;      // Intializes a new rectangle for the measurements of intersecting rectangle
        int x, y, w, h;             // Intializes the 4 fields of measurement
        
        if(rLeft.left + rLeft.width < rRight.left + rRight.width){          // This finds the width and the x coordinate of the new rectangle
            w = rLeft.left + rLeft.width - rRight.left;                     // This sets the width of the rectangle
            x = rLeft.left + rLeft.width - w;                               // This sets the x coordinate of the new rectangle
        }
        else{
            w = rRight.width;           // This sets the width of the rectangle
            x = rRight.left;            // This sets the x coordinate of the new rectangle
        }
        
        if(rBottom.bottom + rBottom.height < rTop.bottom + rTop.height){    // This finds the height and the y coordinate of the new rectangle
            h = rBottom.bottom + rBottom.height - rTop.bottom;              // This sets the height of the rectangle     
            y = rBottom.bottom + rBottom.height - h;                        // This sets the y coordinate of the new rectangle
        }
        else {
            h = rTop.height;            // This sets the height of the rectangle  
            y = rTop.bottom;            // This sets the x coordinate of the new rectangle
        }
        
        rInt = new Rectangle(x,y,w,h);      // This gives the new rectangle values
        
        return rInt;            // This returns the new rectangle if the 2 rectangles intersect
    }
        
    // This is an equals method
    public boolean equals(Rectangle otherRect){
        
        if(otherRect == null)
            return false;
        
        return left == otherRect.left && bottom == otherRect.bottom && width == otherRect.width && height == otherRect.height;      // Checks if rectangle values match
        
    }
    
    // This checks if one rectangle is contained in the other
    public boolean contains(Rectangle otherRect){
        return (this.left <= otherRect.left) && (this.bottom <= otherRect.bottom) && (this.width + this.left >= otherRect.width + otherRect.left) && (this.height + this.bottom >= otherRect.height + otherRect.bottom);
    }
    
    // THis gets the total perimeter of both the rectangles
    public static int totalPerimeter(Rectangle r1, Rectangle r2){
        
        if (intersection(r1,r2).equals(new Rectangle(0,0,0,0))){            // This checks if the rectangles intersect
            return 2*r1.width + 2*r1.height + 2*r2.width + 2*r2.height;     // If they dont, it returns the sum of the 2 rectangles
        }
        
        Rectangle rInt = intersection(r1,r2);
  
        if (r1.contains(r2) == true){           // If r1 contains r2, it displays the perimeter of r1
            return 2*r1.width + 2*r1.height;
        }
        else if (r2.contains(r1) == true){      // If r2 contains r1, it displays the perimeter of r2
            return 2*r2.width + 2*r2.height;
        }
        
        return 2*r1.width + 2*r1.height + 2*r2.width + 2*r2.height - 2*rInt.width - 2*rInt.height;      // Total perimeter if the 2 rectangles are intersecting
                
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
//        These are suggested methods of testing this class        
//        Rectangle r1 = new Rectangle(3,2,4,5);
//        Rectangle r2 = new Rectangle(0,0,0,0);        
//        System.out.println(r1);
//        System.out.println(r2);
//        System.out.println(r1.area());
//        System.out.println(r2.area());
//        System.out.println(r1.perimeter());
//        System.out.println(r2.perimeter());
//        System.out.println(r1.contains(r2));
//        System.out.println(r2.contains(r1));
//        System.out.println(Rectangle.intersection(r1, r2));
//        System.out.println(Rectangle.totalPerimeter(r1,r2));
        
    }

    
}
