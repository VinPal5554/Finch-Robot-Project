import edu.cmu.ri.createlab.terk.robot.finch.Finch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

public class Navigation {

	public static ArrayList<String> arr = new ArrayList<String>();
	public static ArrayList<Integer> arrSpeed = new ArrayList<Integer>(); //These 3 ArrayLists are used in the ReTracing of the program;
	public static ArrayList<Integer> arrDuration = new ArrayList<Integer>();

	public static void main(String[] args) {
		
	}
	
	public void Task3() throws IOException{

		String commands = "";
		int moveCounter = 0;
		int turnMoveDuration = 0; //Most of the variables are being initialized here;
		int turnFinchSpeed = 0;
		int retrace = 0;

		Finch myFinch = new Finch(); //This is used to initialize the Finch Robot;
		Scanner userinput = new Scanner(System.in); //Scanner used to gather inputs from the user;

		System.out.println("=======================================");
		System.out.println("Welcome to the Navigation Program for the Finch Robot!");
		System.out.println("=======================================");
		System.out.println("F for Forward Movement");
		System.out.println("B for Backwards Movement");
		System.out.println("R for Right Movement");			// These lines are used to print the "Welcome Message" for the program;
		System.out.println("L for Left Movement");
		System.out.println("T for ReTracing of Movements");
		System.out.println("W for Exporting of Movements to a .txt file");
		System.out.println("X for Importing Movements from a .txt file");
		System.out.println("Q to terminate the Program");
		System.out.println("=======================================");
		System.out.println("Please input a letter: ");

		commands = userinput.next(); //This is used to take the first input in the program;

		do {
			if (commands.equals("F") || commands.equals("f") || commands.equals("B") || commands.equals("b") || commands.equals("L") || commands.equals("l") || commands.equals("R") || commands.equals("r")) {

				Moving.movement(userinput, commands, myFinch, moveCounter, turnFinchSpeed, turnMoveDuration); //This is one Method that comes from the "Moving" Class;

			}

			if (commands.equals("T") || commands.equals("t")) {

				Collections.reverse(arr);
				Collections.reverse(arrSpeed);	//These 3 lines of code are used to reverse the Arrays for the ReTrace to happen
				Collections.reverse(arrDuration);

				System.out.println("Enter the retracing number steps");
				retrace = userinput.nextInt();

				while (retrace > arr.size()) {
					System.out.println("The number entered is bigger than the size of the Array, please enter a lower number:");
					retrace = userinput.nextInt();
				} //The ReTrace number needs to be lower than the size of the Array, this is the prompt to let the user know;

				while (retrace < 3) {
					System.out.println("the ReTracing has to be bigger than 3! Please enter another number:");
					break;
				} //The ReTrace number needs to be bigger than 3, this is the prompt to let the user know;

				if (retrace >= 3 || retrace <= moveCounter) {

					for (int i = 0; i < retrace; i++) {

						String arrCommands = arr.get(i);
						int speed = arrSpeed.get(i);  //These variables will store each of the executes in the Arrays which will then be used into the following if statements
						int duration = arrDuration.get(i);

						if (arrCommands.equals("F")) {
							myFinch.setWheelVelocities(speed, speed, duration * 1000);
						} else if (arrCommands.equals("B")) {
							myFinch.setWheelVelocities(speed, speed, duration * 1000);
						} else if (arrCommands.equals("L")) {
							myFinch.setWheelVelocities(-100, 100, 1000);
							myFinch.setWheelVelocities(speed, speed, duration * 1000);
						} else if (arrCommands.equals("R")) {
							myFinch.setWheelVelocities(100, -100, 1000);
							myFinch.setWheelVelocities(speed, speed, duration * 1000);
						} else if (arrCommands.equals("T")) {  //The if statements each go through their own check to see which one of the movements it should execute
							System.out.println("Skip T");
						}
					}
					arr.add("T");
					arrSpeed.add(0);
					arrDuration.add(0);
				}
			}
		

			if (commands.equals("W") || commands.equals("w")) {
				try {
					FileWriter file = new FileWriter("navigateFile.txt");
					int size = arr.size();
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z"); //This is to set how the time will be displayed once command W is run
					Date date = new Date(System.currentTimeMillis());
					file.write(formatter.format(date));
					file.write(System.lineSeparator());
					for (int i = 0; i < size; i++) {
						file.write(arr.get(i).toString() + " ");
						file.write(arrSpeed.get(i).toString() + " ");
						file.write(arrDuration.get(i).toString());
						file.write(System.lineSeparator());
					}
					file.close();
				} catch (Exception e) {
					System.out.println(e);
				}
			}

			if (commands.equals("X") || commands.equals("x")) {

				ArrayList<String> arrX = new ArrayList<String>();
				ArrayList<Integer> arrSpeedX = new ArrayList<Integer>();
				ArrayList<Integer> arrDurationX = new ArrayList<Integer>();

				String arrChar = null;
				String time = null;
				String speed = null;
				int lineNumberCount = 0;

				BufferedReader br = new BufferedReader(new FileReader("readcommands.txt"));
				StringBuilder sb = new StringBuilder();
				String line = br.readLine();

				File file = new File ("readcommands.txt");
				if (file.exists()) {
					try {
						FileReader fr = new FileReader(file);
						LineNumberReader lr = new LineNumberReader(fr);
						try {
							while(lr.readLine() != null) {
								lineNumberCount++; //This has been done so that it can read the number of lines in the file then storing it into a variable
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}

				if (lineNumberCount >= 3) { //This is the check that I have used to see if there are 3 or more lines in the text file
					while (line != null) {

						System.out.println(line);
						String str = line;

						String[] arrRead = str.split(",", 3); //This splits the line into 3 parts after the "comma ," has been met
						for (String a : arrRead)
							System.out.println(a);

						arrChar = arrRead[0];

						speed = arrRead[1];
						int speedX = Integer.parseInt(speed);

						time = arrRead[2]; //These 3 variables will store each of the index from the file into ArrayLists
						int timeX = Integer.parseInt(time); //These 2 will pass the Int from the Arrays into variables to be used

						arrX.add(arrChar);
						arrSpeedX.add(speedX);
						arrDurationX.add(timeX);

						if (arrChar.equals("F")) {
							myFinch.setWheelVelocities(speedX, speedX, timeX * 1000);
						} else if (arrChar.equals("B")) {
							myFinch.setWheelVelocities(-speedX, -speedX, timeX * 1000);
						} else if (arrChar.equals("L")) {
							myFinch.setWheelVelocities(-100, 100, 1000);
							myFinch.setWheelVelocities(speedX, speedX, timeX * 1000);
						}else if (arrChar.equals("R")) {
							myFinch.setWheelVelocities(100, -100, 1000);
							myFinch.setWheelVelocities(speedX, speedX, timeX * 1000); //The If statements until now will make the Finch act up
						} else if (arrChar.equals("T")) {
							for (int i = 1; i < speedX + 1; i++) {

								Collections.reverse(arrX);
								Collections.reverse(arrSpeedX); //These will reverse the order of the Arrays
								Collections.reverse(arrDurationX);

								String commandsX = arrX.get(i);
								int speedX2 = arrSpeedX.get(i); //This will get the indexes put into a variable to be used for the if Statements
								int durationX = arrDurationX.get(i);

								System.out.println(line);
								System.out.println(arrX.get(i));
								System.out.println(arrSpeedX.get(i)); //These will print out the values to illustrate which line is being executed
								System.out.println(arrDurationX.get(i));

								if (commandsX.equals("F")) {
									myFinch.setWheelVelocities(speedX2, speedX2, durationX * 1000);
								} else if (commandsX.equals("B")) {
									myFinch.setWheelVelocities(-speedX2, -speedX2, durationX * 1000);
								} else if (commandsX.equals("L")) {
									myFinch.setWheelVelocities(-100, 100, 1000);
									myFinch.setWheelVelocities(speedX2, speedX2, durationX * 1000);
								}else if (commandsX.equals("R")) {
									myFinch.setWheelVelocities(100, -100, 1000);
									myFinch.setWheelVelocities(speedX2, speedX2, durationX * 1000); }

								Collections.reverse(arrX);
								Collections.reverse(arrSpeedX);
								Collections.reverse(arrDurationX);
							}

						}
						line = br.readLine();
					} 
				} else if (lineNumberCount < 3) {
					System.out.println("There needs to be at least 3 Methods in the File to Execute");
				}
			}

			System.out.println("Please enter another letter: ");
			commands = userinput.next();

		} while (!commands.equals("Q"));

		if (commands.equals("Q") || commands.equals("q")) {
			System.out.println("Quit has been entered, see you next time!");

			myFinch.quit();
			System.exit(0);
		}
	}
}