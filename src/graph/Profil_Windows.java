package graph;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import source.challenge;
import source.profilGestion;

/**
 * Fenetre de profil pour la version graphique
 * @author vinsifroid
 * @version 1.0
 */
public class Profil_Windows extends JFrame {

	private static final long serialVersionUID = 5288800453009819186L;
	private CardLayout cl;
	private JPanel card;
	private ResourceBundle LSD;

	public Profil_Windows()
	{
		super();
		LSD = challenge.getResbundle();
		setTitle(LSD.getString("prW_cstr_msg1"));
		setSize(1200,750);
		setLocationRelativeTo(null);
		setResizable(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		/*
		 * On va maintenant faire le contenu du panel
		 */
		card = new JPanel();	//JPanel qui va contenir le CardLayout
		JPanel panelTxtPrinc = new JPanel();	//Panneau pour tous les boutons et les textes

		cl = new CardLayout(30,10);
		card.setLayout(cl);

		GridLayout gd = new GridLayout();
		gd.setColumns(3);
		gd.setRows(7);
		gd.setHgap(50);
		gd.setVgap(30);
		panelTxtPrinc.setLayout(gd);

		final String nom = profilGestion.getName();

		JLabel label1 = new JLabel(LSD.getString("prW_cstr_msg2") + nom);
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		label1.setFont(new Font("Courier", Font.BOLD,20));		

		JButton bouton1 = new JButton(LSD.getString("prW_cstr_msg3"));
		panelTxtPrinc.add(bouton1);

		JButton bouton2= new JButton(LSD.getString("prW_cstr_msg4"));	
		panelTxtPrinc.add(bouton2);

		JButton bouton3= new JButton(LSD.getString("prW_cstr_msg5"));
		panelTxtPrinc.add(bouton3);

		JButton bouton4= new JButton(LSD.getString("prW_cstr_msg6"));		
		panelTxtPrinc.add(bouton4);

		JButton bouton5= new JButton(LSD.getString("prW_cstr_msg7"));		
		panelTxtPrinc.add(bouton5);

		JButton bouton6= new JButton(LSD.getString("prW_cstr_msg8"));		
		panelTxtPrinc.add(bouton6);

		JButton bouton7= new JButton(LSD.getString("prW_cstr_msg9"));		
		panelTxtPrinc.add(bouton7);

		panelTxtPrinc.setBackground(Color.lightGray);
		card.add(panelTxtPrinc, "debut");

		// Constructions des autres panneaux du card
		
		//Option 6 - Configuration
		JPanel panConf = new JPanel();
		panConf.setBackground(Color.lightGray);
		GridLayout gdPanConf = new GridLayout(10,2);
		gdPanConf.setHgap(30);
		gdPanConf.setVgap(5);
		panConf.setLayout(gdPanConf);		
		Properties pop = profilGestion.playerConf.getProp();

		panConf.add(new JLabel(LSD.getString("prW_cstr_msg10"))); panConf.add(new JLabel(pop.getProperty("Language")));
		panConf.add(new JLabel(LSD.getString("prW_cstr_msg11"))); panConf.add(new JLabel(pop.getProperty("joueurs_PlusMoins")));
		panConf.add(new JLabel(LSD.getString("prW_cstr_msg12"))); panConf.add(new JLabel(pop.getProperty("joueurs_PlusMoins")));
		panConf.add(new JLabel(LSD.getString("prW_cstr_msg13"))); panConf.add(new JLabel(pop.getProperty("vies_pendu")));
		panConf.add(new JLabel(LSD.getString("prW_cstr_msg14"))); panConf.add(new JLabel(pop.getProperty("joueurs_pendu")));
		panConf.add(new JLabel(LSD.getString("prW_cstr_msg15"))); panConf.add(new JLabel(pop.getProperty("vies_MasterMind")));
		panConf.add(new JLabel(LSD.getString("prW_cstr_msg16"))); panConf.add(new JLabel(pop.getProperty("difficulty_MasterMind")));
		panConf.add(new JLabel(LSD.getString("prW_cstr_msg17"))); panConf.add(new JLabel(pop.getProperty("joueurs_Puissance")));
		panConf.add(new JLabel(LSD.getString("prW_cstr_msg18"))); panConf.add(new JLabel(pop.getProperty("difficulty_Puissance")));
		
		JButton retourB6 = new JButton("retour");
		retourB6.addActionListener(new BoutonListenerRetour());
		panConf.add(retourB6);
		
		card.add(panConf, "bouton6");
		//Option 2
		final int[] tab = profilGestion.playerOne.getScorePendu();
		card.add(panelBouton(tab,"Score Pendu"), "bouton2");

		//Option 3
		final int[] tab2 = profilGestion.playerOne.getScorePlusMoins();
		card.add(panelBouton(tab2,"Score Plus ou Moins"), "bouton3");

		//Option 4
		final int[] tab3 = profilGestion.playerOne.getScoreMasterMind();
		card.add(panelBouton(tab3,"Score Mastermind"), "bouton4");

		//Option 5
		final int[] tab4 = profilGestion.playerOne.getScorePuissance4();
		card.add(panelBouton(tab4,"Puissance 4"), "bouton5");

		//Tab general
		JPanel JGen = new JPanel();
		JGen.setBackground(Color.lightGray);
		GridLayout gdJGen = new GridLayout(19,2);
		gdJGen.setHgap(50);
		gdJGen.setVgap(7);
		JGen.setLayout(gdJGen);

		JGen.add(new JLabel(LSD.getString("prGe_affPr_msg1") + nom));	JGen.add(new JLabel(""));	

		JGen.add(new JLabel(LSD.getString("prGe_affPr_msg2"))); JGen.add(new JLabel(""));				
		JGen.add(new JLabel(Integer.toString(tab[1])));	JGen.add(new JLabel(Integer.toString(tab[2])));
		JGen.add(new JLabel(Integer.toString(tab[3])));	JGen.add(new JLabel(Integer.toString(tab[4])));		
		JGen.add(new JLabel(LSD.getString("prGe_affPr_msg3")));	JGen.add(new JLabel(Integer.toString(profilGestion.playerOne.scorePenduTotal())));

		JGen.add(new JLabel(LSD.getString("prGe_affPr_msg4")));	JGen.add(new JLabel(""));		
		JGen.add(new JLabel(Integer.toString(tab2[1])));	JGen.add(new JLabel(Integer.toString(tab2[2])));
		JGen.add(new JLabel(Integer.toString(tab2[3])));	JGen.add(new JLabel(Integer.toString(tab2[4])));		
		JGen.add(new JLabel(LSD.getString("prGe_affPr_msg5")));	JGen.add(new JLabel(Integer.toString(profilGestion.playerOne.scorePlusMoinsTotal())));

		JGen.add(new JLabel(LSD.getString("prGe_affPr_msg6")));	JGen.add(new JLabel(""));
		JGen.add(new JLabel(Integer.toString(tab3[1])));	JGen.add(new JLabel(Integer.toString(tab3[2])));
		JGen.add(new JLabel(Integer.toString(tab3[3])));	JGen.add(new JLabel(Integer.toString(tab3[4])));
		JGen.add(new JLabel(LSD.getString("prGe_affPr_msg7")));	JGen.add(new JLabel(Integer.toString(profilGestion.playerOne.scoreMasterMindTotal())));

		JGen.add(new JLabel(LSD.getString("prGe_affPr_msg8")));	JGen.add(new JLabel(""));
		JGen.add(new JLabel(Integer.toString(tab4[1])));	JGen.add(new JLabel(Integer.toString(tab4[2])));
		JGen.add(new JLabel(Integer.toString(tab4[3])));	JGen.add(new JLabel(Integer.toString(tab4[4])));
		JGen.add(new JLabel(LSD.getString("prGe_affPr_msg9"))); JGen.add(new JLabel(Integer.toString(profilGestion.playerOne.scorePuissanceTotal())));

		JGen.add(new JLabel(LSD.getString("prGe_affPr_msg10")));	JGen.add(new JLabel(Integer.toString(profilGestion.playerOne.scoreTotal())));
		JGen.add(new JLabel(""));
		JButton retour = new JButton(LSD.getString("prW_cstr_msg19"));
		retour.addActionListener(new BoutonListenerRetour());
		JGen.add(retour);

		card.add(JGen,"bouton1");		
		/*
		 * Les actions des boutons
		 */

		bouton1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) 
			{
				cl.show(card, "bouton1");
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
				String[] options = new String[]{LSD.getString("prW_cstr_msg20"), LSD.getString("prW_cstr_msg21")};

				String choix = (String)JOptionPane.showInputDialog(null, LSD.getString("prW_cstr_msg22"), LSD.getString("prW_cstr_msg23"), 
						JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

				if(choix.equals(options[0]))
				{
					cl.show(card, "bouton6");
				}else{
					profilGestion.gestionConfig(true);
				}
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

		this.getContentPane().add(label1, BorderLayout.NORTH);
		this.getContentPane().add(card, BorderLayout.CENTER);
		cl.show(card, "debut");
		setVisible(true);
	}
	private JPanel panelBouton(int [] tabS,String nom)
	{
		JPanel jp = new JPanel();
		jp.setLayout(new GridLayout(10,2));
		jp.add(new JLabel(nom));	jp.add(new JLabel(""));
		jp.add(new JLabel((tabS[1]!=-1)?Integer.toString(tabS[1]):" - ")); jp.add(new JLabel((tabS[2]!=-1)?Integer.toString(tabS[2]):" - "));
		jp.add(new JLabel((tabS[3]!=-1)?Integer.toString(tabS[3]):" - ")); jp.add(new JLabel((tabS[4]!=-1)?Integer.toString(tabS[4]):" - "));
		jp.add(new JLabel(LSD.getString("prW_cstr_msg24") + nom + LSD.getString("prW_cstr_msg25"))); 
		if(nom.equals("Puissance 4")){jp.add(new JLabel(Integer.toString(profilGestion.playerOne.scorePuissanceTotal())));}
		else if(nom.equals("Score Mastermind")){jp.add(new JLabel(Integer.toString(profilGestion.playerOne.scoreMasterMindTotal())));}
		else if(nom.equals("Score Plus ou Moins")){jp.add(new JLabel(Integer.toString(profilGestion.playerOne.scorePlusMoinsTotal())));}
		else if(nom.equals("Score Pendu")){jp.add(new JLabel(Integer.toString(profilGestion.playerOne.scorePenduTotal())));}

		jp.add(new JLabel(""));jp.add(new JLabel(""));
		JButton retour = new JButton(LSD.getString("prW_cstr_msg19"));
		retour.addActionListener(new BoutonListenerRetour());
		jp.add(retour);jp.add(new JLabel(""));
		jp.setBackground(Color.lightGray);
		return jp;
	}
	/**
	 * Methode plus générique pour un bouton retour de revenir vers le menu principal
	 * @author vinsifroid
	 *
	 */
	class BoutonListenerRetour implements ActionListener{
		//Redéfinition de la méthode actionPerformed()
		public void actionPerformed(ActionEvent arg0) {    
			cl.show(card, "debut");
		}
	}
}