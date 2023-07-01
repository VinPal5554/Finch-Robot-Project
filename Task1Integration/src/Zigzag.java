//package Code;

import java.io.FileWriter;
import java.io.IOException;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;


public class Zigzag {


 	  
 	  public void welcome(){
 		System.out.println("*****************************************"); 		
		System.out.println("Please place finch level on the floor");	
 		System.out.println("*****************************************"); 		

 	}
 	  
 	public void writeOutput(int speed, int time , int distance){
 		try {
 		      FileWriter myWriter = new FileWriter("Output.txt");
 		      myWriter.write("Distance Covered : "+distance +"cms  ");
 		      myWriter.write("Speed : "+speed+" ");
 		      myWriter.write("Time taken : "+time+" ms  ");
		      
 		      myWriter.close();
 		      System.out.println("Successfully wrote to the file.");
 		    } catch (IOException e) {
 		      System.out.println("An error occurred.");
 		      e.printStackTrace();
 		    }
 		
 	}
	
	
 	  public void moveFinch(int sections, int speed , int time, Finch myFinch ){
 		 
 		  	int tS= 100;
 		  	int tT = 700;
 		    myFinch.sleep(2000);
 		  
 	  		for(int i=0 ; i< sections/2; i++) {
 	  			myFinch.setLED(0,255,0);
 	  	  		myFinch.setWheelVelocities(speed,speed,time);
 	  	  		myFinch.sleep(1000);
 	  	  		myFinch.setWheelVelocities(tS,-tS,tT);
 	  	  		myFinch.setLED(0,0,255);
 	  	  		myFinch.setWheelVelocities(speed,speed,time);
 	  	  		myFinch.sleep(1000);
 	  	  		myFinch.setWheelVelocities(-tS,tS,tT);
 	  		}
 	  		
	  	  		myFinch.setWheelVelocities(-tS,tS,tT);

 	  		
 	 		for(int i=0 ; i< sections/2; i++) {
 	  			myFinch.setLED(0,255,0);
 	  	  		myFinch.setWheelVelocities(speed,speed,time);
 	  	  		myFinch.sleep(1000);
 	  	  		myFinch.setWheelVelocities(-tS,tS,tT);
 	  	  		myFinch.setLED(0,0,255);
 	  	  		myFinch.setWheelVelocities(speed,speed,time);
 	  	  		myFinch.sleep(1000);
 	  	  		myFinch.setWheelVelocities(tS,-tS,tT);
 	  		}
 	 		
 		  
 	  }
}
