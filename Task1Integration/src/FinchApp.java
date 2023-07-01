//package Code;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;

import java.io.BufferedWriter;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class FinchApp extends FinchVar {
	
	public static void run() {
		
		
		
			
		
		FinchVar d1 = new FinchVar();

		Writer writer = null;
		
		//write values from showInfo() to showinfo.txt
		try {
			
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
					"C:\\Users\\Umar\\eclipse-workspace\\java\\FinchEclipse\\src\\Code\\showInfo.txt")));
			writer.write(d1.showInfo());
		} catch (IOException ex) {
			// Report
		} finally {
			try {
				writer.close();
			} catch (Exception ex) {
				/* ignore */}
		}
		//write value from storeHex() into hexValues.txt
		
		
		try(FileWriter fw = new FileWriter("C:\\Users\\Umar\\eclipse-workspace\\java\\FinchEclipse\\src\\Code\\hexValues.txt", true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
			    out.println("\n"+d1.storeHex());

			} catch (IOException e) {

			}
		
		//ask user if they would like to see their information
		System.out.println("Would you like to continue. Enter yes or no");
		Scanner input = new Scanner(System.in);
		String ShowInfo = input.nextLine();
		String confirm = "yes";
		String deny = "no";
		
		//if they enter yes then information will show
		if (ShowInfo.equals(confirm)) {
			d1.getInfo();
		} 
		

		else if (ShowInfo.contains(deny)) {
			System.out.println("");
		} 
		
		
		else {
			System.out.println("command not recognised");
			input = new Scanner(System.in);
			ShowInfo = input.nextLine();
		}

		d1.dance();
	}

	public static void main(String[] args) {
		
	}
	
	public void Task6() {
		
		run();
		
		//asks if user would like to enter a new values. if yes then it will re run run() which restarts the program
		System.out.println("Would you like to enter another number?(Yes/No)");
		Scanner input2 = new Scanner(System.in);
		String confirm = "yes";
		String deny = "no";
		Scanner input = new Scanner(System.in);
		String newNum = input.nextLine();
		if (newNum.equals(confirm)) {
			run();
		} else if (newNum.equals(deny)) {
			getHex();
			System.out.println("Thank you");
		} else {
			System.out.println("command not recognised");
			input = new Scanner(System.in);
			newNum = input.nextLine();

		}

	}

}
