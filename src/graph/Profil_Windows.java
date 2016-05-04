package graph;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import source.profilGestion;

/**
 * 
 * @author vinsifroid
 * @version 1.0
 */
public class Profil_Windows extends JFrame {

	private static final long serialVersionUID = 5288800453009819186L;

	public Profil_Windows()
	{
		super();
		build();
	}

	/**
	 * Build the profil_windows
	 */
	private void build()
	{
		setTitle("Profil");
		setSize(1200,750);
		setLocationRelativeTo(null);
		setResizable(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setContentPane(buildContentPane());
		setVisible(true);
	}
	private JPanel buildContentPane()
	{		
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		panel.setBackground(Color.magenta);
		
		String nom = profilGestion.getName();
		
		c.fill = GridBagConstraints.VERTICAL;
		
		JLabel label1 = new JLabel("PROFIL - " + nom);
		c.weightx = 0.5;
		c.weighty = 1;
		c.fill = GridBagConstraints.VERTICAL;
		c.gridx = 2;
		c.gridy = 0;
		panel.add(label1,  c);
		
		JButton bouton1 = new JButton("Tableau des scores general");
		c.fill = GridBagConstraints.VERTICAL;
		c.weightx = 0.5;	
		c.weighty = 0.5;
		c.gridx = 2;
		c.gridy = 1;
		panel.add(bouton1,  c);
		
		JButton bouton2= new JButton("Score Pendu");
		c.fill = GridBagConstraints.VERTICAL;
		c.weightx = 0.5;	
		c.weighty = 0.5;
		c.gridx = 2;
		c.gridy = 2;
		panel.add(bouton2,  c);
		
		JButton bouton3= new JButton("Score PlusMoins");
		c.fill = GridBagConstraints.VERTICAL;
		c.weightx = 0.5;	
		c.weighty = 0.5;
		c.gridx = 2;
		c.gridy = 3;
		panel.add(bouton3,  c);
		
		JButton bouton4= new JButton("Score MasterMind");
		c.fill = GridBagConstraints.VERTICAL;
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx = 2;
		c.gridy = 4;
		panel.add(bouton4,  c);
		
		JButton bouton5= new JButton("Score Puissance4");
		c.fill = GridBagConstraints.VERTICAL;
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx = 2;
		c.gridy = 5;
		panel.add(bouton5,  c);
		
		JButton bouton6= new JButton("Configuration");
		c.fill = GridBagConstraints.VERTICAL;
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx = 2;
		c.gridy = 6;
		panel.add(bouton6,  c);
		
		JButton bouton7= new JButton("Changer d'utilisateur");
		c.fill = GridBagConstraints.VERTICAL;
		c.weightx = 0.5;
		c.weighty = 0.8;
		c.gridx = 2;
		c.gridy = 7;
		panel.add(bouton7,  c);

		bouton1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) 
			{
				profilGestion.afficheProfil();
			}});
		bouton2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) 
			{
				optionBouton2();
			}});
		bouton3.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) 
			{
				optionBouton3();
			}});
		bouton4.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) 
			{
				optionBouton4();
			}});
		bouton5.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) 
			{
				optionBouton5();
			}});
		bouton6.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) 
			{
				optionBouton6();
				profilGestion.optionConfigurationMenu();
			}});
		bouton7.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) 
			{
				profilGestion.setPremierOuverture(true);
				profilGestion.setExiste(false);
				profilGestion.setConfExiste(false);
				profilGestion.gestion((byte)1);
			}});

		return panel;
	}
	
	private void optionBouton2()
	{
		int[] tab = profilGestion.playerOne.getScorePendu();
		
		SimpleFenetre SF = new SimpleFenetre("Score Pendu",8,2);
		
		SF.addJlabel(new JLabel("Score Pendu")); SF.addJlabel(new JLabel(""));				
		SF.addJlabel(new JLabel(Integer.toString(tab[1])));	SF.addJlabel(new JLabel(Integer.toString(tab[2])));
		SF.addJlabel(new JLabel(Integer.toString(tab[3])));	SF.addJlabel(new JLabel(Integer.toString(tab[4])));		
		SF.addJlabel(new JLabel("Score Pendu total : "));	SF.addJlabel(new JLabel(Integer.toString(profilGestion.playerOne.scorePenduTotal())));		
	}
	private void optionBouton3()
	{
		int[] tab2 = profilGestion.playerOne.getScorePlusMoins();
		
		SimpleFenetre SF = new SimpleFenetre("Score Pendu",8,2);
		
		SF.addJlabel(new JLabel("Score Plus ou Moins"));	SF.addJlabel(new JLabel(""));		
		SF.addJlabel(new JLabel(Integer.toString(tab2[1])));	SF.addJlabel(new JLabel(Integer.toString(tab2[2])));
		SF.addJlabel(new JLabel(Integer.toString(tab2[3])));	SF.addJlabel(new JLabel(Integer.toString(tab2[4])));		
		SF.addJlabel(new JLabel("Score PlusMoins total : "));	SF.addJlabel(new JLabel(Integer.toString(profilGestion.playerOne.scorePlusMoinsTotal())));
	}
	private void optionBouton4()
	{
		int[] tab3 = profilGestion.playerOne.getScoreMasterMind();
		
		SimpleFenetre SF = new SimpleFenetre("Score Pendu",8,2);
		
		SF.addJlabel(new JLabel("Score Mastermind"));	SF.addJlabel(new JLabel(""));
		SF.addJlabel(new JLabel(Integer.toString(tab3[1])));	SF.addJlabel(new JLabel(Integer.toString(tab3[2])));
		SF.addJlabel(new JLabel(Integer.toString(tab3[3])));	SF.addJlabel(new JLabel(Integer.toString(tab3[4])));
		SF.addJlabel(new JLabel("Score Mastermind total : "));	SF.addJlabel(new JLabel(Integer.toString(profilGestion.playerOne.scoreMasterMindTotal())));
	}
	private void optionBouton5()
	{
		int[] tab4 = profilGestion.playerOne.getScorePuissance4();
		
		SimpleFenetre SF = new SimpleFenetre("Score Pendu",8,2);
		
		SF.addJlabel(new JLabel("Puissance 4"));	SF.addJlabel(new JLabel(""));
		SF.addJlabel(new JLabel(Integer.toString(tab4[1])));	SF.addJlabel(new JLabel(Integer.toString(tab4[2])));
		SF.addJlabel(new JLabel(Integer.toString(tab4[3])));	SF.addJlabel(new JLabel(Integer.toString(tab4[4])));
		SF.addJlabel(new JLabel("Score Puissance 4 total : ")); SF.addJlabel(new JLabel(Integer.toString(profilGestion.playerOne.scorePuissanceTotal())));
	}
	private void optionBouton6()
	{
		
	}
}