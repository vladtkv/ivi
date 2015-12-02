package ivi.swing;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class Frame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Frame(String title, Dimension dimension, int state, int dco,
			String ico) {
		setTitle(title);
		setSize(dimension);
		setExtendedState(state);
		setDefaultCloseOperation(dco);
		setLocationRelativeTo(null);
		if (ico != null) {
			Image icon = Toolkit.getDefaultToolkit().getImage(ico);
			setIconImage(icon);
		}
	}
}
