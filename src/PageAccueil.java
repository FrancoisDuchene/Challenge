import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
//import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class PageAccueil extends JFrame
{
	private static final long serialVersionUID = -8435323211801706775L;
	JPanel pageMenu = new JPanel();
    Panel page = new Panel();
    
    private JButton bouton = new JButton("Entrer");
    
    private JMenuBar menuBar = new JMenuBar();
    private JMenu navigation = new JMenu("Navigation");
    private JMenu language = new JMenu("Language");
    private JMenu aPropos = new JMenu("A propos");
    private JMenu profil = new JMenu("Profil");
    
    //Les onglets de navigations
    
    private JMenuItem nav1  = new JMenuItem("Menu Principal");
    private JMenuItem nav2  = new JMenuItem("Quitter");
    private JMenuItem lang1 = new JMenuItem("Français");
    private JMenuItem lang2 = new JMenuItem("Anglais");
    private JMenuItem prop1 = new JMenuItem("Developpeurs");
    private JMenuItem prop2 = new JMenuItem("Jeux");
    private JMenuItem prof1 = new JMenuItem("Se connecter");
    private JMenuItem prof2 = new JMenuItem("Creer un nouveau profil");
    private JCheckBox sound = new JCheckBox("Sound");
    
    private byte langue;
    boolean fin = false;

    public PageAccueil()
    {
    	langue = challenge.getLanguage();
        this.setTitle("Challenge");
        this.setSize(1200,750);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        
        this.navigation.add(nav1);
        this.navigation.add(nav2);
        this.language.add(lang1);
        this.language.add(lang2);
        this.aPropos.add(prop1);
        this.aPropos.add(prop2);
        this.profil.add(prof1);
        this.profil.add(prof2);
        this.sound.setSelected(true);
        
        nav1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event)
            {menu();}});
        nav2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event)
            {System.exit(0);}});
        lang1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event)
            {
            	challenge.setLanguage((byte)1);
            	langue = 1;
            }});
        lang2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event)
            {
            	challenge.setLanguage((byte)2);
            	langue = 2;
            }});
        prop1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event)
            {
            	String message = "";
            	if(langue==1){message = "Développeurs principaux : \n    Bivisi\n    Vinsifroid\nContributeurs :\n    The Muse\n    Po";}
            	else{message = "Main developers : \n    Bivisi\n    Vinsifroid\nContributeurs :\n    The Muse\n    Po";}
                
                JOptionPane.showMessageDialog(null,message, "Crédits",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("res/img/programmeur.jpe"));
            }});
        prop2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event)
            {
                String[] jeux = {"Pendu","Plus ou moins","Mastermind","Puissance 4"};
                String choix = (String) JOptionPane.showInputDialog(null,"Sur quel jeu voulez-voulez vous des infos?","Jeux",JOptionPane.QUESTION_MESSAGE, null,jeux,jeux[3]);
                String corps = "";
                if(choix.equals("Pendu"))
                {corps = "Premier jeu developpe dans challenge.\nLe principe de base est qu'il faut retrouver un mot en proposant des lettres.\nSi on a propose trop de mauvaise lettre, on perd.";}
                else if(choix.equals("Plus ou moins"))
                {corps = "Second jeu developpe dans challenge.\nLe but est de trouver un chiffre au hasard entre deux bornes.\nDes EastersEggs y sont cachés ;)";}
                else if(choix.equals("Mastermind"))
                {corps = "Troisieme jeu developpe dans challenge.\nIl faut retrouver la combinaison de couleur prise au hasard par l'ordinateur.";}
                else if(choix.equals("Puissance 4"))
                {corps = "Dernier jeu en date developpe dans challenge.\nIl faut reussir a aligner 4 jetons de sa couleur dans la grille sans que l'autre joueur n'en aligne avant vous";}
                JOptionPane.showMessageDialog(null,corps, choix,JOptionPane.INFORMATION_MESSAGE);
            }});
        prof1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event)
            {
            	profilGestion.gestion((byte)1);
            }});
        prof2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event)
            {
            	profilGestion.gestion((byte)1);
            }});
        sound.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event)
            {if(((JCheckBox)event.getSource()).isSelected())
            {challenge.continuons = true;challenge.musique = null;challenge.musique = new MThread("Musique");challenge.musique.start();}
            else
            {challenge.continuons = false;}}});
        
        this.menuBar.add(navigation);
        this.menuBar.add(language);
        this.menuBar.add(aPropos);
        this.menuBar.add(profil);
        this.menuBar.add(sound);
        
        bouton.addActionListener(new ActionBouton());
        page.add(bouton);
        
        //Panneau principal
        
        Panel men1 = new Panel("menu");
        JPanel men2 = new JPanel();                     men2.setPreferredSize(new Dimension(this.getWidth(),this.getHeight()/4));
        JButton bMenu1 = new JButton("Pendu");          bMenu1.setPreferredSize(new Dimension(this.getWidth()/6,this.getHeight()/10));
        JButton bMenu2 = new JButton("Plus ou Moins");  bMenu2.setPreferredSize(new Dimension(this.getWidth()/6,this.getHeight()/10));
        JButton bMenu3 = new JButton("Mastermind");     bMenu3.setPreferredSize(new Dimension(this.getWidth()/6,this.getHeight()/10));
        JButton bMenu4 = new JButton("Puissance 4");    bMenu4.setPreferredSize(new Dimension(this.getWidth()/6,this.getHeight()/10));
        JButton bMenu5 = new JButton("Exit");           bMenu5.setPreferredSize(new Dimension(this.getWidth()/6,this.getHeight()/10));

        bMenu1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event)
            {challenge.clear();pendu.menu();}});
        
        bMenu2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event)
            {challenge.clear();plusMoins.menu();}});
        
        bMenu3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event)
            {challenge.clear();Mastermind.menu();}});
        
        bMenu4.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event)
            {challenge.clear();Puissance4.menu();}});
        
        bMenu5.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event)
            {System.exit(0);}});
        
        GridLayout gl = new GridLayout(1,5);
        gl.setHgap(20);
        men2.setLayout(gl);
        men2.add(bMenu1);men2.add(bMenu2);men2.add(bMenu3);
        men2.add(bMenu4);men2.add(bMenu5);
        men2.setBackground(Color.white);

        pageMenu.setLayout(new BoxLayout(pageMenu, BoxLayout.PAGE_AXIS));
        pageMenu.add(men1);pageMenu.add(men2);
        
        this.getContentPane().add(page, BorderLayout.CENTER);
        this.setVisible(true);
        animation();
    }

    private void animation()
    {
        long vitesse = 20;
        int vitesse2 = 2;
        boolean plusX = true,plusY = true;
        while(!fin)
        {
            int x = page.getPosX(),y = page.getPosY();

            if(x+ 220> this.getWidth())
            {plusX = false;}
            if(x-200 <0)
            {plusX = true;}
            if(y+ 290> this.getHeight())
            {plusY = false;}
            if(y-200 <0)
            {plusY = true;}

            if(plusX){x+=vitesse2;}
            else{x-=vitesse2;}
            if(plusY){y+=vitesse2;}
            else{y-=vitesse2;}

            page.setPosX(x);
            page.setPosY(y);
            page.repaint();

            try{Thread.sleep(vitesse);}
            catch(InterruptedException e){}
        }        
        menu();
    }
    
    public void menu()
    {
        this.setJMenuBar(menuBar);
        bouton.setVisible(false);
        this.setContentPane(pageMenu);
        this.setVisible(true);
    }
    
    public class ActionBouton implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            fin = true;
        }
    }
}
