package solveIt;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;



public class Card extends JComponent implements ActionListener {
	private boolean solved;
	private boolean selected;
	private int height;
	private int width;
	private int num=0;
	private int x=0;
	private int y=0;
	private String str=" ";
	private String message;
	private int flip=0;
	private static int count=0;
	Color color=Color.WHITE;
	Color color2=new Color(0,0,0);
	Timer tm=new Timer(5,this);
	private int d=-1;
	
	
	public Card(int rows,int cols, String message) {
		solved=false;
		selected=false;
		//this.rows=rows;
		//this.columns=cols;
		this.width=40;
		this.height=120;
		this.message=message;
		str=message;
		
		addMouseListener(new MouseAdapter( ) {
		      public void mouseClicked(MouseEvent e) {
		    	  count++;
		    	  setSelected(true);
		    	  num=0;
		    	
		    	  if(count<3) {
		    		  tm.start();
		    	  }
		    	  else {
		    		  
		    	  }
		      }
		});
	}
	
	
	public void removeCard() {
		color=new Color(165,159,135);
		//color2=color;
		str=" ";
		num=2;
		tm.start();
		setSelected(false);
		
		
		
	}
	
	public void callStart() {
		num=0;
		tm.start();
		setSelected(false);
		
	}
	public void actionPerformed(ActionEvent e) {
			tm.setInitialDelay(0);
			//System.out.println("Hello");
			
			switch(num) {
				case 0: {
					if(flip==1&&width<30) {
						str=" ";
					}
					width--;
					if(x<40) {
						x++;
					}
					if(width<5) {
						num=1;
					}
					
					break;
				}
				
				case 1: {
					if(d==0&&flip==0) {
						color=new Color(193,154,107);
						str=" ";
					}
					else if(flip%2==0) {
						color=new Color(193,154,107);
						str= " ";
						
					}
					else {
						color=Color.WHITE;
					}
					
					width++;
					if(x>0) {
						x--;
					}
					if(width==35&&flip%2!=0) {
						str=message;
					}
					if(width==40) {
						flip++;
						tm.stop();
						//num=0;
					}
					
					break;
				}
				case 2: {
					System.out.println("Matched");
					tm.stop();
					break;
				}
				
			}
	    	repaint();
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int val) {
		count=val;
	}
	public void setStr(String message) {
		str=message;
	}
	public String getStr() {
		return str;
	}
	
	public void setSolved() {
		solved=true;
	}
	public boolean getSolved() {
		return solved;
	}
	

	public void setSelected(boolean val) {
		selected=val;
	}
	public boolean getSelected() {
		return selected;
	}
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		//Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		//int width = (int)screenSize.getWidth();
		//int height = (int)screenSize.getHeight();
		
		
			g.setColor(color);
		
			g.fillRect(x,y,width,height);
			g.fillRect(x+width,y,width,height);
			g.setColor(color2);
			FontMetrics fm= g.getFontMetrics();
			int strWidth=fm.stringWidth(str);
			g.drawString(str,(80/2)-(strWidth/2),(y+height)/2);
		
			if(d==-1) {
				d=0;
				tm.setInitialDelay(2000);
				tm.start();
				num=0; 
			}
		
	}
	 
	 public static void pause(int ms) {
		    try {
		        Thread.sleep(ms);
		    } catch (InterruptedException e) {
		        System.err.format("IOException: %s%n", e);
		        
		    }
		}
	 
	
	
	
}
