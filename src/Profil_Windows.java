import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
		panel.setLayout(new GridLayout(8,1));
		panel.setBackground(Color.magenta);
		
		String nom = profilGestion.getName();
		
		JLabel label1 = new JLabel("PROFIL - " + nom);
		JButton bouton1 = new JButton("Tableau des scores general");
		JButton bouton2= new JButton("Score Pendu");
		JButton bouton3= new JButton("Score PlusMoins");
		JButton bouton4= new JButton("Score MasterMind");
		JButton bouton5= new JButton("Score Puissance4");
		JButton bouton6= new JButton("Configuration");
		JButton bouton7= new JButton("Changer d'utilisateur");

		bouton1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) 
			{
				//A changer par la suite
				profilGestion.afficheProfil();
			}});
		bouton2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) 
			{
				//A changer par la suite
				optionBouton2();
			}});
		bouton3.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) 
			{
				//A changer par la suite
				optionBouton3();
			}});
		bouton4.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) 
			{
				//A changer par la suite
				optionBouton4();
			}});
		bouton5.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) 
			{
				//A changer par la suite
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

		panel.add(label1);panel.add(bouton1);panel.add(bouton2);
		panel.add(bouton3);panel.add(bouton4);panel.add(bouton5);
		panel.add(bouton6);panel.add(bouton7);

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