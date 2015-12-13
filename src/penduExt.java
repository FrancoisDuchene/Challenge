
public class penduExt {

	/**
	 * this print a hangman according to nombreCoups
	 * @param nombreCoups is number of hits
	 */
	public static void pendre(int nombreCoups)
	{
		switch(nombreCoups)
		{
		case 7:
			System.out.println("");
			break;
		case 6:
			System.out.print("                \n");
			System.out.print("    ############\n");
			System.out.print("    ||   /   |  \n");
			System.out.print("    ||  /    |  \n");
			System.out.print("    || /        \n");
			System.out.print("    ||/         \n");
			System.out.print("    ||          \n");
			System.out.print("    ||          \n");
			System.out.print("    ||          \n");
			System.out.print("    ||          \n");
			System.out.print("    ||          \n");
			System.out.print("    ||          \n");
			System.out.print("    ||          \n");
			System.out.print("    ||          \n");
			System.out.print("    ||          \n");
			System.out.print("    ||          \n");
			System.out.print("    ||          \n");
			System.out.print(" ##########     \n");
			break;          
		case 5:
			System.out.print("                \n");
			System.out.print("    ############\n");
			System.out.print("    ||   /   |  \n");
			System.out.print("    ||  /    |  \n");
			System.out.print("    || /    @@@ \n");
			System.out.print("    ||/    @@@@@\n");
			System.out.print("    ||      @@@ \n");
			System.out.print("    ||          \n");
			System.out.print("    ||          \n");
			System.out.print("    ||          \n");
			System.out.print("    ||          \n");
			System.out.print("    ||          \n");
			System.out.print("    ||          \n");
			System.out.print("    ||          \n");
			System.out.print("    ||          \n");
			System.out.print("    ||          \n");
			System.out.print("    ||          \n");
			System.out.print(" ##########     \n");
			break;		   
		case 4:
			System.out.print("                \n");
			System.out.print("    ############\n");
			System.out.print("    ||   /   |  \n");
			System.out.print("    ||  /    |  \n");
			System.out.print("    || /    @@@ \n");
			System.out.print("    ||/    @@@@@\n");
			System.out.print("    ||      @@@ \n");
			System.out.print("    ||       &  \n");
			System.out.print("    ||     @@@@@\n");
			System.out.print("    ||      @@@ \n");
			System.out.print("    ||      @@@ \n");
			System.out.print("    ||      @@@ \n");
			System.out.print("    ||      @@@ \n");
			System.out.print("    ||          \n");
			System.out.print("    ||          \n");
			System.out.print("    ||          \n");
			System.out.print("    ||          \n");
			System.out.print(" ##########     \n");
			break;		   
		case 3:
			System.out.print("                \n");
			System.out.print("    ############\n");
			System.out.print("    ||   /   |  \n");
			System.out.print("    ||  /    |  \n");
			System.out.print("    || /    @@@ \n");
			System.out.print("    ||/    @@@@@\n");
			System.out.print("    ||      @@@ \n");
			System.out.print("    ||       &  \n");
			System.out.print("    ||     @@@@@\n");
			System.out.print("    ||    / @@@ \n");
			System.out.print("    ||    | @@@ \n");
			System.out.print("    ||    | @@@ \n");
			System.out.print("    ||    * @@@ \n");
			System.out.print("    ||          \n");
			System.out.print("    ||          \n");
			System.out.print("    ||          \n");
			System.out.print("    ||          \n");
			System.out.print(" ##########     \n");
			break;		   
		case 2:
			System.out.print("                  \n");
			System.out.print("    ############  \n");
			System.out.print("    ||   /   |    \n");
			System.out.print("    ||  /    |    \n");
			System.out.print("    || /    @@@   \n");
			System.out.print("    ||/    @@@@@  \n");
			System.out.print("    ||      @@@   \n");
			System.out.print("    ||       &    \n");
			System.out.print("    ||     @@@@@  \n");
			System.out.print("    ||    / @@@ \\ \n");
			System.out.print("    ||    | @@@ | \n");
			System.out.print("    ||    | @@@ | \n");
			System.out.print("    ||    * @@@ * \n");
			System.out.print("    ||            \n");
			System.out.print("    ||            \n");
			System.out.print("    ||            \n");
			System.out.print("    ||            \n");
			System.out.print(" ##########       \n");
			break;		  
		case 1:
			System.out.print("                  \n");
			System.out.print("    ############  \n");
			System.out.print("    ||   /   |    \n");
			System.out.print("    ||  /    |    \n");
			System.out.print("    || /    @@@   \n");
			System.out.print("    ||/    @@@@@  \n");
			System.out.print("    ||      @@@   \n");
			System.out.print("    ||       &    \n");
			System.out.print("    ||     @@@@@  \n");
			System.out.print("    ||    / @@@ \\ \n");
			System.out.print("    ||    | @@@ | \n");
			System.out.print("    ||    | @@@ | \n");
			System.out.print("    ||    * @@@ * \n");
			System.out.print("    ||      $     \n");
			System.out.print("    ||      $     \n");
			System.out.print("    ||      $     \n");
			System.out.print("    ||     c$     \n");
			System.out.print(" ##########       \n");
			break;		  
		case 0:
			System.out.print("                  \n");
			System.out.print("    ############  \n");
			System.out.print("    ||   /   |    \n");
			System.out.print("    ||  /    |    \n");
			System.out.print("    || /    @@@   \n");
			System.out.print("    ||/    @@@@@  \n");
			System.out.print("    ||      @@@   \n");
			System.out.print("    ||       &    \n");
			System.out.print("    ||     @@@@@  \n");
			System.out.print("    ||    / @@@ \\ \n");
			System.out.print("    ||    | @@@ | \n");
			System.out.print("    ||    | @@@ | \n");
			System.out.print("    ||    * @@@ * \n");
			System.out.print("    ||      $ $   \n");
			System.out.print("    ||      $ $   \n");
			System.out.print("    ||      $ $   \n");
			System.out.print("    ||     c$ $o  \n");
			System.out.print(" ##########       \n");
			break;
		}       
	}
}