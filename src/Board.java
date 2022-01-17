package solveIt;
import java.awt.EventQueue;
import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import javax.swing.Timer;
public class Board implements ActionListener{

	private JFrame frame1;
	private JFrame frame2;
	private JFrame frame3;
	private JFrame frame4;
	private JButton play, sGame, options, rules, highscore;
	private GridLayout grid;
	public Card [] cards;
	public static BoardGame game;
	private int num;
	Timer tm=new Timer(10000,this);
	//Timer score =new Timer();
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int width = (int)screenSize.getWidth();
	int height = (int)screenSize.getHeight();
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Board window = new Board(4,4);
					window.frame1.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
		});
		
		
	}

	/**
	 * Create the application.
	 */
	public Board(int x, int y) {
		initialize();
		createMenu();
		buttonClicks();
		buildGrid(4,4);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int)screenSize.getWidth();
		int height = (int)screenSize.getHeight();
		
		
		final ImageIcon icon = new ImageIcon("wood.jpg");
		
		frame1 = new JFrame("Solve N' Match");
		frame1.setBounds(100, 100, width/3, height/2);
		
		
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.getContentPane().setLayout(new BorderLayout(0, 0));
		
		
		
		frame2=new JFrame("Solve N' Match");
		frame2.setBounds(100, 100, width/3, height/2);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame3=new JFrame("Options Menu");
		frame3.setBounds(100, 100, width/5, height/3);
		frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame4=new JFrame("Instructions");
		frame4.setBounds(100, 100, width/3, height/2);
		frame4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	
	private void buildGrid(int x, int y) {
		
		cards=new Card[x*y];
		grid=new GridLayout(x,y,15,15);
		
		JLabel background=new JLabel();
		background.setPreferredSize(new Dimension(width,height));
		background.setLayout(new GridBagLayout());
		GridBagConstraints c= new GridBagConstraints();
		c.anchor=GridBagConstraints.CENTER;
		background.setIcon(new ImageIcon(new ImageIcon("C:\\Users\\Grace\\Desktop\\Memory Game\\w1.png").getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT)));
		
		JPanel b=new JPanel();
		b.setOpaque(false);
		b.setLayout(grid);
		frame2.getContentPane().setLayout(new BorderLayout());
		
		//frame2.getContentPane().setLayout(grid);
		game=new BoardGame(x,y);
		
		for(int i=0;i<cards.length;i++) {
			cards[i]=new Card(x,y,game.getArr(i));
			
		
			cards[i].addMouseListener(new MouseAdapter( ) {
			      public void mouseClicked(MouseEvent e) {
			    	  System.out.println(cards[0].getCount());
			    	 if(cards[0].getCount()==2) {
			    		 tm.setInitialDelay(4000);
			    		 tm.start();
			    		 num=1;
			    		
			    	 }
			    	 /*
			    	 else if(cards[0].getCount()>2) {
			    		 tm.stop();
			    		 tm.setInitialDelay(0);
			    		 tm.start();
			    		 num=1;
			    	 }
			    	 */
			      } 
			 });
			
			
			b.add(cards[i]);
			
			
		
			
			
		}
		 
		b.setPreferredSize(new Dimension(width/4,height/3));
		background.add(b,c);
		c.anchor=GridBagConstraints.PAGE_END;
		background.add(new StopWatch().time,c);
		
		frame2.add(background, BorderLayout.CENTER);		
	}
	
	
	

	
	
	
	public void redo() {
		tm.setDelay(0);
		String tmp1="";
		String tmp2="";
		Card c1=new Card(0,0,"");
		Card c2=new Card(0,0,"");
		boolean matched=false;
		
		int c=0;
		int num=cards.length;
		
			
			if(cards[0].getCount()==2) {
				
				for(int j=0; j<cards.length;j++) {
					
					if(cards[j].getSelected()&&c==0) {
						tmp1=game.getArr(j);
						c1=cards[j];
						c++;
					}
					
					else if(cards[j].getSelected()&&c==1) {
						tmp2=game.getArr(j);
						c2=cards[j];
					}
				
					
				}
				if(tmp2.length()>tmp1.length()) {
					for(int k=0;k<BoardGame.p.size();k++) {
						if(tmp2.equals(game.getP(k).getProblem())&&tmp1.equals(game.getP(k).getAnswer())) {
							game.p.remove(k);
							num=num-2;
							c1.removeCard();
							c2.removeCard();
							matched=true;
							
						}
					}
					if(matched==false) {
						c1.callStart();
						c2.callStart();
					}
					
				}
				else {
					for(int l=0;l<BoardGame.p.size();l++) {
						if(tmp1.equals(game.getP(l).getProblem())&&tmp2.equals(game.getP(l).getAnswer())) {
							game.p.remove(l);
							num=num-2;
							c1.removeCard();
							c2.removeCard();
							matched=true;
						}
					}
					if(matched==false) {
						c1.callStart();
						c2.callStart();
					}
					
				}
				cards[0].setCount(0);
				tm.stop();
			}
			
			
			
	}
	
	
	private void createMenu() {
		Color brown =new Color(115,113,83);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int)screenSize.getWidth();
		int height = (int)screenSize.getHeight();
		
		JLabel background = new JLabel("");
		background.setPreferredSize(new Dimension(width,height));
		background.setLayout(new BorderLayout());
		background.setIcon(new ImageIcon(new ImageIcon("C:\\Users\\Grace\\Desktop\\Memory Game\\w1.png").getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT)));
		frame1.getContentPane().add(background);
		
		JLabel title=new JLabel("Solve N' Match", SwingConstants.CENTER);
		title.setFont(new Font("Wide Latin", Font.PLAIN, 36));
		title.setForeground(Color.WHITE);
		background.add(title, BorderLayout.PAGE_START);
		
		title.setBorder(BorderFactory.createEmptyBorder(height/13,20,0,20));
		
		JPanel menuButtons=new JPanel(new GridLayout(0,1,10,10));
		JLabel buttons1=new JLabel("");
		JLabel buttons2=new JLabel("");
		JLabel buttons3=new JLabel("");
		JLabel buttons4=new JLabel("");
		JLabel buttons5=new JLabel("");
		buttons1.setIcon(new ImageIcon(new ImageIcon("C:\\Users\\Grace\\Desktop\\Memory Game\\wood4.jpg").getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT)));
		buttons2.setIcon(new ImageIcon(new ImageIcon("C:\\Users\\Grace\\Desktop\\Memory Game\\wood4.jpg").getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT)));
		buttons3.setIcon(new ImageIcon(new ImageIcon("C:\\Users\\Grace\\Desktop\\Memory Game\\wood4.jpg").getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT)));
		buttons4.setIcon(new ImageIcon(new ImageIcon("C:\\Users\\Grace\\Desktop\\Memory Game\\wood4.jpg").getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT)));
		buttons5.setIcon(new ImageIcon(new ImageIcon("C:\\Users\\Grace\\Desktop\\Memory Game\\wood4.jpg").getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT)));
		
		buttons1.setLayout(new GridLayout(0,1));
		buttons2.setLayout(new GridLayout(0,1));
		buttons3.setLayout(new GridLayout(0,1));
		buttons4.setLayout(new GridLayout(0,1));
		buttons5.setLayout(new GridLayout(0,1));
		//menuButtons.add(title);
		menuButtons.add(buttons1);
		menuButtons.add(buttons2);
		menuButtons.add(buttons3);
		menuButtons.add(buttons4);
		menuButtons.add(buttons5);
		
		
		menuButtons.setBorder(BorderFactory.createEmptyBorder(30,width/14,height/13,width/14));
		
		menuButtons.setOpaque(false);
		
		play=new JButton("Play");
		play.setFont(new Font("Unispace", Font.PLAIN, 15));
		sGame=new JButton("Load Saved Game");
		sGame.setFont(new Font("Unispace", Font.PLAIN, 15));
	    options=new JButton("Options");
	    options.setFont(new Font("Unispace", Font.PLAIN, 15));
		rules=new JButton("Rules");
		rules.setFont(new Font("Unispace", Font.PLAIN, 15));
		highscore=new JButton("Highscores");
		highscore.setFont(new Font("Unispace", Font.PLAIN, 15));
		
				
		buttons1.add(play);
		buttons2.add(sGame);
		buttons3.add(options);
		buttons4.add(rules);
		buttons5.add(highscore);
		
		play.setContentAreaFilled(false);
		sGame.setContentAreaFilled(false);
		options.setContentAreaFilled(false);
		rules.setContentAreaFilled(false);
		highscore.setContentAreaFilled(false);
		
	
		background.add(menuButtons,BorderLayout.CENTER);
		
	}
	
	
	private void buttonClicks() {
		play.addActionListener(this);
		sGame.addActionListener(this);
		options.addActionListener(this);
		rules.addActionListener(this);
		
		play.setActionCommand("play");
		sGame.setActionCommand("sGame");
		options.setActionCommand("options");
		rules.setActionCommand("rules");
		
	}
		
	@Override
	public void actionPerformed(ActionEvent e) {
		if(num==1) {
			redo();
		}
		else if(e.getActionCommand().equals("back")) {
			frame1.setVisible(true);
			frame2.setVisible(false);
			frame3.setVisible(false);
			frame4.setVisible(false);
		}
		else if(e.getActionCommand().equals("play")) {
			frame1.setVisible(false);
			frame2.setVisible(true);
			frame3.setVisible(false);
			frame4.setVisible(false);
					
		}
		else if(e.getActionCommand().equals("options")) {
			createOptions();
			frame1.setVisible(false);
			frame2.setVisible(false);
			frame3.setVisible(true);
			frame4.setVisible(false);
					
		}
		else if(e.getActionCommand().equals("rules")) {
			createRules();
			frame1.setVisible(false);
			frame2.setVisible(false);
			frame3.setVisible(false);
			frame4.setVisible(true);
					
		}
		else if(e.getActionCommand().equals("4x4")) {
			frame2.getContentPane().removeAll();
			buildGrid(4,4);
		}
		else if(e.getActionCommand().equals("5x4")) {
			frame2.getContentPane().removeAll();
			buildGrid(5,4);
		}
		else if(e.getActionCommand().equals("6x6")) {
			frame2.getContentPane().removeAll();
			buildGrid(6,6);
		}
		
		
	}
	
	
	private void createOptions() {
		
		JLabel background = new JLabel("");
		background.setPreferredSize(new Dimension(width,height));
		background.setLayout(new BorderLayout());
		background.setIcon(new ImageIcon(new ImageIcon("C:\\Users\\Grace\\Desktop\\Memory Game\\w1.png").getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT)));
		
		UIManager.put("TabbedPane.contentOpaque", false);
		JTabbedPane tabbedPane = new JTabbedPane();
		//tabbedPane.setBackground(bac);
	      tabbedPane.setForeground(Color.black);
		JPanel panel1=new JPanel(new BorderLayout());
		tabbedPane.addTab("Board Size", null, panel1,
                "Does nothing");
		JPanel panel2=new JPanel();
		tabbedPane.addTab("Problem Type", null, panel2,
                "Does nothing");
		JPanel panel3=new JPanel();
		tabbedPane.addTab("Difficulty", null, panel3,
                "Does nothing");
		
		frame3.getContentPane().add(background);
		
		background.add(tabbedPane, BorderLayout.CENTER);
		tabbedPane.setOpaque(false);
		panel1.setOpaque(false);
		panel2.setOpaque(false);
		panel3.setOpaque(false);
		
		
		/*
		JPanel opt=new JPanel(new GridLayout(0,1));
		opt.setOpaque(false);
		
		JLabel j1=new JLabel(" ");
		JLabel j2=new JLabel(" ");
		JLabel j3=new JLabel(" ");
		
		j1.setLayout(new GridLayout(0,1));
		j2.setLayout(new GridLayout(0,1));
		j3.setLayout(new GridLayout(0,1));
		
		JButton b_1=new JButton("Board Size");
		JButton b_2=new JButton("Problem Type");
		JButton b_3=new JButton("Difficulty");
		
		b_1.setContentAreaFilled(false);
		b_2.setContentAreaFilled(false);
		b_3.setContentAreaFilled(false);
		
		
	
			
		j1.setIcon(new ImageIcon(new ImageIcon("C:\\Users\\Grace\\Desktop\\Memory Game\\wood4.jpg").getImage().getScaledInstance(width/15,height,Image.SCALE_DEFAULT)));
		j2.setIcon(new ImageIcon(new ImageIcon("C:\\Users\\Grace\\Desktop\\Memory Game\\wood4.jpg").getImage().getScaledInstance(width/15,height,Image.SCALE_DEFAULT)));
		j3.setIcon(new ImageIcon(new ImageIcon("C:\\Users\\Grace\\Desktop\\Memory Game\\wood4.jpg").getImage().getScaledInstance(width/15,height,Image.SCALE_DEFAULT)));
		
		
		
		
		j1.add(b_1);
		j2.add(b_2);
		j3.add(b_3);
		
		opt.add(j1);
		opt.add(j2);
		opt.add(j3);
		
		*/
		
	
		
		
		JPanel boardSize=new JPanel(new GridLayout(0,1));
		boardSize.setOpaque(false);
		Rectangle r=frame3.getBounds();
		int h=r.height;
		int w=r.width;
		boardSize.setBorder(BorderFactory.createEmptyBorder(0,w/4,0,w/4));
		
		ButtonGroup s=new ButtonGroup();
		
		JRadioButton b1=new JRadioButton("4x4");
		b1.setFont(new Font("Verdana", Font.PLAIN, 12));
		b1.setForeground(Color.WHITE);
		b1.setContentAreaFilled(false);
		
		JRadioButton b2=new JRadioButton("5x4");
		b2.setFont(new Font("Verdana", Font.PLAIN, 12));
		b2.setForeground(Color.WHITE);
		b2.setContentAreaFilled(false);
		
		JRadioButton b3=new JRadioButton("6x6");
		b3.setFont(new Font("Verdana", Font.PLAIN, 12));
		b3.setForeground(Color.WHITE);
		b3.setContentAreaFilled(false);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		
		b1.setActionCommand("4x4");
		b2.setActionCommand("5x4");
		b3.setActionCommand("6x6");
		
		s.add(b1);
		s.add(b2);
		s.add(b3);
		
		boardSize.add(b1);
		boardSize.add(b2);
		boardSize.add(b3);
		
		JButton back =new JButton("Back");
		back.addActionListener(this);
		back.setActionCommand("back");
		panel1.add(boardSize, BorderLayout.CENTER);
		panel1.add(back,BorderLayout.PAGE_END);
		
		JPanel pType=new JPanel(new GridLayout(0,1,10,10));
		pType.setOpaque(false);
		pType.setBorder(BorderFactory.createEmptyBorder(h/8,0,h/5,0));
		JLabel type=new JLabel("<html>Problems Types from each of the checkmarked boxes<br> will be included in the game:<html>");
		type.setFont(new Font("Unispace", Font.PLAIN, 12));
		type.setForeground(Color.WHITE);
		JCheckBox mult=new JCheckBox("Multiplication");
		mult.setContentAreaFilled(false);
		mult.setFont(new Font("Verdana", Font.PLAIN, 12));
		mult.setForeground(Color.WHITE);
		JCheckBox add=new JCheckBox("Addition");
		add.setContentAreaFilled(false);
		add.setFont(new Font("Verdana", Font.PLAIN, 12));
		add.setForeground(Color.WHITE);
		JCheckBox sub=new JCheckBox("Subtraction");
		sub.setContentAreaFilled(false);
		sub.setFont(new Font("Verdana", Font.PLAIN, 12));
		sub.setForeground(Color.WHITE);
		JCheckBox div=new JCheckBox("Division");
		div.setContentAreaFilled(false);
		div.setFont(new Font("Verdana", Font.PLAIN, 12));
		div.setForeground(Color.WHITE);
		
		pType.add(mult);
		pType.add(add);
		pType.add(sub);
		pType.add(div);
		
		JButton back2 =new JButton("Back");
		back2.addActionListener(this);
		back2.setActionCommand("back");
		
		
		panel2.add(type,BorderLayout.PAGE_START);
		panel2.add(pType,BorderLayout.CENTER);
		panel2.add(back2,BorderLayout.PAGE_END);
		
		
		
		
		JLabel difficulty=new JLabel("Difficulty");
		difficulty.setFont(new Font("Verdana", Font.PLAIN, 12));
		difficulty.setForeground(Color.WHITE);
		JButton left =new JButton("<");
		JLabel levels=new JLabel("easy");
		levels.setFont(new Font("Verdana", Font.PLAIN, 12));
		levels.setForeground(Color.WHITE);
		JButton right=new JButton(">");
		
		JButton back3 =new JButton("Back");
		back3.addActionListener(this);
		back3.setActionCommand("back");
		
		panel3.add(difficulty);
		panel3.add(left);
		panel3.add(levels);
		panel3.add(right);
		panel3.add(back3,BorderLayout.PAGE_END);
			
		
		
		
		
		
		
		JLabel words=new JLabel("<html>Welcome to Settings...<html>", SwingConstants.CENTER);
		words.setFont(new Font("Unispace", Font.PLAIN, 30));
		words.setForeground(Color.WHITE);
		
		//frame3.add(background);
		//background.add(words, BorderLayout.CENTER); 
		//background.add(pTypes,BorderLayout.PAGE_START);
		//background.add(level,BorderLayout.PAGE_END);
		//background.add(opt,BorderLayout.LINE_START);
	}
	
	
	
	
	
	
	
	
	
	
	private void createRules() {
		
		JLabel inst=new JLabel("Rules",SwingConstants.CENTER);
		
		inst.setFont(new Font("Wide Latin", Font.PLAIN, 36));
		inst.setForeground(Color.WHITE);
		inst.setBorder(BorderFactory.createEmptyBorder(30,20,0,20));
		
		JPanel rules=new JPanel(new GridLayout(0,1));
		
		JLabel r1=new JLabel("<html>1) When the game begins the cards will be faced up for 3 seconds. <br/>During this time try to solve problems and remember where their coressponding matches are located.<html>");
		r1.setFont(new Font("Unispace", Font.PLAIN, 15));
		r1.setForeground(Color.WHITE);
		
		JLabel r2=new JLabel("<html>2) After 3 seconds the cards will flip over and you may click on any two cards at a time to flip them over and see if they are a match.<html>");
		r2.setFont(new Font("Unispace", Font.PLAIN, 15));
		r2.setForeground(Color.WHITE);
		
		JLabel r3=new JLabel("<html>3) As each pair of cards is matched they will disappear from the screen.<html>");
		 
		
		JLabel r4=new JLabel("<html>4) Once all cards have been matched the game is over.<html>");
		r4.setFont(new Font("Unispace", Font.PLAIN, 15));
		r4.setForeground(Color.WHITE);
		
		rules.add(r1);
		rules.add(r2);
		rules.add(r3);
		rules.add(r4);
		
		rules.setOpaque(false);
		
		
		JLabel background = new JLabel("");
		background.setPreferredSize(new Dimension(width,height));
		background.setLayout(new BorderLayout());
		background.setIcon(new ImageIcon(new ImageIcon("C:\\Users\\Grace\\Desktop\\Memory Game\\w1.png").getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT)));
		frame4.getContentPane().add(background);
		
		background.add(inst,BorderLayout.PAGE_START);
		background.add(rules, BorderLayout.CENTER);
		
		
		
		rules.setBorder(BorderFactory.createEmptyBorder(30,width/25,0,width/20));
		
		
	}
	
	 public static void pause(int ms) {
		    try {
		        Thread.sleep(ms);
		    } catch (InterruptedException e) {
		        System.err.format("IOException: %s%n", e);
		        
		        
		    }
		}
}
