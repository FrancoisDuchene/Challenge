import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(buildContentPane());
		setVisible(true);
	}
	private JPanel buildContentPane()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		
		JLabel label1 = new JLabel("GESTIONNAIRE DE PROFIL");

		panel.add(label1);

		return panel;
	}
}
