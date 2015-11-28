package ameisenPack;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Line2D;

public class TestFrame extends Frame
{
  public TestFrame() {
	  
    setTitle("Ein reines, unbeflecktes Frame");  // Fenstertitel setzen
    setSize(400,100);                            // Fenstergröße einstellen  
    addWindowListener(new TestWindowListener()); // EventListener für das Fenster hinzufügen
                                                 // (notwendig, damit das Fenster geschlossen werden kann)
    setVisible(true);                            // Fenster (inkl. Inhalt) sichtbar machen
  }
  
  public void paint (Graphics g) {
	    Graphics2D g2 = (Graphics2D) g;
	 // draw Line2D.Double
	    double x1 = 10;
	    double y1 = 10;
	    double x2 = 20;
	    double y2 = 15;
	    g2.draw(new Line2D.Double(x1, y1, x2, y2));
	 
  }
  
  class TestWindowListener extends WindowAdapter
  {
    public void windowClosing(WindowEvent e)
    {
      e.getWindow().dispose();                   // Fenster "killen"
      System.exit(0);                            // VM "killen" 
    }           
  }

  public static void main (String args[]) 
  {
    new TestFrame ();
 
  }
}