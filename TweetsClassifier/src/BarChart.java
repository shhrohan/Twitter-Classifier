import java.awt.*;
import javax.swing.*;
import ChartDirector.*;


public class BarChart //implements DemoModule
{
	int len;
	public double[] percentage = new double[6];
	BarChart(double[] p,int len)
	{
		this.len=len;
        /*simplebar demo = new simplebar();*/

        //Create and set up the main window
		for(int i=0;i<len;i++)
        {
        	this.percentage[i]=p[i];
        
        }
        JFrame frame = new JFrame(this.toString());
       /* frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);} });*/
        frame.getContentPane().setBackground(Color.white);

        // Create the chart and put them in the content pane
        ChartViewer viewer = new ChartViewer();
        this.createChart(viewer, 0);
        frame.getContentPane().add(viewer);

        // Display the window
        frame.pack();
        frame.setVisible(true);
        
       
        
        
	}
	
	
    //Name of demo program
    public String toString() { return "Bar Graph"; }

    //Number of charts produced in this demo
    public int getNoOfCharts() { return 1; }

    //Main code for creating charts
    public void createChart(ChartViewer viewer, int index)
    {
    	
        // The data for the bar chart
        //double[] data = {85, 156, 179.5, 211, 123,1000};

        // The labels for the bar chart
    	String []labels= new String[6];
    	if(this.len==6){
         labels[0]="Busness";
         labels[1]="Entertainment";
         labels[2]="Health";
         labels[3]="Politics";
         labels[4]="Sports";
         labels[5]="Technology";
         
    	}
    	else
    	{
            labels[0]="Grievence";
            labels[1]="Non-Grievence";

    	}

        // Create a XYChart object of size 250 x 250 pixels
        XYChart c = new XYChart(600, 300);

        // Set the plotarea at (30, 20) and of size 200 x 200 pixels
        c.setPlotArea(200, 20, 300, 250);

        for(int i=0;i<percentage.length;i++)
        {
        	System.out.println(percentage[i]);
        
        }
        
        
        // Add a bar chart layer using the given data
        c.addBarLayer(percentage);

        // Set the labels on the x axis.
        c.xAxis().setLabels(labels);

        // Output the chart
        viewer.setChart(c);

        //include tool tip for the chart
        viewer.setImageMap(c.getHTMLImageMap("clickable", "","title='{xLabel}: US${value}K'"));
    }

    //Allow this module to run as standalone program for easy testing
    public static void main(String[] args)
    {
        //Instantiate an instance of this demo module

    }
}