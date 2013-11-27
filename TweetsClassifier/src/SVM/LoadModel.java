package SVM;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

import SVM.utils.DataFileReader;
import SVM.ca.uwo.csd.ai.nlp.kernel.KernelManager;
import SVM.ca.uwo.csd.ai.nlp.kernel.LinearKernel;
import SVM.ca.uwo.csd.ai.nlp.libsvm.svm_model;
import SVM.ca.uwo.csd.ai.nlp.libsvm.ex.Instance;
import SVM.ca.uwo.csd.ai.nlp.libsvm.ex.SVMPredictor;

public class LoadModel
{
	public static int arr[][];
	static double[] classCount;
	public static double[] loadModel() throws Exception
	{
		KernelManager.setCustomKernel(new LinearKernel());
		 Instance[] testingInstances = DataFileReader.readDataFile("./svm/"+"test.txt");
		 arr=new int[testingInstances.length][6];
		 for(int i=0;i<testingInstances.length;i++)
		 {
			 for(int j=0;j<=5;j++)
				 arr[i][j]=0;
		 }
		for(int i=0;i<=5;i++)
		{
			
			for(int j=i+1;j<=5;j++)
			{
				
				 svm_model model=SVMPredictor.loadModel("./svm/model/"+i+j+".model");
				 double[] predictions = SVMPredictor.predict(testingInstances, model, true);
			     incrementClassValue(predictions,i,j);
			}
		}
		
		findClass(testingInstances.length);
		return classCount;
	}
	
	 private static void findClass(int length) throws IOException
	 {
		 BufferedWriter bf = new BufferedWriter(new FileWriter(new File("retult")));
		 classCount=new double[6];
		
		 for(int i=0;i<=5;i++)
		 {
			 classCount[i]=0;
		 }
		 
		 for(int i=0;i<length;i++)
		 {
			 int max=0;
			 int index=0;
			 for(int j=0;j<=5;j++)
			 {
				 if(max < arr[i][j])
				 {
					 max=arr[i][j];
					 index=j;
				 }
					 
			 }
			 classCount[index]++;
			 if(index == 0)
			 {
				 bf.write(i+"\n");
			 }
		 }
		 bf.close();
	 }
	
    private static void incrementClassValue(double[] predictions, int c1, int c2) 
    {
    	for(int i=0;i<predictions.length;i++)
    	{
    		if(predictions[i] == 1.0)
    		{
    			arr[i][c1]++;
    		}
    		else
    		{
    			arr[i][c2]++;
    		}
    	}
	}
    

}
