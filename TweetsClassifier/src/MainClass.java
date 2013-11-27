import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import weka.core.Stopwords;
import TweetsRefine.*;
import NAIVE.WekaDemo;
import SVM.LoadModel;
import grev.*;

class Chooser extends JFrame
{
	JFileChooser chooser;
	String fileName;

	public Chooser()
	{
		chooser = new JFileChooser();
		int r = chooser.showOpenDialog(new JFrame());
		
		if (r == JFileChooser.APPROVE_OPTION)
		{
			fileName = chooser.getSelectedFile().getPath();
		}
	}
}

public class MainClass extends JFrame implements ActionListener
{
	boolean is_grievence=false;
String modelName=null;
String filePath=null;
double percentage[];
int index=0;
ArrayList<String> stopwords = new ArrayList<String>();

private static final long serialVersionUID = 1L;
public static MainClass frame;
	
JButton button_training, button_test;
JTextField field_training, field_test;

JTextArea query,query_result;

JButton naive,svm,grievence;
JButton execute,exit;

JButton pie;

JButton execute_query;

JLabel label_to_trainin_file,label_to_test_file;
JLabel label_to_query,result;

JButton button_A,button_B;
JLabel label_A, label_B;

JComboBox list;

String class_array[]={"......","Business","Entertainment","Health","Politics","Sports","Technology"};

//JTextArea area ;

public MainClass () throws IOException
{
	this.setLayout(null);
	
	
	BufferedReader stopword_reader = new BufferedReader(new FileReader(new File("stopwords")));
	String stop_word;
	while((stop_word = stopword_reader.readLine())!= null)
	{
		stopwords.add(stop_word);
	}
	
	
	button_A = new JButton("A");
	button_B = new JButton("B");
	
	button_A.setBounds(10, 10, 50, 20);
	button_B.setBounds(10, 40, 50, 20);
	
	label_A = new JLabel();
	label_B = new JLabel();
	
	label_A.setText("Option A: Input by selecting file");
	label_B.setText("Option B: Give input by typing tweet");
	
	label_A.setBounds(80, 10, 600, 20);
	label_B.setBounds(80, 40, 600, 20);
	
	this.add(button_A);
	this.add(button_B);
	this.add(label_A);
	this.add(label_B);
	
	
	naive= new JButton("Naive Model");
	grievence= new JButton("Grievence");
	svm= new JButton("SVM Model");
	
	
	
	label_to_trainin_file = new JLabel();
	label_to_test_file = new JLabel();
	label_to_trainin_file.setText("Training File");
	label_to_test_file.setText("Test File");
	
	label_to_query = new JLabel();
	
	
	
	label_to_query.setText("Enter Tweet");
	
	this.add(naive);
	this.add(svm);
	this.add(grievence);
	
	svm.setEnabled(false);
	grievence.setEnabled(false);
	naive.setEnabled(false);
	
	
	naive.addActionListener(this);
	svm.addActionListener(this);
	grievence.addActionListener(this);
	
	
	//button_training = new JButton("Select Training File");
	button_test = new JButton("Browse");
	execute_query = new JButton("Classify");
	
	execute_query.setEnabled(false);
	button_test.setEnabled(false);
	
	field_training = new JTextField();
	field_test = new JTextField();
	query = new JTextArea();
	query.setWrapStyleWord(true);
	query.setLineWrap(true);
	query.setEnabled(false);
	
	field_test.setEnabled(false);
	
	this.add(execute_query);
	this.add(label_to_query);
	this.add(query);
	
	
	list=new JComboBox();
	
	for(int j=0;j<class_array.length;j++)
		list.addItem(class_array[j]);
	
	
	this.add(list);
	list.setBounds(475, 260, 112, 30);
	list.setEnabled(false);
	
	label_to_query.setBounds(180, 200, 200, 100);
	query.setBounds(20, 260, 450,200);
	label_to_trainin_file.setBounds(10, 100, 100, 25);
	field_training.setBounds(100, 100, 235, 25);
	
	execute_query.setBounds(475, 430, 112, 30);
	
	
	result=new JLabel();
	result.setText("Result");
	result.setBounds(590, 200, 220, 100);
	this.add(label_to_trainin_file);
	this.add(result);
	
	
	query_result = new JTextArea(20,10);
	query_result.setBounds(590, 260, 180,200);
	this.add(query_result);
	
	//button_training.setBounds(230, 10, 200, 25);
	
	this.add(field_training);
	//this.add(button_training);
	
	field_test.setBounds(350, 100, 320, 25);
	label_to_test_file.setBounds(350, 80, 100, 25);
	button_test.setBounds(670,100, 100, 25);
	
	this.add(label_to_test_file);
	
	naive.setBounds(10, 170, 150, 30);
	grievence.setBounds(310, 170, 150, 30);
	svm.setBounds(160, 170, 150, 30);
	
	execute=new JButton("Bar Graph");
	pie =new JButton("Pie Chart");
	execute.setBounds(550, 170, 120, 30);
	pie.setBounds(670, 170, 105,30);
	
	this.add(pie);
	this.add(field_test);
	this.add(button_test);
	
	
	exit= new JButton("Exit");
	
	exit.setBounds(350, 550, 100, 30);
	
	execute.setEnabled(false);
	pie.setEnabled(false);
	this.add(execute);
	this.add(exit);
	
	
	
	
	
	
	//button_training.addActionListener(this);
	button_test.addActionListener(this);
	execute.addActionListener(this);
	pie.addActionListener(this);
	exit.addActionListener(this);
	//query.addActionListener(this);
	query_result.setEditable(false);
	field_training.setText("./home/TrainingFile");
	field_training.setEnabled(false);
	execute_query.addActionListener(this);
	button_A.addActionListener(this);
	button_B.addActionListener(this);
	list.addActionListener(this);
	setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
}




	public static double[] calculatePercentage(double [] classCount, int count)
	   {
	   	double[] pre = new double[6];
	   	int sum=0;
	   	for(int i=0;i<count;i++)
	   	{
	   		sum+=classCount[i];
	   	}
	   	for(int i=0;i<count;i++)
	   	{
	   		pre[i]=(double)(classCount[i]*100)/sum;
	   		DecimalFormat df = new DecimalFormat("#.##");
	        pre[i] = Double.parseDouble(df.format(pre[i]));
	   	}
	   	return pre;
	   }
	
	public void actionPerformed(ActionEvent e) 
	{
		//System.out.println("E= " + e);
	    String s=e.paramString();
	    //System.out.println(" s =" + s);
	    
	    int i=s.indexOf("cmd=");
	    int j=s.indexOf(",",i);
	    
	    
	    String status = s.substring(i+4, j);
	    
	    
	    try
	    {
	    	if(status.compareToIgnoreCase("A")==0)
	    	{
	    		button_test.setEnabled(true);
	    		field_test.setEnabled(true);
	    		execute_query.setEnabled(false);
	    		query.setEnabled(false);
	    		list.setEnabled(false);
	    	}
	    	
	    	else if(status.compareToIgnoreCase("B")==0)
	    	{
	    		execute_query.setEnabled(true);
	    		query.setEnabled(true);
	    		list.setEnabled(true);
	    		button_test.setEnabled(false);
	    		field_test.setEnabled(false);
	    		naive.setEnabled(false);
	    		grievence.setEnabled(false);
	    		svm.setEnabled(false);
	    		execute.setEnabled(false);
	    		pie.setEnabled(false);
	    	}
	    	
	    	else if(status.compareToIgnoreCase("comboBoxChanged")==0)
	    	{
	    		index=list.getSelectedIndex();
	    		
	    	}
	    	
	    	
	    	else if(status.compareToIgnoreCase("Classify")==0)
	    	{
	    		String qry;
	    		
	    		String file_name="test_query";
	    		File f= new File("test_query");
	    		
	    		String array[]=new String[6];
	    		array[0]="Business";
	    		array[1]="Entertainment";
	    		array[2]="Health";
	    		array[3]="Politics";
	    		array[4]="Sports";
	    		array[5]="Technology";
	    	
	    		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
	    		
	    		qry = query.getText();
	    		
	    		StringBuilder final_query  = new StringBuilder();
	    		String[] qry_array = qry.split(" ");
	    		
	    		for(int it = 0 ; it < qry_array.length ; it++)
	    		{
	    			if(!stopwords.contains(qry_array[it]))
	    			{
	    				final_query.append(qry_array[it]+" ");
	    			}
	    		}
	    		
	    		//System.out.println(final_query.toString());
	    		
	    		bw.write(final_query.toString().trim());
	    		bw.close();
	    		
	    		IndexCreatorTest.createIndex(file_name,"svm");
	    		LoadModel.loadModel();
	    		//percentage=LoadModel.calculatePercentage();
	    		percentage = LoadModel.loadModel();
	    		for(int z=0;z<percentage.length;z++)
	    		{
	    			System.out.println(percentage[z]);
	    			if(percentage[z]!=0.0)
	    			{
	    				if(index!=0)
	    	    		{
	    	    			query_result.setText("\nPredicted Class:\n   "+ class_array[list.getSelectedIndex()]+"\n"+"\n"+ "Classified Class :\n   " +array[z]+"\n" );
	    	    		}
	    	    		else
	    	    		{
	    	    			query_result.setText("\nNo Predicted Class\n"+"\n"+ "Classified Class :" +array[z]+"\n" );
	    	    		}
	    				
	    			
	    			}
	    		}
	    		
	    		System.out.println("QUERY = " + qry);
	    		
	    	}
	    	
	    	
	    	
	    	
	    	else if(status.compareToIgnoreCase("Naive Model")==0 && filePath != null)
		    {
		    	
		    	svm.setEnabled(false);
		    	grievence.setEnabled(false);
		    	is_grievence=false;
		    	IndexCreatorTest.createIndex(filePath,"naive");
		    	percentage=WekaDemo.LoadModel();
		    	execute.setEnabled(true);
		    	pie.setEnabled(true);
				
		    	//calc percentage
		    	
		    	
		    	
		    	//System.out.println("Naive");
		    }
		
		    else if(status.compareToIgnoreCase("SVM Model")==0)
		    {
		    	naive.setEnabled(false);
		    	grievence.setEnabled(false);
		    	is_grievence=false;
		    	IndexCreatorTest.createIndex(filePath,"svm");
		    	//LoadModel.loadModel();
		    	percentage = LoadModel.loadModel();
		    	//percentage=LoadModel.calculatePercentage();
		    	
		    	execute.setEnabled(true);
		    	pie.setEnabled(true);
		    	
		    }
		    
		    else if(status.compareToIgnoreCase("grievence")==0)
		    {
		    	naive.setEnabled(false);
		    	svm.setEnabled(false);
		    	is_grievence=true;
		    	System.out.println("Grievence");
		    	GrevIndexCreatorTest.createIndex(filePath, "grev");
		    	
		    	GrevLoadModel.loadModel();
		    	
		    	percentage=GrevLoadModel.calculatePercentage();
		    	System.out.println("Grievence end");
		    
		    	execute.setEnabled(true);
		    	pie.setEnabled(true);
		    	
		    } 
	    
		    else if(status.compareToIgnoreCase("Browse")==0)
		    {
		    	is_grievence=false;
		    	Chooser frame = new Chooser();
		    	field_test.setText(frame.fileName);
		    	
		    	if(frame.fileName!=null)
		    	{
		    		filePath=frame.fileName;
		    		svm.setEnabled(true);
		        	grievence.setEnabled(true);
		        	naive.setEnabled(true);
		        	
		        	execute.setEnabled(true);
			    	pie.setEnabled(true);
		    	}
		    	else
		    	{
		    		svm.setEnabled(false);
		        	grievence.setEnabled(false);
		        	naive.setEnabled(false);
		        	execute.setEnabled(false);
			    	pie.setEnabled(false);
		    	}
		
		    }
		
		    else if(status.compareToIgnoreCase("Bar Graph")==0)
		    {
		    	//call Graph function
		    	if(is_grievence ==true)
		    	{
		    		BarChart d = new BarChart(percentage,2);
		    	
		    	}
		    	else
		    	{
		    		BarChart d = new BarChart(percentage,6);
		    	
		    	
		    	}
		   // 	System.out.println("Execute");
		    }
		    
		    else if(status.compareToIgnoreCase("Pie Chart")==0)
		    {
		    	if(is_grievence ==true)
		    	{
		    	
		    		MyComponent c = new MyComponent(calculatePercentage(percentage,2),2);
		    	}
		    	else
		    	{
		    	
		    		MyComponent c = new MyComponent(calculatePercentage(percentage,6),6);
		    	
		    	}
		    	
		    }
	    	
	    	
		    else if(status.compareToIgnoreCase("exit")==0)
		    {
		    	//System.out.println("Exit");
		    	System.exit(0);
		    }
	    }
	    catch(Exception ep)
	    {
	    	System.out.println(ep);
	    }
	
	}

	public static void main(String args[]) throws IOException 
	{
		frame = new MainClass ();
		frame.setSize(800, 650);
		frame.setLocation(200, 100);
		frame.setVisible(true);
		frame.setResizable(false);
	}
}

