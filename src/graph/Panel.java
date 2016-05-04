package graph;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class Panel extends JPanel
{
    private static int d= (int)(Math.random()*1000);
    private static int e= (int)(Math.random()*5000);
    private int posX = 200;
    private int posY = 200;
    private int w = 0;

    private String name = "";

    public Panel()
    {
        super();
    }

    public Panel(String name)
    {
        super();
        this.name = name;
    }

    public void paintComponent(Graphics g)
    {
        if(this.name.equals(""))
        {
            if(w == 150)
            {e = (int)(Math.random()*5000); d= (int)(Math.random()*e/5.0); w = 0;}
            g.setColor(Color.white);
            g.fillRect(0,0,this.getWidth(),this.getHeight());
            Dessins x = new Dessins(d, posX,posY, 200, e);
            x.draw(g);
            Font font = new Font("Arial", Font.BOLD, 32);
            g.setFont(font);g.setColor(Color.red);
            g.drawString("Bienvenue dans Challenge", this.getWidth()/2-200,80);
        }
        else if (this.name.equals("menu"))
        {
            g.setColor(Color.white);
            g.fillRect(0,0,this.getWidth(),this.getHeight());
            Font font = new Font("Helvetica", Font.BOLD, (int)(this.getHeight()/1.5));
            g.setFont(font);g.setColor(Color.red);
            g.drawString("Menu", this.getWidth()/2-this.name.length()*50,(int)(this.getHeight() /1.5));
        }
    }

    public int getPosX()
    {return posX;}

    public int getPosY()
    {return posY;}

    public void setPosX(int x)
    {this.posX = x;w++;}

    public void setPosY(int y)
    {this.posY = y;}

    public class Dessins
    {
        private class Coordonnee
        {
            private int x = 0;
            private int y = 0;

            public Coordonnee(int x, int y)
            {
                this.x = x;
                this.y = y;
            }

            public int getX()
            {return x;}

            public int getY()
            {return y;}
        }
        public class Segment
        {
            private int x0,y0; // coordonees de la premiere extremite
            private int x1,y1; // coordonnees de la seconde extremite

            /**
             * @pre x0,y0,x1,y1 >=0 et <512
             * @post a construit un segment de droite reliant x0,y0 a x1,y1
             */
            public Segment(int x0, int y0, int x1, int y1)
            {
                this.x0=x0;
                this.y0=y0;
                this.x1=x1;
                this.y1=y1;
            }

            public void draw(Graphics g)
            {
                g.drawLine(x0,y0,x1,y1);
            }
        }

        private int nombre = 0;
        private Coordonnee[] t;
        private int x = 0;
        private int y = 0;// coordonnee du centre
        private int lon = 0;
        private int f= 0;

        public Dessins()
        {
            nombre = 5;
            Coordonnee tab[] = new Coordonnee[nombre];
            t = tab;
            x = 100;
            y = 100;
            lon = 50;
            f = 2;
        }

        public Dessins(int n, int x, int y, int longueur, int modulo)
        {
            this.nombre = n;
            this.x = x;
            this.y = y;
            this.lon = longueur;
            Coordonnee tab[] = new Coordonnee[nombre];
            for(int i  = 0; i < n; i++)
            {
                tab[i] = new Coordonnee( x+ (int)(lon* Math.cos(2*Math.PI/(double)nombre*i-Math.PI/2.0)), y+ (int)(lon* Math.sin(2*Math.PI/(double)nombre*i-Math.PI/2.0)));
            }
            this.t = tab;
            this.f = modulo;
        }

        public void draw(Graphics g)
        {
            Segment s[] = new Segment[nombre];
            for(int k = 0; k< nombre; k++)
            {
                s[k] = new Segment(t[k].getX(),t[k].getY(),t[(f*k)%nombre].getX(), t[(f*k)%nombre].getY());
                if(k%3 == 0)
                {g.setColor(Color.blue);}
                else if (k%3 == 1)
                {g.setColor(Color.blue);}
                else 
                {g.setColor(Color.red);}
                s[k].draw(g);
            }
        }
    }
}