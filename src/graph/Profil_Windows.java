package graph;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

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
		setTitle("Profil");
		setSize(1200,750);
		setLocationRelativeTo(null);
		setResizable(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		/*
		 * On va maintenant faire le contenu du panel
		 */
		JPanel card = new JPanel();	//JPanel qui va contenir le CardLayout
		JPanel panelTxtPrinc = new JPanel();	//Panneau pour tous les boutons et les textes
		
		CardLayout cl = new CardLayout(30,10);
		card.setLayout(cl);
		
		GridLayout gd = new GridLayout();
		gd.setColumns(3);
		gd.setRows(7);
		gd.setHgap(50);
		gd.setVgap(30);
		panelTxtPrinc.setLayout(gd);
		
		String nom = profilGestion.getName();
		
		JLabel label1 = new JLabel("PROFIL - " + nom);
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		label1.setFont(new Font("Courier", Font.BOLD,20));		

		JButton bouton1 = new JButton("Tableau des scores general");
		panelTxtPrinc.add(bouton1);

		JButton bouton2= new JButton("Score Pendu");	
		panelTxtPrinc.add(bouton2);

		JButton bouton3= new JButton("Score PlusMoins");
		panelTxtPrinc.add(bouton3);

		JButton bouton4= new JButton("Score MasterMind");		
		panelTxtPrinc.add(bouton4);

		JButton bouton5= new JButton("Score Puissance4");		
		panelTxtPrinc.add(bouton5);

		JButton bouton6= new JButton("Configuration");		
		panelTxtPrinc.add(bouton6);

		JButton bouton7= new JButton("Changer d'utilisateur");		
		panelTxtPrinc.add(bouton7);
		
		panelTxtPrinc.setBackground(Color.lightGray);
		card.add(panelTxtPrinc, "debut");
		
		// Constructions des autres panneaux du card
		
		//Option 2
		JPanel pan2 = new JPanel();
		pan2.setLayout(new GridLayout(10,2));
		
		final int[] tab = profilGestion.playerOne.getScorePendu();
		pan2.add(new JLabel("Score Pendu")); pan2.add(new JLabel("")); 
		pan2.add(new JLabel((tab[1]!=-1)?Integer.toString(tab[1]):" - ")); pan2.add(new JLabel((tab[2]!=-1)?Integer.toString(tab[2]):" - "));
		pan2.add(new JLabel((tab[3]!=-1)?Integer.toString(tab[3]):" - ")); pan2.add(new JLabel((tab[4]!=-1)?Integer.toString(tab[4]):" - ")); 
		pan2.add(new JLabel("Score Pendu total : ")); pan2.add(new JLabel(Integer.toString(profilGestion.playerOne.scorePenduTotal())));
		pan2.add(new JLabel(""));pan2.add(new JLabel(""));
		JButton retourB1 = new JButton("retour");
		pan2.add(retourB1);pan2.add(new JLabel(""));
		
		card.add(pan2, "bouton2");
		//Option 3
		JPanel pan3 = new JPanel();
		pan3.setLayout(new GridLayout(10,2));
		
		final int[] tab2 = profilGestion.playerOne.getScorePlusMoins();
		pan3.add(new JLabel("Score Plus ou Moins"));	pan3.add(new JLabel(""));
		pan3.add(new JLabel((tab[1]!=-1)?Integer.toString(tab[1]):" - ")); pan3.add(new JLabel((tab[2]!=-1)?Integer.toString(tab[2]):" - "));
		pan3.add(new JLabel((tab[3]!=-1)?Integer.toString(tab[3]):" - ")); pan3.add(new JLabel((tab[4]!=-1)?Integer.toString(tab[4]):" - "));		
		pan3.add(new JLabel("Score PlusMoins total : "));	pan3.add(new JLabel(Integer.toString(profilGestion.playerOne.scorePlusMoinsTotal())));
		pan3.add(new JLabel(""));pan3.add(new JLabel(""));
		JButton retourB2 = new JButton("retour");
		pan3.add(retourB2);pan3.add(new JLabel(""));
		
		card.add(pan3, "bouton3");
		//Option 4
		JPanel pan4 = new JPanel();
		pan4.setLayout(new GridLayout(10,2));
		
		final int[] tab3 = profilGestion.playerOne.getScoreMasterMind();
		pan4.add(new JLabel("Score Mastermind"));	pan4.add(new JLabel(""));
		pan4.add(new JLabel((tab[1]!=-1)?Integer.toString(tab[1]):" - ")); pan4.add(new JLabel((tab[2]!=-1)?Integer.toString(tab[2]):" - "));
		pan4.add(new JLabel((tab[3]!=-1)?Integer.toString(tab[3]):" - ")); pan4.add(new JLabel((tab[4]!=-1)?Integer.toString(tab[4]):" - "));
		pan4.add(new JLabel("Score Mastermind total : "));	pan4.add(new JLabel(Integer.toString(profilGestion.playerOne.scoreMasterMindTotal())));
		pan4.add(new JLabel(""));pan4.add(new JLabel(""));
		JButton retourB3 = new JButton("retour");
		pan4.add(retourB3);pan4.add(new JLabel(""));
		
		card.add(pan4, "bouton4");
		//Option 5
		JPanel pan5 = new JPanel();
		pan5.setLayout(new GridLayout(10,2));
		
		final int[] tab4 = profilGestion.playerOne.getScorePuissance4();
		pan5.add(new JLabel("Puissance 4"));	pan5.add(new JLabel(""));
		pan5.add(new JLabel((tab[1]!=-1)?Integer.toString(tab[1]):" - ")); pan5.add(new JLabel((tab[2]!=-1)?Integer.toString(tab[2]):" - "));
		pan5.add(new JLabel((tab[3]!=-1)?Integer.toString(tab[3]):" - ")); pan5.add(new JLabel((tab[4]!=-1)?Integer.toString(tab[4]):" - "));
		pan5.add(new JLabel("Score Puissance 4 total : ")); pan5.add(new JLabel(Integer.toString(profilGestion.playerOne.scorePuissanceTotal())));
		pan5.add(new JLabel(""));pan5.add(new JLabel(""));
		JButton retourB4 = new JButton("retour");
		pan5.add(retourB4);pan5.add(new JLabel(""));
		
		card.add(pan5, "bouton5");
		
		/*
		 * Les actions des boutons
		 */

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
				cl.show(card, "bouton2");
			}});
		bouton3.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) 
			{
				cl.show(card, "bouton3");
			}});
		bouton4.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) 
			{
				cl.show(card, "bouton4");
			}});
		bouton5.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) 
			{
				cl.show(card, "bouton5");
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
		retourB1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event)
			{
				cl.show(card, "debut");
			}
		});
		retourB2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event)
			{
				cl.show(card, "debut");
			}
		});
		retourB3.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event)
			{
				cl.show(card, "debut");
			}
		});
		retourB4.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event)
			{
				cl.show(card, "debut");
			}
		});
		
		this.getContentPane().add(label1, BorderLayout.NORTH);
		this.getContentPane().add(card, BorderLayout.CENTER);
		cl.show(card, "debut");
		setVisible(true);
	}

	private void optionBouton6()
	{
		//TODO ici une fenetre contenant le text brut s'affiche et une autre boite de dialogue
		// prend les requetes de l'utilisateur. Ce n'est pas efficace et ergonomique
		// Il faudrait soit faire une fenetre de Configuration soit faire un Card Layout avec la fentre de profil
		// Pour ainsi afficher la configuration etc
		
		String[] options = new String[]{"Listing des propriétés", "Charger la configuration"};
		
		String choix = (String)JOptionPane.showInputDialog(null, "Choisissez votre option", "Menu de Configuration", 
															JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
		
		if(choix.equals(options[0]))
		{
			Properties pop = profilGestion.playerConf.getProp();
			
			SimpleFenetre SF = new SimpleFenetre("Proprietes - " + profilGestion.name, 9, 4);
			
			SF.addEmptyLabel();SF.addJlabel(new JLabel("Language : "));SF.addJlabel(new JLabel(pop.getProperty("Language"))); SF.addEmptyLabel();
			SF.addEmptyLabel();SF.addJlabel(new JLabel("joueurs_PlusMoins : "));SF.addJlabel(new JLabel(pop.getProperty("joueurs_PlusMoins"))); SF.addEmptyLabel();
			SF.addEmptyLabel();SF.addJlabel(new JLabel("limMax_PlusMoins : "));SF.addJlabel(new JLabel(pop.getProperty("limMax_PlusMoins"))); SF.addEmptyLabel();
			SF.addEmptyLabel();SF.addJlabel(new JLabel("vies_pendu : "));SF.addJlabel(new JLabel(pop.getProperty("vies_pendu"))); SF.addEmptyLabel();
			SF.addEmptyLabel();SF.addJlabel(new JLabel("joueurs_pendu : "));SF.addJlabel(new JLabel(pop.getProperty("joueurs_pendu"))); SF.addEmptyLabel();
			SF.addEmptyLabel();SF.addJlabel(new JLabel("vies_MasterMind : "));SF.addJlabel(new JLabel(pop.getProperty("vies_MasterMind"))); SF.addEmptyLabel();
			SF.addEmptyLabel();SF.addJlabel(new JLabel("difficulty_MasterMind : "));SF.addJlabel(new JLabel(pop.getProperty("difficulty_MasterMind"))); SF.addEmptyLabel();
			SF.addEmptyLabel();SF.addJlabel(new JLabel("joueurs_Puissance : "));SF.addJlabel(new JLabel(pop.getProperty("joueurs_Puissance"))); SF.addEmptyLabel();
			SF.addEmptyLabel();SF.addJlabel(new JLabel("difficulty_Puissance : "));SF.addJlabel(new JLabel(pop.getProperty("difficulty_Puissance"))); SF.addEmptyLabel();
			
		}else{
			profilGestion.gestionConfig(true);
		}
	}
}