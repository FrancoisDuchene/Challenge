package graph;

import javax.swing.JFrame;

public abstract class Challenge_JFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * @param opt_DefaultCloseOpreation : 1. Do_nothing_on_close 2. Hide_on_close
	 * 3.Dispose_on_close  4 Exit_on_close
	 */
	public Challenge_JFrame(byte opt_DefaultCloseOperation)
	{
		super();
		this.setTitle("Window");
		this.setSize(1200,750);
		this.setLocationRelativeTo(null);
		setResizable(true);
		switch(opt_DefaultCloseOperation)
		{
		case 0:
			this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			break;
		case 1:
			this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			break;
		case 2:
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			break;
		case 3:
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			break;
		} 
	}
	/**
	 * @param title : to put the title of the window
	 * @param opt_DefaultCloseOpreation : 1. Do_nothing_on_close 2. Hide_on_close
	 * 3.Dispose_on_close  4 Exit_on_close
	 */
	public Challenge_JFrame(String title, byte opt_DefaultCloseOperation)
	{
		super();
		this.setTitle(title);
		this.setSize(1200,750);
		this.setLocationRelativeTo(null);
		setResizable(true);
		switch(opt_DefaultCloseOperation)
		{
		case 0:
			this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			break;
		case 1:
			this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			break;
		case 2:
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			break;
		case 3:
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			break;
		} 
	}
}