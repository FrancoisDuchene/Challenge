import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class PageAccueil extends JFrame
{
    JPanel pageMenu = new JPanel();
    Panel page = new Panel();
    
    private JMenuBar menuBar = new JMenuBar();
    private JMenu navigation = new JMenu("Navigation");
    private JMenu language = new JMenu("Language");
    private JMenu aPropos = new JMenu("A propos");
    private JMenu profil = new JMenu("Profil");
    
    private JMenuItem nav1  = new JMenuItem("Menu Principal");
    private JMenuItem nav2  = new JMenuItem("Quitter");
    private JMenuItem lang1 = new JMenuItem("Français");
    private JMenuItem lang2 = new JMenuItem("Anglais");
    private JMenuItem prop1 = new JMenuItem("Developpeurs");
    private JMenuItem prop2 = new JMenuItem("Jeux");
    private JMenuItem prof1 = new JMenuItem("Se connecter");
    private JMenuItem prof2 = new JMenuItem("Creer un nouveau profil");
    private JCheckBox sound = new JCheckBox("Sound");
    
    boolean fin = false;

    public PageAccueil()
    {
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
            {}});
        lang2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event)
            {}});
        prop1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event)
            {}});
        prop2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event)
            {}});
        prof1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event)
            {}});
        prof2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event)
            {}});
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
        
        JPanel men1 = new JPanel();
        men1.setBackground(Color.white);

        JButton bouton = new JButton("Entrer");
        bouton.addActionListener(new ActionBouton());
        page.add(bouton);

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
