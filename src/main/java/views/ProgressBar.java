package main.java.views;

import javax.swing.*;

public class ProgressBar extends JProgressBar{
	private static final long serialVersionUID = 1L;
	JProgressBar progressBar;

	public ProgressBar() {}
	
	public void increase(){
		setValue(getValue()+1);
		repaint();
	}
	
	public void reset(){
		setValue(0);
		repaint();
	}
	
	public void setMax(int max){
		setMaximum(max);
	}
}
