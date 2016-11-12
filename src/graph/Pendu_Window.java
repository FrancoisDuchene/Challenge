package graph;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
//import java.util.ResourceBundle;
import java.awt.CardLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import source.pendu;

//import source.challenge;

public class Pendu_Window extends Challenge_JFrame implements ActionListener{

	private static final long serialVersionUID = -601011480318072498L;
	
	private JTextField jtf;
	private JTextArea textArea;
	private String reponseUser;

	public Pendu_Window(String motSecret)
	{
		super("Pendu",(byte) 2);
		setText(null);
		//ResourceBundle LSD = challenge.getResbundle();
		
		//Construction de la fenetre
		
		JPanel card = new JPanel();
		CardLayout cl = new CardLayout(300,75);
		card.setLayout(cl);
		
		JLabel titre = new JLabel("Pendu");
		titre.setFont(new Font("Courier", Font.BOLD,100));
		titre.setHorizontalAlignment(SwingConstants.CENTER);
		
		//Premiere surface de la pile - Menu
		
		JPanel jp = new JPanel();
		GridLayout gl1 = new GridLayout(3,1,600,100);
		jp.setLayout(gl1);
		JButton menuB1 = new JButton("Jouer");
		jp.add(menuB1);
		JButton menuB2 = new JButton("MultiJoueur");
		jp.add(menuB2);
		JButton menuB3 = new JButton("Options");
		jp.add(menuB3);
		
		card.add(jp, "menu");
		
		//Deuxieme surface de la pile - 1 Joueur
		
		JPanel contenu = new JPanel();
		GridLayout gd = new GridLayout();
		gd.setColumns(2);
		gd.setRows(3);
		gd.setHgap(50);
		gd.setVgap(50);
		contenu.setLayout(gd);
		this.add(contenu);
		
		JLabel txt1 = new JLabel("Introduisez une lettre");
		txt1.setFont(new Font("Courier",Font.TRUETYPE_FONT ,14));
		contenu.add(txt1);
		
		contenu.add(new JLabel(""));
		
		jtf = new JTextField(3);
		jtf.addActionListener(this);
		contenu.add(jtf);
		
		textArea = new JTextArea(5, 20);
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);
		contenu.add(scrollPane);
		JButton jeuB1 = new JButton("Retour");
		contenu.add(jeuB1);
		
		card.add(contenu, "jeu1");
		
		//Troisieme surface de la pile - les options
		
		JPanel optPane = new JPanel();
		GridLayout gl3 = new GridLayout(3,1,20,100);
		optPane.setLayout(gl3);
		JButton menOB1 = new JButton("Le nombre de vies");
		optPane.add(menOB1);
		JButton menOB2 = new JButton("Le nombre de joueurs");
		optPane.add(menOB2);
		JButton menOB3 = new JButton("Retour");
		optPane.add(menOB3);
		
		card.add(optPane, "optionsMenu");
		
		//Actions des boutons
		
		jeuB1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) 
			{
				cl.show(card, "menu");
			}});
		menuB1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) 
			{
				cl.show(card, "jeu1");
			}});
		menuB2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) 
			{
				cl.show(card, "jeu1");
			}});
		menuB3.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) 
			{
				cl.show(card, "optionsMenu");
			}});
		menOB1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) 
			{
				String seVies = (String)JOptionPane.showInputDialog(card,"Quel nombre de vies souhaiteriez-vous ?",
						"Vies Pendu",JOptionPane.QUESTION_MESSAGE);
				pendu.setVies(Short.parseShort(seVies));
				JOptionPane.showMessageDialog(card, "Nombre de vies actuels :" + pendu.getVies() , "Information", JOptionPane.INFORMATION_MESSAGE);
			}});
		menOB2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) 
			{
				String seJoueur = (String)JOptionPane.showInputDialog(card,"Quel nombre de vies souhaiteriez-vous ?",
						"Vies Pendu",JOptionPane.QUESTION_MESSAGE);
				pendu.setJoueurs(Byte.parseByte(seJoueur));
				JOptionPane.showMessageDialog(card, "Nombre de joueurs :" + pendu.getVies() , "Information", JOptionPane.INFORMATION_MESSAGE);
			}});
		menOB3.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) 
			{
				cl.show(card, "menu");
			}});
		//Fin de la construction de la fenetre, on ajoute les panneaux et label
		//Au content panel
		
		this.getContentPane().add(titre, BorderLayout.NORTH);
		this.getContentPane().add(card, BorderLayout.CENTER);
		cl.show(card, "menu");
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		setText(jtf.getText());
		textArea.append(getText() + "\n");
		jtf.selectAll();
		
		textArea.setCaretPosition(textArea.getDocument().getLength());
	}
	
	private String getText() {
		return reponseUser;
	}

	private void setText(String text) {
		this.reponseUser = text;
	}

	public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Pendu_Window pw = new Pendu_Window("");
                pw.setVisible(true);
            }
        });
    }
}

