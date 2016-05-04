package graph;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SimpleFenetre extends JFrame {

	private static final long serialVersionUID = 3474489514864721123L;
	private JPanel chewie;

	public SimpleFenetre(String title, int lignes, int colonnes) throws HeadlessException {
		super(title);
		build(lignes, colonnes);
	}
	private void build(int lignes, int colonnes)
	{
		setSize(1200,750);
		setLocationRelativeTo(null);
		setResizable(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setContentPane(buildContentPane(lignes, colonnes));		
		this.setVisible(true);
	}
	private JPanel buildContentPane(int lignes, int colonnes)
	{
		chewie = new JPanel();
		chewie.setLayout(new GridLayout(lignes,colonnes));
		chewie.setBackground(new Color(0.85f, 0.9f, 1.0f));	
		
		return chewie;
	}
	/**
	 * Attention /!\ Ne peut pas depasser la limite de JLabel imposee par la taille
	 * de la grille !!! Autrement, ne pourra jamais depasser ligne*colonnes
	 * @param lab un JLabel a rajouter
	 */
	public void addJlabel(JLabel lab)
	{
			chewie.add(lab);					
	}
	public void repaintSimpleF()
	{
		chewie.repaint();
	}
}