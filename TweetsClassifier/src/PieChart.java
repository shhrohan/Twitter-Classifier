import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.JFrame;

class Slice {
   double value;
   Color color;
   public Slice(double value, Color color) {  
      this.value = value;
      this.color = color;
   }
}

class MyComponent extends JComponent {
	
int len;
	
   //Slice[] slices = { new Slice(5, Color.red), new Slice(33, Color.green),new Slice(30,Color.blue),new Slice(20, Color.yellow),new Slice(10,Color.pink) ,new Slice(15, Color.orange) };
   
   Slice[] slices = new Slice[6];
   
   MyComponent(double[] percentage,int len)
   {
	   this.len=len;
	    JFrame frame = new JFrame();
	      frame.getContentPane().add(this);
	      frame.setSize(600, 400);
	      frame.setVisible(true);
	      frame.setResizable(false);

	      if(len==6)
	      {
	      slices[0]=new Slice (percentage[0],Color.red);
	      slices[1]=new Slice (percentage[1],Color.green);
	      slices[2]=new Slice (percentage[2],Color.blue);
	      slices[3]=new Slice (percentage[3],Color.magenta);
	      slices[4]=new Slice (percentage[4],Color.pink);
	      slices[5]=new Slice (percentage[5],Color.GRAY);
	   
	      }
	      else
	      {
	    	  slices[0]=new Slice (percentage[0],Color.red);
	    	  slices[1]=new Slice (percentage[1],Color.green);
	    
	      }

	   
   }
   
   public void paint(Graphics g)
   {
      drawPie((Graphics2D) g, getBounds(), slices);
      
   }
   
   void drawPie(Graphics2D g, Rectangle area, Slice[] slices)
   {
	   int x=450;
	   int y=80;
	   String Class[]=new String[6];
	   if(this.len==6)
	   {
	   Class[0]="Business";
	   Class[1]="Entertainment";
	   Class[2]="Health";
	   Class[3]="Politics";
	   Class[4]="Sports";
	   Class[5]="Technology";
	   }
	   else
	   {
		   Class[0]="Grievence";
		   Class[1]="Non-Grievence";

	   }
	   
      double total = 0.0D;
      for (int i = 0; i < this.len; i++)
      {
         total += slices[i].value;
      }
      
      double curValue = 0.0D;
      int startAngle = 0;
      for (int i = 0; i < this.len; i++) {
         startAngle = (int) (curValue * 360 / total);
         int arcAngle = (int) (slices[i].value * 360 / total);
         g.setColor(slices[i].color);
         g.fillArc(150, 50, 270, 270, startAngle, arcAngle);
         g.drawString(Class[i] +": "+ slices[i].value + "%" , x, y);
            curValue += slices[i].value;
            y+=30;
      }
   }
}
public class PieChart {
   public static void main(String[] argv) {
	   
	   }
}