package grev;

import SVM.utils.DataFileReader;
import SVM.ca.uwo.csd.ai.nlp.kernel.KernelManager;
import SVM.ca.uwo.csd.ai.nlp.kernel.LinearKernel;
import SVM.ca.uwo.csd.ai.nlp.libsvm.svm_model;
import SVM.ca.uwo.csd.ai.nlp.libsvm.ex.Instance;
import SVM.ca.uwo.csd.ai.nlp.libsvm.ex.SVMPredictor;

public class GrevLoadModel
{
	public static int arr[][];
	static int[] classCount;
	public static void loadModel() throws Exception
	{
		KernelManager.setCustomKernel(new LinearKernel());
		 Instance[] testingInstances = DataFileReader.readDataFile("./grev/"+"test.txt");
		 arr=new int[testingInstances.length][2];
		 for(int i=0;i<testingInstances.length;i++)
		 {
			 for(int j=0;j<=1;j++)
				 arr[i][j]=0;
		 }
		 svm_model model=SVMPredictor.loadModel("./grev/01.model");
		 double[] predictions = SVMPredictor.predict(testingInstances, model, true);
	     incrementClassValue(predictions,0,1);
		
		findClass(testingInstances.length);
		
	}
	
	 private static void findClass(int length)
	 {
		 classCount=new int[2];
		
		 for(int i=0;i<=1;i++)
		 {
			 classCount[i]=0;
		 }
		 
		 for(int i=0;i<length;i++)
		 {
			 int max=0;
			 int index=0;
			 for(int j=0;j<=1;j++)
			 {
				 if(max < arr[i][j])
				 {
					 max=arr[i][j];
					 index=j;
				 }
					 
			 }
			 classCount[index]++;
		 }
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
    
    public static double[] calculatePercentage()
    {
    	double[] pre = new double[2];
    	int sum=0;
    	for(int i=0;i<=1;i++)
    	{
    		sum+=classCount[i];
    	}
    	for(int i=0;i<=1;i++)
    	{
    		pre[i]=(double)(classCount[i]*100)/sum;
    		System.out.print(pre[i]+" ");
    	}
    	return pre;
    }
}
