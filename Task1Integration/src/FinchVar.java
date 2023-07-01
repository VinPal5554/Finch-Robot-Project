//package Code;

import javax.swing.*; 

import edu.cmu.ri.createlab.terk.robot.finch.Finch;
//import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

import javax.swing.JFrame;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class FinchVar{
	private static String userInput;

	protected static int decimal;
	private int octal;
	private String userInfo;
	private String binary;
	private int duration;
	private final int speedLimit = 255;
	private int velocity;
	private int red;
	private int blue;
	private int green;
	protected static ArrayList<Integer> hexList;

	public FinchVar() {
		
		//sets variables to each method of these methods 
		this.userInput = setHexValue();
		ArrayList<String> hexList = new ArrayList<>();
		this.decimal = setDecimal();
		//
		//
		
		this.octal = decToOct();
		this.binary = decToBinary();
		this.duration = setDuration();
		this.velocity = setVelocity();
		this.red = setRed();
		this.blue = setBlue();
		this.green = setGreen();
		
	}

	public void dance() {
		//new Finch object
		
		
		Finch fin = new Finch();
		
		//reverses binary string and reads through it
		String bin = binary;
		String raw = new StringBuilder(bin).reverse().toString();

		int[] num = new int[raw.length()];

		for (int i = 0; i < raw.length(); i++) {
			num[i] = raw.charAt(i) - '0';
		}

		for (int i : num) {
			//if value equals 1 then moves forwards, if value equals 0 it moves backwards
			if (i == 1) {

				fin.setWheelVelocities(velocity, velocity, duration);
				fin.setLED(red, green, blue, 1000);
			} else if (i == 0) {

				fin.setWheelVelocities(velocity * -1, velocity * -1, duration);
				fin.setLED(red, green, blue, 1000);
			}

			fin.setLED(0, 0, 0);
		}
		


	}
	
		
		
		
	public String setHexValue() {
		
		
		String answer = null;
		boolean notValid = true;
		
		// will keep looping through this whilst user enters a a value that does not match regex
		do {
		System.out.println("enter Hexadecimal value 1 or 2 digits long");
		Scanner input = new Scanner(System.in);
		String chosenHex = input.nextLine();
		// regex to ensure user input matches hexadecimal parameters
		if ((chosenHex.matches("[0-9a-fA-F]+")) && (chosenHex.length() < 3)) {
			answer = chosenHex;
			notValid = false;
		}

		else { 
			
			System.out.println("input not valid");
			notValid =true;
		}
		}while(notValid);
		
		return answer;


		
		
	}

	private static int setDecimal() {
		String hexNumber = userInput;
		String hexstring = "0123456789ABCDEF";  
		hexNumber = hexNumber.toUpperCase();  
		int num = 0;  
		for (int i = 0; i < hexNumber.length(); i++)  
		{  
			char ch = hexNumber.charAt(i);  
			int n = hexstring.indexOf(ch);  
			num = 16*num + n;  
		}  
		
		return num;  
		//String hex = userInput;
		//hex.toUpperCase();
		//int dec = Integer.parseInt(hex, 16);
		//return dec;

	}

	private String decToBinary() {
		String binValue = "";
		int bin = decimal;
		int binary[] = new int[40];
	     int index = 0;
	     while(bin > 0){
	       binary[index++] = bin%2;
	       bin = bin/2;
	     }
	     for(int i = index-1;i >= 0;i--){
	        binValue = binValue + binary[i];
	     }
		return binValue;
		
		/*String s = Integer.toBinaryString(decimal);
		 *return s;
		 */
	}

	private int decToOct() {

		int remainder;
		int decConvert = decimal;
		String octValue = "";
		char digits[] = { '0', '1', '2', '3', '4', '5', '6', '7' };
		while (decConvert > 0) {
			remainder = decConvert % 8;
			octValue = digits[remainder] + octValue;
			decConvert = decConvert / 8;

		}
		int i = Integer.parseInt(octValue);
		return i;

		/*String octValue = Integer.toOctalString(decimal);
		int i = Integer.parseInt(octValue);
		return i;*/

	}
	//set duartion according to length of users hexadecimal input
	public int setDuration() {
		
		int duration = 0;
		if (userInput.length() == 1) {
			duration = 1000;
		} else {
			duration = 500;
		}
		return duration;

	}
	//set wheel speed to octal or decimal depending on what value is larger
	public int setVelocity() {
		int speed;
		if (octal > speedLimit) {
			speed = speedLimit;
		} else if (octal < 50) {
			speed = octal + 50;
		} else {
			speed = octal;
		}
		return speed;
	}
	
	//set green value of LED
	private int setGreen() {
		int green1 = (decimal % 80) * 3;
		return green1;
	}
	//set blue value of LED
	private int setBlue() {
		int blue1 = 0;
		if (green > red) {
			blue1 = green;
		} else if (red > green) {
			blue1 = red;
		}
		return blue1;
	}
	//set red value of LED
	private int setRed() {
		int red1 = decimal;
		return red1;
	}
	//show info of relevant variables to input into text file 
	public String showInfo() {

		return "" + "Hex: " + userInput + " " + "Decimal: " + decimal + " " + "Octal: " + octal + " " + "Binary: "
				+ binary + " Speed: " + velocity + "\n LED - red: " + red + " green: " + green + " blue " + blue;
	}
	
	
	//put decimal values into an array then sort it via run method in FinchApp class then convert it back to hexadecimal and return the value for storing later
	public String storeHex() {
		String hexa = userInput;
				return  hexa;
	}

	
	//show info in showinfo.txt
	public void getInfo() {
		File file = new File(
				"C:\\Users\\Umar\\eclipse-workspace\\java\\FinchEclipse\\src\\Code\\showInfo.txt");
		BufferedReader br = null;
		try {
			FileReader fr = new FileReader(file);
			br = new BufferedReader(fr);
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(file.toString() + " not found");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(file.toString() + " is not readable");
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("unable to close" + file.toString());
			} catch (NullPointerException e) {
				// File was not opened
			}

		}
	}
	
	//show info stored in hexvalues.txt
	public static void getHex() {
		File file = new File("C:\\Users\\Umar\\eclipse-workspace\\java\\FinchEclipse\\src\\Code\\hexValues.txt");
		BufferedReader br = null;
		try {
			FileReader fr = new FileReader(file);
			br = new BufferedReader(fr);
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(file.toString() + " not found");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(file.toString() + " is not readable");
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("unable to close" + file.toString());
			} catch (NullPointerException e) {
				// File was not opened
			}

		}

	}
}
