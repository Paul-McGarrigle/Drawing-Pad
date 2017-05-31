import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;

import javax.swing.*;

class Applic extends JFrame implements Serializable{
	
	protected Container panel;
	
	//Frame Settings
	public Applic(){
		super("Drawing Pad");
		setLocation(300,100);
		setSize(800,500);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setContentPane(panel = new Panel());
		setVisible(true);
	}
	
	public static void main(String args[]){
		SwingUtilities.invokeLater(new Runnable(){
			
			public void run(){
				new Applic();
			}
		});
	}	
}
