package Main;

import javax.swing.*;

import com.formdev.flatlaf.FlatDarkLaf;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Music.MusicLocator;

public class Main {
   public JFrame window;
   public JButton openButton, restartButton, StopButton, PauseButton;
   private JButton[] b;
   public JFileChooser chooser;
   public MusicLocator locator;
   public ImageIcon icon, runningIcon;
   private JLabel text;
   public boolean running;
   public boolean isPaused;
   private int option;
   private buttonAction ba;
  
   public Main() {
	  this.window = new JFrame("Audio Player (Limited)");
	  this.icon = new ImageIcon(this.getClass().getResource("/Icons/logo.png"));
      this.runningIcon = new ImageIcon(this.getClass().getResource("/Icons/logo_running.png"));
      this.text = new JLabel("Music Player"); 
      this.running = false;
      this.isPaused = false;
      this.chooser = new JFileChooser();
      this.locator = new MusicLocator(this);
      this.ba = new buttonAction(this);
   }
   public void createWindow() {
	   window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   window.setLayout(null);
	   window.setResizable(false);
	   window.setPreferredSize(new Dimension(650, 650));
	   window.pack();
	   window.setLocationRelativeTo(null);
	   window.setVisible(true);
	   window.setIconImage(icon.getImage());
	   window.getContentPane().setBackground(new Color(70, 70, 70));
	   
	   enableLF();
       setButtons();
	   setTextArea();
	   ba.addAction();
   }
   private void setButtons() {
	    b = new JButton[] {
			openButton = createButton(openButton, "Open", 180, 165, 220, 40, true),
			restartButton = createButton(restartButton, "Restart", 180, 215, 220, 40, false),
			StopButton = createButton(StopButton, "Stop", 180, 345, 220, 40, false),
			PauseButton = createButton(PauseButton, "Pause", 180, 395, 220, 40, false),
	    }; 
	   for(JButton button : b) {
		   button.setFocusable(false);
		   button.setBackground(Color.white);
		   button.setForeground(Color.black);
	       button.putClientProperty("JButton.buttonType", "roundRect");
	       button.putClientProperty("FlatLaf.background", Color.white);
	       button.putClientProperty("FlatLaf.foreground", Color.black);
		   window.getContentPane().add(button);
	   }
   }
   public void setTextArea() {
	  text = createLabel(text, "Audio Player (Limited)", 25, 10, 590, 110, false, Color.white, 0, new Font("Book Antiqua", 1, 30));
	  window.getContentPane().add(text);
   }
   private JButton createButton(JButton b, String text, final int x, final int y, final int w, final int h, boolean enabled) {
	    b = new JButton(text);
	    b.setBounds(x, y, w, h);
	    b.setEnabled(enabled);
        return b;
   }
   private JLabel createLabel(JLabel l, String text, final int x, final int y, final int w, final int h, boolean focus, Color wc, int ha, Font font) {
	    l = new JLabel(text);
	    l.setFocusable(focus);
	    l.setForeground(wc);
	    l.setBounds(x, y, w, h);
	    l.setHorizontalAlignment(ha);
	    l.setFont(font);
        return l;
   }
   public void open() {
   	      option = chooser.showOpenDialog(null);
   	      running = true;
   	      if(option == JFileChooser.APPROVE_OPTION) {
   	    	  JOptionPane.showConfirmDialog(null, "Music Will likely play, but ensure that its 'wav' file, if it is not wav file than it won't play anything", "Audio Player (Limited)", 0);
   	    	  try {
   	    		  if (running) {
   	    		  PauseButton.setEnabled(true);
   	    		  StopButton.setEnabled(true);
   	    		  restartButton.setEnabled(true);
   	    		  openButton.setEnabled(false);
   	    		  locator.prepare();
   	    		  locator.start();
   	    		  window.setIconImage(runningIcon.getImage());
   	    		  
   	    		  PauseButton.addActionListener(new ActionListener() {
   	    			  public void actionPerformed(ActionEvent e) {
   	    				  if (!isPaused) {
   	    					  locator.pauseMusic();
   	    					  PauseButton.setText("Unpause");
   	    					  locator.saveMoment();
   	    					  window.setIconImage(icon.getImage());
   	    					  isPaused = true;
   	    				  } else {
   	    					  PauseButton.setText("Pause");
   	    					  locator.loadMoment();
   	    					  window.setIconImage(runningIcon.getImage());
   	    					  isPaused = false;
   	    				  }
   	    			  }
   	    		  });
     	      }
   	         }catch(Exception e) {
                System.out.println("Failed to add action: " + e.getMessage());
            }  
       }
   }
   private void enableLF() {
	   try {
		UIManager.setLookAndFeel(new FlatDarkLaf());
	   } catch (UnsupportedLookAndFeelException e) {
    		e.printStackTrace();
	   }
   }
}