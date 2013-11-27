package NAIVE;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Instances;
import weka.core.OptionHandler;
import weka.core.Utils;
import weka.filters.Filter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class WekaDemo
{
  protected Classifier m_Classifier = null;
  protected Filter m_Filter = null;
  protected String m_TrainingFile = null;
  protected Instances m_Training = null;
  protected Evaluation m_Evaluation = null;
  protected static double[] classCount=new double[6];
  public WekaDemo()
  {
    super();
  }

  public void setTraining(String name) throws Exception
  {
    m_TrainingFile = name;
    m_Training     = new Instances(new BufferedReader(new FileReader(m_TrainingFile)));
    m_Training.setClassIndex(m_Training.numAttributes() - 1);
  }
  
  public static String usage()
  {
    return
        "\nusage:\n  " + WekaDemo.class.getName() 
        + "  CLASSIFIER <classname> [options] \n"
        + "  FILTER <classname> [options]\n"
        + "  DATASET <trainingfile>\n\n"
        + "e.g., \n"
        + "  java -classpath \".:weka.jar\" WekaDemo \n"
        + "    CLASSIFIER weka.classifiers.trees.J48 -U \n"
        + "    FILTER weka.filters.unsupervised.instance.Randomize \n"
        + "    DATASET iris.arff\n";
  }
  
  public void setClassifier(String name, String[] options) throws Exception 
  {
	  m_Classifier =(Classifier)Utils.forName(Classifier.class,name,options);
  }
  
  public void setFilter(String name, String[] options) throws Exception 
  {
	    m_Filter = (Filter) Class.forName(name).newInstance();
	    if (m_Filter instanceof OptionHandler)
	      ((OptionHandler) m_Filter).setOptions(options);
  }
  public static void createModle() throws Exception
  {
	  System.out.println("model creation start");
	    Vector<String> classifierOptions = new Vector<String>();
	    Vector<String> filterOptions = new Vector<String>();
	    
	    String classifier = "weka.classifiers.bayes.NaiveBayes";
	    String filter = "weka.filters.unsupervised.instance.Randomize";
	    WekaDemo demo = new WekaDemo();
	    demo.setClassifier(classifier,(String[]) classifierOptions.toArray(new String[classifierOptions.size()]));
	    demo.setFilter(filter,(String[]) filterOptions.toArray(new String[filterOptions.size()]));
	    demo.setTraining("./naive/"+"naive.arff");
	    demo.m_Classifier.buildClassifier(demo.m_Training );
	    weka.core.SerializationHelper.write("./naive/"+"naive.model",demo.m_Classifier );
	    System.out.println("model creation end");
  }
   public static double[] LoadModel() throws Exception
   {
	    
	    for(int i=0;i<6;i++)
	    {
	    	classCount[i]=0.0;
	    }
		Classifier cls = (Classifier) weka.core.SerializationHelper.read("./naive/"+"naive.model");
	    Instances isTrainingSet = new Instances(new BufferedReader(new FileReader("./naive/"+"test.arff")));
	    isTrainingSet.setClassIndex(isTrainingSet.numAttributes() - 1);
	    Evaluation eTest = new Evaluation(isTrainingSet);
	    List<BufferedWriter> list=new ArrayList<BufferedWriter>();
	    
	    for(int i=0;i<6;i++)
	    {
	    	BufferedWriter bw=new BufferedWriter(new FileWriter(new File("./naive/"+i)));
	    	list.add(bw);
	    }
	    double [] answers = eTest.evaluateModel(cls, isTrainingSet);
	   int count=0;
	   
	    for(double answer :  answers)
	    {
	    	
	    	if(answer == 0.0)
	    	{
	    		classCount[0]++;
	    		list.get(0).write(count+++" "+answer+"\n");
	    	}
	    	else if(answer == 1.0)
	    	{
	    		classCount[1]++;
	    		list.get(1).write(count+++" "+answer+"\n");
	    	}
	    	else if(answer == 2.0)
	    	{
	    		classCount[2]++;
	    		list.get(2).write(count+++" "+answer+"\n");
	    	}
	    	else if(answer == 3.0)
	    	{
	    		classCount[3]++;
	    		list.get(3).write(count+++" "+answer+"\n");
	    	}
	    	else if(answer == 4.0)
	    	{
	    		classCount[4]++;
	    		list.get(4).write(count+++" "+answer+"\n");
	    	}
	    	else if(answer == 5.0)
	    	{
	    		classCount[5]++;
	    		list.get(5).write(count+++" "+answer+"\n");
	    	}
	    }
	    for(int i=0;i<=5;i++)
	    {
	    	list.get(i).close();
	    }
	    list.clear();
	    return classCount;//
	    //return calculatePercentage();
	  }
   
   public static double[] calculatePercentage()
   {
   	double[] pre = new double[6];
   	int sum=0;
   	for(int i=0;i<=5;i++)
   	{
   		sum+=classCount[i];
   	}
   	for(int i=0;i<=5;i++)
   	{
   		pre[i]=(double)(classCount[i]*100)/sum;
   		DecimalFormat df = new DecimalFormat("#.##");
        pre[i] = Double.parseDouble(df.format(pre[i]));
   	}
   	return pre;
   }
   public static void main(String arr[]) throws Exception
   {
	   createModle();
   }
}