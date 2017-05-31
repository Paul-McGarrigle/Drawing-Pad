import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Panel extends JPanel implements Serializable{
	
	protected Toolbar toolbar;
	protected ArrayList<Shape> shapeList;
	protected int count = 0, index = 0, shapeType = 0,
				dialogBtn = 0, confirmation, r = 0;
	protected Shape line, circle, triangle, square, rectangle, aShape;
	protected Color shapeColour = Color.BLACK;
	protected File myFile = new File("saveFile.ser");
	protected Boolean check = false;
	protected Point p1, p2, p3, p4;
	
	public Panel() {
		
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		toolbar = new Toolbar();
		add(toolbar, BorderLayout.NORTH);
		shapeList = new ArrayList<Shape>();

		//Event Handlers
		//Mouse Listeners
		this.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent mouse) {
				count++;
				if(shapeType == 3 && count%3 == 0 && p2 != null){
					p4 = mouse.getPoint();
					count = 0;
				}else if (p1 == null || p2 != null) {
					p1 = mouse.getPoint();
					p2 = null;
				} else {
					p2 = mouse.getPoint();
				}
				repaint();
				generateShapes();
			}   
		});
		
		this.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent mouse) {
				p3 = mouse.getPoint();
				repaint();
			}
		});
		
		//Menu Item Listeners (Color Select)
		toolbar.colour.add(new JMenuItem("Black")).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shapeColour = Color.BLACK;
			}
		});
		toolbar.colour.add(new JMenuItem("Blue")).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shapeColour = Color.BLUE;
			}
		});
		toolbar.colour.add(new JMenuItem("Cyan")).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shapeColour = Color.CYAN;
			}
		});
		toolbar.colour.add(new JMenuItem("Dark Gray")).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shapeColour = Color.DARK_GRAY;
			}
		});
		toolbar.colour.add(new JMenuItem("Gray")).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shapeColour = Color.GRAY;
			}
		});
		toolbar.colour.add(new JMenuItem("Green")).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shapeColour = Color.GREEN;
			}
		});
		toolbar.colour.add(new JMenuItem("Light Gray")).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shapeColour = Color.LIGHT_GRAY;
			}
		});
		toolbar.colour.add(new JMenuItem("Majenta")).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shapeColour = Color.MAGENTA;
			}
		});
		toolbar.colour.add(new JMenuItem("Orange")).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shapeColour = Color.ORANGE;
			}
		});
		toolbar.colour.add(new JMenuItem("Pink")).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shapeColour = Color.PINK;
			}
		});
		toolbar.colour.add(new JMenuItem("Red")).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shapeColour = Color.RED;
			}
		});
		toolbar.colour.add(new JMenuItem("White")).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shapeColour = Color.WHITE;
			}
		});
		toolbar.colour.add(new JMenuItem("Yellow")).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shapeColour = Color.YELLOW;
			}
		});
		
		//Action Listeners Buttons, used to update shapeType Variable with appropriate shape value
		toolbar.lineBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	shapeType = 1;
            }    
		});
		toolbar.circleBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	shapeType = 2;
            }
		});
		toolbar.triangleBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	shapeType = 3;
            }
		});
		toolbar.squareBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	shapeType = 4;
            }
		});
		toolbar.rectBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	shapeType = 5;
            }
		});
		
		//The Undo Button Action Listener will remove the last shape to be added to the 
		//Shape ArrayList & will continue to remove shapes until the ArrayList is empty
		toolbar.undoBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(shapeList.size() != 0){
	            	shapeList.remove(shapeList.size()-1);
	        		repaint();
            	}
            }
		});
		
		//The Reset Button Action Listener will remove all shapes from the Panel by clearing 
		//the ArrayList but the user will be prompted and asked if they wish to continue 
		//with the Reset process & they must select yes, to continue with Reset, or no
		//to cancel the Reset Process
		toolbar.resetBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	confirmation = JOptionPane.showConfirmDialog (null, 
            			"All shapes will be removed proceed?","Warning", dialogBtn);
            	if(confirmation == JOptionPane.YES_OPTION){
            	if(shapeList.size() != 0){
            		shapeList.clear();
	        		repaint();
            	}
            }
            }
		});
		
		//The Save Action Button Handler will save all Objects in the ArrayList, by looping through 
		//the ArrayList, to a Binary File specified in the myFile Variable
		toolbar.saveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try {
					ObjectOutputStream save = new ObjectOutputStream(new FileOutputStream(myFile));
	            	for(Shape s: shapeList){
	            		save.writeObject(s);
	            	}
	            	save.close();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
            }
		});
		
		//The Load Action Button Handler will read the Serialized Objects from the Binary File
		//& load these Objects back to the ArrayList and therefore the Drawing Panel
		toolbar.loadBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
            	try {
            		ObjectInputStream load = new ObjectInputStream(new FileInputStream(myFile));
            		aShape = (Shape)load.readObject();
            		shapeList.add(aShape);
                    index = 1;
            		
                    while(aShape != null){
                    	aShape = (Shape)load.readObject();
                    	shapeList.add(aShape);
                    	index++;
                    	repaint();
                    }
                    load.close();
                    
				} catch (Exception e1) {}
            }
		});
	}
	
	//Creating & adding Shapes to ArrayList, shapeType identifies the type of Shape, i.e.
	//1 for Line, 2 for Circle, 3 for Triangle, 4 for Square etc. & count modulus (%)
	//is used to differentiate between clicks
	public void generateShapes(){
		check = true;
		if(shapeType == 1 && count%2 == 0){
			line = new Line(p1, p2, shapeColour, shapeType);
			shapeList.add(line);
		}else if(shapeType == 2 && count%2 == 0){
			circle = new Circle(p1, p2, shapeColour, shapeType);
			shapeList.add(circle);
		}else if(shapeType == 3 && count%3 == 0){
			triangle = new Triangle(p1, p2, p4, shapeColour, shapeType, count);
			shapeList.add(triangle);
		}else if(shapeType == 4 && count%2 == 0){
			square = new Square(p1, p2, shapeColour, shapeType);
			shapeList.add(square);
		}else if(shapeType == 5 && count%2 == 0){
			rectangle = new Rectangle(p1, p2, shapeColour, shapeType);
			shapeList.add(rectangle);
		}
	}
	
	//Print Component is where the Shapes in the ArrayList are drawn to the
	//drawing Panel using the Graphics2D Library
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
	  	g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	                RenderingHints.VALUE_ANTIALIAS_ON);
	    g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
	                RenderingHints.VALUE_RENDER_QUALITY);

	    //Dynamic Drawing
		if(shapeType == 1 && count%2 == 1){
			line = new Line(p1, p3, shapeColour, shapeType);
			line.draw(g2d);
		}else if(shapeType == 2 && count%2 == 1){
			circle = new Circle(p1, p3, shapeColour, shapeType);
			circle.draw(g2d);
		}else if(shapeType == 3 && count%3 != 0) {
			try {
				triangle = new Triangle(p1, p3, p2, shapeColour, shapeType, count);
				triangle.draw(g2d);
			}catch(NullPointerException e){}
		}else if (shapeType == 4 && count % 2 == 1) {
			square = new Square(p1, p3, shapeColour, shapeType);
			square.draw(g2d);
		}else if (shapeType == 5 && count % 2 == 1) {
			rectangle = new Rectangle(p1, p3, shapeColour, shapeType);
			rectangle.draw(g2d);
		}

		//Saving images to panel
		for (Shape s : shapeList) {
			try {
				s.draw(g2d);
			}catch(NullPointerException e){}
		}
	}
}
