import edu.cmu.ri.createlab.terk.robot.finch.Finch;
import javax.swing.JOptionPane;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Draw_Shape {

	static ArrayList<Shape> shapesDrawn = new ArrayList<Shape>(); // creates new array list all shapes drawn and which shapes
	static Integer noOfSquare = 0, noOfTriangle = 0; // frequency of shapes
	static Finch myFinch = new Finch ();
	
	public static void main(String args[]) {
		
	}
	
	public void Task2() {
		
		boolean validinput = true;
		do {

			JOptionPane.showMessageDialog(null, "Place finch level to draw a shape or to quit");
			while (!myFinch .isFinchLevel()) {
			}
			myFinch.setLED(0, 255, 0, 2000); // Finch lights green to show program has started
			
			String UserInput = JOptionPane.showInputDialog(null, "Input 'S', 'Q, or 'T'");

			if (UserInput.equalsIgnoreCase("s")) {
				System.out.println(UserInput);
				System.out.println("input valid");
				drawSquare();
				validinput = true;
			} else if (UserInput.equalsIgnoreCase("t")) {
				System.out.println(UserInput);
				System.out.println("input valid");
				drawTriangle();
				validinput = true;
			} else if (UserInput.equalsIgnoreCase("q")) {
				System.out.println(UserInput);
				System.out.println("input valid");
				storeValuesAndExit();
				validinput = true;
			} else
				JOptionPane.showMessageDialog(null, "Invalid input, enter S, T, or Q");
			validinput = false;

		} while (!validinput);
		
	}

	private static void drawSquare() {
		boolean valid = false;
		do {
	
			String length = JOptionPane.showInputDialog(null, "Input between 15 - 85 cm");
			Integer len = Integer.parseInt(length); // parsing a string length entered by user into integer
			if (len > 14 && len < 86) {
				valid = true;
				Shape shape = new Shape(); // Single object to store the square
				shape.setShapeName("Square"); //shape name gets stored in private list
				// This will hold the sides of a square 
				ArrayList<Integer> side = new ArrayList<Integer>(); //array list has been created here for the length
				side.add(len); // added square side to array list
				
				shape.setLengths(side); // This is assigning the array list to shape object
				// draw square
				double startTime = System.currentTimeMillis();
				myFinch.setWheelVelocities((50), (50), (300*len));
				myFinch.setWheelVelocities(80, -80, 1000);
				myFinch.setWheelVelocities((50), (50), (300*len));
				myFinch.setWheelVelocities(80, -80, 1000);
				myFinch.setWheelVelocities((50), (50), (300*len));
				myFinch.setWheelVelocities(80, -80, 1000);
				myFinch.setWheelVelocities((50), (50), (300*len));
				myFinch.buzz(500, 1000);
				double endTime = System.currentTimeMillis();
				System.out.println((endTime - startTime) / 1000);
				
				Draw_Shape.noOfSquare++; //adds 1 to how many squares are drawn
				Draw_Shape.shapesDrawn.add(shape); // It will store the shapes drawn while running the program -- add what shape is drawn to array list later to text file
			} else {
				JOptionPane.showMessageDialog(null, "Invalid number, enter between 15 - 85.");
				valid = false;
			}
	
		} while (!valid);
	}

	private static void drawTriangle() {
		boolean valid = false;
		do {

			String a = JOptionPane.showInputDialog(null, "Please enter side a length.");
			String b = JOptionPane.showInputDialog(null, "Please enter side b length.");
			String c = JOptionPane.showInputDialog(null, "Please enter side c length.");

			Integer sideA, sideB, sideC; // parsing string a, b, c lengths entered by user into integers
			sideA = Integer.parseInt(a);
			sideB = Integer.parseInt(b);
			sideC = Integer.parseInt(c);
			
			if(!(sideA > 14 && sideA < 86)) {
				JOptionPane.showMessageDialog(null, "Side A is not between 15 - 85. Please re-enter all values again");
				valid = false;
			}
			
			if(!(sideB > 14 && sideB < 86))
			{
				JOptionPane.showMessageDialog(null, "Side B is not between 15 - 85. Please re-enter all values again");
				valid = false;
			}
			
			if(!(sideC > 14 && sideC < 86))
			{
				JOptionPane.showMessageDialog(null, "Side C is not between 15 - 85. Please re-enter all values again");
				valid = false;
			}
			
			
			if (((sideA + sideB) > sideC && (sideA + sideC) > sideB && (sideB + sideC) > sideA) && 
					(sideA > 14 && sideA < 86) && (sideB > 14 && sideB < 86) && (sideC > 14 && sideC < 86)) {
				valid = true;

				Shape shape = new Shape(); // new shape for storing the triangle object
				shape.setShapeName("Triangle");

				ArrayList<Integer> sides = new ArrayList<Integer>(); //initialises 3 sides into array list
				 

				sides.add(sideA); //stores 3 sides of triangle into array list - shape class. 
				sides.add(sideB);
				sides.add(sideC);
				
				shape.setLengths(sides); // Triangle has been created with three sides - this then puts it onto the array list of the shape class

				// draw triangle
				Double angleC = Math.toDegrees(Math.acos((Math.pow(sideA, 2)+ Math.pow(sideB, 2) - Math.pow(sideC, 2))/(2*sideA*sideB))); // angles of triangle
				Double angleA = Math.toDegrees(Math.acos((Math.pow(sideA, 2)+ Math.pow(sideC, 2) - Math.pow(sideA, 2))/(2*sideB*sideC)));
				Double angleB = Math.toDegrees(Math.acos((Math.pow(sideA, 2)+ Math.pow(sideC, 2) - Math.pow(sideB, 2))/(2*sideA*sideC)));
				
				double turn1 = (180 - angleC);
				double turn2 = (180 - angleA);
				double turn3 = (180 - angleB);
						
				int f, g, h;
				f = (int) Math.round(turn1);
				g = (int) Math.round(turn2);
				h = (int) Math.round(turn3);
				
				double startTime = System.currentTimeMillis();
				myFinch.setWheelVelocities(50, 50, 300*sideA);
				myFinch.setWheelVelocities(80, -80, 11*f);
				myFinch.setWheelVelocities(50, 50, 300*sideB);
				myFinch.setWheelVelocities(80, -80, 11*g);
				myFinch.setWheelVelocities(50, 50, 300*sideC);
				myFinch.setWheelVelocities(80, -80, 11*h);
				myFinch.buzz(500, 1000);
				double endTime = System.currentTimeMillis();
				System.out.println((endTime - startTime) / 1000);
				
				Draw_Shape.noOfTriangle++; // frequency of how many times triangle has been drawn
				Draw_Shape.shapesDrawn.add(shape); // add shape to array list to store on text file //'shape' is the object created

			} else {
				JOptionPane.showMessageDialog(null,
						"triangle cannot be formed. Please re-enter values to satisfy condition a + b > c, a + c > b, b + c > a");
				valid = false;
			}
	
		} while (!valid);
		
	}

	private static void storeValuesAndExit() { // 'q'
		
		//
		Integer largestShape = -1; // will store the largest length of the shape
		String largestShapeName = ""; //largest shape name
		
		// These variables will be used for triangle shape once a triangle shape is made while drawing
		Integer largestLengthA = 0,largestLengthB = 0, largestLengthC = 0; 
	
		BufferedWriter bw = null; // txt file
		try {
	
			File file = new File("log.txt"); // creates file
	
			// Checks if the file with name log.txt already exits within drive otherwise it will override the already placed file in drive
			if (!file.exists()) {
				file.createNewFile(); // it creates new file if doesn't exist in drive 
			}
			
			// Like a package it will hold the file object
			FileWriter fw = new FileWriter(file); 
			
			// this will store the file within drive
			bw = new BufferedWriter(fw);
			
			String row = ""; // each row in txt file
			for (Shape s : Draw_Shape.shapesDrawn) {
				
				if (s.getShapeName().equals("Square")) {
					Integer side = s.getLengths().get(0); //square information
					side*=4;
					
					// Checks for the largest length of any object drawn
					if(side > largestShape) {
						largestShape  = side; // value of square put on to text file - temp.
						largestShapeName = "Square"; // Store the name of the largest shape - temp.
					}
					row = s.getShapeName() + ":" + side+", "; //shape name and its length
				} else {
					
					Integer a = s.getLengths().get(0); //array list starts with 0...sides 1, 2, 3 of triangle
					Integer b = s.getLengths().get(1);
					Integer c = s.getLengths().get(2);	
					// this is for the highest value of triangle
					Integer triangleLargest = -1;
					
					triangleLargest = a+b+c;
					// replace existing larger shape, e.g. if triangle is bigger than square
					if(triangleLargest > largestShape)
					{
						// if triangle is larger than existing shape, then largest is triangle
						largestShape = triangleLargest;
						largestShapeName = "Triangle";
						largestLengthA = a; //lengths of triangle stored onto text file
						largestLengthB = b;
						largestLengthC = c;
					}
					
					Double angleC = Math.toDegrees(Math.acos((Math.pow(a, 2)+ Math.pow(b, 2) - Math.pow(c, 2))/(2*a*b))); // angles of triangle
					Double angleA = Math.toDegrees(Math.acos((Math.pow(b, 2)+ Math.pow(c, 2) - Math.pow(a, 2))/(2*b*c)));
					Double angleB = Math.toDegrees(Math.acos((Math.pow(a, 2)+ Math.pow(c, 2) - Math.pow(b, 2))/(2*a*c)));
					
					row = s.getShapeName() + ":" + a + "," + b + ","+ c + "(Angles : "+angleA+","+angleB+","+angleC+") , ";	//writes angles in text file		
										
				}
				// Writes into text file
				bw.write(row);
			}
			
			// Append the largest size and length here
			if(largestShapeName.equals("Square"))
			{
				row = "\nLargest Shape is : "+largestShapeName+" with length : "+(largestShape/4)+"\n";
			}else {
				row = "\nLargest Shape is : "+largestShapeName+" with lengths : "+largestLengthA+", "+largestLengthB+", "+largestLengthC+" \n";
			}
			
			
			// Append most frequently used shape..
			if(Draw_Shape.noOfSquare>Draw_Shape.noOfTriangle) //compare frequency of shapes
			{
				row+= "Square : "+Draw_Shape.noOfSquare+" times";
			}else {
				row+= "Triangle : "+Draw_Shape.noOfTriangle+" times";
			}
			System.out.println("File written Successfully");
			bw.write(row);
	
		} catch (IOException ioe) {
			System.out.println("Unable to create file, try again.");
		} finally {
			try {
				// file has been created successfully and has been written into drive then close the bw
				if (bw != null)
					bw.close();
			} catch (Exception ex) {
				System.out.println("Error in closing the BufferedWriter" + ex);
			}
			
		}
		System.exit(0);
	}

}