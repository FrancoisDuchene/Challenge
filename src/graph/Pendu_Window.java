package graph;

import java.awt.GridLayout;

import javax.swing.JPanel;

public class Pendu_Window extends Challenge_JFrame {

	private static final long serialVersionUID = -601011480318072498L;

	public Pendu_Window()
	{
		super("Pendu",(byte) 2);
		JPanel contenu = new JPanel();
		GridLayout gd = new GridLayout();
		contenu.setLayout(gd);
	}
}
