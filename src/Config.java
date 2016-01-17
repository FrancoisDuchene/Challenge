import java.io.*;

public class Config
{
	private String name;
	private byte language;
	public Config(String name)
	{
		this.name = name;
		this.language = challenge.getLanguage();		
	}
	public boolean saveConfig()
	{
		PrintWriter yoda = null;
		try{
			yoda = new PrintWriter(new FileWriter(name + ".cf"));
			yoda.println(language);
		}catch(FileNotFoundException e1)
		{
			System.err.println(e1.getMessage());
		}catch(IOException e2)
		{
			System.err.println(e2.getMessage());
		}catch(Exception e3)
		{
			System.err.println(e3.getMessage());
			return false;
		}finally{
			try{
				yoda.close();
				return true;
			}catch(Exception e2){
				System.err.println(e2.getMessage());
			}
		}
		return false;
	}
	public void chargeConfig()
	{
		BufferedReader Luke = null;
		try{
			Luke = new BufferedReader(new FileReader(name + ".cf"));
			String force = "";
			force = Luke.readLine();
			language = Byte.parseByte(force);
		}catch(FileNotFoundException e1)
		{
			System.err.println(e1.getMessage());
		}catch(IOException e2)
		{
			System.err.println(e2.getMessage());
		}catch(Exception e3)
		{
			System.err.println(e3.getMessage());
		}finally{
			try{
				Luke.close();
			}catch(Exception e2){
				System.err.println(e2.getMessage());
				System.exit(-1);
			}
		}
	}
	public void paramInto()
	{
		this.language = challenge.getLanguage();
	}
	public void paramExo()
	{
		challenge.setLanguage(language);
	}
}
