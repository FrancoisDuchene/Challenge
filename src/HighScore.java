import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class HighScore {

	/**
	 * Le mode du Highscore, c'est a dire cela definit sur quel jeu le highscore travaille
	 * 4 possibilites :
	 * 	- 1 pendu
	 *  - 2 plusmoins
	 *  - 3 mastermind
	 *  - 4 puissance4
	 */
	private byte mode;
	private byte language;

	//Toutes les valeurs du Highscore trié dans l'ordre décroissant
	// val1 est la plus grande valeur
	// val10 la plus petite

	private int val1;
	private int val2;
	private int val3;
	private int val4;
	private int val5;
	private int val6;
	private int val7;
	private int val8;
	private int val9;
	private int val10;

	//Tous les noms des joueurs ayant fait les valeurs

	private String val1Player;
	private String val2Player;
	private String val3Player;
	private String val4Player;
	private String val5Player;
	private String val6Player;
	private String val7Player;
	private String val8Player;
	private String val9Player;
	private String val10Player;

	private String specific_Name;
	private String fileName;
	private String [][] tableauScore;
	private Properties blaster;

	public HighScore(byte mode) throws INVALID_MODE
	{
		val1=0;val2=0;val3=0;val4=0;val5=0;val6=0;val7=0;val8=0;val9=0;val10=0;
		val1Player=" ";val2Player=" ";val3Player=" ";val4Player=" ";val5Player=" ";
		val6Player=" ";val7Player=" ";val8Player=" ";val9Player=" ";val10Player=" ";
		blaster = new Properties();
		this.mode = mode;
		this.fileName = null;
		updateSpecific_Name();
		switch(mode)
		{
		case 1:
			this.fileName = "saves/HighScore/" + "pendu" + ".hg";
			break;
		case 2:
			this.fileName = "saves/HighScore/" + "plusMoins" + ".hg";
			break;
		case 3:
			this.fileName = "saves/HighScore/" + "mastermind" + ".hg";
			break;
		case 4:
			this.fileName = "saves/HighScore/" + "puissance4" + ".hg";
			break;
		default:
			this.fileName = null;
			throw new INVALID_MODE("Mauvais_mode_donne");
		}
		tableauScore = null;
	}
	private void updateSpecific_Name()
	{
		language = challenge.getLanguage();
		switch(mode)
		{
		case 1:
			if(language==1){ this.specific_Name = "Pendu"; 
			}else{ this.specific_Name = "Hangman"; }
			break;
		case 2:
			if(language==1){ this.specific_Name = "PlusMoins"; }
			else{ this.specific_Name = "HighLow"; }
			break;
		case 3:
			if(language==1){ this.specific_Name = "Mastermind"; }
			else{ this.specific_Name = "Mastermind"; }
			break;
		case 4:
			if(language==1){ this.specific_Name = "Puissance4"; }
			else{ this.specific_Name = "Connect4"; }
			break;
		default:
			this.specific_Name = "error";
		}
	}

	/**
	 * Cette fonction mes à jour les propriétés avec les valeurs inclue en mémoire du programme
	 */
	private void updateProperties()
	{
		blaster.setProperty("val1", Integer.toString(val1));
		blaster.setProperty("val2", Integer.toString(val2));
		blaster.setProperty("val3", Integer.toString(val3));
		blaster.setProperty("val4", Integer.toString(val4));
		blaster.setProperty("val5", Integer.toString(val5));
		blaster.setProperty("val6", Integer.toString(val6));
		blaster.setProperty("val7", Integer.toString(val7));
		blaster.setProperty("val8", Integer.toString(val8));
		blaster.setProperty("val9", Integer.toString(val9));
		blaster.setProperty("val10", Integer.toString(val10));	

		blaster.setProperty("val1Player", val1Player);
		blaster.setProperty("val2Player", val2Player);
		blaster.setProperty("val3Player", val3Player);
		blaster.setProperty("val4Player", val4Player);
		blaster.setProperty("val5Player", val5Player);
		blaster.setProperty("val6Player", val6Player);
		blaster.setProperty("val7Player", val7Player);
		blaster.setProperty("val8Player", val8Player);
		blaster.setProperty("val9Player", val9Player);
		blaster.setProperty("val10Player", val10Player);
	}
	/**
	 * Cette fonction sauvegarde le tableau de inclu en memoire
	 */
	public void save()
	{
		FileOutputStream Solo = null;
		try {
			File fichier = new File(fileName);
			Solo = new FileOutputStream(fichier);
			blaster.store(Solo, " Proprietes");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			System.err.println(e1.getMessage());
		}catch (IOException e2) {
			e2.printStackTrace();
			System.err.println(e2.getMessage());
		}catch (Exception e3) {
			e3.printStackTrace();
			System.err.println(e3.getMessage());
		}finally{
			try{
				Solo.close();
			}catch(Exception e4){
				e4.printStackTrace();
				System.err.println(e4.getMessage());
			}
		}
	}
	/**
	 * Cette fonction charge la propriété contenu dans le fichier saves/HighScore/<FichierSpecifique> et ensuite
	 * met a jour toues les variables du HighScore
	 */
	public void charge()
	{
		FileInputStream C3PO = null;

		try {
			File fichier = new File(fileName);
			C3PO = new FileInputStream(fichier);
			blaster.load(C3PO);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			System.err.println(e1.getMessage());
		}catch (IOException e2) {
			e2.printStackTrace();
			System.err.println(e2.getMessage());
		}catch (Exception e3) {
			e3.printStackTrace();
			System.err.println(e3.getMessage());
		}finally{
			try{
				C3PO.close();
			}catch(Exception e4){
				e4.printStackTrace();
				System.err.println(e4.getMessage());
			}
		}
		val1 = Integer.parseInt(blaster.getProperty("val1"));
		val2 = Integer.parseInt(blaster.getProperty("val2"));
		val3 = Integer.parseInt(blaster.getProperty("val3"));
		val4 = Integer.parseInt(blaster.getProperty("val4"));
		val5 = Integer.parseInt(blaster.getProperty("val5"));
		val6 = Integer.parseInt(blaster.getProperty("val6"));
		val7 = Integer.parseInt(blaster.getProperty("val7"));
		val8 = Integer.parseInt(blaster.getProperty("val8"));
		val9 = Integer.parseInt(blaster.getProperty("val9"));
		val10 = Integer.parseInt(blaster.getProperty("val10"));
		
		val1Player = blaster.getProperty("val1Player");
		val2Player = blaster.getProperty("val2Player");
		val3Player = blaster.getProperty("val3Player");
		val4Player = blaster.getProperty("val4Player");
		val5Player = blaster.getProperty("val5Player");
		val6Player = blaster.getProperty("val6Player");
		val7Player = blaster.getProperty("val7Player");
		val8Player = blaster.getProperty("val8Player");
		val9Player = blaster.getProperty("val9Player");
		val10Player = blaster.getProperty("val10Player");
	}
	public void afficheHighScore()
	{
		updateSpecific_Name();
		System.out.println("HighScore " + specific_Name);

		System.out.println(val1Player + " : " + val1);
		System.out.println(val2Player + " : " + val2);
		System.out.println(val3Player + " : " + val3);
		System.out.println(val4Player + " : " + val4);
		System.out.println(val5Player + " : " + val5);
		System.out.println(val6Player + " : " + val6);
		System.out.println(val7Player + " : " + val7);
		System.out.println(val8Player + " : " + val8);
		System.out.println(val9Player + " : " + val9);
		System.out.println(val10Player + " : " + val10);
	}

	public boolean addValeur(String nom, int val)
	{
		if(val<val1)
		{
			if(val<val2)
			{
				if(val<val3)
				{
					if(val<val4)
					{
						if(val<val5)
						{
							if(val<val6)
							{
								if(val<val7)
								{
									if(val<val8)
									{
										if(val<val9)
										{
											if(val<val10)
											{
												return false;
											}else{
												val10=val;

												val10Player = nom;
											}
										}else{
											val10=val9;
											val9=val;

											val10Player = val9Player;
											val9Player = nom;
										}
									}else{
										val10=val9;
										val9=val8;
										val8=val;

										val10Player = val9Player;
										val9Player = val8Player;
										val8Player = nom;
									}
								}else{
									val10=val9;
									val9=val8;
									val8=val7;
									val7=val;

									val10Player = val9Player;
									val9Player = val8Player;
									val8Player = val7Player;
									val7Player = nom;
								}
							}else{
								val10=val9;
								val9=val8;
								val8=val7;
								val7=val6;
								val6=val;

								val10Player = val9Player;
								val9Player = val8Player;
								val8Player = val7Player;
								val7Player = val6Player;
								val6Player = nom;
							}
						}else{
							val10=val9;
							val9=val8;
							val8=val7;
							val7=val6;
							val6=val5;
							val5=val;

							val10Player = val9Player;
							val9Player = val8Player;
							val8Player = val7Player;
							val7Player = val6Player;
							val6Player = val5Player;
							val5Player = nom;
						}
					}else{
						val10=val9;
						val9=val8;
						val8=val7;
						val7=val6;
						val6=val5;
						val5=val4;
						val4=val;

						val10Player = val9Player;
						val9Player = val8Player;
						val8Player = val7Player;
						val7Player = val6Player;
						val6Player = val5Player;
						val5Player = val4Player;
						val4Player = nom;
					}
				}else{
					val10=val9;
					val9=val8;
					val8=val7;
					val7=val6;
					val6=val5;
					val5=val4;
					val4=val3;
					val3=val;

					val10Player = val9Player;
					val9Player = val8Player;
					val8Player = val7Player;
					val7Player = val6Player;
					val6Player = val5Player;
					val5Player = val4Player;
					val4Player = val3Player;
					val3Player = nom;
				}
			}else{
				val10=val9;
				val9=val8;
				val8=val7;
				val7=val6;
				val6=val5;
				val5=val4;
				val4=val3;
				val3=val2;
				val2=val;

				val10Player = val9Player;
				val9Player = val8Player;
				val8Player = val7Player;
				val7Player = val6Player;
				val6Player = val5Player;
				val5Player = val4Player;
				val4Player = val3Player;
				val3Player = val2Player;
				val2Player = nom;
			}
		}else{
			val10=val9;
			val9=val8;
			val8=val7;
			val7=val6;
			val6=val5;
			val5=val4;
			val4=val3;
			val3=val2;
			val2=val1;
			val1=val;

			val10Player = val9Player;
			val9Player = val8Player;
			val8Player = val7Player;
			val7Player = val6Player;
			val6Player = val5Player;
			val5Player = val4Player;
			val4Player = val3Player;
			val3Player = val2Player;
			val2Player = val1Player;
			val1Player = nom;
		}
		updateProperties();
		save();
		return true;
	}
	public Properties getProperties()
	{
		return blaster;
	}

	public void setFileName(String FileName)
	{
		this.fileName = FileName; 
	}
	public void setMode(byte mode)
	{
		this.mode = mode;
	}
	public void setTableauScore(String [][] tableauScore)
	{
		this.tableauScore = tableauScore;
	}
	public byte getMode()
	{
		return mode;
	}
	public String [][] getTableauScore()
	{
		return tableauScore;
	}
}