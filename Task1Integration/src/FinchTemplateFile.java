//package Code;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;
import java.util.Scanner;
import java.util.Random; 

public class FinchTemplateFile
  {	
	
   public static void main(final String[] args) {
	   
   }
   
   public void Task4() {
      

	   	Finch myFinch = new Finch();
   	  	int length;
   	  	int zigzagSec;
   	  	int speed;
   	  	int time;

   	  	final int maxZigzagSec = 12;
   	  	Random rnd = new Random();
   	  	Scanner sc = new Scanner(System.in);
   	  	Zigzag z = new Zigzag();

   	  	
   	  	do{	
   	  		System.out.println("Note : The length of ZIGZAG should be in between 15 to 85 cms and ZIGZAG sections should be even and less than 12 \n\n");
   	  		System.out.println("Please Enter the length of ZIGZAG in cms : ");
   	  		length = sc.nextInt();
   	  		System.out.println("Please Enter the Number of ZIGZAG sections : ");
   	  		zigzagSec = sc.nextInt();
		
   	  		if ((length <= 85 && length >= 15)  && (zigzagSec%2) == 0 && zigzagSec <= maxZigzagSec){
   	  			break;
	
   	  		} else 
   	  			System.out.println("Please enter correct details ");
		
   	  	} while (true);
      	   
   		speed = rnd.nextInt(155)+100;
  		System.out.println("Random Num : "+speed);
  		int speedTemp = (speed*147)/1000;
  		time = (length*1000)/speedTemp;
  		System.out.println("speed : "+speed);
  		System.out.println("time : "+time);
  		
  		z.writeOutput(speed, time, length);
  		z.moveFinch(zigzagSec, speed, time, myFinch);
 			

      myFinch.quit();
      System.exit(0);
      
      }
   }

