import java.util.Scanner;
import edu.cmu.ri.createlab.terk.robot.finch.Finch;

public class Moving {

	public static void movement(Scanner userInput, String commands, Finch myFinch, int moveCounter, int turnFinchSpeed, int turnMoveDuration) {

		int finchSpeed, moveDuration = 0;
		
		System.out.println("Please enter the finch Speed: ");
		do {
			System.out.println("Finch must have a speed of more (>) than 1 and less (<) than 200: ");
			finchSpeed = userInput.nextInt();
		} while ((finchSpeed < 1 || finchSpeed > 200));

		System.out.println("Please enter the finch Movement Duration: ");
		do {
			System.out.println("Finch must move more (>) than 1 sec and less (<) than 6 seconds: ");
			moveDuration = userInput.nextInt();
		} while ((moveDuration < 0 || moveDuration > 7));

		if (commands.equals("F")) {
			myFinch.setWheelVelocities(finchSpeed,finchSpeed,moveDuration*1000);
			Navigation.arr.add("F");
			Navigation.arrSpeed.add(finchSpeed);
			Navigation.arrDuration.add(moveDuration);
			moveCounter++;
		} else if (commands.equals("B")) {
			myFinch.setWheelVelocities(-finchSpeed,-finchSpeed,moveDuration*1000);
			Navigation.arr.add("B");
			Navigation.arrSpeed.add(-finchSpeed);
			Navigation.arrDuration.add(moveDuration);
			moveCounter++;
		} else if (commands.equals("R")) {
			myFinch.setWheelVelocities(100,-100,1000);
			myFinch.setWheelVelocities(finchSpeed,finchSpeed,moveDuration*1000);
			Navigation.arr.add("R");
			Navigation.arrSpeed.add(turnFinchSpeed);
			Navigation.arrDuration.add(turnMoveDuration);
			moveCounter++;
		} else if (commands.equals("L")) {
			myFinch.setWheelVelocities(-100,100,1000);
			myFinch.setWheelVelocities(finchSpeed,finchSpeed,moveDuration*1000);
			Navigation.arr.add("L");
			Navigation.arrSpeed.add(turnFinchSpeed);
			Navigation.arrDuration.add(turnMoveDuration);
			moveCounter++;
		}
	}
}
