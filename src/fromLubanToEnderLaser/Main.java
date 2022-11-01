package fromLubanToEnderLaser;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Main implements Action {
	JFrame frame = null;
	JPanel panel = null;
	JButton openFile = null;
	JButton saveFile = null;
	JTextArea origTextArea;
	JTextArea convTextArea;
	String origText = "";
	String convText = "";
	public static void main(String[] args) {
		Main mainFrame = new Main();
		mainFrame.frame = new JFrame();
		mainFrame.panel = new JPanel();
		mainFrame.openFile = new JButton("Choose a g-code file...");
		mainFrame.saveFile = new JButton("Save your work...");
		mainFrame.origTextArea = new JTextArea();
		mainFrame.origTextArea.setPreferredSize(new Dimension(395,600));
		mainFrame.convTextArea = new JTextArea();
		mainFrame.convTextArea.setPreferredSize(new Dimension(395,600));

		
		mainFrame.openFile.setAction (mainFrame);
		mainFrame.openFile.setText("Choose a g-code file...");
		mainFrame.openFile.setEnabled(true);
		mainFrame.saveFile.setAction (mainFrame);
		mainFrame.saveFile.setText("Save your work...");
		mainFrame.saveFile.setEnabled(true);
		
		mainFrame.panel.setLayout(new BorderLayout());
		mainFrame.panel.add(mainFrame.openFile,BorderLayout.NORTH);
		mainFrame.panel.add(mainFrame.saveFile,BorderLayout.SOUTH);
		mainFrame.panel.add(mainFrame.origTextArea,BorderLayout.WEST);
		mainFrame.panel.add(mainFrame.convTextArea,BorderLayout.EAST);
		
		mainFrame.frame.add(mainFrame.panel);
		mainFrame.frame.setSize(new Dimension(800,600));
		mainFrame.frame.setResizable(false);
		mainFrame.frame.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(openFile)) {
			System.out.println("Opening a file");
			JFileChooser chooser = new JFileChooser();
		    FileNameExtensionFilter filter = new FileNameExtensionFilter(
		        "nc & g-code files", "nc", "gcode");
		    chooser.setFileFilter(filter);
		    int returnVal = chooser.showOpenDialog(frame);
		    if(returnVal == JFileChooser.APPROVE_OPTION) {
		       System.out.println("You chose to open this file: " +
		            chooser.getSelectedFile().getName());
		       try {
				origText = Files.readString(chooser.getSelectedFile().toPath());
				convText = origText.replace("M106", "")
								   .replace("M107", "")
								   .replace("M3", "M106")
								   .replace("M5", "M107");
				origTextArea.setText(origText);
				convTextArea.setText(convText);
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		      
		    }
		} if(e.getSource().equals(saveFile)) {
			System.out.println("Saving file");
		}
	    
	}

	@Override
	public Object getValue(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putValue(String key, Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setEnabled(boolean b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		// TODO Auto-generated method stub
		
	}

}
