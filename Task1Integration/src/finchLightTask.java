import java.util.Scanner; // Import the scanner class
import java.util.ArrayList;
import edu.cmu.ri.createlab.terk.robot.finch.Finch;


public class finchLightTask {

    //creating class variables
    private static int counter = -1;
    private static int numOfLightDetected = 0;
    private static long clock = 0;
    private static long length_of_sessions = 0;
    private static final ArrayList <Integer> lSV = new ArrayList <Integer>(); //The array that holds the sensor values
    private static final ArrayList <Integer> init_lSV = new ArrayList <Integer>();//The array that holds the initial sensor values
    private static int threshHold = 20; // the variable for what the light sensor must reach to be classed as a light detection
    
    
    private static void quit_finch(Finch myFinch)//stopping the session
    {
        myFinch.quit();//quits the finch session
        System.exit(0);
    }
    private static void print_log(Finch myFinch)
    {
        Scanner answer = new Scanner(System.in);// creates a scanner object
        System.out.println("If you want to see your Data Log type any number. If not type any character(s)");// asks for user input
        String choice = answer.next();// pause to check if user has put anything in
        
        if(checkNum(choice)) { // method to check if the input in a number
     	   data_log();
        	quit_finch(myFinch);
        	}
        	else {
        	System.out.println("Goodbye");
        	quit_finch(myFinch);
        }
        
    }
        static boolean checkNum(String choice) {
         try {
        	 Integer.parseInt(choice); //resolves the string choice to return a integer
         }catch(NumberFormatException ex) {// attempts to convert invalid string into a numeric wrapper class
        	 return false; //if it can't it'll return false
         }
        return true;
 }    
        private static long time_converter(long starting_time, long finish_time, boolean choice)
        {
            long result = 0;
            result = choice ? (finish_time-starting_time) : ((finish_time-starting_time) / 1000);// gets time in both milliseconds and sections 
            return result;
        }
        
        
        private static String data_from_init_lSV()
        {
            String init_value;// initialise the sting
            if(init_lSV.isEmpty() == false)// checking if the array is emty
            {
                int init_left = init_lSV.get(0);//gets the 1st index
                int init_right = init_lSV.get(1);//gets the 2nd index 

                init_value = "The initial left and right light sensor values are " + init_left + " " + init_right; //prints to the console
            }
            else
            {
                init_value = "Both sensors returned 0";// since they both are 0.
            }
            return init_value;//return the statement depending on the met conditions.
        }
        private static String data_from_lSV()
        {
            String min_max;
            if(lSV.isEmpty() == false)
            {
                int max_value = lSV.get(0);
                int min_value = lSV.get(0);
                int lightAverage = (max_value + min_value)/2;
                for(int i = 0; i < lSV.size(); i++)//this ensures that it will search every index of the array
                {
                    if(lSV.get(i) > max_value)// checking if current index has a high value than the current max value
                    	max_value = lSV.get(i);// replaces the maximum value
                }

                for(int i = 0; i < lSV.size(); i++)//this ensures that it will search every index of the array
                {
                    if(lSV.get(i) < min_value)
                       min_value = lSV.get(i);//will swap out the new minimum value
                }

                min_max = ("The highest and lowest light sensor values are " + " " + max_value + " and " + min_value + "respectively" +
                		" and the light average is "+ lightAverage);
            }
            else
            {
                min_max = ("The highest, lowest and light average light sensor values are all 0");
            }
            return min_max;
        }
    

        private static void follow_light_source(Finch myFinch, int left_value, int right_value)// will follow the light defending on the difference od the 2 sensors
        {
            int result;
            boolean leftRight = (left_value > right_value);
            result = leftRight ? (left_value-right_value) : (right_value-left_value);
            myFinch.setWheelVelocities(leftRight ? 0 : result, leftRight ? result : 0, 1000);
            myFinch.setWheelVelocities(75,75,1000);
            myFinch.setLED(leftRight ? left_value : right_value ,0,0);//using the left and right values to determine the intensity of the red colour
            if (left_value == right_value)
                myFinch.setWheelVelocities(255,255,1000);
        }
        
        private static void detected_light(Finch myFinch)// function when the light source has been detected
        {
            clock = System.currentTimeMillis();
            numOfLightDetected++;// since the light has been detected the number of light detections is incremented by one
            int left_value = myFinch.getLeftLightSensor();//gets the left sensor value for this current time
            int right_value = myFinch.getRightLightSensor();//gets the left sensor value for this current time
            lSV.add(left_value); //adds that value to the array
            lSV.add(right_value);//adds that value to the array
            

            follow_light_source(myFinch,left_value,right_value);// since light has been detected the finch will now follow it
        }
        
        private static void undetected_light(Finch myFinch)//the function when a light source has not been detected by not meeting the threshold
        {
            myFinch.setLED(255,255,0);//set to yellow as light i'snt found yet
            myFinch.setWheelVelocities(40,40,700);
        }
        
        private static void undetected_light_in4(Finch myFinch)//the function when a light source has not been detected in 4 seconds
        {
            myFinch.setLED(128,0,128);// purple to indicate when it is turning
            myFinch.sleep(700);
            myFinch.setWheelVelocities(0, 150, 1000);// this is worked out to be a 90 degree turn
        }
        
        private static void multiple_light_detections(Finch myFinch)
        {
            length_of_sessions = System.currentTimeMillis();// The function when the finch has detected Light more than once.
            int left_value = myFinch.getLeftLightSensor();
            int right_value = myFinch.getRightLightSensor();
            init_lSV.add(left_value);//adds the values from the new detection to the initialLSV array 
            init_lSV.add(right_value);
        }

        private static void data_log()
        {
            System.out.println(data_from_lSV());
            System.out.println(data_from_init_lSV());
            System.out.println(time_converter(length_of_sessions,System.currentTimeMillis(),false) + " " + "seconds was the length of the session");
            System.out.println("The number of times light was detected was " + " " +numOfLightDetected+ " ");
        }
        
        public static void main(final String[] args) {
        
        }
        
         public void Task1() {
        	 
            Finch myFinch = new Finch();
                {
                    clock = System.currentTimeMillis();
                    while(myFinch.isFinchLevel() == true && myFinch.isBeakUp() == false)
                    {
                        counter++;

                        if(counter >= 1)
                        {
                           if(myFinch.getLeftLightSensor() <= threshHold && myFinch.getRightLightSensor() <= threshHold)//while finch is level and not on tail 
                        {
                                //no light source detected in five seconds
                           if((time_converter(clock, System.currentTimeMillis(),true) > 4000))//if 4 seconds has passed the undetected in 4 function will be called
                        {
                           undetected_light_in4(myFinch);
                           clock = System.currentTimeMillis();
                           myFinch.setLED(0,255,255,500);// to confirm that this function has been run (light blue)
                        }
                           else
                             undetected_light(myFinch);
                        }
                           else
                        {
                             detected_light(myFinch);
                             myFinch.buzz(100, 400);
                            }
                        }
                        else
                        {
                            multiple_light_detections(myFinch);
                        }

                    }
                    print_log(myFinch);//prints the data log only if the user enters a number
                }

            }
        }



