import java.awt.FlowLayout;
import java.io.Serializable;

import javax.swing.*;

public class Toolbar extends JPanel implements Serializable{
	
	protected JMenuBar bar;
	protected JMenu colour;
	protected JButton lineBtn, circleBtn, triangleBtn, squareBtn, rectBtn,
			undoBtn, resetBtn, saveBtn, loadBtn;
	
	public Toolbar(){
		//Creating Button Components to be held in Toolbar
		setLayout(new FlowLayout(FlowLayout.LEFT));
		lineBtn = new JButton("Line");
		circleBtn = new JButton("Circle");
		triangleBtn = new JButton("Triangle");
		squareBtn = new JButton("Square");
		rectBtn = new JButton("Rectangle");
		undoBtn = new JButton("Undo");
		resetBtn = new JButton("Reset");
		saveBtn = new JButton("Save");
		loadBtn = new JButton("Load");
		bar = new JMenuBar();
		colour = new JMenu("Select Colour");
		bar.add(colour);
		
		//Adding Components to Toolbar
		add(lineBtn);
		add(circleBtn);
		add(triangleBtn);
		add(squareBtn);
		add(rectBtn);
		add(bar);
		add(undoBtn);
		add(resetBtn);
		add(saveBtn);
		add(loadBtn);
	}

}
