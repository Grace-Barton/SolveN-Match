package solveIt;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class StopWatch extends JComponent implements ActionListener{
	
		Timer tm=new Timer(1000,this);

		JLabel time=new JLabel();
		
		
		int elapsed=0;
		int sec=0;
		int min=0;
		int hr=0;
		
		String seconds=String.format("%02d",sec);
		String minutes=String.format("%02d",min);
		String hours=String.format("%02d",hr);
		
		public StopWatch() {
			time.setText(hours+":"+minutes+":"+seconds);
			time.setFont(new Font("Verdana", Font.PLAIN, 12));
			time.setForeground(Color.WHITE);
			tm.start();
		}
		
		
		public void actionPerformed(ActionEvent e) {
			elapsed+=1000;
			hr=elapsed/3600000;
			min=(elapsed/60000)%60;
			sec=(elapsed/1000)%60;
			
			seconds=String.format("%02d",sec);
			minutes=String.format("%02d",min);
			hours=String.format("%02d",hr);
			
			time.setText(hours+":"+minutes+":"+seconds);
			
			
		}
}


