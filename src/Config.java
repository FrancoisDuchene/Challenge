import java.io.*;
import java.util.Properties;

public class Config
{
	private String name;
	private byte language;
	private Properties force;
	public Config(String name)
	{
		this.name = name;
		this.language = challenge.getLanguage();	
		this.force = new Properties();
	}
	/**
	 * Save the Configuration in a properties file
	 */
	public void saveConfig()
	{
		final String filename = "saves/" + name + ".properties";
		FileOutputStream luke = null;
		
		force.setProperty("Language", Byte.toString(language));
		//force.list(System.out); si on veut afficher les le documents
		
		try {
			File fichier = new File(filename);
			luke = new FileOutputStream(fichier);
			force.store(luke, " Proprietes");			
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
				luke.close();
			}catch(Exception e4){
				e4.printStackTrace();
				System.err.println(e4.getMessage());
			}
		}
	}
	public void chargeConfig()
	{
		final String filename = "saves/" + name + ".properties";
		FileInputStream leia = null;
		
		try {
			File fichier = new File(filename);
			leia = new FileInputStream(fichier);
			force.load(leia);
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
				leia.close();
			}catch(Exception e4){
				e4.printStackTrace();
				System.err.println(e4.getMessage());
			}
		}
		language = Byte.parseByte(force.getProperty("Language"));
	}
	public void paramInto()
	{
		this.language = challenge.getLanguage();
	}
	public void paramExo()
	{
		challenge.setLanguage(language);
	}
	public Properties getProp() {
		return force;
	}
	public void setProp(Properties prop) {
		this.force = prop;
	}
}
