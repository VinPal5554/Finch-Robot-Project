import edu.cmu.ri.createlab.terk.robot.finch.Finch;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.Toolkit;
import java.awt.Dimension;

class IntegrationGUI {
	
	JFrame mainWindow = new JFrame("Code Integration");
	protected static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	protected static int SCREENWIDTH = (int)screenSize.getWidth();
	protected static int SCREENHEIGHT = (int)screenSize.getHeight();
	protected JButton mainButtonArr[] = new JButton[6];
	
	public void checkResolution(int x, int y) {
		
		int incrementVar;
		int xStartingPosition;
		JLabel text = new JLabel();
		text.setText("Please select one of the buttons to begin: ");
		
		if (x == 1920 && y == 1080) {   
			text.setBounds(840, 70, 500, 200);
			mainWindow.add(text);
			
			xStartingPosition = 360;   
			incrementVar = 480;
	
			for (int i = 0; i < 3; ++i) {
				mainWindow.add(mainButtonArr[i]);
				mainButtonArr[i].setBounds(xStartingPosition, 270, 240, 240);
				xStartingPosition += incrementVar;
			}
			xStartingPosition = 360;   
			for (int i = 3; i < mainButtonArr.length; ++i) {
				mainWindow.add(mainButtonArr[i]);
				mainButtonArr[i].setBounds(xStartingPosition, 540, 240, 240);
				xStartingPosition += incrementVar;
			}
		}
		
		else {  
		
			mainWindow = new JFrame("Please select one of the buttons to begin");
			
			for (int i = 0; i < mainButtonArr.length; ++i) {
				mainWindow.add(mainButtonArr[i]);
			}
			
			mainWindow.setLayout(new GridLayout(3,3));
			mainWindow.setSize(500, 500);
		}
		
		mainWindow.setVisible(true);
	}
	
	public void getWindow() {
		
		mainWindow.setSize(SCREENWIDTH, SCREENHEIGHT);
		mainWindow.setResizable(false);
		mainWindow.setLayout(null); 
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainButtonArr[0] = new JButton("Dance");
		mainButtonArr[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				mainWindow.setVisible(false);
				FinchApp danceObj = new FinchApp();
				danceObj.Task6();
			}
		});
		
		mainButtonArr[1] = new JButton("Detect Object");
		mainButtonArr[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				GUI beginInterface = new MainInterface();
				beginInterface.getWindow();
			}
		});
		
		mainButtonArr[2] = new JButton("Draw Shape");
		mainButtonArr[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Draw_Shape drawObj = new Draw_Shape();
				drawObj.Task2();
				
			}
		});
		
		mainButtonArr[3] = new JButton("Follow Light");
		mainButtonArr[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				mainWindow.setVisible(false);
				finchLightTask lightObj = new finchLightTask();
				lightObj.Task1();
			}
		});
		
		mainButtonArr[4] = new JButton("Navigation");
		mainButtonArr[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				mainWindow.setVisible(false);
				Navigation navObj = new Navigation();
	
				try {
					navObj.Task3();
					
				} catch(Exception f) {
					f.getStackTrace();
				}
			}
		});
		
		mainButtonArr[5] = new JButton("Zigzag");
		mainButtonArr[5].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				mainWindow.setVisible(false);
				FinchTemplateFile zigZagObj = new FinchTemplateFile();
				zigZagObj.Task4();
			}
		});
		
		checkResolution(SCREENWIDTH, SCREENHEIGHT); 
		
	}
}

public class Task {
	
	public static void main (String args[]) {
		IntegrationGUI obj = new IntegrationGUI();
		obj.getWindow();
	}

}

