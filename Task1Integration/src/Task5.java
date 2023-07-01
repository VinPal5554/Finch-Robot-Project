import edu.cmu.ri.createlab.terk.robot.finch.Finch;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.io.File;
import java.awt.event.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

abstract class GUI implements ActionListener {
	
	protected static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	protected static int SCREENWIDTH = (int)screenSize.getWidth();
	protected static int SCREENHEIGHT = (int)screenSize.getHeight();
	protected JButton mainButtonArr[] = new JButton[5];
	protected JButton minigameArr[] = new JButton[4];
	protected JFrame mainWindow = new JFrame("Detect Object");
	protected JFrame minigameWindow = new JFrame("Minigames");
	protected JFrame executionLogWindow = new JFrame("Execution Log");
	protected JButton executionButtonYes = new JButton("Yes");
	protected JButton executionButtonNo = new JButton("No");
	
	abstract public void getWindow();  // overwritten in derived classes
	
	public void checkResolution(int x, int y, JButton button[], JFrame currentFrame) { // sets GUI component locations
		int incrementVar;
		int xStartingPosition;
		JLabel text = new JLabel();
		text.setText("Please select one of the buttons to begin: ");
		if (x == 1920 && y == 1080) {   
			if (button == mainButtonArr) {
				text.setBounds(840, 100, 500, 200);
				currentFrame.add(text);
			
				xStartingPosition = 120;   
				incrementVar = 480;
				int i;
				for (i = 0; i < button.length - 1; ++i) {
					currentFrame.add(button[i]);
					button[i].setBounds(xStartingPosition, 420, 240, 240);
					xStartingPosition += incrementVar;
				}
				button[i].setBounds(0, 0, 120, 120);
				currentFrame.add(button[i]);
			}
			
			else {
				text.setBounds(840, 100, 500, 200); 
				currentFrame.add(text);
			
				xStartingPosition = 360;   
				incrementVar = 480;
				int i;
				for (i = 0; i < button.length - 1; ++i) {
					currentFrame.add(button[i]);
					button[i].setBounds(xStartingPosition, 420, 240, 240);
					xStartingPosition += incrementVar;
				}
				button[i].setBounds(0, 0, 120, 120);
				currentFrame.add(button[i]);
			}
		}
		
		// default GUI is set up in case monitor resolution does not match
		else {  
			if (button == mainButtonArr) {    
				currentFrame = new JFrame("Please select a button to begin");
				for (int i = 0; i < button.length - 1; ++i) {
					currentFrame.add(button[i]);
				}
				currentFrame.setLayout(new GridLayout(2,2));
				currentFrame.setSize(500, 500);
			}
			else {
				currentFrame = new JFrame("Please select a button to begin");
				for (int i = 0; i < button.length - 1; ++i) {
					currentFrame.add(button[i]);
				}
				currentFrame.setLayout(new GridLayout(3,1));
				currentFrame.setSize(500, 500);
			}
		}
		
		currentFrame.setVisible(true);
	}
		
	public void getExecutionLogWindow(String currentMode, String infoArr[]) {  // sets up execution log GUI depending on mode
		executionLogWindow.setSize(SCREENWIDTH, SCREENHEIGHT);
		executionLogWindow.setLayout(null);
		executionLogWindow.setResizable(false);
		
		JLabel executionLogText = new JLabel("Would you like to view the log of execution?");
		executionLogWindow.add(executionLogText);
		executionLogWindow.add(executionButtonYes);
		executionLogWindow.add(executionButtonNo);
		
		executionButtonYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame executionLog = new JFrame("Execution Log Information");
				executionLog.setSize(500,500);
				executionLog.setLayout(null);
				executionLog.setResizable(false);
				
				if (currentMode == "Scaredy") {
					JLabel info1 = new JLabel("Mode: " + infoArr[0]);
					JLabel info2 = new JLabel("Duration of program: " + infoArr[1] + " seconds");
					JLabel info3 = new JLabel("Number of objects encountered: " + infoArr[2] + " objects");
					executionLog.add(info1);
					executionLog.add(info2);
					executionLog.add(info3);
					info1.setBounds(0, 0, 300, 30);
					info2.setBounds(0, 20, 300, 50);
					info3.setBounds(0, 40, 300, 70);
					
				}
				else if (currentMode == "Curious") {
					JLabel info1 = new JLabel("Mode: " + infoArr[0]);
					JLabel info2 = new JLabel("Duration of program: " + infoArr[1] + " seconds");
					JLabel info3 = new JLabel("Number of objects encountered: " + infoArr[2] + " objects");
					executionLog.add(info1);
					executionLog.add(info2);
					executionLog.add(info3);
					info1.setBounds(0, 0, 300, 30);
					info2.setBounds(0, 20, 300, 50);
					info3.setBounds(0, 40, 300, 70);
				}
				else if (currentMode == "HeatSeeking") {
					JLabel info1 = new JLabel("Mode: " + infoArr[0]);
					JLabel info2 = new JLabel("Time taken to find human: " + infoArr[1] + " seconds");
					JLabel info3 = new JLabel("Number of objects encountered: " + infoArr[2] + " objects");
					JLabel info4 = new JLabel("Temperature of different objects encountered: " + infoArr[3]);
					executionLog.add(info1);
					executionLog.add(info2);
					executionLog.add(info3);
					executionLog.add(info4);
					info1.setBounds(0, 0, 300, 30);
					info2.setBounds(0, 20, 300, 50);
					info3.setBounds(0, 40, 300, 70);
					info4.setBounds(0, 60, 300, 90);
				}
				else if (currentMode == "TreasureMaze") {
					JLabel info1 = new JLabel("Mode: " + infoArr[0]);
					JLabel info2 = new JLabel("Duration of program: " + infoArr[1] + " seconds");
					JLabel info3 = new JLabel("Number of turns taken: " + infoArr[2]);
					JLabel info4 = new JLabel("Types of turns taken: " + infoArr[3]);
					executionLog.add(info1);
					executionLog.add(info2);
					executionLog.add(info3);
					executionLog.add(info4);
					info1.setBounds(0, 0, 300, 30);
					info2.setBounds(0, 20, 300, 50);
					info3.setBounds(0, 40, 300, 70);
					info4.setBounds(0, 60, 300, 90);
				}
				else if (currentMode == "ShapeGuessing") {
					JLabel info1 = new JLabel("Mode: " + infoArr[0]);
					JLabel info2 = new JLabel("Estimate of shape:  " + infoArr[1]);
					JLabel info3 = new JLabel("Number of vertices: " + infoArr[2]);
					JLabel info4 = new JLabel("Duration of program: " + infoArr[3] + " seconds");
					executionLog.add(info1);
					executionLog.add(info2);
					executionLog.add(info3);
					executionLog.add(info4);
					info1.setBounds(0, 0, 300, 30);
					info2.setBounds(0, 20, 300, 50);
					info3.setBounds(0, 40, 300, 70);
					info4.setBounds(0, 60, 300, 90);
				}
				
				executionLog.setVisible(true);
			}
		});
		executionButtonNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		executionLogWindow.setSize(500, 500);
		executionLogText.setBounds(130, 0, 300, 100);
		executionButtonYes.setBounds(40, 150, 150, 150);
		executionButtonNo.setBounds(300, 150, 150, 150);
		
		executionLogWindow.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) { // function is overwritten to allow different functionality
		
	}
}

class MainInterface extends GUI {
	
	public void getWindow() {  // sets up main GUI page
		
		mainWindow.setSize(SCREENWIDTH, SCREENHEIGHT);
		mainWindow.setResizable(false);
		mainWindow.setLayout(null); 
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainButtonArr[0] = new JButton("Any");
		mainButtonArr[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Random randObj = new Random();
				Mode.chooseRandomMode(randObj);
			}
		});
		
		mainButtonArr[1] = new JButton("Curious Finch");
		mainButtonArr[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Mode curiousRefObj = new Curious();
				curiousRefObj.startProgram();
			}
		});
		
		mainButtonArr[2] = new JButton("Scaredy Finch");
		mainButtonArr[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Mode scaredyRefObj = new Scaredy();
				scaredyRefObj.startProgram();
			}
		});
		
		mainButtonArr[3] = new JButton("Minigames");
		mainButtonArr[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainWindow.setVisible(false);
				MinigameInterface interfaceTwo = new MinigameInterface();
				interfaceTwo.getWindow();
			}
		});
		
		File file = new File("home.png");
		String absolutePath = file.getAbsolutePath();
		mainButtonArr[4] = new JButton(new ImageIcon(absolutePath));
		mainButtonArr[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (mainWindow.isVisible() || minigameWindow.isVisible()) {
					mainWindow.setVisible(false);
					minigameWindow.setVisible(false);
				}
				MainInterface testObj = new MainInterface();
				testObj.getWindow();
			}
		});
		
		checkResolution(SCREENWIDTH, SCREENHEIGHT, mainButtonArr, mainWindow); 
	}
}

class MinigameInterface extends GUI {
	
	public void getWindow() {  // sets minigame GUI page
		
		minigameWindow.setSize(SCREENWIDTH, SCREENHEIGHT);
		minigameWindow.setLayout(null);
		minigameWindow.setResizable(false);
		
		minigameArr[0] = new JButton("Heat-Seeking");
		minigameArr[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Mode heatSeekingObj = new HeatSeekingGame();
				heatSeekingObj.startProgram();
			}
		});
		minigameArr[1] = new JButton("Treasure-Maze");
		minigameArr[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Mode treasureMazeObj = new TreasureMazeGame();
				treasureMazeObj.startProgram();
			}
		});
		minigameArr[2] = new JButton("Shape-Guessing (experimental)");
		minigameArr[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Mode shapeGuessingObj = new ShapeGuessingGame();
				shapeGuessingObj.startProgram();
			}
		});
		
		File file = new File("home.png");
		String absolutePath = file.getAbsolutePath();
		minigameArr[3] = new JButton(new ImageIcon(absolutePath)); // guarantees that home icon will display with any directory
		minigameArr[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (mainWindow.isVisible() || minigameWindow.isVisible()) {
					mainWindow.setVisible(false);
					minigameWindow.setVisible(false);
				}
				MainInterface testObj = new MainInterface();
				testObj.getWindow();
			}
		});
		
		checkResolution(SCREENWIDTH, SCREENHEIGHT, minigameArr, minigameWindow);  
	}

}

public class Task5 {
	
	public static void main (String args[]) {
	
		GUI beginInterface = new MainInterface();
		beginInterface.getWindow();
	}
}

abstract class Mode {
	
	protected Finch myFinchObj = new Finch();
	protected static String userChoice;
	protected static int numOfObjects = 0;
	protected Random randObj = new Random();
	protected int possibleDirections[] = {1, 2, 3, 4};
	protected int MAXLIMIT = possibleDirections.length;
	protected int MINLIMIT = 0;
	
	abstract void startProgram();  // overwritten in derived classes

	void MoveDirection(Finch finch) { // default function for moving
		
		while (finch.isFinchLevel() && !finch.isBeakUp()) {
			int direction = possibleDirections[randObj.nextInt(MAXLIMIT - MINLIMIT) + MINLIMIT];
			
			if (direction == 1) {
				finch.setWheelVelocities(200, 200, 1000);
				finch.sleep(1000);
			}
			else if (direction == 2) {
				finch.setWheelVelocities(-200, -200, 1000);
				finch.sleep(1000);
			}
			else if (direction == 3) {
				finch.setWheelVelocities(-200, 200, 500);
				finch.setWheelVelocities(200, 200, 500);
				finch.sleep(1000);
			}
			else if (direction == 4) {
				finch.setWheelVelocities(200, -200, 500);
				finch.setWheelVelocities(200, 200, 500);
				finch.sleep(1000);
			}
			
			if (finch.isObstacle()) {
				++numOfObjects;
				break;
			}
		}
	}
	
	static void chooseRandomMode(Random rand) {   // randomly selects mode
		String choiceArr[] = {"Curious", "Scaredy"};
		final int UPPERLIMIT = 1;
		final int LOWERLIMIT = 0;
		int randIndex = rand.nextInt((UPPERLIMIT + 1) - LOWERLIMIT) - LOWERLIMIT;
		userChoice = choiceArr[randIndex];
		if (userChoice == "Curious") {
			Mode obj = new Curious();
			obj.startProgram();
		}
		else {
			Mode obj = new Scaredy();
			obj.startProgram();
		}
	}
}

class Curious extends Mode {
	
	protected String executionLogInformation[] = new String[3];
	
	void startProgram() { // starts Curious mode
		
		long initialCuriousTime = System.nanoTime();
		userChoice = "Curious";
		GUI executionLog = new MainInterface();
		
		
		while (!myFinchObj.isFinchLevel()) {
			myFinchObj.setLED(255, 255, 255, 1000);
			myFinchObj.sleep(1000);  
			if (numOfObjects > 0) {
				break;
			}
		}
	
		MoveDirection(myFinchObj);
		
		while (myFinchObj.isObstacle() && (!myFinchObj.isBeakUp())) {
			myFinchObj.stopWheels();
			myFinchObj.setLED(0, 0, 255, 1000);
			if (!myFinchObj.isObstacle()) {
				break;
			}
		}
		
		long initialCheck = System.nanoTime();
		while (!myFinchObj.isObstacle() && (!myFinchObj.isBeakUp())) { // checks whether Finch has not encountered an object for 5 seconds
			myFinchObj.setLED(0, 255, 0);                                   
			myFinchObj.setWheelVelocities(200, 200, 1000);
			long finalCheck = System.nanoTime();
			long timeWithoutObject = finalCheck - initialCheck;
			long timeInMilli = TimeUnit.NANOSECONDS.toMillis(timeWithoutObject);
		
			if (myFinchObj.isObstacle() && (!myFinchObj.isBeakUp())) {
				++numOfObjects;
				while (myFinchObj.isObstacle() && (!myFinchObj.isBeakUp())) {
					myFinchObj.stopWheels();
					myFinchObj.setLED(0, 0, 255, 1000);
					initialCheck = System.nanoTime();
				}
			}
			
			if (timeInMilli >= 5000.0 && (!myFinchObj.isBeakUp())) {
				myFinchObj.sleep(1000);
				myFinchObj.setLED(255, 255, 255, 1000);
				startProgram();
			}
		}
		long finalCuriousTime = System.nanoTime();
		long timeWithoutObject = finalCuriousTime - initialCuriousTime;
		long timeInSeconds = TimeUnit.NANOSECONDS.toSeconds(timeWithoutObject);
		String durationOfProgram = String.valueOf(timeInSeconds);
		String numberOfObjects = String.valueOf(numOfObjects);
		executionLogInformation[0] = userChoice;
		executionLogInformation[1] = durationOfProgram; // stores execution log information
		executionLogInformation[2] = numberOfObjects;
		
		executionLog.getExecutionLogWindow("Curious", executionLogInformation);
	}
}

class Scaredy extends Mode {
	
	protected String executionLogInformation[] = new String[3];
	
	void startProgram() { // starts Scaredy mode
		
		userChoice = "Scaredy";
		GUI executionLog = new MainInterface();
		long initialScaredyTime = System.currentTimeMillis();
		
		while (!myFinchObj.isFinchLevel()) {
			myFinchObj.setLED(255, 255, 255, 1000);
			myFinchObj.sleep(1000);  
		}
		
		while (myFinchObj.isFinchLevel() && (!myFinchObj.isBeakUp())) {
			do {
				myFinchObj.setLED(0, 255, 0);
				MoveDirection(myFinchObj);
			} while (!myFinchObj.isObstacle() && (!myFinchObj.isBeakUp()));
			
			if (myFinchObj.isObstacle()) {
				myFinchObj.buzz(7900, 2000);
				myFinchObj.sleep(1000);
				myFinchObj.setLED(255, 0, 0);
				myFinchObj.setWheelVelocities(-200, -200 , 1000);
				myFinchObj.setWheelVelocities(-150, 150, 1500);
				myFinchObj.setWheelVelocities(200,  200, 3000);
			}
		}
		long finalScaredyTime = System.currentTimeMillis(); // stores execution log information
		long timeWithoutObject = finalScaredyTime - initialScaredyTime;
		long timeInSeconds = TimeUnit.MILLISECONDS.toSeconds(timeWithoutObject);
		String durationOfProgram = String.valueOf(timeInSeconds);
		String numberOfObjects = String.valueOf(numOfObjects);
		executionLogInformation[0] = userChoice;
		executionLogInformation[1] = durationOfProgram;
		executionLogInformation[2] = numberOfObjects;
		executionLog.getExecutionLogWindow("Scaredy", executionLogInformation);
	}
}

class HeatSeekingGame extends Mode {
	
	protected long timeToFindHumanSecond;
	protected ArrayList<String> tempOfObjects = new ArrayList<>();
	protected double objectTemp;
	protected String executionLogInformation[] = new String[4];
	protected long timeToFindHumanNano;
	
    void startProgram() { // starts Heat Seeking Minigame
    	GUI executionLog = new MainInterface();
    	long heatInitialTime = System.nanoTime();
    	final double MINTEMP = 30.0;  // lowest likely temperature of human skin
    	final double MAXTEMP = 40.0;  // highest likely temperature of human skin
    	userChoice = "HeatSeeking";
    	
    	while (!myFinchObj.isFinchLevel()) {
			myFinchObj.setLED(255, 255, 255, 1000);
			myFinchObj.sleep(1000);  
		}
    	
    	while (!myFinchObj.isBeakUp()) {
    		MoveDirection(myFinchObj);
    		if (myFinchObj.isObstacle()) {
    		objectTemp = myFinchObj.getTemperature();
    		String stringObjectTemp = String.valueOf(objectTemp);
    		tempOfObjects.add(stringObjectTemp);
    		}
    		
    		if (objectTemp < MAXTEMP && objectTemp > MINTEMP) {
    			long heatFinalTime = System.nanoTime();
    			timeToFindHumanNano = heatFinalTime - heatInitialTime;
    			timeToFindHumanSecond = TimeUnit.NANOSECONDS.toSeconds(timeToFindHumanNano);
    			while (myFinchObj.isObstacle()) {
    				myFinchObj.stopWheels();
    				myFinchObj.setLED(255, 0, 0);
    				if (myFinchObj.isBeakUp()) {
    					String timeToFindHuman = String.valueOf(timeToFindHumanNano);
    					String numberOfObjects = String.valueOf(numOfObjects);
    					String differentTemps = "";
    					for (int i = 0; i < tempOfObjects.size(); ++i) {
    						String currentTempString = tempOfObjects.get(i);
    						differentTemps = differentTemps + currentTempString + " "; 
    					}
    					executionLogInformation[0] = userChoice;
    					executionLogInformation[1] = timeToFindHuman;
    					executionLogInformation[2] = numberOfObjects;
    					executionLogInformation[3] = differentTemps;
    					executionLog.getExecutionLogWindow("Scaredy", executionLogInformation);
    				}
    			}
    		}
    	}
    	String timeToFindHuman = String.valueOf(timeToFindHumanNano); 
		String numberOfObjects = String.valueOf(numOfObjects);
		String differentTemps = "";
		for (int i = 0; i < tempOfObjects.size(); ++i) {
			String currentTempString = tempOfObjects.get(i);
			differentTemps = differentTemps + currentTempString + " "; 
		}
		executionLogInformation[0] = userChoice; // stores heat-seeking execution log information
		executionLogInformation[1] = timeToFindHuman;
		executionLogInformation[2] = numberOfObjects;
		executionLogInformation[3] = differentTemps;
		executionLog.getExecutionLogWindow("Scaredy", executionLogInformation);
    }
}

class TreasureMazeGame extends Mode {
	
	protected long navigateMazeTimeSecond;
	protected int numOfTurnsTook = 0;
	protected ArrayList<String> typeOfTurns = new ArrayList<>();
	protected String executionLogInformation[] = new String[4];
	
	void startProgram() { // starts treasure maze minigame
		long mazeInitialTime = System.nanoTime();
		GUI executionLog = new MainInterface();
		userChoice = "TreasureMaze";
		
		while (!myFinchObj.isFinchLevel()) {
			myFinchObj.setLED(255, 255, 255, 1000);
			myFinchObj.sleep(1000);  
		}
		
		MoveDirection(myFinchObj);
		long mazeFinalTime = System.nanoTime();
		long navigateMazeTimeNano = mazeFinalTime - mazeInitialTime;
		navigateMazeTimeSecond = TimeUnit.NANOSECONDS.toSeconds(navigateMazeTimeNano);
		String mazeTimeTaken = String.valueOf(navigateMazeTimeSecond);
		String numberOfTurns = String.valueOf(numOfTurnsTook);
		String differentTurns = "";
		for (int i = 0; i < typeOfTurns.size(); ++i) {
			String currentTempString = typeOfTurns.get(i);
			differentTurns = differentTurns + currentTempString + " ";
		}
		executionLogInformation[0] = userChoice;
		executionLogInformation[1] = mazeTimeTaken;
		executionLogInformation[2] = numberOfTurns; // stores treasure maze minigame information
		executionLogInformation[3] = differentTurns;
		executionLog.getExecutionLogWindow("Scaredy", executionLogInformation);
	}
	
	void MoveDirection(Finch finch) { // MoveDirection is overwritten here
		
		while (!finch.isBeakUp()) {
			if (finch.isObstacle()) {   
				finch.setWheelVelocities(-100, 100, 2000);
				if (finch.isObstacle()) {
					finch.setWheelVelocities(50, -100, 1300);
					finch.setWheelVelocities(50, -100, 1300);
					if (finch.isObstacle()) {
						break;
					}
					else {
						typeOfTurns.add("Right");
						++numOfTurnsTook;
						finch.setWheelVelocities(100, 100, 2000);
					}
				}
				else {
					typeOfTurns.add("Left");
					++numOfTurnsTook;
					finch.setWheelVelocities(100, 100, 2000);
				}
			}
			else {
				typeOfTurns.add("Straight");
				++numOfTurnsTook;
				finch.setWheelVelocities(100, 100, 2000);
			}
		}
	}
}

class ShapeGuessingGame extends Mode {
	
	protected String shapeGuess;
	protected ArrayList<Integer> shapeArr = new ArrayList<>();
	protected int numOfVertices = 0;
	protected int WIDTH;
	protected int HEIGHT;
	protected int SIDES; // needed so that Finch wont scan whole object more than once
	protected int iterationVar = 1;
	protected int sideVar = 0;
	protected long timeToScanShapeSecond;
	protected String executionLogInformation[] = new String[4];
	protected GUI executionLog = new MainInterface();
	
	void startProgram() { // starts shape guessing minigame
		
		while (!myFinchObj.isFinchLevel()) {
			myFinchObj.setLED(255, 255, 255, 1000);
			myFinchObj.sleep(1000);  
		}
		
		JFrame shapeGuessWindow = new JFrame("Shape-Guessing game");
		shapeGuessWindow.setLayout(null);
		shapeGuessWindow.setSize(500, 500);
		
		shapeGuessWindow.setResizable(false);
		
		userChoice = "ShapeGuessing";
		
		JLabel shapeInfo1 = new JLabel("Please enter a width, height and number of sides.");
		shapeGuessWindow.add(shapeInfo1);
		shapeInfo1.setBounds(0, 0, 300, 30);
		
		JLabel width = new JLabel("Width");
		shapeGuessWindow.add(width);
		width.setBounds(0, 40, 300, 30);
		JTextField enterWidth = new JTextField(15);
		shapeGuessWindow.add(enterWidth);
		enterWidth.setBounds(50, 40, 300, 60);
		enterWidth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String widthInput = enterWidth.getText();
				WIDTH = Integer.parseInt(widthInput);
			}
		});
		
		JLabel height = new JLabel("Height");
		shapeGuessWindow.add(height);
		height.setBounds(0, 120, 300, 30);
		JTextField enterHeight = new JTextField(15);
		shapeGuessWindow.add(enterHeight);
		enterHeight.setBounds(50, 120, 300, 60);
		enterHeight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String heightInput = enterHeight.getText();
				HEIGHT = Integer.parseInt(heightInput);
			}
		});
		
		JLabel sides = new JLabel("Sides");
		shapeGuessWindow.add(sides);
		sides.setBounds(0, 200, 300, 30);
		JTextField enterSides = new JTextField(15);
		shapeGuessWindow.add(enterSides);
		enterSides.setBounds(50, 200, 300, 60);
		enterSides.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sideInput = enterSides.getText();
				SIDES = Integer.parseInt(sideInput);
				MoveDirection(myFinchObj);
			}
		});
		
		shapeGuessWindow.setVisible(true);
	}
	
	void MoveDirection(Finch finch) { // Move direction is overwritten here again for this mode
		
		long shapeInitialTime = System.nanoTime();
		
		while (!finch.isBeakUp()) {
			while (finch.isObstacle() && WIDTH > iterationVar && SIDES > 0) {
				finch.setWheelVelocities(0, -150, 1000);
				finch.setWheelVelocities(100, 100, 1000);
				finch.setWheelVelocities(-130, 0, 1000);
				finch.setWheelVelocities(100, 100, 1000);
				++iterationVar;
				shapeArr.add(1);
			}
			if (sideVar == SIDES) {
				long shapeFinalTime = System.nanoTime();
				long timeToScanShapeNano = shapeFinalTime - shapeInitialTime;
				timeToScanShapeSecond = TimeUnit.NANOSECONDS.toSeconds(timeToScanShapeNano);
				shapeGuess = guessShape(); 
				executionLogInformation[0] = userChoice;
				executionLogInformation[1] = shapeGuess; // stores shape guessing minigame information
				executionLogInformation[2] = String.valueOf(numOfVertices);
				executionLogInformation[3] = String.valueOf(timeToScanShapeSecond);
				executionLog.getExecutionLogWindow("ShapeGuessing", executionLogInformation);
				break;
			}
			else {
				finch.setWheelVelocities(0, 100, 1000); // Finch turns another 45 degrees to face corner
				if (finch.isObstacle()) {
					shapeArr.add(2);
					++sideVar;
					iterationVar = 1;
					++numOfVertices;
					finch.setWheelVelocities(0, 100, 1000); // Finch turns another 45 degrees to face shape height
					while (finch.isObstacle() && HEIGHT > iterationVar) {
						// turn finch and move to next direction
						++iterationVar;
						shapeArr.add(1);
					}
					finch.setWheelVelocities(0, 100, 1000); // Finch turns 45 degrees to check if encountered corner
					if (finch.isObstacle()) {
						shapeArr.add(2);
						++sideVar;
						iterationVar = 1;
						++numOfVertices;
						finch.setWheelVelocities(0, 100, 1000); // Finch turns another 45 degrees to face shape height
						continue;
					}
				}
			}
		}
	}
	
	String guessShape() { // returns a shape guess based on scanned information
		
		// shapeArr will look something like this: e.g 111211121112
		// 1 stands for side, 2 stands for corner
		String shapeGuess;   
		int lengthOfSide = 0;
		ArrayList<Integer> lengthArr = new ArrayList<>();
		
		for (int i = 0; i < shapeArr.size(); ++i) {
			
			if (shapeArr.get(i) == 1) {
				++lengthOfSide;
			}
			else {
				lengthArr.add(lengthOfSide);
				lengthOfSide = 0;
			}
		}
		
		if (SIDES == 3) {
			
			if (lengthArr.get(0) == lengthArr.get(1) && lengthArr.get(1) == lengthArr.get(2) && lengthArr.get(0) == lengthArr.get(2)) {
				shapeGuess = "Equalateral Triangle";
				return shapeGuess;
			}
			else if(lengthArr.get(0) != lengthArr.get(1) && lengthArr.get(1) != lengthArr.get(2) && lengthArr.get(0) != lengthArr.get(2)) {
				shapeGuess = "Scalene Triangle";
				return shapeGuess;
			}
			else {
				shapeGuess = "Isosceles Triangle";
				return shapeGuess;
			}
		}
		
		if (SIDES == 4) {
			
			int counter = 0;
			ArrayList<Integer> lengthArrCopy = (ArrayList)lengthArr.clone();
			for (int j = 0; j < lengthArr.size(); ++j) { 
				for (int i = 0; i < lengthArr.size(); ++i) {
					if (lengthArr.get(j) == lengthArrCopy.get(i)) {
						++counter;
					}
				}
			}
			if (counter == 16) {
				shapeGuess = "Square";
				return shapeGuess;
			}
			else if (counter == 8) {
				shapeGuess = "Rectangle";
				return shapeGuess;
			}
			else {
				shapeGuess = "Trapezium";
				return shapeGuess;
			}
		}
		
		switch (SIDES) {
	
			case 5:
				shapeGuess = "Pentagon";
				return shapeGuess;
			case 6:
				shapeGuess = "Hexagon";
				return shapeGuess;
			case 7:
				shapeGuess = "Heptagon";
				return shapeGuess;
			case 8:
				shapeGuess = "Octagon";
				return shapeGuess;
			case 9:
				shapeGuess = "Nonagon";
				return shapeGuess;
			case 10:
				shapeGuess = "Decagon";
				return shapeGuess;
			case 11:
				shapeGuess = "Hendecagon";
				return shapeGuess;
			case 12:
				shapeGuess = "Dodecagon";
				return shapeGuess;
		}
		
		return "Error calculating shape"; // if none of these are met, an error must have occured
	}
}

