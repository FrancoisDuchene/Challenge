import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class SoundPlayer extends Thread
{
    private String filename;
    private Position curPosition;
    private final int EXTERNAL_BUFFER_SIZE = 524288; // 128Kb
    enum Position {LEFT, RIGHT, NORMAL};

    public SoundPlayer(String wavfile)
    {
        filename = wavfile;
        curPosition = Position.NORMAL;
    }

    public void run()
    {
        while(challenge.continuons)
        {
            File soundFile = new File(filename);
            if (!soundFile.exists()) {
                System.err.println("Wave file not found: " + filename);
                return;
            }

            //Declaration des Objets utiles
            AudioInputStream audioInputStream = null;
            AudioFormat format = null;
            SourceDataLine auline = null;
            DataLine.Info info = null;

            try {
                audioInputStream = AudioSystem.getAudioInputStream(soundFile);
                format = audioInputStream.getFormat();
                info = new DataLine.Info(SourceDataLine.class, format);
                auline = (SourceDataLine) AudioSystem.getLine(info);
                auline.open(format);

                if (auline.isControlSupported(FloatControl.Type.PAN)) {
                    FloatControl pan = (FloatControl) auline.getControl(FloatControl.Type.PAN);
                    if (curPosition == Position.RIGHT) {
                        pan.setValue(1.0f);
                    } else if (curPosition == Position.LEFT) {
                        pan.setValue(-1.0f);
                    }
                }
                auline.start();
                int nBytesRead = 0;
                byte[] abData = new byte[EXTERNAL_BUFFER_SIZE];

                while (nBytesRead != -1 && challenge.continuons) 
                {
                    nBytesRead = audioInputStream.read(abData, 0, abData.length);
                    if (nBytesRead >= 0) {
                        auline.write(abData, 0, nBytesRead);
                    }
                }
            } catch (LineUnavailableException e) {
                e.printStackTrace();
                return;
            } catch (IOException e) {
                e.printStackTrace();
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            } finally {
                auline.drain();
                auline.close();
            }
        }
    }
}

