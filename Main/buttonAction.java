package Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class buttonAction {
	public Main m;
	
	public buttonAction(Main m) {
		this.m = m;
	}
	public void addAction() {
        m.openButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                m.open();
         }
     });
     m.restartButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	m.locator.restart();
        	m.window.setIconImage(m.runningIcon.getImage());
        }
     });
     m.StopButton.addActionListener(new ActionListener() { 
        public void actionPerformed(ActionEvent e) {
        	m.running = false;
           if (!m.running) {
        	   m.locator.stop();
        	   m.window.setIconImage(m.icon.getImage());
        	   m.restartButton.setEnabled(false);
        	   m.PauseButton.setEnabled(false);
        	   m.openButton.setEnabled(true);
               m.StopButton.setEnabled(false);
           }
        }
     });
    }
}