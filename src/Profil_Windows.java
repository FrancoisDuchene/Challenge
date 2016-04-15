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
				profilGestion.playerOne.afficheScoreSPd();
			}});
		bouton3.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) 
			{
				//A changer par la suite
				profilGestion.playerOne.afficheScoreSPM();
			}});
		bouton4.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) 
			{
				//A changer par la suite
				profilGestion.playerOne.afficheScoreSMM();
			}});
		bouton5.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) 
			{
				//A changer par la suite
				profilGestion.playerOne.afficheScoreSPU();
			}});
		bouton6.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) 
			{
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
}
