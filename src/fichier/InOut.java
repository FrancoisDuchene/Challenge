package fichier;
import java.io.*;
/**
 * To read the characters,...
 * 
 * @author Luis
 * @version 0.1
 */
public class InOut
{
    public final static char EOF = (char) 0xFFFF;
    public final static char LN = '\n';

    /**
     * @pre --
     * @post return the byte that was in the console. Skip blanks.
     */
    public static byte getByte()
    {
        return (byte) readInteger(-128L,127L);
    }

    /**
     * @pre --
     * @post return the short that was in the console. Skip blanks.
     */
    public static short getShort()
    {
        return (short)readInteger(-32768L,32767L);
    }

    /**
     * @pre --
     * @post return the int that was in the console. Skip blanks.
     */
    public static int getInt()
    {
        return (int)readInteger(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /**
     * @pre --
     * @post return the long that was in the console. Skip blanks.
     */
    public static int getLong()
    {
        return (int)readInteger(Long.MIN_VALUE, Long.MAX_VALUE);
    }

    /**
     * @pre --
     * @post read int in console.
     */
    private static long readInteger(long min, long max) throws NumberFormatException
    {
        BufferedReader bf = null;
        long x = Integer.MIN_VALUE;
        while(x == Integer.MIN_VALUE)
        {
            try{
                bf = new BufferedReader(new InputStreamReader (System.in));
                StringBuffer sb = new StringBuffer();
                boolean fin = true;
                while(fin)
                {
                    char s = (char)bf.read(); 
                    if(s == ' '  || !Character.isDigit(s) && s != '-')
                    {
                        fin = false;
                    }
                    else{
                        sb.append(s);
                    }
                }
                x = Long.parseLong(sb.toString());
            }
            catch(IOException e1)
            {System.err.println("IOException readInteger() InOut :" + e1.getMessage());}
            catch(NumberFormatException e2)
            {System.err.println("NumberFormatException readInteger() InOut :" + e2.getMessage());}
            catch(Exception e3)
            {System.err.println("Exception readInteger() InOut :" + e3.getMessage());}
        }
        if(x > max || x < min)
        {
            throw new NumberFormatException("Nombre trop grand ou trop petit max = "+ max + "   min = "+ min + "    nombre = " + x);
        }
        return x;
    }

    /**
     * @pre --
     * @post return the char that was in the console. Skip blanks.
     */
    public static char getChar()
    {
        BufferedReader bf = null;
        char x = ' ';
        try{
            bf = new BufferedReader(new InputStreamReader (System.in));
            x = (char)bf.read();
        }
        catch(IOException e1)
        {System.err.println("IOException getChar() InOut :" + e1.getMessage());}
        catch(Exception e2)
        {System.err.println("Exception getChar() InOut :" + e2.getMessage());}
        
        if(x == ' ')
        {
            x = getChar();
        }
        return x;
    }

    /**
     * @pre --
     * @post return the word that was in the console. Skip blanks.
     */
    public static String getWord()
    {
        BufferedReader bf = null;
        String x = null;
        try{
            bf = new BufferedReader(new InputStreamReader (System.in));
            StringBuffer sb = new StringBuffer();
            boolean fin = true;
            while(fin)
            {
                char s = (char)bf.read();
                if(s == ' ' || !Character.isLetter(s))
                {
                    fin = false;
                }
                else{
                    sb.append(s);
                }
            }
            x = sb.toString();
        }
        catch(IOException e1)
        {System.err.println("IOException getWord() InOut :" + e1.getMessage());}
        catch(Exception e2)
        {System.err.println("Exception getWord() InOut :" + e2.getMessage());}

        if(x != null && x.equals(" "))
        {
            x = getWord();
        }
        return x;
    }

    public static String getLine()
    {
        BufferedReader bf = null;
        String x = null;
        try{
            bf = new BufferedReader(new InputStreamReader (System.in));
            x = bf.readLine();
        }
        catch(IOException e1)
        {System.err.println("IOException getLine() InOut :" + e1.getMessage());}
        catch(Exception e2)
        {System.err.println("Exception getline() InOut :" + e2.getMessage());}
        return x;
    }
    
    public static double getDouble()
    {
    	BufferedReader bf = null;
    	double x = Double.NaN;
    	try{
    		bf = new BufferedReader(new InputStreamReader(System.in));
    		String s = bf.readLine();
    		x = Double.parseDouble(s);
    	}catch(IOException e1){
    		System.err.println("IOException getDouble() InOut :" + e1.getMessage());
    	}catch(NumberFormatException e2){
    		System.err.println("NumberFormatException getDouble() InOut :" + e2.getMessage());
    	}catch(Exception e3){
    		System.err.println("Exception getDouble() InOut :" + e3.getMessage());
    	}
    	return x;
    }

    public static String Mot(String s)
    {
        boolean ok = false;
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < s.length() && !ok ; i++)
        {
            if(!Character.isLetter(s.charAt(i)))
            {
                ok = true;
            }
            else
            {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
    
    // Methode liees a la lecture de fichiers
    
    /**
	 * 
	 * @param path le chemin du dossier dans lequel chercher
	 * @param fileName le fichier a trouver
	 * @return true si le fichier existe et false si il n'existe pas dans le dossier specifie
	 */
	public static boolean fichierExiste(String path, String fileName)
	{
		File folder = new File(path);
		File[] files = folder.listFiles();
		for(int i=0; i < files.length; i++)
		{
			if(files[i].isFile() && files[i].getName().equals(fileName))
			{
				return true;
			}
		}
		return false;
	}
}